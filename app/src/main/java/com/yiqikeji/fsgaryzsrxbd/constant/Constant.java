package com.yiqikeji.fsgaryzsrxbd.constant;

import android.os.Environment;

public class Constant {

    public static final String TOKEN = "token";
    public static final String POLICEID = "policeId";

    public static final String TIME = "time";
    public static final String MKDIRNAME = "zsrkImages";
    public static final String SDPATH = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).toString() + "/" + Constant.MKDIRNAME;
    public static final String USERNAME = "username";
    public static final String PCS = "pcs";//派出所
    public static final String POLICENAME = "policeName";//警员
    public static final String FJ = "fj";//分局
    public static final String ZRQ = "zrq";//责任区

    public static final String LOGINDO = "loginDo";//登录
    public static final String LOGGING = "missingPopulationList";//工作记录
    public static final String PERSONDETAIL = "personneletails";//详情
    public static final String COMPAREN = "compareN";//人员比对
    public static final String CONFIRM = "confirm";//确认接口
    public static final String UPREMARKS = "upRemarks";//修改备注
    public static final String UPTOKEN = "upToken";//退出

    public static final String DataObjId = "";//服务端设定的 资源名称 最长32位
    public static final String REQUESTID = "5684ce1c01ee454baeecf7d047d40aeb";// 客户端传入的ID，当返回数据正确时，响应方会同样的返回
    public static final String VERSION = "20181207";//
    public static final String SESSIONID = "sessionId";//
    public static final String PAGESIZE = "20";//

    public static final String APPID = "appId";//



}
