import org.gradle.api.JavaVersion

const val kotlinVersion = "1.5.0"
const val gradleVersion = "4.1.1"
const val daggerHiltVersion = "2.35"
const val kotlinSerializationVersion = "1.5.0"
const val realmVersion = "10.4.0"

object AndroidSDK {
    const val compileSdk = 30
    const val buildTools = "30.0.2"
}

object DefaultConfig {
    const val applicationID = "com.gytisdev.bahometask"
    const val minSdk = 23
    const val targetSdk = 30
    const val versionCode = 1
    const val versionName = "1.0.0"

    const val instrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
}

object BuildTypes {
    const val release = "release"
}

object ProguardFile {
    const val textFile = "proguard-android-optimize.txt"
    const val ruleFile = "proguard-rules.pro"
}

object BuildPlugins {
    const val androidGradlePlugin = "com.android.tools.build:gradle:$gradleVersion"
    const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion"
    const val daggerHiltGradlePlugin = "com.google.dagger:hilt-android-gradle-plugin:${daggerHiltVersion}"
    const val safeArgsGradlePlugin =
        "androidx.navigation:navigation-safe-args-gradle-plugin:${Dependencies.AndroidX.navigationComponentVersion}"

    const val taskTypeClean = "clean"

    const val androidApplication = "com.android.application"
    const val kotlinAndroid = "android"
    const val kotlinAndroidExtensions = "android.extensions"
    const val kotlinKapt = "kotlin-kapt"
    const val daggerHiltAndroid = "dagger.hilt.android.plugin"
    const val kotlinSafeArgs = "androidx.navigation.safeargs.kotlin"
    const val kotlinSerialization = "plugin.serialization"
}

object Options {
    val sourceCompatibilityJavaVersion = JavaVersion.VERSION_1_8
    val targetCompatibilityJavaVersion = JavaVersion.VERSION_1_8
    const val jvmTarget = "1.8"
}

object Dependencies {

    object Local {
        // For local modules
    }

    object Kotlin {
        const val kotlinStdLib = "org.jetbrains.kotlin:kotlin-stdlib:$kotlinVersion"
    }

    object AndroidX {
        const val navigationComponentVersion = "2.3.5"
        private const val coreKtxVersion = "1.3.2"
        private const val appCompatVersion = "1.2.0"
        private const val constraintLayoutVersion = "2.0.4"
        private const val swipeRefreshLayoutVersion = "1.1.0"
        private const val lifecycleVersion = "2.3.1"
        private const val roomVersion = "2.3.0"

        const val coreKtx = "androidx.core:core-ktx:$coreKtxVersion"
        const val appCompat = "androidx.appcompat:appcompat:$appCompatVersion"
        const val constraintLayout = "androidx.constraintlayout:constraintlayout:$constraintLayoutVersion"
        const val navigationComponentFragment = "androidx.navigation:navigation-fragment-ktx:$navigationComponentVersion"
        const val navigationComponentUI = "androidx.navigation:navigation-ui-ktx:$navigationComponentVersion"
        const val swipeRefreshLayout = "androidx.swiperefreshlayout:swiperefreshlayout:$swipeRefreshLayoutVersion"
        const val lifecycleViewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycleVersion"
        const val lifecycleLiveData = "androidx.lifecycle:lifecycle-livedata-ktx:$lifecycleVersion"
        const val room = "androidx.room:room-runtime:$roomVersion"
        const val roomKtx = "androidx.room:room-ktx:$roomVersion"
    }

    object Google {
        private const val materialVersion = "1.3.0"

        const val materialDesign = "com.google.android.material:material:$materialVersion"
        const val daggerHilt = "com.google.dagger:hilt-android:${daggerHiltVersion}"
        const val daggerHiltCompiler = "com.google.dagger:hilt-android-compiler:${daggerHiltVersion}"
    }

    object Square {
        private const val retrofitVersion = "2.9.0"

        const val retrofit = "com.squareup.retrofit2:retrofit:$retrofitVersion"
    }

    object Jetbrains {
        private const val kotlinSerializationJsonVersion = "1.2.1"

        const val kotlinSerialization = "org.jetbrains.kotlinx:kotlinx-serialization-json:$kotlinSerializationJsonVersion"
    }

    object Misc {
        private const val retrofitKotlinSerializationConverterVersion = "0.8.0"

        const val retrofitKotlinSerializationConverter =
            "com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:$retrofitKotlinSerializationConverterVersion"
    }

    object Test {
        private const val jUnitVersion = "4.13.2"
        private const val androidXjUnitVersion = "1.1.2"

        const val jUnit = "junit:junit:${jUnitVersion}"
        const val androidXjUnit = "androidx.test.ext:junit:${androidXjUnitVersion}"
    }
}