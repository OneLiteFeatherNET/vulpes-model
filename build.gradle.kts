import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
	id("org.springframework.boot") version "3.1.5"
	id("io.spring.dependency-management") version "1.1.3"
	kotlin("jvm") version "1.9.20"
	kotlin("plugin.spring") version "1.9.20"
	`maven-publish`
}

group = "net.theevilreaper.vulpes.api"
version = "0.0.1-SNAPSHOT"

java {
	sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter")
	implementation("org.springframework.boot:spring-boot-starter-data-mongodb:3.0.2")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks {
	compileKotlin {
		compilerOptions {
			freeCompilerArgs.add("-Xjsr305=strict")
			jvmTarget.set(JvmTarget.JVM_17)
		}
	}

	test {
		useJUnitPlatform()
	}

	bootBuildImage {
		builder.set("paketobuildpacks/builder-jammy-base:latest")
	}
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