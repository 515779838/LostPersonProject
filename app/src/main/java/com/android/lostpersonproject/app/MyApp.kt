package com.android.lostpersonproject.app

import android.app.Activity
import android.app.Application
import android.content.Context
//import com.tencent.bugly.crashreport.CrashReport

/**
 * Created by Administrator on 2017/10/30 0030.
 */
class MyApp : Application() {
    companion object {
        // 初始化activity数组
        private var  activities = ArrayList<Activity>()
        fun getActivies() = activities!!
        var context: Context? = null
    }

    override fun onCreate() {
        super.onCreate()
        // 初始化bugly异常监听
//        CrashReport.initCrashReport(this)
        context = applicationContext
    }

}