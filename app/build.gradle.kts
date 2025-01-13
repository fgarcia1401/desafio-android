plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.ksp)
    id("kotlin-parcelize")
}

android {
    namespace = "com.picpay.desafio.android"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.picpay.desafio.android"
        minSdk = 21
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        vectorDrawables {
            useSupportLibrary = true
        }

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("debug") {
        }
        getByName("release") {
            isMinifyEnabled = true
            isShrinkResources = true
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
        viewBinding = true
    }

    packaging {
        resources.excludes.add("META-INF/LICENSE.md")
        resources.excludes.add("META-INF/LICENSE-notice.md")
    }
}

dependencies {
    //Features
    implementation(project(":features:contacts"))
    implementation(project(":core:compose"))
    implementation(project(":core:network"))
    implementation(project(":common"))

    implementation(libs.kotlinStdlib)
    implementation(libs.coreKtx)
    implementation(libs.appCompat)
    implementation(libs.material)
    implementation(libs.constraintLayout)

    implementation(libs.koinCore)
    implementation(libs.koinAndroid)
    implementation(libs.koin.annotations)
    ksp(libs.koin.compiler)

    implementation(libs.coroutinesCore)
    implementation(libs.coroutinesAndroid)
    testImplementation(libs.coroutinesTest)

    androidTestImplementation(libs.testRunner)
    androidTestImplementation(libs.espressoCore)
    androidTestImplementation(libs.testCoreKtx)

    // Navigation Component
    implementation(libs.navigation.fragment.ktx)
    implementation(libs.navigation.ui.ktx)

    // Jetpack Compose
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.material)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.navigation.compose)
    implementation(libs.androidx.compose.foundation)

    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
}


