package com.carden.wanandroid_master

import android.app.Application
import com.tencent.bugly.crashreport.CrashReport

class App:Application() {
    override fun onCreate() {
        super.onCreate()
        CrashReport.initCrashReport(this,"00baa5cba5",true)
    }
}