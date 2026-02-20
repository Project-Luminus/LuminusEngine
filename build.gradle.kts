plugins {
    kotlin("jvm") version "2.3.0"
}

group = "one.nxeu"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(libs.minestom)
    implementation(libs.arrow.core)
    implementation(libs.arrow.fx.coroutines)

    // Logging
    implementation(libs.slf4j.api)
    implementation(libs.log4j.core)
    implementation(libs.log4j.slf4j.impl)
    implementation(libs.kotlin.logging)

    testImplementation(kotlin("test"))
}

kotlin {
    jvmToolchain(25)
}

tasks.test {
    useJUnitPlatform()
}