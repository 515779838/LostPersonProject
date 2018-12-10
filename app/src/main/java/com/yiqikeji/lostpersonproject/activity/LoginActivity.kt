package com.yiqikeji.lostpersonproject.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.yiqikeji.lostpersonproject.R
import com.yiqikeji.lostpersonproject.base.BaseActivity
import com.yiqikeji.lostpersonproject.bean.UserBean
import com.yiqikeji.lostpersonproject.constant.Constant
import com.yiqikeji.lostpersonproject.tool.NetTools.net
import com.yiqikeji.lostpersonproject.tool.SPTools
import com.yiqikeji.lostpersonproject.url.Urls
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.toast

/**
 * 登录
 */
class LoginActivity : BaseActivity() , View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
//        et_name.setText(SPTools[this@LoginActivity,Constant.USERNAME,""].toString())
        if (!et_name.text.isEmpty()){
            et_name.setSelection(et_name.text.length)
        }
        btn_login.setOnClickListener(this)

    }

    override fun onClick(p0: View?) {
        when (p0!!.id) {
            btn_login.id -> {
                if (et_name.text.toString().trim().isEmpty()){
                    toast("请输入用户名")
                }else if(et_pw.text.toString().trim().isEmpty()){
                    toast("请输入密码")
                }else{
                    net_login()
                }
            }

        }
    }

    private fun net_login() {
        val map = hashMapOf<String, String>()
        map["username"] = et_name.text.toString()
        map["password"] = et_pw.text.toString()

        net(map, Urls().auth_login, this) { response ->
            var userBean = Gson().fromJson(response.data, UserBean::class.java)

            SPTools.put(this@LoginActivity, Constant.TOKEN, ""+userBean.token)
            SPTools.put(this@LoginActivity, Constant.USERNAME, ""+et_name.text.toString())

            val intent = Intent(this@LoginActivity, MainActivity::class.java)
            intent.putExtra("userBean",userBean)
            startActivity(intent)
            finish()
        }
    }

}