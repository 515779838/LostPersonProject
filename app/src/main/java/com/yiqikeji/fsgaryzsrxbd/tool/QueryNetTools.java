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
import com.yiqikeji.fsgaryzsrxbd.bean.QueryRequestBean;
import com.yiqikeji.fsgaryzsrxbd.bean.QueryResponseBean;
import com.yiqikeji.fsgaryzsrxbd.constant.Constant;
import com.yiqikeji.fsgaryzsrxbd.url.Urls;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.Callback;
import com.zhy.http.okhttp.request.RequestCall;

import org.json.JSONObject;

import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.Response;


public class QueryNetTools {

    public static void net(String json, String url, final Activity context, final MyCallBack myCallBack) {
        net(json, url, context, myCallBack, "正在加载...");
    }

    public static void net(String json, String url, final Activity context, final MyCallBack myCallBack, final String msg) {
        net(json, url, context, myCallBack, msg, true, true);
    }

    public static void net(final String json, final String url, final Activity context, final MyCallBack myCallBack, final String msg, final boolean isShow, final boolean isDismiss) {

        Log.e("zj", "url = " + url);
//        FileTools.saveFile(json,4);

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
                    ((BaseActivity) context).showProgressDialog(msg);
                }
            }

            @Override
            public BaseBean parseNetworkResponse(Response response, int id) throws Exception {
                String json = response.body().string();
                Log.i("zj", "url : " + url);
                Log.e("zj", "response : " + json);
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
                    Log.e("zj", "Exception  = " + e.toString());
                    Log.e("zj", "Exception  = " + e.getClass().getSimpleName());

                    // 其它异常
                    Toast.makeText(context, "网络错误", Toast.LENGTH_SHORT).show();
                }
                ((BaseActivity) context).dismissProgressDialog();
            }

            @Override
            public void onResponse(BaseBean baseBean, int id) {
                // 无数据布局隐藏
//                ((BaseActivity) context).setListToastView(0, "", 0, false);
                if (baseBean != null) {
                    if (!baseBean.getId().equals("null")) {
                        QueryResponseBean.Result resultBean = new Gson().fromJson(baseBean.getResult(), QueryResponseBean.Result.class);
                        if ("token失效".equals(resultBean.getMsg())) {
                            // 登录信息失效
                            SPTools.INSTANCE.put(context, Constant.TOKEN, "");

                            Toast.makeText(context, "账号已被登出", Toast.LENGTH_SHORT).show();

                            for (int i = 0; i < MyApp.Companion.getActivies().size(); i++) {
                                MyApp.Companion.getActivies().get(i).finish();
                            }
                            context.startActivity(new Intent(context, LoginActivity.class));
                        } else {
                            if (resultBean.getData() == null) {
                                Log.e("zj","11111");
                                Toast.makeText(context, resultBean.getMsg(), Toast.LENGTH_SHORT).show();
                                ((BaseActivity) context).dismissProgressDialog();
                                return;

                            }
                            if ("1".equals(resultBean.getCode())) {
                                Log.e("zj","22222");

                                if (isDismiss) {
                                    ((BaseActivity) context).dismissProgressDialog();
                                }
                                if (myCallBack != null) {
                                    myCallBack.getData(baseBean);
                                }

                            } else {
                                Toast.makeText(context, resultBean.getMsg(), Toast.LENGTH_SHORT).show();
                                ((BaseActivity) context).dismissProgressDialog();
                            }
                        }

                    } else {
                        if (isDismiss) {
                            ((BaseActivity) context).dismissProgressDialog();
                        }
                        QueryResponseBean.Error errorBean = new Gson().fromJson(baseBean.getError(), QueryResponseBean.Error.class);
                        if (errorBean != null) {
                            if ("无效的链接".equals(errorBean.getMessage())) {

                                Log.e("zj", "无效的链接");
                                ConnectNetTools.net(DataTools.getConnectJson(context), new Urls().connect, context, new ConnectNetTools.MyCallBack() {
                                    @Override
                                    public void getData(BaseBean response) {
                                        Log.e("zj", "SPTools getSessionId = " + SPTools.INSTANCE.get(context, Constant.SESSIONID, ""));

                                        ConnectResponseBean.Result resultBean = new Gson().fromJson(response.getResult(), ConnectResponseBean.Result.class);
                                        Log.e("zj", "ConnectResponseBean getSessionId = " + resultBean.getData().getSessionId());
                                        SPTools.INSTANCE.put(context, Constant.SESSIONID, "" + resultBean.getData().getSessionId());
                                        QueryRequestBean requestBean = new Gson().fromJson(json, QueryRequestBean.class);
                                        requestBean.getParams().getData().setSessionId("" + SPTools.INSTANCE.get(context, Constant.SESSIONID, ""));
//
                                        String jsonNew = new Gson().toJson(requestBean);
                                        FileTools.saveFile(jsonNew,3);
                                        Log.e("zj", "jsonNew = " + jsonNew);

                                        net(jsonNew, url, context, myCallBack);

                                    }
                                });
                            } else {
                                Toast.makeText(context, "错误信息： code:" + errorBean.code + "，message=" + errorBean.message, Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(context, "返回格式有误", Toast.LENGTH_SHORT).show();
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