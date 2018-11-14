package com.android.lostpersonproject.activity

import android.content.Intent
import android.os.Bundle
import com.android.lostpersonproject.R
import com.android.lostpersonproject.adapter.LoggingAdapter
import com.android.lostpersonproject.base.BaseActivity
import com.android.lostpersonproject.bean.LoggingBean
import kotlinx.android.synthetic.main.activity_logging.*

class LoggingActivity : BaseActivity() {

    private var beans:ArrayList<LoggingBean>? = null
private var mAdapter:LoggingAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_logging)
        setTextTitle("工作记录")
        setLeftBtn(true)
        onClick()
        initView()
    }

    private fun initView() {
        tv1.text = "1"
        tv2.text = "2"

        setData()
    }

    private fun setData() {
        beans = ArrayList()

        var bean1 = LoggingBean("1","赵某某","XX街道XX社区XX小区","")
        var bean2 = LoggingBean("2","张某某","XX街道XX社区XX小区","")
        var bean3 = LoggingBean("3","王某某","XX街道XX社区XX小区","")
        var bean4 = LoggingBean("4","钱某某","XX街道XX社区XX小区","")
        var bean5 = LoggingBean("5","赵某某","XX街道XX社区XX小区","")
        var bean6 = LoggingBean("6","周某某","XX街道XX社区XX小区","")
        var bean7 = LoggingBean("7","吴某某","XX街道XX社区XX小区","")
        var bean8 = LoggingBean("8","郑某某","XX街道XX社区XX小区","")
        var bean9 = LoggingBean("9","冯某某","XX街道XX社区XX小区","")
        var bean10 = LoggingBean("10","鲁某某","XX街道XX社区XX小区","")

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

        mAdapter = LoggingAdapter(beans!!,LoggingActivity@this)
        listView.adapter = mAdapter

        listView.setOnItemClickListener { adapterView, view, i, l ->
            val intent = Intent(this@LoggingActivity, PersonDetailActivity::class.java)
            intent.putExtra("bean",beans!![i])
            startActivity(intent)        }

    }

    private fun onClick() {
        ll_1.setOnClickListener {
            ll_1.setBackgroundResource(R.drawable.background_btn3)
            ll_2.setBackgroundResource(R.drawable.shape_corner_gary)

            tv1.text = "1"
            tv2.text = "2"

        }
        ll_2.setOnClickListener {
            ll_2.setBackgroundResource(R.drawable.background_btn3)
            ll_1.setBackgroundResource(R.drawable.shape_corner_gary)

            tv1.text = "1"
            tv2.text = "2"
        }
    }
}
