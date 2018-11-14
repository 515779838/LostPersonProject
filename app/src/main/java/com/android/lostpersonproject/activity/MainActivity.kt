package com.android.lostpersonproject.activity

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.support.v4.content.FileProvider
import android.support.v7.app.AppCompatActivity
import com.android.lostpersonproject.R
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File

class MainActivity : AppCompatActivity() {

    private val icon = "上传地址"
    //相册请求码
    private val ALBUM_REQUEST_CODE = 1
    //相机请求码
    private val CAMERA_REQUEST_CODE = 2
    //剪裁请求码
    private val CROP_REQUEST_CODE = 3
    //调用照相机返回图片文件
    private var tempFile: File? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        onClick()


    }

    private fun onClick() {
        ll_camera.setOnClickListener {

            getPicFromCamera();//调用相机

//            startActivity(Intent(this@MainActivity, SearchActivity::class.java))
        }
        ll_note.setOnClickListener {
            startActivity(Intent(this@MainActivity, LoggingActivity::class.java))
        }
    }

    private fun getPicFromCamera() { //用于保存调用相机拍照后所生成的文件
        tempFile = File(Environment.getExternalStorageDirectory().path, System.currentTimeMillis().toString() + ".jpg")
        //跳转到调用系统相机
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        //判断版本
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            //如果在Android7.0以上,使用FileProvider获取Uri
            intent.flags = Intent.FLAG_GRANT_WRITE_URI_PERMISSION
            val contentUri = FileProvider.getUriForFile(this@MainActivity, "com.hansion.chosehead", tempFile!!)
            intent.putExtra(MediaStore.EXTRA_OUTPUT, contentUri)
        } else {
            //否则使用Uri.fromFile(file)方法获取Uri
            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(tempFile))
        }
        startActivityForResult(intent, CAMERA_REQUEST_CODE)
    }



}
