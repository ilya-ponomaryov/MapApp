package com.example.mapapp.common.application

import android.app.Application
import com.example.mapapp.BuildConfig
import com.yandex.mapkit.MapKitFactory

class MapApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        MapKitFactory.setApiKey(BuildConfig.YANDEX_MAP_API_KEY)
    }
}