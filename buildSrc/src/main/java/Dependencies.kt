object Dependencies {
    const val ANDROIDX_CORE = "androidx.core:core-ktx:${Versions.ANDROIDX_CORE}"
    const val APPCOMPAT = "androidx.appcompat:appcompat:${Versions.APPCOMPAT}"
    const val MATERIAL = "com.google.android.material:material:${Versions.MATERIAL}"
    const val CONSTRAINT_LAYOUT =
        "androidx.constraintlayout:constraintlayout:${Versions.CONSTRAINT_LAYOUT}"
    const val LEGACY_SUPPORT =
        "androidx.legacy:legacy-support-v4:${Versions.ANDROIDX_LEGACY_SUPPORT}"
    const val VIEWPAGER2 = "androidx.viewpager2:viewpager2:${Versions.VIEWPAGER2}"
    const val NAVIGATION_COMPONENT =
        "androidx.navigation:navigation-fragment-ktx:${Versions.NAVIGATION_COMPONENT}"
    const val NAVIGATION_UI =
        "androidx.navigation:navigation-ui-ktx:${Versions.NAVIGATION_COMPONENT}"
    const val DOTS_INDICATOR = "com.tbuonomo:dotsindicator:${Versions.DOTS_INDICATOR}"
    const val RETROFIT2 = "com.squareup.retrofit2:retrofit:${Versions.RETROFIT2}"
    const val GSON_CONVERTER = "com.squareup.retrofit2:converter-gson:${Versions.GSON_CONVERTER}"
    const val OKHTTP = "com.squareup.okhttp3:okhttp:${Versions.OKHTTP}"
    const val LIFECYCLE_EXT = "androidx.lifecycle:lifecycle-extensions:${Versions.LIFECYCLE_EXT}"
    const val FRAGMENT_KTX = "androidx.fragment:fragment-ktx:${Versions.FRAGMENT_KTX}"
    const val LIFECYCLE_VIEWMODEL =
        "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.LIFECYCLE_VIEWMODEL}"
    const val ACTIVITY_KTX = "androidx.activity:activity-ktx:${Versions.ACTIVITY_KTX}"
    const val DAGGER_HILT = "com.google.dagger:hilt-android:${Versions.DAGGER_HILT}"
    const val DAGGER_HILT_COMPILER =
        "com.google.dagger:hilt-android-compiler:${Versions.DAGGER_HILT}"
    const val HILT_LIFECYCLE_VIEWMODEL =
        "androidx.hilt:hilt-lifecycle-viewmodel:${Versions.HILT_LIFECYCLE_VIEWMODEL}"
    const val PAGING = "androidx.paging:paging-runtime:${Versions.PAGING}"
    const val GLIDE = "com.github.bumptech.glide:glide:${Versions.GLIDE}"
    const val GLIDE_COMPILER = "com.github.bumptech.glide:compiler:${Versions.GLIDE_COMPILER}"
    const val LOGGING_INTERCEPTOR =
        "com.squareup.okhttp3:logging-interceptor:${Versions.LOGGING_INTERCEPTOR}"
    const val DATASTORE_PREFERENCES =
        "androidx.datastore:datastore-preferences:${Versions.DATASTORE_PREFERENCES}"
    const val FIREBASE_BOM = "com.google.firebase:firebase-bom:${Versions.FIREBASE_BOM}"
    const val FIREBASE_ANALYTICS="com.google.firebase:firebase-analytics-ktx"
    const val FIREBASE_CRASHLYTCS="com.google.firebase:firebase-crashlytics-ktx"
    const val ROOM_RUNTIME = "androidx.room:room-runtime:${Versions.ROOM}"
    const val ROOM_COMPILER = "androidx.room:room-compiler:${Versions.ROOM}"
    const val ROOM_EXTENSIONS = "androidx.room:room-ktx:${Versions.ROOM}"
    const val ROOM_PAGING="androidx.room:room-paging:${Versions.ROOM_PAGING}"


    //classpath
    const val TOOLS_BUILD_GRADLE = "com.android.tools.build:gradle:${Versions.TOOLS_BUILD_GRADLE}"
    const val KOTLIN_GRADLE_PLUGIN =
        "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.KOTLIN_GRADLE_PLUGIN}"
    const val DAGGER_HILT_GRADLE_PLUGIN =
        "com.google.dagger:hilt-android-gradle-plugin:${Versions.DAGGER_HILT}"
    const val NAVIGATION_SAFE_ARGS =
        "androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.NAVIGATION_SAFE_ARGS}"
    const val KTLINT_GRADLE = "org.jlleitschuh.gradle:ktlint-gradle:${Versions.KTLINT_VERSION}"
    const val GOOGLE_SERVICES = "com.google.gms:google-services:${Versions.GOOGLE_SERVICES}"
    const val CRASHLYTCS_GRADLE="com.google.firebase:firebase-crashlytics-gradle:${Versions.CRASHLYTCS_GRADLE}"

    //test
    const val JUNIT = "junit:junit:${Versions.JUNIT}"
    const val EXT_JUNIT = "androidx.test.ext:junit:${Versions.EXT_JUNIT}"
    const val ESPRESSO_CORE = "androidx.test.espresso:espresso-core:${Versions.ESPRESSO_CORE}"
}