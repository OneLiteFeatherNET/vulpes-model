rootProject.name = "vulpes-spring-api"

plugins {
    id("io.micronaut.platform.catalog") version "4.4.5"
}

dependencyResolutionManagement {
    pluginManagement {
        repositories {
            gradlePluginPortal()
            maven("https://eldonexus.de/repository/maven-public/")
        }
    }
}
