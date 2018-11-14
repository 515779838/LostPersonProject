package com.android.lostpersonproject.activity

import android.app.Activity
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.AdapterView
import com.android.lostpersonproject.R
import com.android.lostpersonproject.adapter.SearchAdapter
import com.android.lostpersonproject.base.BaseActivity
import com.android.lostpersonproject.bean.SearchBean
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.activity_search.*
import java.io.File
import java.io.FileInputStream
import java.io.FileNotFoundException
import java.io.IOException

class SearchActivity : BaseActivity() {


    private var sdPath = ""
    private var picPath = ""
    private var beans:ArrayList<SearchBean>? = null
    private var mAdapter:SearchAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        initView()
    }

    private fun initView() {
         picPath = intent.getStringExtra("picPath")

        /**
         * 这种方法是通过内存卡的路径进行读取图片，所以的到的图片是拍摄的原图
         */
        var fis: FileInputStream? = null
        try {
            Log.e("sdPath2", picPath)
            //把图片转化为字节流
            fis = FileInputStream(picPath)
            //把流转化图片
            val bitmap = BitmapFactory.decodeStream(fis)
            iv_head.setImageBitmap(bitmap)

        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        } finally {
            try {
                fis!!.close()//关闭流
            } catch (e: IOException) {
                e.printStackTrace()
            }

        }


        iv_toolsbar_back.setOnClickListener {
            finish()
        }


        iv_head.setOnClickListener {
            ll_title.setBackgroundResource(R.drawable.background_title5)
            ll_failed.visibility = View.VISIBLE
            ll_camera.visibility = View.VISIBLE

        }


        val appDir = File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).toString() + "/image")
        if (!appDir.exists()) {
            appDir.mkdirs()
        }
        sdPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).toString() + "/image"
        picPath = sdPath + "/" + "temp.png"
        Log.e("sdPath1", sdPath)


        ll_camera.setOnClickListener {
            val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)

            val uri = Uri.fromFile(File(picPath))
            //为拍摄的图片指定一个存储的路径
            cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, uri)
            startActivityForResult(cameraIntent, 0)

        }

        Glide.with(this@SearchActivity)
            .load("http://img5.duitang.com/uploads/item/201506/07/20150607110911_kY5cP.jpeg").apply(
                RequestOptions.bitmapTransform(CircleCrop())
            ).into(iv_head)


        beans = ArrayList()

        var bean1 = SearchBean("1","赵某某","90%","")
        var bean2 = SearchBean("2","张某某","90%","")
        var bean3 = SearchBean("3","王某某","90%","")
        var bean4 = SearchBean("4","钱某某","90%","")
        var bean5 = SearchBean("5","赵某某","90%","")
        var bean6 = SearchBean("6","周某某","90%","")
        var bean7 = SearchBean("7","吴某某","90%","")
        var bean8 = SearchBean("8","郑某某","90%","")
        var bean9 = SearchBean("9","冯某某","90%","")
        var bean10 = SearchBean("10","鲁某某","90%","")

        beans!!.add(bean1)
        beans!!.add(bean2)
        beans!!.add(bean3)
        beans!!.add(bean4)
        beans!!.add(bean5)
        beans!!.add(bean6)
        beans!!.add(bean7)
        beans!!.add(bean8)
        beans!!.add(bean9)
        beans!!.add(bean10)
        mAdapter = SearchAdapter(beans!!,SearchActivity@this)
        gridView.adapter = mAdapter

        gridView!!.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
            Log.e("zj","position = "+position)

            startActivity(Intent(this@SearchActivity,ConfirmPersonActivity::class.java))
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        Log.e("zj", "1111111")

        if (resultCode == Activity.RESULT_OK) {

            if (requestCode == 0) {
                Log.e("zj", "2222")


                /**
                 * 这种方法是通过内存卡的路径进行读取图片，所以的到的图片是拍摄的原图
                 */
                var fis: FileInputStream? = null
                try {
                    Log.e("sdPath2", picPath)
                    //把图片转化为字节流
                    fis = FileInputStream(picPath)
                    //把流转化图片
                    val bitmap = BitmapFactory.decodeStream(fis)
                    iv_head.setImageBitmap(bitmap)

                } catch (e: FileNotFoundException) {
                    e.printStackTrace()
                } finally {
                    try {
                        fis!!.close()//关闭流
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }

                }
            }
        }
    }
}
