package com.yiqikeji.lostpersonproject.constant;

import android.os.Environment;

public class Constant {

    public static final String TOKEN = "token";
    public static final String TIME = "time";
    public static final String MKDIRNAME = "zsrkImages";
    public static final String SDPATH = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).toString() + "/" + Constant.MKDIRNAME;
    public static final String USERNAME = "username";
}
