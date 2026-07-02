rootProject.name = "vulpes-model"

plugins {
    id("io.micronaut.platform.catalog") version "5.0.1"
}

dependencyResolutionManagement {
    versionCatalogs {
        create("libs") {
            version("uuid.creator", "6.1.1")

            library("uuid.creator", "com.github.f4b6a3", "uuid-creator").versionRef("uuid.creator")
        }
    }
}
