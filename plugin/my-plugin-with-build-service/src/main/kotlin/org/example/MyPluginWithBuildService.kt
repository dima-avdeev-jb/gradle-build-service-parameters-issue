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
        println("Hello MyPluginWithBuildService")

        val myServiceProvider = project.gradle.sharedServices.registerIfAbsent(
            "myService",
            MyService::class.java
        ) {
            if (System.getenv("USE_WORKAROUND") == "true") {
                // workaround is simple to access property at least once
                it.parameters.someInt
            }
        }
        getEventsListenerRegistry().onTaskCompletion(myServiceProvider)
    }
}

abstract class MyService : BuildService<MyParameters>, OperationCompletionListener {
    override fun onFinish(event: FinishEvent?) {

    }
}

interface MyParameters : BuildServiceParameters {
    val someInt: Property<Int>
}
