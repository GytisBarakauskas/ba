buildscript {
    repositories {
        google()
        jcenter()
    }
    dependencies {
        classpath(BuildPlugins.androidGradlePlugin)
        classpath(BuildPlugins.kotlinGradlePlugin)
        classpath(BuildPlugins.daggerHiltGradlePlugin)
        classpath(BuildPlugins.safeArgsGradlePlugin)
    }
}

allprojects {
    repositories {
        maven { setUrl("https://jitpack.io") }
        google()
        jcenter()
    }
}

tasks.register(BuildPlugins.taskTypeClean, Delete::class) {
    delete(rootProject.buildDir)
}