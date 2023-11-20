/*
 * Copyright 2020-2023 JetBrains s.r.o. and respective authors and developers.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE.txt file.
 */

package org.example

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.provider.Property
import org.gradle.api.services.BuildService
import org.gradle.api.services.BuildServiceParameters
import org.gradle.build.event.BuildEventsListenerRegistry
import org.gradle.tooling.events.FinishEvent
import org.gradle.tooling.events.OperationCompletionListener
import javax.inject.Inject


@Suppress("unused")
abstract class MyPluginWithBuildService : Plugin<Project> {
    @Inject
    abstract fun getEventsListenerRegistry(): BuildEventsListenerRegistry

    override fun apply(project: Project) {
        val myServiceProvider = project.gradle.sharedServices.registerIfAbsent(
            "myService",
            MyService::class.java
        ) {
            //todo workaround
//            it.parameters.someInt
        }
        getEventsListenerRegistry().onTaskCompletion(myServiceProvider)
    }
}


abstract class MyService : BuildService<MyParameters>, OperationCompletionListener {
    override fun onFinish(event: FinishEvent?) {

    }
}

interface MyParameters: BuildServiceParameters {
    val someInt: Property<Int>
}
