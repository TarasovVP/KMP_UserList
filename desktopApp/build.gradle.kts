@file:OptIn(ExperimentalKotlinGradlePluginApi::class)

import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi

plugins {
    alias(libs.plugins.kotlinMultiplatform)
}

kotlin {
    jvmToolchain(21)
    jvm {
        binaries {
            executable {
                mainClass.set("MainKt")
            }
        }
    }

    sourceSets {
        jvmMain.dependencies {
            implementation(projects.shared)

            implementation(libs.kotlinx.coroutines.core)
            implementation(libs.kotlinx.coroutines.swing)
        }
    }
}
