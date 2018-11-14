package com.android.lostpersonproject.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.android.lostpersonproject.R
import com.android.lostpersonproject.base.BaseActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.activity_confirm_person.*

/**
 * 人员确认
 */
class ConfirmPersonActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_confirm_person)
        initView()
    }

    private fun initView() {
        iv_toolsbar_back.setOnClickListener {
            finish()
        }

        ll_confirm.setOnClickListener {
            finish()
        }

        Glide.with(this@ConfirmPersonActivity)
            .load("http://img5.duitang.com/uploads/item/201506/07/20150607110911_kY5cP.jpeg").apply(
                RequestOptions.bitmapTransform(CircleCrop())
            ).into(iv_head)

    }
}
