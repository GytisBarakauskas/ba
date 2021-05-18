plugins {
    id(BuildPlugins.androidApplication)
    id(BuildPlugins.daggerHiltAndroid)
    kotlin(BuildPlugins.kotlinAndroid)
    kotlin(BuildPlugins.kotlinAndroidExtensions)
    id(BuildPlugins.kotlinKapt)
    id(BuildPlugins.kotlinSafeArgs)
    kotlin(BuildPlugins.kotlinSerialization) version kotlinSerializationVersion
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
    testOptions {
        unitTests {
            isIncludeAndroidResources = true
        }
    }
}

dependencies {
    implementation(Dependencies.Kotlin.kotlinStdLib)
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
    kapt(Dependencies.AndroidX.roomCompiler)

    // UI
    implementation(Dependencies.Google.materialDesign)
    implementation(Dependencies.AndroidX.swipeRefreshLayout)
    implementation(Dependencies.Misc.glide)
    implementation(Dependencies.Misc.glideCompiler)
    implementation(Dependencies.Misc.shimmer)

    // DI
    implementation(Dependencies.Google.daggerHilt)
    kapt(Dependencies.Google.daggerHiltCompiler)

    // Testing
    testImplementation(Dependencies.Test.roboelectric)
    testImplementation(Dependencies.Test.jUnit)
    testImplementation(Dependencies.Test.mockk)
    testImplementation(Dependencies.Test.androidXCoreTesting)
    testImplementation(Dependencies.Test.testRunner)
    testImplementation(Dependencies.Test.androidXjUnit)
    testImplementation(Dependencies.Test.mockkAndroid)
    testImplementation(Dependencies.Test.kotlinCoroutinesTest)
}