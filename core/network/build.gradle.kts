plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.ksp)
}

val baseUrlProd = "\"https://609a908e0f5a13001721b74e.mockapi.io/picpay/api/\""
val baseUrlQa = "\"https://609a908e0f5a13001721b74e.mockapi.io/picpay/api/\""
val baseUrlMock = "\"http://127.0.0.1:50001/\""

var baseUrl = if (gradle.startParameter.taskRequests.toString().contains("Release")) {
    baseUrlProd
} else {
    baseUrlQa
}

android {
    namespace = "com.picpay.desafio.android.core.network"
    compileSdk = 35

    defaultConfig {
        minSdk = 21
    }

    buildTypes {
        buildTypes {
            getByName("debug") {
                buildConfigField("String", "BASE_URL", baseUrl)
                buildConfigField("String", "BASE_URL_MOCK", baseUrlMock)
            }
            getByName("release") {
                buildConfigField("String", "BASE_URL", baseUrl)
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
            buildConfig = true
        }

    }
}

dependencies {

    implementation(libs.coreKtx)

    implementation(libs.koinCore)
    implementation(libs.koinAndroid)
    implementation(libs.koin.annotations)
    ksp(libs.koin.compiler)

    implementation(libs.retrofit)
    implementation(libs.retrofitAdapterRxjava2)
    implementation(libs.retrofitConverterGson)
    implementation(libs.okhttp)

    testImplementation(libs.junit)
    androidTestImplementation(libs.espressoCore)
}