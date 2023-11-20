## Gradle, Issue with BuildServiceParameters

Where are and Issue with convention plugins and BuildServiceParameters.
Pay attention at file [MyPluginWithBuildService.kt](plugin%2Fmy-plugin-with-build-service%2Fsrc%2Fmain%2Fkotlin%2Forg%2Fexample%2FMyPluginWithBuildService.kt).
If I used BuildServiceParameters with some parameters, and never access them - build fails.  
To reproduce it run:  
```bash
./reproduce-issue.sh
```

I found workaround - simple get access to all properties inside BuildServiceParameters at least once.  
To check workaround, run:
```bash
./use-workaround.sh
```

### Question
Maybe where are some documentation that describe this behavior, or maybe related Issue already created?


### Additional context:
#### Exception
```
* Exception is:
org.gradle.internal.snapshot.impl.IsolationException: Could not isolate value org.example.MyParameters_Decorated@33db25b2 of type MyParameters
        at org.gradle.internal.snapshot.impl.DefaultIsolatableFactory.isolate(DefaultIsolatableFactory.java:51)
        at org.gradle.api.services.internal.RegisteredBuildServiceProvider.instantiate(RegisteredBuildServiceProvider.java:127)
        at org.gradle.api.services.internal.RegisteredBuildServiceProvider.getInstance(RegisteredBuildServiceProvider.java:118)
        at org.gradle.api.services.internal.RegisteredBuildServiceProvider.calculateOwnValue(RegisteredBuildServiceProvider.java:111)
        at org.gradle.api.internal.provider.AbstractMinimalProvider.calculateOwnPresentValue(AbstractMinimalProvider.java:80)
        at org.gradle.api.internal.provider.AbstractMinimalProvider.get(AbstractMinimalProvider.java:100)
        at org.gradle.internal.build.event.DefaultBuildEventsListenerRegistry$ForwardingBuildEventConsumer.handle(DefaultBuildEventsListenerRegistry.java:261)
        at org.gradle.internal.build.event.DefaultBuildEventsListenerRegistry$ForwardingBuildEventConsumer.handle(DefaultBuildEventsListenerRegistry.java:238)
        at org.gradle.internal.build.event.DefaultBuildEventsListenerRegistry$AbstractListener.run(DefaultBuildEventsListenerRegistry.java:180)
        at org.gradle.internal.concurrent.ExecutorPolicy$CatchAndRecordFailures.onExecute(ExecutorPolicy.java:64)
        at org.gradle.internal.concurrent.AbstractManagedExecutor$1.run(AbstractManagedExecutor.java:47)
Caused by: java.lang.IllegalStateException: BuildScopeServices has been closed.
        at org.gradle.internal.service.DefaultServiceRegistry.serviceRequested(DefaultServiceRegistry.java:289)
        at org.gradle.internal.service.DefaultServiceRegistry.getService(DefaultServiceRegistry.java:328)
        at org.gradle.internal.service.DefaultServiceRegistry.find(DefaultServiceRegistry.java:323)
        at org.gradle.internal.service.DefaultServiceRegistry.get(DefaultServiceRegistry.java:308)
        at org.gradle.internal.instantiation.generator.ManagedObjectFactory.getObjectFactory(ManagedObjectFactory.java:136)
        at org.gradle.internal.instantiation.generator.ManagedObjectFactory.newInstance(ManagedObjectFactory.java:88)
        at org.example.MyParameters_Decorated.getSomeInt(Unknown Source)
        at org.example.MyParameters_Decorated.unpackState(Unknown Source)
        at org.gradle.internal.snapshot.impl.AbstractValueProcessor.processValue(AbstractValueProcessor.java:134)
        at org.gradle.internal.snapshot.impl.DefaultIsolatableFactory.isolate(DefaultIsolatableFactory.java:49)
        ... 10 more
```

#### Environment
 - MacOS 13.5.2 (22G91)
 - OpenJDK 17.0.8 2023-07-18 LTS