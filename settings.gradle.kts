dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()

    }
}
rootProject.name = "sadik_fodamy"
include (":app",":data",":domain")

include(":testt:myapplication")
