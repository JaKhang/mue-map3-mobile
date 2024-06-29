plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.mue.music"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.mue.music"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
}

dependencies {
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
    implementation(libs.retrofit.v2110)
    implementation(libs.converter.gson)
    implementation(libs.dagger.android.support)
    compileOnly(libs.dagger)
    annotationProcessor(libs.dagger.compiler)
    compileOnly(libs.projectlombok.lombok)
    annotationProcessor(libs.projectlombok.lombok)
    implementation(libs.okhttp)
    implementation(libs.logging.interceptor.v4120)
    implementation(libs.glide)               // Thêm thư viện glide để hiển thị ảnh từ Internet
    annotationProcessor(libs.glide.compiler)






}