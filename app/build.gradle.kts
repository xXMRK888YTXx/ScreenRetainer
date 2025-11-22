plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.ksp)
    alias(libs.plugins.google.services)
    alias(libs.plugins.firebase.crashlytics)
    id("com.guardsquare.appsweep") version ("latest.release")
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.xxmrk888ytxx.screenretainer"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.xxmrk888ytxx.screenretainer"
        minSdk = 23
        targetSdk = 36
        versionCode = 6
        versionName = "1.1.2r"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"),"proguard-rules.pro")
            testProguardFile("test-proguard-rules.pro")
        }

        debug {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    //Project
    implementation(project(":CoreCompose"))
    implementation(project(":AppListScreen"))
    implementation(project(":PackageInfoProvider"))
    implementation(project(":AdminReceiver"))
    implementation(project(":OpenAppChangedTrackerService"))
    implementation(project(":PreferencesStorage"))
    implementation(project(":BottomBarScreen"))
    implementation(project(":SettingsScreen"))
    implementation(project(":Database"))
    implementation(project(":QuickSettingsButtonService"))

    //Dagger
    ksp(libs.dagger.compiler)

    //Compose
    implementation(libs.navigation.compose)
    implementation(libs.accompanist.systemuicontroller)
    implementation(libs.appcompat)
    implementation(libs.appcompat.resources)
    implementation(platform("com.google.firebase:firebase-bom:34.6.0"))
    implementation("com.google.firebase:firebase-crashlytics-ktx:19.4.4")
    implementation("com.google.firebase:firebase-analytics-ktx:22.5.0")
}
