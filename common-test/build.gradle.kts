plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinAndroid)
}

android {
    namespace = "com.picpay.desafio.commontest"
    compileSdk = libs.versions.compileSdk.get().toInt()

    defaultConfig {
        minSdk = libs.versions.minSdk.get().toInt()
    }

    buildTypes {
        release {
            isMinifyEnabled = false
        }
    }
    packaging {
        resources.excludes.add("META-INF/LICENSE.md")
        resources.excludes.add("META-INF/LICENSE-notice.md")
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {
    implementation(project(":core:network"))
    implementation(project(":common"))

    implementation(libs.mockk.core)
    implementation(libs.gson)
    implementation(libs.coroutinesTest)
    implementation(libs.retrofit)
    implementation(libs.mockwebserver)

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