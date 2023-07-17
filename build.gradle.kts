import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "3.1.1"
    id("io.spring.dependency-management") version "1.1.0"
    kotlin("jvm") version "1.8.22"
    kotlin("kapt") version "1.5.10"
    kotlin("plugin.spring") version "1.8.22"
}

group = "net.elau.example"
version = "0.0.1-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
    mavenCentral()
}

extra["springCloudVersion"] = "2022.0.3"
extra["mapstructVersion"] = "1.5.5.Final"

dependencies {
    kapt("org.mapstruct:mapstruct-processor:${property("mapstructVersion")}")
    implementation("org.mapstruct:mapstruct:${property("mapstructVersion")}")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("io.micrometer:micrometer-observation")
    implementation("io.micrometer:micrometer-tracing-bridge-brave")
//    implementation("io.zipkin.reporter2:zipkin-reporter-brave")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.springframework.cloud:spring-cloud-starter-stream-kafka")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.cloud:spring-cloud-stream-test-binder")
}

dependencyManagement {
    imports {
        mavenBom("org.springframework.cloud:spring-cloud-dependencies:${property("springCloudVersion")}")
    }
}

kapt {
    correctErrorTypes = true
    arguments {
        arg("mapstruct.defaultComponentModel", "spring")
    }
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs += "-Xjsr305=strict"
        jvmTarget = "17"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
