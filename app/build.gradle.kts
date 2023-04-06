plugins {
    id ("com.android.application")
    id ("org.jetbrains.kotlin.android")
    id (Deps.Dagger.DaggerKaptPlugin)
    id ("com.google.gms.google-services")
    id ("com.google.firebase.crashlytics")
    id ("com.guardsquare.appsweep") version("latest.release")
}

android {
    namespace = Config.packageName
    compileSdk = Config.compileSdk

    defaultConfig {
        applicationId = Config.packageName
        minSdk = Config.minSdk
        targetSdk = Config.compileSdk
        versionCode = 4
        versionName = "1.1.0r"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = Config.isR8ProGuardEnableForRelease
            proguardFiles("proguard-android-optimize.txt","proguard-rules.pro")
        }

        debug {
            isMinifyEnabled = Config.isR8ProGuardEnableForDebug
            proguardFiles("proguard-android-optimize.txt","proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Deps.Compose.ComposeKotlinCompiler
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    //Project
    implementation(project(Project.CoreCompose))
    implementation(project(Project.AppListScreen))
    implementation(project(Project.PackageInfoProvider))
    implementation(project(Project.AdminReceiver))
    implementation(project(Project.OpenAppChangedTrackerService))
    implementation(project(Project.PreferencesStorage))
    implementation(project(Project.BottomBarScreen))
    implementation(project(Project.SettingsScreen))
    implementation(project(Project.Database))
    implementation(project(Project.QuickSettingsButtonService))
    implementation(project(Project.AdmobManager))

    //Dagger
    kapt(Deps.Dagger.DaggerKaptCompiler)

    //Compose
    implementation(Deps.Compose.Navigation)
    implementation(Deps.Compose.SystemUiController)
    implementation(Deps.AppCompat.appCompat)
    implementation(Deps.AppCompat.appCompatRes)
    implementation (platform("com.google.firebase:firebase-bom:31.3.0"))
    implementation ("com.google.firebase:firebase-crashlytics-ktx")
    implementation ("com.google.firebase:firebase-analytics-ktx")
}