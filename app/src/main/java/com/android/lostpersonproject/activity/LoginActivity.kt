package com.android.lostpersonproject.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.android.lostpersonproject.R
import com.android.lostpersonproject.base.BaseActivity
import com.android.lostpersonproject.tool.NetTools.net
import com.android.lostpersonproject.url.Urls
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseActivity() , View.OnClickListener {
    //
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        btn_login.setOnClickListener(this)

    }

    override fun onClick(p0: View?) {
        when (p0!!.id) {
            btn_login.id -> {
//                net_login()
                val intent = Intent(this@LoginActivity, MainActivity::class.java)
                startActivity(intent)
                finish()
            }

        }
    }

    private fun net_login() {
        val map = hashMapOf<String, String>()
        map["username"] = et_name.text.toString()
        map["password"] = et_pw.text.toString()

        net(map, Urls().auth_login, this) { response ->
            val intent = Intent(this@LoginActivity, MainActivity::class.java)
            intent.putExtra("data",response.data!!)
            startActivity(intent)
            finish()
        }
    }

}
