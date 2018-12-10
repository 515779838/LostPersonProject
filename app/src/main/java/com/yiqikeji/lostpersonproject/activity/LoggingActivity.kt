package com.yiqikeji.lostpersonproject.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.yiqikeji.lostpersonproject.R
import com.yiqikeji.lostpersonproject.adapter.LoggingAdapter
import com.yiqikeji.lostpersonproject.base.BaseActivity
import com.yiqikeji.lostpersonproject.bean.LoggingBean
import com.yiqikeji.lostpersonproject.tool.NetTools
import com.yiqikeji.lostpersonproject.url.Urls
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_logging.*

/**
 * 工作记录
 */
class LoggingActivity : BaseActivity() {

    private var mAdapter: LoggingAdapter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_logging)
        setTextTitle("工作记录")
        setLeftBtn(true)
        onClick()

        net_populationList("1")
    }

    private fun net_populationList(typeId: String) {
        val map = hashMapOf<String, String>()

        map["typeId"] = typeId;//0失踪人员 1找到失踪人
        NetTools.net(map, Urls().missingPopulationList, this) { response ->
            Log.e("zj", "missingPopulationList = " + response.data)

            var bean = Gson().fromJson(response.data, LoggingBean::class.java)

            setData(bean, typeId)
        }
    }

    private fun setData(bean: LoggingBean, typeId: String) {
        var msg = ""

        if (bean != null) {
            tv1.text = "" + bean.missingNum
            tv2.text = "" + bean.findNum

            if (typeId == "0") {
                msg = "暂无失踪人员"
            } else if (typeId == "1") {
                msg = "暂无已寻找人员"
            }
            setListToastView(bean.result.size,
                    msg,
                    R.mipmap.ic_no_data)

            mAdapter = LoggingAdapter(bean.result, LoggingActivity@ this)
            listView.adapter = mAdapter

            listView.setOnItemClickListener { _, _, i, _ ->
                val intent = Intent(this@LoggingActivity, PersonDetailActivity::class.java)
                intent.putExtra("id", bean.result[i].id)
                startActivity(intent)
            }
        } else {
            setListToastView(0,
                    msg,
                    R.mipmap.ic_no_data)
        }


    }

    private fun onClick() {
        ll_1.setOnClickListener {
            ll_1.setBackgroundResource(R.drawable.background_btn3)
            ll_2.setBackgroundResource(R.drawable.shape_corner_gary)

            net_populationList("1")

        }
        ll_2.setOnClickListener {
            ll_2.setBackgroundResource(R.drawable.background_btn3)
            ll_1.setBackgroundResource(R.drawable.shape_corner_gary)

            net_populationList("0")

        }
    }
}
