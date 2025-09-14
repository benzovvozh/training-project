plugins {
    application
    checkstyle
    jacoco
    id("org.springframework.boot") version "3.4.5"
    id("io.spring.dependency-management") version "1.1.7"
    id("io.freefair.lombok") version "8.10"
    id("io.sentry.jvm.gradle") version "5.2.0"
}

group = "io.project"
version = "0.0.1-SNAPSHOT"
description = "SubscribtionsService"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.cloud:spring-cloud-starter-openfeign:4.2.2")
    implementation("org.slf4j:slf4j-api:2.0.16")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    developmentOnly("org.springframework.boot:spring-boot-devtools")
    implementation("io.github.wimdeblauwe:error-handling-spring-boot-starter:4.2.0")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.8.1")
    //kafka
    implementation ("org.springframework.kafka:spring-kafka")

    implementation("org.instancio:instancio-junit:3.3.0")
    implementation("org.apache.commons:commons-lang3:3.13.0")
    implementation("org.apache.commons:commons-text:1.9")

    implementation("com.querydsl:querydsl-jpa:5.0.0:jakarta")
    annotationProcessor("com.querydsl:querydsl-apt:5.0.0:jakarta")
    annotationProcessor("jakarta.persistence:jakarta.persistence-api:3.1.0")

    implementation("org.openapitools:jackson-databind-nullable:0.2.6")
    implementation("org.mapstruct:mapstruct:1.5.5.Final")
    annotationProcessor("org.mapstruct:mapstruct-processor:1.5.5.Final")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("net.javacrumbs.json-unit:json-unit-assertj:3.2.2")
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter:5.10.0")
    testImplementation("org.assertj:assertj-core:3.25.3")

    runtimeOnly("com.h2database:h2")
    runtimeOnly("org.postgresql:postgresql:42.6.0")
}

tasks.withType<Test> {
    useJUnitPlatform()
}


