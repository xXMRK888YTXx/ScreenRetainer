pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}
rootProject.name = "ScreenRetainer"
include (":app")
include(":CoreCompose")
include(":CoreAndroid")
include(":AppListScreen")
include("PackageInfoProvider")
include(":AdminReceiver")
include(":OpenAppChangedTrackerService")
include(":PreferencesStorage")
include(":BottomBarScreen")
include(":SettingsScreen")
include(":Database")
include(":QuickSettingsButtonService")
include(":AdmobManager")
