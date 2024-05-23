rootProject.name = "vulpes-spring-api"

dependencyResolutionManagement {
    repositories {
        mavenCentral()
    }
    versionCatalogs {
        create("libs") {
            version("kotlin", "1.9.23")

            library("spring.starter", "org.springframework.boot", "spring-boot-starter").withoutVersion()
            library(
                "spring.starter.mongodb",
                "org.springframework.boot",
                "spring-boot-starter-data-mongodb"
            ).withoutVersion()

            library("spring.starter.test", "org.springframework.boot", "spring-boot-starter-test").withoutVersion()
            library("kotlin.reflect", "org.jetbrains.kotlin", "kotlin-reflect").withoutVersion()

            plugin("spring", "org.springframework.boot").version("3.3.0")
            plugin("spring.dependency", "io.spring.dependency-management").version("1.1.4")
            plugin("kotlin.jvm", "org.jetbrains.kotlin.jvm").versionRef("kotlin")
            plugin("kotlin.spring", "org.jetbrains.kotlin.plugin.spring").versionRef("kotlin")
        }
    }
}