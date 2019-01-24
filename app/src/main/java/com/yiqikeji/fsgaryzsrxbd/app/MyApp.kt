package com.yiqikeji.fsgaryzsrxbd.app

import android.app.Activity
import android.app.Application
import android.content.Context
import com.tencent.bugly.crashreport.CrashReport
import com.ycgis.pclient.CrashHandler


//import com.tencent.bugly.crashreport.CrashReport

class MyApp : Application() {
    companion object {
        // 初始化activity数组
        private var activities = ArrayList<Activity>()

        fun getActivies() = activities!!
        var context: Context? = null
    }

    override fun onCreate() {
        super.onCreate()
//         初始化bugly异常监听
//        CrashReport.initCrashReport(this)
        CrashReport.initCrashReport(applicationContext, "5d27b4f284", true);

//
//        val crashHandler = CrashHandler.getInstance()
//        crashHandler.init(this)
        context = applicationContext
    }

}