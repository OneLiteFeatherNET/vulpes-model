rootProject.name = "vulpes-model"

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
