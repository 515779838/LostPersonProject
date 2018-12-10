package com.yiqikeji.lostpersonproject.base

import android.app.ProgressDialog
import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import android.widget.TextView
import com.yiqikeji.lostpersonproject.R
import org.jetbrains.anko.imageResource

open class BaseActivity : AppCompatActivity() {

    var progressDialog: ProgressDialog? = null

    var pageIndex = 1
    var pageSize = 20

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    /**
     * 修改标题

     * @param title 标题名
     */
    protected fun setTextTitle(title: String) {
        (findViewById<TextView>(R.id.tv_toolsbar_right)).text = title
    }

    /**
     * 修改左按钮

     * @param flag 是否显示
     */
    protected fun setLeftBtn(flag: Boolean) = if (flag) {
        (findViewById<ImageView>(R.id.iv_toolsbar_left)).visibility = View.VISIBLE
        (findViewById<ImageView>(R.id.iv_toolsbar_left)).setOnClickListener {
            hideSoftInput()
            finish()
        }
    } else {
        (findViewById<ImageView>(R.id.iv_toolsbar_left)).visibility = View.GONE
    }

    open fun showProgressDialog() {
        showProgressDialog("加载中...")
    }

    open fun showProgressDialog(msg: String) {
        try {
            if (progressDialog == null) {
                progressDialog = ProgressDialog(this)
//                progressDialog!!.setIndeterminateDrawable(resources.getDrawable(R.drawable.progress_bgcolor))
            }
            if (progressDialog != null && !progressDialog!!.isShowing) {
                progressDialog!!.setCanceledOnTouchOutside(false)
                progressDialog!!.setMessage(msg)
                progressDialog!!.show()
            }
        } catch (e: Exception) {

        }
    }

    open fun dismissProgressDialog() {
        try {
            if (progressDialog != null && progressDialog!!.isShowing) {
                progressDialog!!.dismiss()
            }
        } catch (e: Exception) {

        }
    }

    /**
     * 收起键盘
     */
    protected fun hideSoftInput() {
        try {
            val mInputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            mInputMethodManager.hideSoftInputFromWindow(this.currentFocus!!.windowToken, 0)
        } catch (e: Exception) {

        }
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    fun dip2px(dpValue: Float): Int {
        val scale = resources.displayMetrics.density
        return (dpValue * scale + 0.5f).toInt()
    }

    /**
     * 返回当前程序版本名
     */
    fun getAppVersionName(): String {
        var versionName: String? = ""
        try {
            // ---get the package info---
            val pm = packageManager
            val pi = pm.getPackageInfo(packageName, 0)
            versionName = pi.versionName
            if (versionName == null || versionName.isEmpty()) {
                return ""
            }
        } catch (e: Exception) {
            Log.e("VersionInfo", "Exception", e)
        }

        return versionName!!
    }

    /**
     * 返回当前程序版本
     */
    fun getAppVersionCode(): String {
        var versionId = 0
        try {
            // ---get the package info---
            val pm = packageManager
            val pi = pm.getPackageInfo(packageName, 0)
            versionId = pi.versionCode
            if (versionId <= 0) {
                return ""
            }
        } catch (e: Exception) {
            Log.e("VersionInfo", "Exception", e)
        }

        return "$versionId"
    }

    /**
     * dp转换成px
     */
     fun dp2px( dpValue: Float): Int {
        val scale = resources.displayMetrics.density
        return (dpValue * scale + 0.5f).toInt()
    }

    /**
     * size 当前页面总共获取数据条数
     * msg 无数据提示语
     * resId 无数据提示图
     */
    fun setListToastView(size: Int, msg: String, resId: Int) {
        setListToastView(size, msg, resId, true)
    }

    /**
     * size 当前页面总共获取数据条数
     * msg 无数据提示语
     * resId 无数据提示图
     */
    fun setListToastView(size: Int, msg: String, resId: Int, isShow: Boolean) {
        if (isShow) {
            if (findViewById<View>(R.id.view_no_data) != null) {
                if (size == 0) {
                    // toast("暂无数据")
                    findViewById<View>(R.id.view_no_data).visibility = View.VISIBLE
                    findViewById<View>(R.id.view_no_data).setOnClickListener { }
                    findViewById<View>(R.id.view_no_data).findViewById<TextView>(R.id.textView).text = msg
                    findViewById<View>(R.id.view_no_data).findViewById<ImageView>(R.id.imageView).imageResource = resId
                } else {
                    if (pageIndex != 1) {
//                        toast("无更多数据")
                    }
                    findViewById<View>(R.id.view_no_data).visibility = View.GONE
                    findViewById<View>(R.id.view_no_data).findViewById<TextView>(R.id.textView).text = ""
                    findViewById<View>(R.id.view_no_data).findViewById<ImageView>(R.id.imageView).imageResource = android.R.color.transparent
                }
            }
        } else {
            if (findViewById<View>(R.id.view_no_data) != null) {
                findViewById<View>(R.id.view_no_data).visibility = View.GONE
                findViewById<View>(R.id.view_no_data).findViewById<TextView>(R.id.textView).text = ""
                findViewById<View>(R.id.view_no_data).findViewById<ImageView>(R.id.imageView).imageResource = android.R.color.transparent
            }
        }
    }
}
