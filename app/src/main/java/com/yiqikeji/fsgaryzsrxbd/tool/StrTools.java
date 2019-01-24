package com.yiqikeji.fsgaryzsrxbd.tool;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class StrTools {

    public static List<Map<String,Bitmap>> bitmapList = new ArrayList<>();

    public static String formateRate(String rateStr) {
        if (rateStr.indexOf(".") != -1) {
            //获取小数点的位置
            int num = 0;
            num = rateStr.indexOf(".");

            //获取小数点后面的数字 是否有两位 不足两位补足两位
            String dianAfter = rateStr.substring(0, num + 1);
            String afterData = rateStr.replace(dianAfter, "");
            if (afterData.length() < 2) {
                afterData = afterData + "0";
            } else {
                afterData = afterData;
            }
            return rateStr.substring(0, num) + "." + afterData.substring(0, 2);
        } else {
            if (rateStr == "1") {
                return "100";
            } else {
                return rateStr;
            }
        }
    }

    public static String getRandomString(int length) {
        String str = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(36);
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }

    public static String getTime() {
        long currentTime = System.currentTimeMillis();

        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmssSSS");

        Date date = new Date(currentTime);
        String time = "" + formatter.format(date);
        return time;
    }

    public static Drawable getDrawable(Activity activity,String base64) {
        String replaceStr = "";
        if (base64.contains("#imges#")) {
             replaceStr = base64.replace("#imges#", "");

        }
        Bitmap bitmap = FileTools.stringToBitmap(replaceStr);
        Drawable drawable = new BitmapDrawable(bitmap);
        Map<String,Bitmap> map = new HashMap<>();
        map.put(activity.getLocalClassName(),bitmap);
        bitmapList.add(map);
        return drawable;
    }

    public static void clearBitmap(Activity activity){
        try {
            for (int i = 0; i < bitmapList.size(); i++) {
                if(bitmapList.get(i).get(activity.getLocalClassName())!=null){
                    Bitmap bitmap = bitmapList.get(i).get(activity.getLocalClassName());
                    bitmap.recycle();
                    bitmap = null;
                    bitmapList.remove(i);
                    i--;
                }
            }
        }catch (Exception e){

        }
    }

}
