import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.3.50"
    application
}

repositories {
    mavenCentral()
    mavenLocal()
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation("com.example.math:MathUtil:1.0")
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

application {
    mainClassName = "Calculator"
}

tasks.register("enableComposite") {
    doFirst {
        val file = File("composite-enable")
        if (!file.exists()) file.createNewFile()
    }
}

tasks.register("disableComposite") {
    doFirst {
        val file = File("composite-enable")
        if (file.exists()) file.delete()
    }
}