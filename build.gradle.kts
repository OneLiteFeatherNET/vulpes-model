import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.spring)
    alias(libs.plugins.spring.dependency)
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.kotlin.spring)
    `maven-publish`
}

group = "net.theevilreaper.vulpes.api"
val baseVersion = "0.1.0-SNAPSHOT"

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }
}

dependencies {
    implementation(libs.spring.starter)
    implementation(libs.spring.starter.mongodb)
    implementation(libs.kotlin.reflect)
    testImplementation(libs.spring.starter.test)
}

tasks {
    compileKotlin {
        compilerOptions {
            freeCompilerArgs.add("-Xjsr305=strict")
            jvmTarget.set(JvmTarget.JVM_21)
        }
    }

    test {
        useJUnitPlatform()
    }

    bootBuildImage {
        builder.set("paketobuildpacks/builder-jammy-base:latest")
    }
}

version = if (System.getenv().containsKey("CI")) {
    "${baseVersion}+${System.getenv("CI_COMMIT_SHORT_SHA")}"
} else {
    baseVersion
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            from(components["java"])
        }
    }

    if (System.getenv().containsKey("CI")) {
        repositories {
            maven {
                name = "GitLab"
                val ciApiv4Url = System.getenv("CI_API_V4_URL")
                val projectId = System.getenv("CI_PROJECT_ID")
                url = uri("$ciApiv4Url/projects/$projectId/packages/maven")
                credentials(HttpHeaderCredentials::class.java) {
                    name = "Job-Token"
                    value = System.getenv("CI_JOB_TOKEN")
                }
                authentication {
                    create<HttpHeaderAuthentication>("header")
                }
            }
        }
    }
}