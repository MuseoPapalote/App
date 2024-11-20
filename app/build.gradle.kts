plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.example.papalote"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.papalote"
        minSdk = 24
        targetSdk = 34
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
    //Escaneo de qr
    implementation("com.google.android.gms:play-services-code-scanner:16.1.0")

    // Añadir esta línea para tener acceso a Button y Text
    implementation("androidx.compose.material:material:1.5.1")

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
    implementation("androidx.compose.foundation:foundation:1.5.1") // Manejo de gestos y layouts
    implementation("androidx.compose.ui:ui:1.5.1") // Componentes básicos de UI
    implementation("androidx.navigation:navigation-compose:2.5.3")
    implementation(libs.androidx.appcompat)
    implementation ("androidx.compose.material:material-icons-extended:1.5.1")
    implementation ("androidx.compose.material3:material3:1.1.0")
    implementation ("androidx.compose.ui:ui-tooling-preview:1.5.1")
    // Jetpack Compose Foundation
    implementation ("androidx.compose.foundation:foundation:1.5.1")



    // Testing dependencies
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}