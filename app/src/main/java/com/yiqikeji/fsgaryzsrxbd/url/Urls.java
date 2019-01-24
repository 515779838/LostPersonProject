package com.yiqikeji.fsgaryzsrxbd.url;

public class Urls {

    public final static String base_service = "http://47.92.250.134:8980";
    public final  String connect = base_service + "/js/api/jwt/connect";// 连接

    public final  String auth_login = base_service + "/js/api/jwt/loginDo";// 登录
    public final  String missingPopulationList = base_service + "/js/api/jwt/missingPopulationList";// 走失人员

    public final  String personneletails = base_service + "/js/api/jwt/personneletails";// 人员详情
    public final static String compareN = base_service + "/js/api/jwt/face/compareN";// 人脸对比接口
    public final static String confirm = base_service + "/js/api/jwt/confirm";// 确认接口
    public final static String upRemarks = base_service + "//js/api/jwt/upRemarks ";// 修改备注
    public final static String auth_logout = base_service + "//js/api/jwt/upToken ";// 退出



}
