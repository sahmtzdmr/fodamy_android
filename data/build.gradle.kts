plugins {
    id(Plugins.ANDROID_LIBRARY)
    id(Plugins.KOTLIN_ANDROID)
    id(Plugins.KOTLIN_PARCELIZE)
    id(Plugins.KOTLIN_KAPT)
}
val DBNAME: String by project

android {
    compileSdk = (ConfigData.COMPILE_SDK_VERSION)
    buildToolsVersion(ConfigData.BUILD_TOOLS_VERSION)

    defaultConfig {
        minSdk = (ConfigData.MIN_SDK_VERSION)
        targetSdk = (ConfigData.TARGET_SDK_VERSION)

        testInstrumentationRunner = ConfigData.TEST_INSTRUMENTATION_RUNNER
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions {
            jvmTarget = "1.8"
        }
    }
    buildTypes {
        getByName("debug") {
            buildConfigField("String", "DBNAME", DBNAME)
        }
    }
}

dependencies {
    api(project(Modules.DOMAIN))
    implementation(Dependencies.ANDROIDX_CORE)
    implementation(Dependencies.APPCOMPAT)
    implementation(Dependencies.MATERIAL)
    implementation(Dependencies.GSON_CONVERTER)
    implementation(Dependencies.DAGGER_HILT)
    kapt(Dependencies.DAGGER_HILT_COMPILER)
    implementation(Dependencies.DAGGER_HILT_GRADLE_PLUGIN)
    implementation(Dependencies.PAGING)
    implementation(Dependencies.LIFECYCLE_EXT)
    implementation(Dependencies.LIFECYCLE_VIEWMODEL)
    implementation(Dependencies.DATASTORE_PREFERENCES)
    implementation(Dependencies.LOGGING_INTERCEPTOR)
    testImplementation(Dependencies.JUNIT)
    androidTestImplementation(Dependencies.EXT_JUNIT)
    androidTestImplementation(Dependencies.ESPRESSO_CORE)
    implementation(Dependencies.RETROFIT2)
    api(Dependencies.ROOM_RUNTIME)
    kapt(Dependencies.ROOM_COMPILER)
    implementation(Dependencies.ROOM_EXTENSIONS)
    implementation(Dependencies.GLIDE)
    kapt(Dependencies.GLIDE_COMPILER)
    implementation(Dependencies.ROOM_PAGING)

}
