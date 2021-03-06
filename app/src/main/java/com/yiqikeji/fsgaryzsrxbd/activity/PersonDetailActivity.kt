package com.yiqikeji.fsgaryzsrxbd.activity

import android.app.AlertDialog
import android.content.Intent
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import com.yiqikeji.fsgaryzsrxbd.R
import com.yiqikeji.fsgaryzsrxbd.adapter.GalleryAdapter
import com.yiqikeji.fsgaryzsrxbd.base.BaseActivity
import com.yiqikeji.fsgaryzsrxbd.bean.PersonDetailBean
import com.yiqikeji.fsgaryzsrxbd.url.Urls
import com.yiqikeji.fsgaryzsrxbd.view.CustomDialog
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.request.RequestOptions
import com.google.gson.Gson
import com.ycgis.pclient.PService
import com.yiqikeji.fsgaryzsrxbd.bean.OperateRequestBean
import com.yiqikeji.fsgaryzsrxbd.bean.OperateResponseBean
import com.yiqikeji.fsgaryzsrxbd.constant.Constant
import com.yiqikeji.fsgaryzsrxbd.tool.*
import kotlinx.android.synthetic.main.activity_person_detail.*
import org.jetbrains.anko.toast
import java.util.ArrayList

/**
 * 人员详情
 */
class PersonDetailActivity : BaseActivity() {

    private var mAdapter: GalleryAdapter? = null

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
        var id = intent.getStringExtra("id")
        var type = intent.getStringExtra("type")
//        ll_confirm.visibility = View.GONE
//        var parmas = ll_main.layoutParams as LinearLayout.LayoutParams
//        parmas.setMargins(dp2px(20f),dp2px(80f),dp2px(20f),dp2px(20f))

        if (type == "0"){
            ll_confirm.visibility = View.GONE
            et_remark.isFocusable = false
            et_remark.isClickable = false
        }else{
            ll_confirm.visibility = View.VISIBLE
            et_remark.isFocusable = true
            et_remark.isClickable = true

        }

        ll_confirm.setOnClickListener {

            var builder = AlertDialog.Builder(this@PersonDetailActivity);

            builder.setTitle("提示");
            builder.setMessage("确定修改此人备注？");

            builder.setPositiveButton("确定") { dialog, which ->
//                SPTools.put(this,Constant.SESSIONID,"1111");

                net_upRemarks(id)
                hideSoftInput()
                dialog.dismiss()
                finish()
            }

            builder.setNegativeButton("取消") { dialog, which ->
                dialog.dismiss()
            }

            builder.show()

        }

        net_personneletails(id)
    }

    private fun net_personneletails(baseId: String) {

        var condition = "baesId = '" + baseId + "' and token = '" + SPTools[this@PersonDetailActivity, Constant.TOKEN, ""] + "'"
        var json = DataTools.putQueryParams(this@PersonDetailActivity, Constant.PERSONDETAIL, condition, "typeId,token", "","1")

        QueryNetTools.net(json, Urls().personneletails, this) { response ->
            Log.e("zj", "personneletails ===" + response.result.toString())
            var bean = DataTools.getPersonDetailBean(response.result)
            setData(bean)
        }
    }

    private fun setData(bean: PersonDetailBean) {

        Glide.with(this@PersonDetailActivity)
                .load(StrTools.getDrawable(this@PersonDetailActivity,bean.portrait_nav)).apply(
                        RequestOptions.bitmapTransform(CircleCrop())
                ).into(iv_head)

        tv_name.text = bean.name
        tv_phone.text = bean.mePhone
        tv_cardNum.text = bean.cardId
        tv_address.text = bean.address
        tv_jhr1.text = bean.guardianOne
        tv_relationship.text = bean.relationOne
        tv_cardNum1.text = bean.guardianOneCardId
        tv_address1.text = bean.guardianOneAddress
        tv_phone1.text = bean.guardianOneTel
        et_remark.setText(bean.remarks)

        tv_jhr2.text = bean.guardianTwo
        tv_relationship2.text = bean.relationTwo
        tv_cardNum2.text = bean.guardianTwoCardId
        tv_address2.text = bean.guardianTwoAddress
        tv_phone2.text = bean.guardianTwoTel

        tv_policeStationId.text = bean.policeStationName
        tv_localPoliceStationId.text = bean.localPoliceStationName
        tv_responsibilityAreaId.text = bean.responsibilityAreaName

        if (et_remark.text.isNotEmpty()) {
            et_remark.setSelection(et_remark.text.length)
        }

        ll_call.setOnClickListener {
            var mIntent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + bean.guardianOneTel));//跳转到拨号界面，同时传递电话号码
            startActivity(mIntent)
        }

        ll_call2.setOnClickListener {
            var mIntent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + bean.guardianTwoTel));
            startActivity(mIntent)
        }
        if (bean != null && bean.photos.isEmpty()) {
            recyclerview_horizontal.visibility = View.GONE
        } else {
            recyclerview_horizontal.visibility = View.VISIBLE
        }

        //设置布局管理器
        val linearLayoutManager = LinearLayoutManager(this)
        linearLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
        recyclerview_horizontal.layoutManager = linearLayoutManager
        //设置适配器
        mAdapter = GalleryAdapter(this, bean.photos)
        recyclerview_horizontal.setAdapter(mAdapter)
        recyclerview_horizontal.adapter = mAdapter

        mAdapter!!.setOnItemClick { id, position ->

            Log.e("zj", "position = $position")
            val view = LayoutInflater.from(this).inflate(R.layout.view_colse, null)
            var dialog: CustomDialog? = null
            builder = CustomDialog.Builder(this@PersonDetailActivity)
            builder!!.setContentView(view)
            builder!!.setCancle(true)
            var image = view.findViewById<View>(R.id.image) as ImageView

            var ll_close = view.findViewById<View>(R.id.ll_close) as LinearLayout

            var requestOptions = RequestOptions()
            requestOptions.error(R.mipmap.ic_error).placeholder(R.mipmap.ic_default)
            Glide.with(this@PersonDetailActivity)
                    .load(StrTools.getDrawable(this@PersonDetailActivity,bean.photos[position].portrait)).apply(
                            requestOptions
                    ).into(image)


            ll_close.setOnClickListener {
                dialog!!.dismiss()
            }

            dialog = builder!!.create() as CustomDialog
            dialog!!.show()
        }
    }

    private fun net_upRemarks(baseId: String) {

        val operations = ArrayList<OperateRequestBean.Params.Data.Operation>()
        val operation = OperateRequestBean.Params.Data.Operation()

        var condition = "baesId = '" + baseId + "' and remarks = '" + et_remark.text.trim().toString() + "' and token = '" + SPTools[this@PersonDetailActivity, Constant.TOKEN, ""] + "'"
        operation.setOperationId(StrTools.getRandomString(32))
        operation.setCondition(condition)
        operation.setDataObjId(Constant.DataObjId)

        val operationDataArrayList = ArrayList<OperateRequestBean.Params.Data.Operation.OperationData>()
        val operationData = OperateRequestBean.Params.Data.Operation.OperationData()
        val fieldValues = ArrayList<OperateRequestBean.Params.Data.Operation.OperationData.FieldValues>()
        val fieldValues1 = OperateRequestBean.Params.Data.Operation.OperationData.FieldValues()
        fieldValues1.setField("remarks")
        fieldValues1.setValue("" + et_remark.text.trim().toString())
        fieldValues.add(fieldValues1)
        operationData.setFieldValues(fieldValues)
        operationDataArrayList.add(operationData)
        operation.setData(operationDataArrayList)

        operations.add(operation)
        val json = DataTools.putOperateParams(this@PersonDetailActivity, Constant.UPREMARKS, operations)


        OperateNetTools.net(json, Urls.upRemarks, this) { response ->
            Log.e("zj", "upRemarks ===" + response.result.toString())
            var responseBean = Gson().fromJson(response.result, OperateResponseBean.Result::class.java)

            toast("" + responseBean.data[0].operationMsg)
        }
    }
}
