plugins {
    java
    jacoco
    `maven-publish`
    `java-library`
    id("io.micronaut.library") version "4.5.3"
}

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
        version = rootProject.version as String
        artifactId = "vulpes-model"
        groupId = rootProject.group as String
        pom {
            name = "Vulpes Model"
            description = "Vulpes Model for OneLiteFeather"
            url = "https://github.com/OneLiteFeatherNET/vulpes-model"
            licenses {
                license {
                    name = "AGPL-3.0"
                    url = "https://www.gnu.org/licenses/agpl-3.0.en.html"
                }
            }
            developers {
                developer {
                    id = "themeinerlp"
                    name = "Phillipp Glanz"
                    email = "p.glanz@madfix.me"
                }
                developer {
                    id = "theEvilReaper"
                    name = "Steffen Wonning"
                    email = "steffenwx@gmail.com"
                }
                scm {
                    connection = "scm:git:git://github.com:OneLiteFeatherNET/vulpes-model.git"
                    developerConnection = "scm:git:ssh://git@github.com:OneLiteFeatherNET/vulpes-model.git"
                    url = "https://github.com/OneLiteFeatherNET/vulpes-model"
                }
            }
        }

        repositories {
            maven {
                authentication {
                    credentials(PasswordCredentials::class) {
                        // Those credentials need to be set under "Settings -> Secrets -> Actions" in your repository
                        username = System.getenv("ONELITEFEATHER_MAVEN_USERNAME")
                        password = System.getenv("ONELITEFEATHER_MAVEN_PASSWORD")
                    }
                }

                name = "OneLiteFeatherRepository"
                val releasesRepoUrl = uri("https://repo.onelitefeather.dev/onelitefeather-releases")
                val snapshotsRepoUrl = uri("https://repo.onelitefeather.dev/onelitefeather-snapshots")
                url = if (version.toString().contains("SNAPSHOT")) snapshotsRepoUrl else releasesRepoUrl
            }
        }
    }
}