package com.android.lostpersonproject.activity

import android.app.Activity
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.os.Handler
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import com.android.lostpersonproject.R
import com.android.lostpersonproject.adapter.SearchAdapter
import com.android.lostpersonproject.base.BaseActivity
import com.android.lostpersonproject.bean.SearchBean
import com.android.lostpersonproject.constant.Constant
import com.android.lostpersonproject.tool.NetTools
import com.android.lostpersonproject.tool.NetTools.netFile
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.request.RequestOptions
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_search.*
import top.zibin.luban.Luban
import top.zibin.luban.OnCompressListener
import java.io.File
import java.io.FileInputStream
import java.io.FileNotFoundException
import java.io.IOException
import java.util.HashMap

class SearchActivity : BaseActivity() {

    private var picPath = ""
    private var imageName = ""
    private var mAdapter: SearchAdapter? = null

    private var mUri: Uri? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        initView()
    }

    private fun initView() {
        var picPath2 = intent.getStringExtra("picPath")
        imageName = intent.getStringExtra("imageName")

        Glide.with(this@SearchActivity).load(picPath2).apply(RequestOptions.bitmapTransform(CircleCrop())).into(iv_head)

        //上传文件（图片）时 先睡一会在上传
        Handler().postDelayed({

            CompressPhoto(imageName, picPath2)

        }, 500)

        iv_toolsbar_back.setOnClickListener {
            finish()
        }

        val appDir = File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).toString() + "/" + Constant.MKDIRNAME)
        if (!appDir.exists()) {
            appDir.mkdirs()
        }

        ll_camera.setOnClickListener {
            val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)

            mUri = Uri.fromFile(File(picPath))
            //为拍摄的图片指定一个存储的路径
            cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, mUri)
            startActivityForResult(cameraIntent, 0)

        }

    }

    override fun onResume() {
        super.onResume()
        picPath = Constant.SDPATH + "/" + System.currentTimeMillis() + ".jpg"
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK) {

            if (requestCode == 0) {
                // 刷新在系统相册中显示
                try {
                    MediaStore.Images.Media.insertImage(contentResolver,
                            MediaStore.Images.Media.getBitmap(this@SearchActivity.contentResolver, mUri),
                            resources.getString(R.string.app_name), "")

                    val intent = Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE)
                    intent.data = mUri
                    sendBroadcast(intent)
                } catch (e: IOException) {
                    e.printStackTrace()
                }
                val names = mUri!!.path.split("/".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
                imageName = names[names.size - 1]
                if (!imageName.toLowerCase().endsWith(".jpg") && !imageName.toLowerCase().endsWith(".png")) {
                    imageName = imageName + ".png"
                }

                Glide.with(this@SearchActivity).load(picPath).apply(RequestOptions.bitmapTransform(CircleCrop())).into(iv_head)

                CompressPhoto(imageName, picPath)
            }
        }
    }


    /**
     * 上传头像文件
     */
    private fun CompressPhoto(imageName: String, picPath: String) {
        var mName = ""

        Luban.with(this@SearchActivity)
                .load(picPath)
                .setTargetDir(Constant.SDPATH)
                //        .ignoreBy(100)                                  // 忽略不压缩图片的大小
                .setCompressListener(object : OnCompressListener {
                    override fun onSuccess(file: File?) {
                        // TODO 压缩成功后调用，返回压缩后的图片文件
                        Log.e("zj", "file name = " + file!!.name.toString())
                        mName = file!!.name.toString()

                        netFile(imageName, mName)
                    }

                    override fun onError(e: Throwable?) {
                        // TODO 当压缩过程出现问题时调用
                        Log.e("zj", "file e = " + e.toString())

                    }

                    override fun onStart() {
                        // TODO 压缩开始前调用，可以在方法内启动 loading UI
                    }

                }).launch()

    }

    private fun netFile(imageName: String, name: String) {
        val map = HashMap<String, File>()
        map["imageBase64"] = File(Constant.SDPATH , name)

        netFile(map, this) { response ->
            Log.e("zj", "netFile sdPath = " + Constant.SDPATH  + ",imageName = " + imageName + ",name = " + name)

            Log.e("zj", "netFile = " + response.data)
            var file1 = File(Constant.SDPATH , imageName)
            var file2 = File(Constant.SDPATH , name)
            file1.delete()
            file2.delete()
//
            var searchBean = Gson().fromJson(response.data, SearchBean::class.java)
//
            setData(searchBean)
        }
    }

    private fun setData(bean:SearchBean) {

        if (bean.flag == "0"){
            ll_title.setBackgroundResource(R.drawable.background_title5)
            ll_failed.visibility = View.VISIBLE
            ll_camera.visibility = View.VISIBLE
        }else{
            ll_title.setBackgroundResource(R.drawable.background_title4)
            ll_failed.visibility = View.GONE
            ll_camera.visibility = View.GONE
        }

        mAdapter = SearchAdapter(bean.ioScore,bean.result, SearchActivity@ this)
        gridView.adapter = mAdapter

        gridView!!.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
            var mIntent = Intent(this@SearchActivity, ConfirmPersonActivity::class.java)
            mIntent.putExtra("id", bean.result[position].customId)
            startActivity(mIntent)
        }
    }
}
