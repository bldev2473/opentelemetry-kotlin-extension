plugins {
    id("io.spring.dependency-management") version "1.1.0"
    id("maven-publish")
    kotlin("jvm") version "1.9.22"
    kotlin("plugin.spring") version "1.9.22"
}

group = "com.bldev.otel.extension"
version = "1.0-SNAPSHOT"

dependencyManagement {
    imports {
        mavenBom("io.opentelemetry:opentelemetry-bom:1.40.0")
    }
}

repositories {
    mavenCentral()
    maven {
      name = "sonatype"
      url = uri("https://oss.sonatype.org/content/repositories/snapshots")
    }
}

val springVersion by extra { "6.1.11" }

dependencies {
    // Spring
    implementation("org.springframework:spring-core:$springVersion")
    implementation("org.springframework:spring-beans:$springVersion")
    implementation("org.springframework:spring-aop:$springVersion")
    implementation("org.springframework:spring-context-support:$springVersion")

    // Opentelemetry
    implementation("io.opentelemetry:opentelemetry-api")
    implementation("io.opentelemetry:opentelemetry-sdk")

    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(17)
}

publishing {
    publications {
        create<MavenPublication>("maven") {
          groupId = "com.bldev.otel"
          artifactId = "extension"
          version = "0.0.1"

          from(components["java"])
        }
    }
}
