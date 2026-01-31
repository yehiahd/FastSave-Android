package com.appizona.yehiahd.fastsaveexample

import android.app.Application
import com.appizona.yehiahd.fastsave.FastSave

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        FastSave.init(applicationContext)
    }
}
