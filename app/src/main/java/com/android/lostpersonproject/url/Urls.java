package com.android.lostpersonproject.url;

public class Urls {

    public final static String base_service = "http://app.huihaiedu.cn/base_service/";//生产环境，手机后台

    public final static String base_url = base_service + "mobile/v1/getServices";


    public final  String auth_login = base_url + "v1/auth/login";// 登录

    public final static String file_upload_url = base_url + "v1/auth/file_upload_url";// 上传文件

    public final  String getAppList = base_url + "v1/homePage/models_todoTasks";// 获取应用列表
    public final static String comment_get_Class_Grade = base_url + "v1/term_send_word/get_grade_class";// 获取届年班 (教师)

    public final static String getCarouselList = base_url + "v1/homePage/rotate_image"; //获取首页轮播图
    public final static String auth_updatePwd = base_url + "v1/auth/set_pwd";// 修改密码
    public final static String uploadHead = base_url + "v1/user/save_portrait";// 上传头像
    public final static String appCheckUpdate = base_service + "mobile/v1/getNewVersion";// 手机app检查更新
    public final static String getSchOrStuListByUser = base_url + "v1/auth/schools_children";// 根据用户名密码获取（教师学校列表、家长学生列表）
}
