package com.yiqikeji.fsgaryzsrxbd.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ListView
import com.yiqikeji.fsgaryzsrxbd.R
import com.yiqikeji.fsgaryzsrxbd.adapter.LoggingAdapter
import com.yiqikeji.fsgaryzsrxbd.base.BaseActivity
import com.yiqikeji.fsgaryzsrxbd.bean.LoggingBean
import com.yiqikeji.fsgaryzsrxbd.url.Urls
import com.google.gson.Gson
import com.handmark.pulltorefresh.library.PullToRefreshBase
import com.ycgis.pclient.PService
import com.yiqikeji.fsgaryzsrxbd.constant.Constant
import com.yiqikeji.fsgaryzsrxbd.tool.DataTools
import com.yiqikeji.fsgaryzsrxbd.tool.QueryNetTools
import com.yiqikeji.fsgaryzsrxbd.tool.SPTools
import kotlinx.android.synthetic.main.activity_logging.*
import java.util.ArrayList

/**
 * 工作记录
 */
class LoggingActivity : BaseActivity() {

    private var mAdapter: LoggingAdapter? = null

    private var type = "1"

    private var list = ArrayList<LoggingBean.ResultBean>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_logging)
        setTextTitle("工作记录")
        setLeftBtn(true)
        onClick()
//        SPTools.put(this,Constant.SESSIONID,"1111");

        net_populationList(type)
    }

    private fun net_populationList(typeId: String) {
//        val map = hashMapOf<String, String>()
//
//        map["typeId"] = typeId;//0失踪人员 1找到失踪人
//        NetTools.net(map, Urls().missingPopulationList, this) { response ->
//            Log.e("zj", "missingPopulationList = " + response.data)
//
//            var bean = Gson().fromJson(response.data, LoggingBean::class.java)
//
//            setData(bean, typeId)
//        }

        var condition = "typeId='" + typeId + "' and token = '" + SPTools[this@LoggingActivity, Constant.TOKEN, ""] + "'"
Log.e("zj","condition = "+condition)
        var json = DataTools.putQueryParams(this@LoggingActivity, Constant.LOGGING, condition, "typeId,token", "", "" + pageIndex)

        QueryNetTools.net(json, Urls().missingPopulationList, this) { response ->
            var bean = DataTools.getLoggingBean(response.result)
            setData(bean, typeId)
        }
    }

    private fun setData(bean: LoggingBean, typeId: String) {
        var msg = ""
        list.clear()
        if (bean != null) {

            if (bean.missingNum != null) {
                tv1.text = "" + bean.missingNum
            } else {
                tv1.text = "0"
            }

            if (bean.findNum != null) {
                tv2.text = "" + bean.findNum
            } else {
                tv2.text = "0"
            }

            if (typeId == "0") {
                msg = "暂无失踪人员"
            } else if (typeId == "1") {
                msg = "暂无已寻找人员"
            }
            Log.e("zj","list = "+list.size)
            list.addAll(bean.result)
            setListToastView(list.size,
                    msg,
                    R.mipmap.ic_no_data)

            mAdapter = LoggingAdapter(bean.result, LoggingActivity@ this)
            listView.setAdapter(mAdapter)
//            listView.adapter = mAdapter

            listView.mode = PullToRefreshBase.Mode.DISABLED
            listView.setOnRefreshListener(object : PullToRefreshBase.OnRefreshListener2<ListView> {
                override fun onPullDownToRefresh(refreshView: PullToRefreshBase<ListView>) {
                    listView.postDelayed({ listView.onRefreshComplete() }, 250)
                    listView.mode = PullToRefreshBase.Mode.BOTH
                    pageIndex = 0
                    list.clear()
                    net_populationList(type)

                }

                override fun onPullUpToRefresh(refreshView: PullToRefreshBase<ListView>) {
                    pageIndex++
                    net_populationList(type)

                }
            })

            listView.setOnItemClickListener { _, _, i, _ ->
                val intent = Intent(this@LoggingActivity, PersonDetailActivity::class.java)
                intent.putExtra("id", bean.result[i-1].id)
                intent.putExtra("type", type)
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
            type = "1"
            net_populationList(type)

        }
        ll_2.setOnClickListener {
            ll_2.setBackgroundResource(R.drawable.background_btn3)
            ll_1.setBackgroundResource(R.drawable.shape_corner_gary)
            type = "0"
            net_populationList(type)

        }
    }
}
