package com.example.recycleview.app

import android.app.Application
import android.content.Context
import android.content.SharedPreferences





class MyApp : Application() {
    override fun onCreate() {
        instance = this
        super.onCreate()
    }
    fun putDouble(edit: SharedPreferences.Editor, key: String?, value: Double): SharedPreferences.Editor? {
        return edit.putLong(key, java.lang.Double.doubleToRawLongBits(value))
    }

    fun getDouble(prefs: SharedPreferences, key: String?, defaultValue: Double): Double {
        return java.lang.Double.longBitsToDouble(
            prefs.getLong(
                key,
                java.lang.Double.doubleToLongBits(defaultValue)
            )
        )
    }

    companion object {
        var instance: MyApp? = null
            private set
        val context: Context?
            get() = instance
    }
}