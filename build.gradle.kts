plugins {
  java
    id("org.springframework.boot") version "3.2.1"
    id("io.spring.dependency-management") version "1.1.4"
}

group = "dev.jutty.mirante.server"
version = "0.1.2-SNAPSHOT"

java {
  sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
  mavenCentral()
}

dependencies {
  implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-data-rest")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    implementation("org.springframework.security:spring-security-crypto:6.2.1")
    implementation("org.bouncycastle:bcprov-jdk15on:1.64")
    runtimeOnly("com.h2database:h2")
}

tasks.withType<Test> {
  useJUnitPlatform()
}
