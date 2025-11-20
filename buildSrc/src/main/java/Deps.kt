

object Deps {
    private const val MockkVersion = "1.14.6"

    object Compose {
        const val PluginName = "org.jetbrains.kotlin.plugin.compose"
        const val PluginVersion = "2.2.21"
        private const val ComposeVersion = "1.9.5"
        private const val MaterialVersion = "1.9.5"
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
        const val MockkAndroid = "io.mockk:mockk-android:$MockkVersion"
        const val MockkAgent = "io.mockk:mockk-agent:$MockkVersion"
    }

    object AndroidX {
        const val AndroidCore = "androidx.core:core-ktx:1.17.0"
        const val LifeCycle = "androidx.lifecycle:lifecycle-runtime-ktx:2.10.0"
        const val ComposeActivity =  ("androidx.activity:activity-compose:1.12.0")
    }

    object ViewModel {
        private const val version = "2.10.0"
        const val ViewModel = ("androidx.lifecycle:lifecycle-viewmodel:$version")
        const val ViewModelKotlin =  ("androidx.lifecycle:lifecycle-viewmodel-ktx:$version")
    }

    object Dagger { //https://dagger.dev/
        const val version = "2.57.2"
        const val DaggerCore = "com.google.dagger:dagger:$version"
        const val DaggerKaptCompiler = "com.google.dagger:dagger-compiler:$version"
        const val KspPlugin = "com.google.devtools.ksp"
    }

    object Coroutines {
        private const val version = "1.10.2"
        const val CoroutinesAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$version"
        object Test {
            const val CoroutinesTest = "org.jetbrains.kotlinx:kotlinx-coroutines-test:$version"
        }
    }

    object Test {
        const val JUnit = "junit:junit:4.13.2"
        const val Mockk = "io.mockk:mockk:$MockkVersion"
        const val Testing = "org.testng:testng:7.11.0"
        const val MockkJMVAgent = "io.mockk:mockk-agent-jvm:$MockkVersion"
    }

    object InstrumentalTest {
        private const val testVersion = "1.7.0"
        const val espresso = "androidx.test.espresso:espresso-core:3.7.0"
        const val testRunner = "androidx.test:runner:1.7.01"
        const val testCore = "androidx.test:core:$testVersion"
        const val jUnit = "androidx.test.ext:junit-ktx:1.3.04"
        const val testRules = "androidx.test:rules:$testVersion"
    }

    object Coil {
        const val coil = "io.coil-kt:coil-compose:2.7.0"
    }

    object DataStore {
        const val dataStore = "androidx.datastore:datastore-preferences:1.2.0"
    }

    object Room {
        const val version = "2.8.4"
        const val PluginName = "androidx.room"
        const val RoomRuntime =  "androidx.room:room-runtime:$version"
        const val KaptCompiler = "androidx.room:room-compiler:$version"
        const val RoomKTX = "androidx.room:room-ktx:$version"
        object Test {
            const val RoomTest = "androidx.room:room-testing:$version"
        }
    }

    object AppCompat {
        private const val version = "1.7.1"
        const val appCompat = "androidx.appcompat:appcompat:$version"
        const val appCompatRes = "androidx.appcompat:appcompat-resources:$version"
    }

    object AdMob {
        const val adMob = "com.google.android.gms:play-services-ads:24.7.0"
    }

    object ImmutableCollection {
        const val collectionsImmutable = "org.jetbrains.kotlinx:kotlinx-collections-immutable:0.4.0"
    }


}