plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.example.papalote"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.papalote"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
        freeCompilerArgs += "-Xuse-ir"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation ("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.0")


    // Google Auth
    implementation ("androidx.core:core-ktx:1.15.0")
    implementation ("androidx.appcompat:appcompat:1.4.0")
    implementation ("com.google.android.material:material:1.5.0")
    implementation ("com.google.android.gms:play-services-auth:20.7.0")
    implementation ("androidx.credentials:credentials:1.0.0")
    implementation ("androidx.credentials:credentials-play-services-auth:1.0.0")
    implementation ("com.android.volley:volley:1.2.1")
    implementation ("org.json:json:20210307")

    //Escaneo de qr
    implementation("com.google.android.gms:play-services-code-scanner:16.1.0")

    // Añadir esta línea para tener acceso a Button y Text
    //implementation("androidx.compose.material:material:1.5.1")
    //implementation(libs.androidx.datastore.preferences.v100)
    //implementation(libs.hilt.android)
    //implementation(libs.androidx.material)
    //implementation(libs.androidx.datastore.preferences)
    implementation (libs.jbcrypt)
    implementation (libs.androidx.security.crypto)
    implementation (libs.retrofit)
    implementation (libs.converter.gson)
    implementation (libs.kotlinx.coroutines.android)
    implementation (libs.androidx.lifecycle.viewmodel.compose)

    // Dependencias existentes
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation("androidx.compose.material:material:1.5.1")
    implementation("androidx.compose.ui:ui:1.5.1")

    implementation("androidx.navigation:navigation-compose:2.5.3")
    implementation(libs.androidx.appcompat)
    implementation ("androidx.compose.material:material-icons-extended:1.5.1")
    implementation ("androidx.compose.material3:material3:1.1.0")
    implementation ("androidx.compose.ui:ui-tooling-preview:1.5.1")
    // Jetpack Compose Foundation
    implementation ("androidx.compose.foundation:foundation:1.5.1")
    implementation(libs.googleid)


    // Testing dependencies
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}