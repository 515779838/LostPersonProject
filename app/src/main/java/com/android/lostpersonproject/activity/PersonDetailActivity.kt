package com.android.lostpersonproject.activity

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import com.android.lostpersonproject.R
import com.android.lostpersonproject.adapter.GalleryAdapter
import com.android.lostpersonproject.base.BaseActivity
import com.android.lostpersonproject.bean.LoggingBean
import com.android.lostpersonproject.view.CustomDialog
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.activity_person_detail.*
import java.util.*



/**
 * 人员详情
 */
class PersonDetailActivity : BaseActivity() {

    private var bean: LoggingBean? = null
    private var mAdapter: GalleryAdapter? = null

    private var mDatas: List<Int>? = null

    private var builder: CustomDialog.Builder? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_person_detail)
        initView()
    }

    private fun initView() {
        iv_toolsbar_back.setOnClickListener {
            hideSoftInput()
            finish()
        }

        bean = intent.getSerializableExtra("bean") as LoggingBean?

//        ll_confirm.visibility = View.GONE
//        var parmas = ll_main.layoutParams as LinearLayout.LayoutParams
//        parmas.setMargins(dp2px(20f),dp2px(80f),dp2px(20f),dp2px(20f))

        ll_confirm.setOnClickListener {
            finish()
        }

        setData()
    }

    private fun setData() {

        Glide.with(this@PersonDetailActivity)
            .load("http://img5.duitang.com/uploads/item/201506/07/20150607110911_kY5cP.jpeg").apply(
                RequestOptions.bitmapTransform(CircleCrop())
            ).into(iv_head)

        mDatas = ArrayList(
            Arrays.asList(
                R.mipmap.ic_launcher,
                R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher,
                R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher
            )
        )

        ll_call.setOnClickListener {
            var mIntent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + "17386843212"));//跳转到拨号界面，同时传递电话号码
            startActivity(mIntent)
        }

        //设置布局管理器
        val linearLayoutManager = LinearLayoutManager(this)
        linearLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
        recyclerview_horizontal.setLayoutManager(linearLayoutManager)
        //设置适配器
        mAdapter = GalleryAdapter(this, mDatas)
        recyclerview_horizontal.setAdapter(mAdapter)
        mAdapter = GalleryAdapter(PersonDetailActivity@ this, mDatas)
        recyclerview_horizontal.adapter = mAdapter

        mAdapter!!.setOnItemClick { id, position ->

            Log.e("zj", "position = " + position)
            val view = LayoutInflater.from(this).inflate(R.layout.view_colse, null)
            var dialog: CustomDialog? = null
            builder = CustomDialog.Builder(this@PersonDetailActivity)
            builder!!.setContentView(view)
            builder!!.setCancle(true)
            var image = view.findViewById<View>(R.id.image) as ImageView

            var ll_close = view.findViewById<View>(R.id.ll_close) as LinearLayout

            var requestOptions = RequestOptions()
            requestOptions.error(R.mipmap.ic_launcher).placeholder(R.mipmap.ic_launcher)
            Glide.with(this@PersonDetailActivity)
                .load("http://img5.duitang.com/uploads/item/201506/07/20150607110911_kY5cP.jpeg").apply(
                    requestOptions
                ).into(image)


            ll_close.setOnClickListener {
                dialog!!.dismiss()
            }

            dialog = builder!!.create() as CustomDialog
            dialog!!.show()


        }
    }
}
