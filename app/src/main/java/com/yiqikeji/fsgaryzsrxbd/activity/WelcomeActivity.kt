package com.yiqikeji.fsgaryzsrxbd.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import com.yiqikeji.fsgaryzsrxbd.R
import com.yiqikeji.fsgaryzsrxbd.base.BaseActivity
import com.yiqikeji.fsgaryzsrxbd.constant.Constant
import com.yiqikeji.fsgaryzsrxbd.tool.FileTools
import com.yiqikeji.fsgaryzsrxbd.tool.SPTools

class WelcomeActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        Handler().postDelayed({

            if ("" != SPTools[this@WelcomeActivity,Constant.TOKEN,""]) {
                val intent = Intent(this@WelcomeActivity, MainActivity::class.java)
                startActivity(intent)
                finish()
            }else{
                if (FileTools.deleteDirectory(Constant.SDPATH)) {
                    val intent = Intent(this@WelcomeActivity, LoginActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }



        }, 500)
    }

}
