plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id(Deps.Dagger.KspPlugin)
    id("com.google.gms.google-services")
    id("com.google.firebase.crashlytics")
    id("com.guardsquare.appsweep") version ("latest.release")
    id(Deps.Compose.PluginName) version Deps.Compose.PluginVersion
}

android {
    namespace = Config.packageName
    compileSdk = Config.compileSdk

    defaultConfig {
        applicationId = Config.packageName
        minSdk = Config.minSdk
        targetSdk = Config.compileSdk
        versionCode = 6
        versionName = "1.1.2r"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = Config.isR8ProGuardEnableForRelease
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"),"proguard-rules.pro")
            testProguardFile("test-proguard-rules.pro")
        }

        debug {
            isMinifyEnabled = Config.isR8ProGuardEnableForDebug
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = Config.sourceCompatibility
        targetCompatibility = Config.targetCompatibility
    }
    kotlinOptions {
        jvmTarget = Config.jvmTarget
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
    ksp(Deps.Dagger.DaggerKaptCompiler)

    //Compose
    implementation(Deps.Compose.Navigation)
    implementation(Deps.Compose.SystemUiController)
    implementation(Deps.AppCompat.appCompat)
    implementation(Deps.AppCompat.appCompatRes)
    implementation(platform("com.google.firebase:firebase-bom:34.6.0"))
    implementation("com.google.firebase:firebase-crashlytics-ktx:19.4.4")
    implementation("com.google.firebase:firebase-analytics-ktx:22.5.0")
}