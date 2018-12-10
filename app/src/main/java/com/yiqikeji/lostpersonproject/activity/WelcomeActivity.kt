package com.yiqikeji.lostpersonproject.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import com.yiqikeji.lostpersonproject.R
import com.yiqikeji.lostpersonproject.constant.Constant
import com.yiqikeji.lostpersonproject.tool.FileTools

class WelcomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        Handler().postDelayed({

            if (FileTools.deleteDirectory(Constant.SDPATH)) {
                val intent = Intent(this@WelcomeActivity, LoginActivity::class.java)
                startActivity(intent)
                finish()
            }

        }, 500)
    }

}
