buildscript {
    dependencies {
        classpath("com.google.gms:google-services:4.4.4")
        classpath("com.google.firebase:firebase-crashlytics-gradle:3.0.6")
    }
}// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id ("com.android.application") version "8.13.1" apply false
    id ("com.android.library") version "8.13.1" apply false
    id ("org.jetbrains.kotlin.android") version "2.2.21" apply false
    id ("com.google.devtools.ksp") version "2.2.21-2.0.4" apply false
    id ("androidx.room") version Deps.Room.version apply false
}