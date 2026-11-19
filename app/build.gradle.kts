plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "br.com.hjc.escalatrabalho"
    compileSdk = 35

    defaultConfig {
        applicationId = "br.com.hjc.escalatrabalho"
        minSdk = 26
        targetSdk = 35
        versionCode = 1
        versionName = "0.1.0"
    }
}

