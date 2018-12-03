package com.android.lostpersonproject.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import com.android.lostpersonproject.R
import com.android.lostpersonproject.constant.Constant
import com.android.lostpersonproject.tool.FileTools

class WelcomeActivity : AppCompatActivity() {

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_welcome)

            Handler().postDelayed({

                if ( FileTools.deleteDirectory(Constant.SDPATH)){
                    val intent = Intent(this@WelcomeActivity, LoginActivity::class.java)
                    startActivity(intent)
                    finish()
                }

            }, 500)
        }

}
