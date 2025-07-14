plugins {
    java
    jacoco
    `maven-publish`
    `java-library`
    id("io.micronaut.library") version "4.5.3"
}

group = "net.onelitefeather.vulpes"
version = "1.4.0"

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }
    withSourcesJar()
    withJavadocJar()

}

dependencies {
    // Annotation Processors
    annotationProcessor(mn.micronaut.data.processor)

    implementation(mn.micronaut.data.processor)
    implementation(mn.micronaut.data.jpa)
    implementation(mn.micronaut.hibernate.jpa)
    api(libs.uuid.creator)
}

tasks {
    compileJava {
        options.encoding = "UTF-8"
        options.release.set(21)
    }

    test {
        finalizedBy(rootProject.tasks.jacocoTestReport)
        testLogging {
            events("passed", "skipped", "failed")
        }
    }

}

publishing {
    publications.create<MavenPublication>("maven") {
        from(components["java"])
    }

    repositories {
        maven {
            authentication {
                credentials(PasswordCredentials::class) {
                    username = System.getenv("ONELITEFEATHER_MAVEN_USERNAME")
                    password = System.getenv("ONELITEFEATHER_MAVEN_PASSWORD")
                }
            }
            name = "OneLiteFeatherRepository"
            url = if (project.version.toString().contains("SNAPSHOT")) {
                uri("https://repo.onelitefeather.dev/onelitefeather-snapshots")
            } else {
                uri("https://repo.onelitefeather.dev/onelitefeather-releases")
            }
        }
    }
}
