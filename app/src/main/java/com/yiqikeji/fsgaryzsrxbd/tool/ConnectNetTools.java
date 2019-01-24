package com.yiqikeji.fsgaryzsrxbd.tool;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.yiqikeji.fsgaryzsrxbd.activity.LoginActivity;
import com.yiqikeji.fsgaryzsrxbd.app.MyApp;
import com.yiqikeji.fsgaryzsrxbd.base.BaseActivity;
import com.yiqikeji.fsgaryzsrxbd.bean.BaseBean;
import com.yiqikeji.fsgaryzsrxbd.bean.ConnectResponseBean;
import com.yiqikeji.fsgaryzsrxbd.bean.OperateResponseBean;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.Callback;
import com.zhy.http.okhttp.request.RequestCall;

import org.json.JSONObject;

import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.Response;

public class ConnectNetTools {

    public static void net(String json, String url, final Activity context, final MyCallBack myCallBack) {
        net(json, url, context, myCallBack, "正在加载...");
    }

    public static void net(String json, String url, final Activity context, final MyCallBack myCallBack, final String msg) {
        net(json, url, context, myCallBack, msg, false, true);
    }

    public static void net(String json, final String url, final Activity context, final MyCallBack myCallBack, final String msg, final boolean isShow, final boolean isDismiss) {
        // 网络请求
        RequestCall call = OkHttpUtils.postString().url(url)
                .mediaType(MediaType.parse("application/json"))
                .content(json)
                .build();

        call.connTimeOut(10000000L);
        call.readTimeOut(10000000L);

        call.execute(new Callback<BaseBean>() {
            @Override
            public void onBefore(Request request, int id) {
                if (isShow) {
                    Log.e("zj","1 ===="+System.currentTimeMillis());
                    ((BaseActivity) context).showProgressDialog(msg);
                }
            }

            @Override
            public BaseBean parseNetworkResponse(Response response, int id) throws Exception {
                String json = response.body().string();
                Log.i("cyf7", "url : " + url);
                Log.e("cyf7", "response : " + json);
                JSONObject jsonObject = new JSONObject(json);
                BaseBean bean = new BaseBean();
                bean.setId(jsonObject.optString("id"));
                bean.setJsonrpc(jsonObject.optString("jsonrpc"));
                bean.setResult(jsonObject.optString("result"));
                bean.setError(jsonObject.optString("error"));
                return bean;
            }

            @Override
            public void onError(Call call, Exception e, int id) {
                // 无数据布局隐藏(后期可做网络错误显示)
                ((BaseActivity) context).setListToastView(0, "", 0, false);
                if (e.getClass().getSimpleName().equals("ConnectException")) {
                    // 无法连接网络
                    Toast.makeText(context, "无法连接服务器", Toast.LENGTH_SHORT).show();
                } else if (e.getClass().getSimpleName().equals("SocketTimeoutException")) {
                    // 网络连接超时
                    Toast.makeText(context, "当前网络不给力，请检查网络", Toast.LENGTH_SHORT).show();
                } else {
                    // 其它异常
                    Toast.makeText(context, "网络错误", Toast.LENGTH_SHORT).show();
                    Log.e("Exception gson：", e.toString());
                }
                ((BaseActivity) context).dismissProgressDialog();
            }

            @Override
            public void onResponse(BaseBean baseBean, int id) {
                // 无数据布局隐藏
                if (baseBean != null) {
                    if (baseBean.getId() != null) {
                        ConnectResponseBean.Result resultBean = new Gson().fromJson(baseBean.getResult(), ConnectResponseBean.Result.class);
                        if ("1".equals(resultBean.getCode())) {
                            if (isDismiss) {
                                Log.e("zj","2 ===="+System.currentTimeMillis());

                                ((BaseActivity) context).dismissProgressDialog();
                            }
                            if (myCallBack != null) {
                                myCallBack.getData(baseBean);
                            }

                        } else {
                            Log.e("zj","3 ===="+System.currentTimeMillis());
                            Toast.makeText(context, resultBean.getMsg(), Toast.LENGTH_SHORT).show();
                            ((BaseActivity) context).dismissProgressDialog();
                        }
                    } else {
                        if (isDismiss) {
                            Log.e("zj","4 ===="+System.currentTimeMillis());
                            ((BaseActivity) context).dismissProgressDialog();
                        }
                        ConnectResponseBean.Error errorBean = new Gson().fromJson(baseBean.getError(), ConnectResponseBean.Error.class);
                        if (errorBean != null) {
                            Toast.makeText(context, "错误信息： code:" + errorBean.code + "，message=" + errorBean.message, Toast.LENGTH_SHORT).show();
                        }
                    }

                } else {
                    Toast.makeText(context, "返回数据异常", Toast.LENGTH_SHORT).show();
                    ((BaseActivity) context).dismissProgressDialog();
                }
            }

            @Override
            public void onAfter(int id) {

            }

        });
    }


    public interface MyCallBack {
        void getData(BaseBean response);
    }
}
