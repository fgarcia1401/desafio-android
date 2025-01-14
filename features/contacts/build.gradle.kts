plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.ksp)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.detekt)
    id("kotlin-parcelize")
}

android {
    namespace = "com.picpay.desafio.android.feature.contacts"
    compileSdk = libs.versions.compileSdk.get().toInt()

    defaultConfig {
        minSdk = libs.versions.minSdk.get().toInt()

        testInstrumentationRunner = "com.picpay.desafio.android.contacts.util.KoinTestRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
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
    }

    packaging {
        resources.excludes.add("META-INF/LICENSE.md")
        resources.excludes.add("META-INF/LICENSE-notice.md")
    }

}

dependencies {
    implementation(project(":core:network"))
    implementation(project(":core:compose"))
    implementation(project(":common"))
    implementation(project(":common-test"))

    implementation(libs.kotlinStdlib)
    implementation(libs.coreKtx)
    implementation(libs.appCompat)
    implementation(libs.constraintLayout)
    implementation(libs.material)

    implementation(libs.koinCore)
    implementation(libs.koinAndroid)
    implementation(libs.koin.compose)
    implementation(libs.koin.annotations)
    implementation(libs.coil.compose)
    ksp(libs.koin.compiler)

    implementation(libs.lifecycleViewModelKtx)
    implementation(libs.lifecycleRuntimeKtx)

    implementation(libs.coroutinesCore)
    implementation(libs.coroutinesAndroid)
    testImplementation(libs.coroutinesTest)

    implementation(libs.gson)
    implementation(libs.retrofitConverterGson)

    implementation(libs.retrofit)
    implementation(libs.okhttp)
    implementation(libs.mockwebserver)

    detektPlugins(libs.detekt.formatting)

    // Navigation Component
    implementation(libs.navigation.fragment.ktx)
    implementation(libs.navigation.ui.ktx)

    // Jetpack Compose
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.material)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.navigation.fragment.ktx)
    implementation(libs.navigation.ui.ktx)
    implementation(libs.navigation.compose)

    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)

    //Room
    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.room.ktx)
    ksp(libs.androidx.room.compiler)

    // Test
    testImplementation(libs.junit)
    testImplementation(libs.mockk.core)
    implementation(libs.koinTest)
    implementation(libs.androidx.ui.test.junit4.android)
    testImplementation(libs.androidx.room.testing)

    testImplementation(libs.mockwebserver)
    androidTestImplementation(libs.mockwebserver)
    androidTestImplementation(libs.testRunner)
    androidTestImplementation(libs.espressoCore)
    androidTestImplementation(libs.koin.test.junit4)
    androidTestImplementation(libs.testCoreKtx)
    androidTestImplementation(libs.mockk.core)
    androidTestImplementation(libs.mockk.android)

}