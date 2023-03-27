

object Deps {
    private const val MockkVersion = "1.13.4"

    object Compose {
        private const val ComposeVersion = "1.4.0"
        private const val MaterialVersion = "1.4.0"
        const val ComposeKotlinCompiler = "1.4.3"
        const val UI = "androidx.compose.ui:ui:$ComposeVersion"
        const val Tooling = "androidx.compose.ui:ui-tooling-preview:$ComposeVersion"
        const val Material = "androidx.compose.material:material:$MaterialVersion"
        const val TestJUnit = "androidx.compose.ui:ui-test-junit4:$ComposeVersion"
        const val TestTooling = "androidx.compose.ui:ui-tooling:$ComposeVersion"
        const val TestManifest = "androidx.compose.ui:ui-test-manifest:$ComposeVersion"
        const val Navigation = "androidx.navigation:navigation-compose:2.5.3"
        const val GoogleFonts =  "androidx.compose.ui:ui-text-google-fonts:$ComposeVersion"
        const val SystemUiController = "com.google.accompanist:accompanist-systemuicontroller:0.29.2-rc"
    }

    object TestAndroid {
        const val AndroidJUnit = "androidx.test.ext:junit:1.1.4"
        const val Espresso = "androidx.test.espresso:espresso-core:3.5.0"
        const val MockkAndroid = "io.mockk:mockk-android:$MockkVersion"
        const val MockkAgent = "io.mockk:mockk-agent:$MockkVersion"
    }

    object AndroidX {
        const val AndroidCore = "androidx.core:core-ktx:1.9.0"
        const val LifeCycle = "androidx.lifecycle:lifecycle-runtime-ktx:2.6.0"
        const val ComposeActivity =  ("androidx.activity:activity-compose:1.7.0-rc01")
    }

    object ViewModel {
        private const val version = "2.6.0"
        const val ViewModel = ("androidx.lifecycle:lifecycle-viewmodel:$version")
        const val ViewModelKotlin =  ("androidx.lifecycle:lifecycle-viewmodel-ktx:$version")
    }

    object Dagger { //https://dagger.dev/
        const val version = "2.44"
        const val DaggerCore = "com.google.dagger:dagger:$version"
        const val DaggerKaptCompiler = "com.google.dagger:dagger-compiler:$version"
        const val DaggerKaptPlugin = "kotlin-kapt"
    }

    object Coroutines {
        private const val version = "1.6.4"
        const val CoroutinesAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$version"
        object Test {
            const val CoroutinesTest = "org.jetbrains.kotlinx:kotlinx-coroutines-test:$version"
        }
    }

    object Test {
        const val JUnit = "junit:junit:4.13.2"
        const val Mockk = "io.mockk:mockk:$MockkVersion"
        const val Testing = "org.testng:testng:6.9.6"
    }

    object InstrumentalTest {
        private const val testVersion = "1.5.0"
        const val espresso = "androidx.test.espresso:espresso-core:3.5.0"
        const val testRunner = "androidx.test:runner:1.5.1"
        const val testCore = "androidx.test:core:$testVersion"
        const val jUnit = "androidx.test.ext:junit-ktx:1.1.4"
        const val testRules = "androidx.test:rules:$testVersion"
    }

    object Coil {
        const val coil = "io.coil-kt:coil-compose:2.2.2"
    }

    object DataStore {
        const val dataStore = "androidx.datastore:datastore-preferences:1.0.0"
    }


}