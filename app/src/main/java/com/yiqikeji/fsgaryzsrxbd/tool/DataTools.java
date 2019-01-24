package com.yiqikeji.fsgaryzsrxbd.tool;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.yiqikeji.fsgaryzsrxbd.bean.ConnectBean;
import com.yiqikeji.fsgaryzsrxbd.bean.LoggingBean;
import com.yiqikeji.fsgaryzsrxbd.bean.OperateRequestBean;
import com.yiqikeji.fsgaryzsrxbd.bean.PersonDetailBean;
import com.yiqikeji.fsgaryzsrxbd.bean.QueryRequestBean;
import com.yiqikeji.fsgaryzsrxbd.bean.QueryResponseBean;
import com.yiqikeji.fsgaryzsrxbd.bean.SearchBean;
import com.yiqikeji.fsgaryzsrxbd.bean.UserBean;
import com.google.gson.Gson;
import com.ycgis.pclient.PService;
import com.yiqikeji.fsgaryzsrxbd.constant.Constant;

import java.util.ArrayList;


public class DataTools {

    public static String getConnectJson(Context context) {
        ConnectBean connectBean = new ConnectBean();
        connectBean.setId(Constant.REQUESTID);
        ConnectBean.Params params = new ConnectBean.Params();

        ConnectBean.Params.Data data = new ConnectBean.Params.Data();

        data.setAppId(Constant.APPID);
        data.setTimestamp(StrTools.getTime());
        data.setNonce(StrTools.getRandomString(32));
        data.setVersion(Constant.VERSION);
        params.setData(data);
        connectBean.setParams(params);
        String json = new Gson().toJson(connectBean);
        Log.e("zj","json = "+json);

        return json;
    }

    public static String putQueryParams(Context context, String method, String condition, String fields, String orderby, String pageNo) {
        PService user = new PService(context);

        Log.e("zj","condition2 = "+condition);
        QueryRequestBean jsonBean = new QueryRequestBean();

        jsonBean.setId(Constant.REQUESTID);//给的 写死的
        jsonBean.setMethod(method);//给的 写死的
        QueryRequestBean.Params params = new QueryRequestBean.Params();

        QueryRequestBean.Params.Data data = new QueryRequestBean.Params.Data();

        Log.e("zj", "putQueryParams sessionId = " + SPTools.INSTANCE.get(context, Constant.SESSIONID, ""));
        data.setSessionId("" + SPTools.INSTANCE.get(context, Constant.SESSIONID, ""));//"调用connect接口时返回的值"
        data.setDataObjId(Constant.DataObjId);
        data.setVersion(Constant.VERSION);
        data.setCondition(condition);
        data.setFields(fields);
        data.setOrderBy(orderby);

        QueryRequestBean.Params.Data.UserInfo userInfo = new QueryRequestBean.Params.Data.UserInfo();
        QueryRequestBean.Params.Data.Page page = new QueryRequestBean.Params.Data.Page();

        page.setPageNo(pageNo);
        page.setPageSize(Constant.PAGESIZE);
        if (user != null) {
            userInfo.setUserId("用户ID，警号 = " + user.getUserAccount());
            userInfo.setUserName("用户姓名，姓名 = " + user.getPoliceName());
            userInfo.setUserDeptNo("用户所属单位编码，即12位公安机关单位编码 = " + user.getZZJGDm());
            userInfo.setSn("");
            userInfo.setSfzh("身份证号 = " + user.getSFZ());
        } else {
            Toast.makeText(context, "用户为null", Toast.LENGTH_SHORT).show();
        }

        data.setUserInfo(userInfo);
        data.setPage(page);
        params.setData(data);

        jsonBean.setParams(params);

        String json = new Gson().toJson(jsonBean);
        Log.e("zj", "json = " + json);

        return json;
    }

    public static String putOperateParams(Context context, String method, ArrayList<OperateRequestBean.Params.Data.Operation> operations) {

        PService user = new PService(context);

        OperateRequestBean jsonBean = new OperateRequestBean();

        jsonBean.setId(Constant.REQUESTID);//给的 写死的
        jsonBean.setMethod(method);//给的 写死的
        OperateRequestBean.Params params = new OperateRequestBean.Params();

        OperateRequestBean.Params.Data data = new OperateRequestBean.Params.Data();
        data.setSessionId("" + SPTools.INSTANCE.get(context, Constant.SESSIONID, ""));//"调用connect接口时返回的值"

        data.setVersion(Constant.VERSION);
        data.setOperations(operations);

        OperateRequestBean.Params.Data.UserInfo userInfo = new OperateRequestBean.Params.Data.UserInfo();

        if (user != null) {
            userInfo.setUserId("用户ID，警号 = " + user.getUserAccount());
            userInfo.setUserName("用户姓名，姓名 = " + user.getPoliceName());
            userInfo.setUserDeptNo("用户所属单位编码，即12位公安机关单位编码 = " + user.getZZJGDm());
            userInfo.setSn("");
            userInfo.setSfzh("身份证号 = " + user.getSFZ());
        } else {
            Toast.makeText(context, "用户为null", Toast.LENGTH_SHORT).show();
        }

        data.setUserInfo(userInfo);

        params.setData(data);


        jsonBean.setParams(params);

        String json = new Gson().toJson(jsonBean);
        Log.e("zj", "putOperateParams = " + json);

        return json;
    }

    //登录接口返回解析
    public static UserBean getUserBean(String resultJson) {

        UserBean userBean = new UserBean();


        QueryResponseBean.Result resultBean = new Gson().fromJson(resultJson, QueryResponseBean.Result.class);

        for (int i = 0; i < resultBean.getData().get(0).getFieldValues().size(); i++) {

            if (resultBean.getData().get(0).getFieldValues().get(i).getField().equals("policeStationName")) {
                userBean.setPoliceStationName(resultBean.getData().get(0).getFieldValues().get(i).getValue());
            }
            if (resultBean.getData().get(0).getFieldValues().get(i).getField().equals("localPoliceStationName")) {
                userBean.setLocalPoliceStationName(resultBean.getData().get(0).getFieldValues().get(i).getValue());
            }
            if (resultBean.getData().get(0).getFieldValues().get(i).getField().equals("policeName")) {
                userBean.setPoliceName(resultBean.getData().get(0).getFieldValues().get(i).getValue());
            }
            if (resultBean.getData().get(0).getFieldValues().get(i).getField().equals("policeId")) {
                userBean.setPoliceId(resultBean.getData().get(0).getFieldValues().get(i).getValue());
            }
            if (resultBean.getData().get(0).getFieldValues().get(i).getField().equals("token")) {
                userBean.setToken(resultBean.getData().get(0).getFieldValues().get(i).getValue());
            }
            if (resultBean.getData().get(0).getFieldValues().get(i).getField().equals("responsibilityAreaName")) {
                userBean.setResponsibilityAreaName(resultBean.getData().get(0).getFieldValues().get(i).getValue());
            }
        }
        return userBean;
    }

    //走失人员接口返回解析

    public static LoggingBean getLoggingBean(String resultJson) {

        LoggingBean loggingBean = new LoggingBean();

        Log.e("zj", "resultJson=" + resultJson);
        QueryResponseBean.Result resultBean = new Gson().fromJson(resultJson, QueryResponseBean.Result.class);
        ArrayList<LoggingBean.ResultBean> resultBeans = new ArrayList<>();

        for (int i = 0; i < resultBean.getData().size(); i++) {
            LoggingBean.ResultBean resultBean1 = new LoggingBean.ResultBean();
            for (int j = 0; j < resultBean.getData().get(i).getFieldValues().size(); j++) {
                if (resultBean.getData().get(0).getFieldValues().get(j).getField().equals("findNum")) {
                    loggingBean.setFindNum(resultBean.getData().get(0).getFieldValues().get(j).getValue());
                }
                if (resultBean.getData().get(0).getFieldValues().get(j).getField().equals("missingNum")) {
                    loggingBean.setMissingNum(resultBean.getData().get(0).getFieldValues().get(j).getValue());
                }

                if (resultBean.getData().get(i).getFieldValues().get(j).getField().equals("id")) {
                    resultBean1.setId(resultBean.getData().get(i).getFieldValues().get(j).getValue());
                }
                if (resultBean.getData().get(i).getFieldValues().get(j).getField().equals("portrait")) {
                    resultBean1.setPortrait(resultBean.getData().get(i).getFieldValues().get(j).getValue());
                }
                if (resultBean.getData().get(i).getFieldValues().get(j).getField().equals("address")) {
                    resultBean1.setAddress(resultBean.getData().get(i).getFieldValues().get(j).getValue());
                }
                if (resultBean.getData().get(i).getFieldValues().get(j).getField().equals("name")) {
                    resultBean1.setName(resultBean.getData().get(i).getFieldValues().get(j).getValue());
                }
            }
            if (resultBean1.getId() != null) {
                resultBeans.add(resultBean1);
            }
        }
        loggingBean.setResult(resultBeans);

        return loggingBean;
    }

    //人员详情接口返回解析

    public static PersonDetailBean getPersonDetailBean(String resultJson) {

        PersonDetailBean personDetailBean = new PersonDetailBean();

        QueryResponseBean.Result resultBean = new Gson().fromJson(resultJson, QueryResponseBean.Result.class);
        ArrayList<PersonDetailBean.PhotosBean> photosBeans = new ArrayList<>();

        for (int i = 0; i < resultBean.getData().size(); i++) {
            PersonDetailBean.PhotosBean photosBean = new PersonDetailBean.PhotosBean();
            for (int j = 0; j < resultBean.getData().get(i).getFieldValues().size(); j++) {
                if (resultBean.getData().get(0).getFieldValues().get(j).getField().equals("id")) {
                    personDetailBean.setId(resultBean.getData().get(0).getFieldValues().get(j).getValue());
                }
                if (resultBean.getData().get(0).getFieldValues().get(j).getField().equals("remarks")) {
                    personDetailBean.setRemarks(resultBean.getData().get(0).getFieldValues().get(j).getValue());
                }
                if (resultBean.getData().get(0).getFieldValues().get(j).getField().equals("name")) {
                    personDetailBean.setName(resultBean.getData().get(0).getFieldValues().get(j).getValue());
                }
                if (resultBean.getData().get(0).getFieldValues().get(j).getField().equals("cardId")) {
                    personDetailBean.setCardId(resultBean.getData().get(0).getFieldValues().get(j).getValue());
                }
                if (resultBean.getData().get(0).getFieldValues().get(j).getField().equals("address")) {
                    personDetailBean.setAddress(resultBean.getData().get(0).getFieldValues().get(j).getValue());
                }
                if (resultBean.getData().get(0).getFieldValues().get(j).getField().equals("mePhone")) {
                    personDetailBean.setMePhone(resultBean.getData().get(0).getFieldValues().get(j).getValue());
                }
                if (resultBean.getData().get(0).getFieldValues().get(j).getField().equals("guardianOne")) {
                    personDetailBean.setGuardianOne(resultBean.getData().get(0).getFieldValues().get(j).getValue());
                }
                if (resultBean.getData().get(0).getFieldValues().get(j).getField().equals("relationOne")) {
                    personDetailBean.setRelationOne(resultBean.getData().get(0).getFieldValues().get(j).getValue());
                }
                if (resultBean.getData().get(0).getFieldValues().get(j).getField().equals("guardianOneCardId")) {
                    personDetailBean.setGuardianOneCardId(resultBean.getData().get(0).getFieldValues().get(j).getValue());
                }
                if (resultBean.getData().get(0).getFieldValues().get(j).getField().equals("guardianOneAddress")) {
                    personDetailBean.setGuardianOneAddress(resultBean.getData().get(0).getFieldValues().get(j).getValue());
                }
                if (resultBean.getData().get(0).getFieldValues().get(j).getField().equals("guardianOneTel")) {
                    personDetailBean.setGuardianOneTel(resultBean.getData().get(0).getFieldValues().get(j).getValue());
                }
                if (resultBean.getData().get(0).getFieldValues().get(j).getField().equals("guardianTwo")) {
                    personDetailBean.setGuardianTwo(resultBean.getData().get(0).getFieldValues().get(j).getValue());
                }
                if (resultBean.getData().get(0).getFieldValues().get(j).getField().equals("relationTwo")) {
                    personDetailBean.setRelationTwo(resultBean.getData().get(0).getFieldValues().get(j).getValue());
                }
                if (resultBean.getData().get(0).getFieldValues().get(j).getField().equals("guardianTwoCardId")) {
                    personDetailBean.setGuardianTwoCardId(resultBean.getData().get(0).getFieldValues().get(j).getValue());
                }
                if (resultBean.getData().get(0).getFieldValues().get(j).getField().equals("guardianTwoAddress")) {
                    personDetailBean.setGuardianTwoAddress(resultBean.getData().get(0).getFieldValues().get(j).getValue());
                }
                if (resultBean.getData().get(0).getFieldValues().get(j).getField().equals("guardianTwoTel")) {
                    personDetailBean.setGuardianTwoTel(resultBean.getData().get(0).getFieldValues().get(j).getValue());
                }
                if (resultBean.getData().get(0).getFieldValues().get(j).getField().equals("portrait_nav")) {
                    personDetailBean.setPortrait_nav(resultBean.getData().get(0).getFieldValues().get(j).getValue());
                }
                if (resultBean.getData().get(0).getFieldValues().get(j).getField().equals("policeStationName")) {
                    personDetailBean.setPoliceStationName(resultBean.getData().get(0).getFieldValues().get(j).getValue());
                }
                if (resultBean.getData().get(0).getFieldValues().get(j).getField().equals("localPoliceStationName")) {
                    personDetailBean.setLocalPoliceStationName(resultBean.getData().get(0).getFieldValues().get(j).getValue());
                }
                if (resultBean.getData().get(0).getFieldValues().get(j).getField().equals("responsibilityAreaName")) {
                    personDetailBean.setResponsibilityAreaName(resultBean.getData().get(0).getFieldValues().get(j).getValue());
                }

                if (resultBean.getData().get(i).getFieldValues().get(j).getField().equals("portrait")) {
                    photosBean.setPortrait(resultBean.getData().get(i).getFieldValues().get(j).getValue());
                }
            }

            if (!photosBean.getPortrait().equals("")) {
                photosBeans.add(photosBean);
            }
        }
        personDetailBean.setPhotos(photosBeans);

        return personDetailBean;
    }

    //人脸对比接口返回解析

    public static SearchBean getSearchBean(String resultJson) {
        SearchBean searchBean = new SearchBean();

        QueryResponseBean.Result resultBean = new Gson().fromJson(resultJson, QueryResponseBean.Result.class);
        ArrayList<SearchBean.ResultBean> resultBeans = new ArrayList<>();

        for (int i = 0; i < resultBean.getData().size(); i++) {
            SearchBean.ResultBean resultBean1 = new SearchBean.ResultBean();

            for (int j = 0; j < resultBean.getData().get(i).getFieldValues().size(); j++) {
                if (resultBean.getData().get(0).getFieldValues().get(j).getField().equals("flag")) {
                    searchBean.setFlag(resultBean.getData().get(0).getFieldValues().get(j).getValue());
                }
                if (resultBean.getData().get(0).getFieldValues().get(j).getField().equals("isScore")) {
                    searchBean.setIsScore(resultBean.getData().get(0).getFieldValues().get(j).getValue());
                }

                if (resultBean.getData().get(i).getFieldValues().get(j).getField().equals("customId")) {
                    resultBean1.setCustomId(resultBean.getData().get(i).getFieldValues().get(j).getValue());
                }
                if (resultBean.getData().get(i).getFieldValues().get(j).getField().equals("name")) {
                    resultBean1.setName(resultBean.getData().get(i).getFieldValues().get(j).getValue());
                }
                if (resultBean.getData().get(i).getFieldValues().get(j).getField().equals("score")) {
                    resultBean1.setScore(resultBean.getData().get(i).getFieldValues().get(j).getValue());
                }
                if (resultBean.getData().get(i).getFieldValues().get(j).getField().equals("portrait")) {
                    resultBean1.setPortrait(resultBean.getData().get(i).getFieldValues().get(j).getValue());
                }
            }

            if (resultBean1.getCustomId() != null && resultBean1.getName() != null && resultBean1.getPortrait() != null && resultBean1.getScore() != null) {
                resultBeans.add(resultBean1);
            }
        }
        searchBean.setResult(resultBeans);

        return searchBean;

    }

    //确认接口返回解析

    //修改备注接口返回解析
}
