package com.android.lostpersonproject.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.android.lostpersonproject.R
import com.android.lostpersonproject.base.BaseActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseActivity() , View.OnClickListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        btn_login.setOnClickListener(this)

    }

    override fun onClick(p0: View?) {
        when (p0!!.id) {
            btn_login.id -> {
                val intent = Intent(this@LoginActivity, Main2Activity::class.java)
                startActivity(intent)
                finish()
            }

        }
    }
}
