package com.yiqikeji.lostpersonproject.tool;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.yiqikeji.lostpersonproject.bean.RequestBean;
import com.yiqikeji.lostpersonproject.bean.ResponseBean;
import com.yiqikeji.lostpersonproject.bean.UserBean;
import com.google.gson.Gson;
import com.ycgis.pclient.PService;

public class DataTools {

    public static String putParams(Context context, String method, String sessionId, String condition, String fields, String orderby, PService user) {
        RequestBean jsonBean = new RequestBean();

        jsonBean.setId("1");//给的 写死的
        jsonBean.setMethod(method);//给的 写死的
        RequestBean.Params params = new RequestBean.Params();

        RequestBean.Params.Data data = new RequestBean.Params.Data();

        data.setSessionId(sessionId);//"调用connect接口时返回的值"
        data.setDataObjId("服务端设定");
        data.setVersion("20181207");
        data.setCondition("condition");
        data.setFields("fields");
        data.setOrderBy("orderby");

        RequestBean.Params.Data.UserInfo userInfo = new RequestBean.Params.Data.UserInfo();

        if (user != null){
            userInfo.setUserId("用户ID，警号 = " + user.getUserAccount());
            userInfo.setUserName("用户姓名，姓名 = " + user.getPoliceName());
            userInfo.setUserDeptNo("用户所属单位编码，即12位公安机关单位编码 = " + user.getZZJGDm());
            userInfo.setSn("");
            userInfo.setSfzh("身份证号 = " + user.getSFZ());
        }else {
            Toast.makeText(context,"用户为null",Toast.LENGTH_SHORT).show();
        }

        data.setUserInfo(userInfo);
        params.setData(data);

        jsonBean.setParams(params);

        String json = new Gson().toJson(jsonBean);
        Log.e("zj", "json = " + json);

        return json;
    }

    //登录接口返回解析

    public static UserBean getUserBean(String resultJson){

        resultJson =  "{\n" +
                "    \"jsonrpc\":\"2.0\",\n" +
                "    \"id\":\"1\",\n" +
                "    \"result\":{\n" +
                "        \"code\":\"1\",\n" +
                "        \"msg\":\"OK\",\n" +
                "        \"data\":[\n" +
                "            {\n" +
                "                \"sourceId\":\"DS-01\",\n" +
                "                \"fieldValues\":[\n" +
                "                    {\n" +
                "                        \"field\":\"policeStationName\",\n" +
                "                        \"value\":\"小明\",\n" +
                "                        \"isCode\":0,\n" +
                "                        \"codeValue\":\"\"\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"field\":\"localPoliceStationName\",\n" +
                "                        \"value\":\"20\",\n" +
                "                        \"isCode\":0,\n" +
                "                        \"codeValue\":\"\"\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"field\":\"policeName\",\n" +
                "                        \"value\":\"男\",\n" +
                "                        \"isCode\":1,\n" +
                "                        \"codeValue\":\"2\"\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"field\":\"policeId\",\n" +
                "                        \"value\":\"15501011234\",\n" +
                "                        \"isCode\":0,\n" +
                "                        \"codeValue\":\"\"\n" +
                "                    }\n" +
                "                ]\n" +
                "            }\n" +
                "        ],\n" +
                "        \"page\":{\n" +
                "            \"pageSize\":10,\n" +
                "            \"pageNo\":1,\n" +
                "            \"total\":1\n" +
                "        },\n" +
                "        \"sign\":\"1qqqqwwwwwwwwwwwwww\"\n" +
                "    }\n" +
                "}";

        UserBean userBean = new UserBean();
        ResponseBean resultBean = new Gson().fromJson(resultJson, ResponseBean.class);

        for (int i = 0; i < resultBean.getResult().getData().get(0).getFieldValues().size(); i++) {

            if (resultBean.getResult().getData().get(0).getFieldValues().get(i).getField().equals("policeStationName")){
                userBean.setPoliceStationName(resultBean.getResult().getData().get(0).getFieldValues().get(i).getValue());
            }
            if (resultBean.getResult().getData().get(0).getFieldValues().get(i).getField().equals("localPoliceStationName")){
                userBean.setLocalPoliceStationName(resultBean.getResult().getData().get(0).getFieldValues().get(i).getValue());
            }
            if (resultBean.getResult().getData().get(0).getFieldValues().get(i).getField().equals("policeName")){
                userBean.setPoliceName(resultBean.getResult().getData().get(0).getFieldValues().get(i).getValue());
            }
            if (resultBean.getResult().getData().get(0).getFieldValues().get(i).getField().equals("policeId")){
                userBean.setPoliceId(resultBean.getResult().getData().get(0).getFieldValues().get(i).getValue());
            }
            if (resultBean.getResult().getData().get(0).getFieldValues().get(i).getField().equals("token")){
                userBean.setToken(resultBean.getResult().getData().get(0).getFieldValues().get(i).getValue());
            }
            if (resultBean.getResult().getData().get(0).getFieldValues().get(i).getField().equals("responsibilityAreaName")){
                userBean.setResponsibilityAreaName(resultBean.getResult().getData().get(0).getFieldValues().get(i).getValue());
            }
        }
        return userBean;
    }

    //走失人员接口返回解析

    //人员详情接口返回解析

    //人脸对比接口返回解析

    //确认接口返回解析

    //修改备注接口返回解析
}
