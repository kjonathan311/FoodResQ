package com.capstone.foodresq.ui

import android.app.Application
import android.content.res.Configuration
import android.os.Build
import androidx.appcompat.app.AppCompatDelegate

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        // Force light mode
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        // If you are using the latest Android version (Android 10 and above)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val config = resources.configuration
            config.uiMode = config.uiMode and Configuration.UI_MODE_NIGHT_MASK.inv()
            resources.updateConfiguration(config, resources.displayMetrics)
        }
    }
}
