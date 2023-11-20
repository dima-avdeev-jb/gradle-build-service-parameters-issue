plugins {
    kotlin("jvm") version "1.9.0"
    id("java-gradle-plugin")
    id("maven-publish")
}

repositories {
    mavenCentral()
    mavenLocal()
}

group = "org.example"
version = "1.0.0"

gradlePlugin {
    plugins {
        create("my-plugin-with-build-service") {
            id = "my-plugin-with-build-service"
            implementationClass = "org.example.MyPluginWithBuildService"
        }
    }
}
//gradlePluginConfig {
//    pluginId = "my-plugin-with-build-service"
//    implementationClass = "org.example.MyPluginWithBuildService"
//}

publishing {
    publications {
        create<MavenPublication>("maven") {
            description = "This is plugin to describe issue with BuildServiceParameters"
            artifactId = "my-plugin-with-build-service-gradle-plugin"
            version = "1.0.0"
        }
    }
}

dependencies {
    compileOnly(gradleApi())
    compileOnly(kotlin("gradle-plugin-api"))
    compileOnly(kotlin("gradle-plugin"))
}
