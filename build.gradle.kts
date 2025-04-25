//

buildscript {
    val kotlin_version = "2.1.0"  // Update to the latest compatible Kotlin version
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath ("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlin_version")
        // Other classpath dependencies
    }
}

plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.google.gms.google.services) apply false

}