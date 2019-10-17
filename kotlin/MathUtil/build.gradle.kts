import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

group = "com.example.math"
version = "1.0"

plugins {
    kotlin("jvm") version "1.3.50"
    id("maven-publish")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = "com.example.math"
            artifactId = "MathUtil"
            version = "1.0"

            from(components["java"])
        }
    }
    repositories {
        maven {
            name = "internalRepo"
            setUrl("../../repo")
        }
    }
}