package com.yiqikeji.fsgaryzsrxbd.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import com.yiqikeji.fsgaryzsrxbd.R
import com.yiqikeji.fsgaryzsrxbd.base.BaseActivity
import com.yiqikeji.fsgaryzsrxbd.constant.Constant
import com.yiqikeji.fsgaryzsrxbd.url.Urls
import com.google.gson.Gson
import com.tencent.bugly.crashreport.CrashReport
import com.ycgis.pclient.PService
import com.ycgis.pclient.PService.addAppLog
import com.yiqikeji.fsgaryzsrxbd.bean.*
import com.yiqikeji.fsgaryzsrxbd.tool.*
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.toast

/**
 * 登录
 */
class LoginActivity : BaseActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        et_name.setText(SPTools[this@LoginActivity, Constant.USERNAME, ""].toString())
        if (!et_name.text.isEmpty()) {
            et_name.setSelection(et_name.text.length)
        }
        btn_login.setOnClickListener(this)
        CrashReport.testJavaCrash();
    }

    override fun onClick(p0: View?) {
        when (p0!!.id) {
            btn_login.id -> {
                if (et_name.text.toString().trim().isEmpty()) {
                    toast("请输入用户名")
                } else if (et_pw.text.toString().trim().isEmpty()) {
                    toast("请输入密码")
                } else {
                    net_connect()
//                    net_login()
                }
            }

        }
    }

    private fun net_connect() {
        ConnectNetTools.net(DataTools.getConnectJson(this),Urls().connect,this@LoginActivity, {
            Log.e("zj", "response ===" + it.result.toString())
            val resultBean = Gson().fromJson<ConnectResponseBean.Result>(it.result, ConnectResponseBean.Result::class.java)

            if (resultBean != null && resultBean.data != null && resultBean.data.sessionId != null) {
                SPTools.put(this@LoginActivity, Constant.SESSIONID, "" + resultBean.data.sessionId)
                net_login()
            } else {
                toast("sessionId为空")
            }
        },"正在加载...",true,true)


//        //现调用connect接口 获取sessionId
//        ConnectNetTools.net(DataTools.getConnectJson(this), Urls().connect, this) { response ->
//            Log.e("zj", "response ===" + response.result.toString())
//            val resultBean = Gson().fromJson<ConnectResponseBean.Result>(response.result, ConnectResponseBean.Result::class.java)
//
//            if (resultBean != null && resultBean.data != null && resultBean.data.sessionId != null) {
//                SPTools.put(this@LoginActivity, Constant.SESSIONID, "" + resultBean.data.sessionId)
//                net_login()
//            } else {
//                toast("sessionId为空")
//            }
//        }
    }

    private fun net_login() {

        var condition = "username='" + et_name.text.toString() + "' and password = '" + et_pw.text.toString() + "'"
        var json = DataTools.putQueryParams(this@LoginActivity, Constant.LOGINDO, condition, "username,password", "","1")

//        QueryNetTools.net(json, Urls().auth_login, this) { response ->
//
//
////            addAppLog(this@LoginActivity,"登录",resources.getString(R.string.app_name), 1);
//            var userBean = DataTools.getUserBean(response.result)
//
//
//            SPTools.put(this@LoginActivity, Constant.TOKEN, "" + userBean.token)
//            SPTools.put(this@LoginActivity, Constant.POLICEID, "" + userBean.policeId)
//            SPTools.put(this@LoginActivity, Constant.PCS, "" + userBean.localPoliceStationName)
//            SPTools.put(this@LoginActivity, Constant.POLICENAME, "" + userBean.policeName)
//            SPTools.put(this@LoginActivity, Constant.FJ, "" + userBean.policeStationName)
//            SPTools.put(this@LoginActivity, Constant.ZRQ, "" + userBean.responsibilityAreaName)
//
//            SPTools.put(this@LoginActivity, Constant.USERNAME, "" + et_name.text.toString())
//
//            startActivity(Intent(this@LoginActivity, MainActivity::class.java))
//            finish()
//        }

        QueryNetTools.net(json,Urls().auth_login,this@LoginActivity, {
            var userBean = DataTools.getUserBean(it.result)


            SPTools.put(this@LoginActivity, Constant.TOKEN, "" + userBean.token)
            SPTools.put(this@LoginActivity, Constant.POLICEID, "" + userBean.policeId)
            SPTools.put(this@LoginActivity, Constant.PCS, "" + userBean.localPoliceStationName)
            SPTools.put(this@LoginActivity, Constant.POLICENAME, "" + userBean.policeName)
            SPTools.put(this@LoginActivity, Constant.FJ, "" + userBean.policeStationName)
            SPTools.put(this@LoginActivity, Constant.ZRQ, "" + userBean.responsibilityAreaName)

            SPTools.put(this@LoginActivity, Constant.USERNAME, "" + et_name.text.toString())

            startActivity(Intent(this@LoginActivity, MainActivity::class.java))
            finish()
        },"正在加载...",false,true)
    }
}
