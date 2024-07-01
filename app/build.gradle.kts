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
    implementation(libs.media3.exoplayer)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
    implementation(libs.retrofit.v2110)                 // Call api library
    implementation(libs.okhttp)                         // HTTP request library
    implementation(libs.converter.gson)                 // Json converter library
    implementation(libs.logging.interceptor.v4120)      // Log api request
    compileOnly(libs.dagger)                            // Dependency Injection
    annotationProcessor(libs.dagger.compiler)
    implementation(libs.dagger.android.support)
    compileOnly(libs.projectlombok.lombok)              // Auto getter setter constructor...
    annotationProcessor(libs.projectlombok.lombok)
    implementation(libs.glide)                          // Thêm thư viện glide để hiển thị ảnh từ Internet
    annotationProcessor(libs.glide.compiler)
    implementation(libs.media3.exoplayer.hls) //  HLS player
    implementation(libs.media3.exoplayer)
    implementation(libs.palette)





}