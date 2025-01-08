plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.ksp)
    alias(libs.plugins.compose.compiler)
    id("kotlin-parcelize")
}

android {
    namespace = "com.picpay.desafio.android.feature.contacts"
    compileSdk = 35

    defaultConfig {
        minSdk = 21

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
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

}


dependencies {
    implementation(project(":core:network"))
    implementation(project(":core:compose"))
    implementation(project(":common"))

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
    implementation(libs.lifecycleLiveDataKtx)
    implementation(libs.lifecycleRuntimeKtx)

    implementation(libs.coroutinesCore)
    implementation(libs.coroutinesAndroid)
    testImplementation(libs.coroutinesTest)

    implementation(libs.gson)

    implementation(libs.retrofit)
    implementation(libs.okhttp)
    implementation(libs.mockwebserver)

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

    testImplementation(libs.junit)
    testImplementation(libs.mockitoCore)
    testImplementation(libs.mockitoKotlin)
    implementation(libs.koinTest)

    androidTestImplementation(libs.testRunner)
    androidTestImplementation(libs.espressoCore)
    androidTestImplementation(libs.testCoreKtx)
}