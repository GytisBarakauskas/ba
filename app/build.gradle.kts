plugins {
    kotlin(BuildPlugins.kotlinAndroid)
    kotlin(BuildPlugins.kotlinAndroidExtensions)
    kotlin(BuildPlugins.kotlinSerialization) version kotlinSerializationVersion
    id(BuildPlugins.androidApplication)
    id(BuildPlugins.daggerHiltAndroid)
    id(BuildPlugins.kotlinKapt)
    id(BuildPlugins.kotlinSafeArgs)
}

android {
    compileSdkVersion(AndroidSDK.compileSdk)
    buildToolsVersion(AndroidSDK.buildTools)
    defaultConfig {
        applicationId = DefaultConfig.applicationID
        minSdkVersion(DefaultConfig.minSdk)
        targetSdkVersion(DefaultConfig.targetSdk)
        versionCode = DefaultConfig.versionCode
        versionName = DefaultConfig.versionName
        testInstrumentationRunner = DefaultConfig.instrumentationRunner
    }

    buildTypes {
        getByName(BuildTypes.release) {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile(ProguardFile.textFile),
                ProguardFile.ruleFile
            )
        }
    }
    compileOptions {
        sourceCompatibility(Options.sourceCompatibilityJavaVersion)
        targetCompatibility(Options.targetCompatibilityJavaVersion)
    }
    kotlinOptions {
        jvmTarget = Options.jvmTarget
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(Dependencies.Kotlin.kotlinStdLib)
    implementation(Dependencies.Google.materialDesign)
    implementation(Dependencies.AndroidX.appCompat)
    implementation(Dependencies.AndroidX.constraintLayout)
    implementation(Dependencies.AndroidX.coreKtx)
    implementation(Dependencies.AndroidX.navigationComponentFragment)
    implementation(Dependencies.AndroidX.navigationComponentUI)
    implementation(Dependencies.AndroidX.lifecycleViewModel)
    implementation(Dependencies.AndroidX.lifecycleLiveData)
    implementation(Dependencies.Square.retrofit)
    implementation(Dependencies.Misc.retrofitKotlinSerializationConverter)
    implementation(Dependencies.Jetbrains.kotlinSerialization)
    implementation(Dependencies.AndroidX.room)
    implementation(Dependencies.AndroidX.roomKtx)

    // UI
    implementation(Dependencies.AndroidX.swipeRefreshLayout)

    // DI
    implementation(Dependencies.Google.daggerHilt)
    kapt(Dependencies.Google.daggerHiltCompiler)

    // Testing
    testImplementation(Dependencies.Test.jUnit)
    androidTestImplementation(Dependencies.Test.androidXjUnit)
}