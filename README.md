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
