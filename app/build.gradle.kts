plugins {
    id (Plugins.ANDROID_APPLICATION)
    id (Plugins.KOTLIN_ANDROID)
    id (Plugins.KOTLIN_KAPT)
    id (Plugins.DAGGER_HILT_ANDROID)
    id (Plugins.NAVIGATION_SAFE_ARGS)
    id (Plugins.KOTLIN_PARCELIZE)
    id (Plugins.KTLINT)
    id("org.jetbrains.kotlin.android")
}

android {
    compileSdkVersion (ConfigData.COMPILE_SDK_VERSION)
    buildToolsVersion (ConfigData.BUILD_TOOLS_VERSION)

    defaultConfig {
        applicationId =ConfigData.APPLICATION_ID
        minSdk = (ConfigData.MIN_SDK_VERSION)
        targetSdk = (ConfigData.TARGET_SDK_VERSION)
        versionCode = (ConfigData.VERSION_CODE)
        versionName = (ConfigData.VERSION_NAME)

        testInstrumentationRunner =ConfigData.TEST_INSTRUMENTATION_RUNNER
    }
    buildFeatures{
        dataBinding = true
        viewBinding = true

    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    api (project(Modules.DOMAIN))
    api (project(Modules.DATA))
    implementation (Dependencies.ANDROIDX_CORE)
    implementation (Dependencies.APPCOMPAT)
    implementation (Dependencies.MATERIAL)
    implementation (Dependencies.CONSTRAINT_LAYOUT)
    implementation (Dependencies.LEGACY_SUPPORT)
    implementation (Dependencies.VIEWPAGER2)
    implementation (Dependencies.NAVIGATION_COMPONENT)
    implementation (Dependencies.NAVIGATION_UI)
    implementation (Dependencies.DOTS_INDICATOR)
    api (Dependencies.RETROFIT2)
    implementation (Dependencies.GSON_CONVERTER)
    implementation (Dependencies.OKHTTP)
    implementation (Dependencies.LIFECYCLE_EXT)
    implementation (Dependencies.FRAGMENT_KTX)
    implementation (Dependencies.LIFECYCLE_VIEWMODEL)
    implementation (Dependencies.ACTIVITY_KTX)
    implementation (Dependencies.DAGGER_HILT)
    implementation("androidx.legacy:legacy-support-v4:1.0.0")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.3.1")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.3.1")
    kapt (Dependencies.DAGGER_HILT_COMPILER)
//    implementation (Dependencies.HILT_LIFECYCLE_VIEWMODEL)
    implementation (Dependencies.PAGING)
    implementation (Dependencies.GLIDE)
    annotationProcessor (Dependencies.GLIDE_COMPILER)
    implementation(Dependencies.LOGGING_INTERCEPTOR)
    implementation (Dependencies.DATASTORE_PREFERENCES)

    testImplementation (Dependencies.JUNIT)
    androidTestImplementation (Dependencies.EXT_JUNIT)
    androidTestImplementation (Dependencies.ESPRESSO_CORE)
}