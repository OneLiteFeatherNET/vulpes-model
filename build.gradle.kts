plugins {
    java
    jacoco
    `maven-publish`
    id("de.chojo.publishdata") version "1.4.0"
}

group = "net.theevilreaper.vulpes.api"
version = "1.0.0"

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }
    withSourcesJar()
}

dependencies {
    annotationProcessor(mn.micronaut.serde.processor)
    annotationProcessor(mn.micronaut.mongo.core)
    implementation(mn.micronaut.data.document.processor)
    implementation(mn.micronaut.data.mongodb)
    implementation(mn.micronaut.mongo.core)
    implementation(mn.micronaut.runtime)
}
tasks {
    compileJava {
        options.encoding = "UTF-8"
        options.release.set(21)
    }

    test {
        finalizedBy(rootProject.tasks.jacocoTestReport)
        jvmArgs("-Dminestom.inside-test=true")
        testLogging {
            events("passed", "skipped", "failed")
        }
    }

}

publishData {
    addBuildData()
    useGitlabReposForProject("265", "https://gitlab.onelitefeather.dev/")
    publishTask("jar")
}

publishing {
    publications.create<MavenPublication>("maven") {
        // configure the publication as defined previously.
        publishData.configurePublication(this)
        version = publishData.getVersion(false)
    }

    repositories {
        maven {
            credentials(HttpHeaderCredentials::class) {
                name = "Job-Token"
                value = System.getenv("CI_JOB_TOKEN")
            }
            authentication {
                create("header", HttpHeaderAuthentication::class)
            }


            name = "Gitlab"
            // Get the detected repository from the publishing data
            url = uri(publishData.getRepository())
        }
    }
}
