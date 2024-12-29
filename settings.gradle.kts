rootProject.name = "vulpes-spring-api"

plugins {
    id("io.micronaut.platform.catalog") version "4.4.4"
}

dependencyResolutionManagement {
    repositories {
        mavenCentral()
    }
    versionCatalogs {
        create("libs") {
            version("kotlin", "2.0.21")
            plugin("kotlin.jvm", "org.jetbrains.kotlin.jvm").versionRef("kotlin")
        }
    }
}