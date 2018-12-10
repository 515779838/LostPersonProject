package com.yiqikeji.lostpersonproject.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yiqikeji.lostpersonproject.base.BaseActivity;
import com.yiqikeji.lostpersonproject.bean.UserBean;
import com.yiqikeji.lostpersonproject.constant.Constant;
import com.yiqikeji.lostpersonproject.tool.DataTools;
import com.ycgis.pclient.PService;

import java.io.File;
import java.io.IOException;

public class MainActivity extends BaseActivity {

    private String picPath;//图片存储路径
    private String IMAGE_FILE_NAME;//
    private LinearLayout ll_camera, ll_note;
    private TextView tv_fj, tv_pcs, tv_zrq, tv_jy;

    private ImageView iv_title;

    private UserBean userBean;

    private Uri mUri;

    private PService user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.yiqikeji.lostpersonproject.R.layout.activity_main);
        initView();
        onClick();

        user = new PService(this);



        Log.e("zj","user getaddress= "+user.getaddress());
        Log.e("zj","user getCimPort= "+user.getCimPort());
        Log.e("zj","user getPassword= "+user.getPassword());
        Log.e("zj","user getPHONE= "+user.getPHONE());
        //用户ID，警号

        Log.e("zj","用户ID，警号  = ");
        Log.e("zj","用户姓名，姓名  = ");
        Log.e("zj","用户所属单位编码，即12位公安机关单位编码。  = ");
        Log.e("zj","证书SN，针对于使用安全卡或安全类证书  = ");

        Log.e("zj","获取登录用户名称  = "+user.getPoliceName());
        Log.e("zj","民警身份证号 = "+user.getSFZ());
        Log.e("zj","获取登录用户帐号 = "+user.getUserAccount());
        Log.e("zj","获取登录用户所在部门名称  = "+user.getZZJGName());
        Log.e("zj","获取登录用户所在部门编号  = "+user.getZZJGDm());

        userBean = (UserBean) getIntent().getSerializableExtra("userBean");

        if (userBean != null) {
            tv_fj.setText(userBean.getPoliceStationName());
            tv_pcs.setText(userBean.getLocalPoliceStationName());
            tv_zrq.setText(userBean.getResponsibilityAreaName());
            tv_jy.setText(userBean.getPoliceName());

        }
        File appDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM) + "/" + Constant.MKDIRNAME);
        if (!appDir.exists()) {
            appDir.mkdirs();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        picPath = Constant.SDPATH  + "/" + System.currentTimeMillis() + ".jpg";

    }

    private void initView() {
        ll_camera = findViewById(com.yiqikeji.lostpersonproject.R.id.ll_camera);
        ll_note = findViewById(com.yiqikeji.lostpersonproject.R.id.ll_note);

        tv_fj = findViewById(com.yiqikeji.lostpersonproject.R.id.tv_fj);
        tv_pcs = findViewById(com.yiqikeji.lostpersonproject.R.id.tv_pcs);
        tv_zrq = findViewById(com.yiqikeji.lostpersonproject.R.id.tv_zrq);
        tv_jy = findViewById(com.yiqikeji.lostpersonproject.R.id.tv_jy);
        iv_title = findViewById(com.yiqikeji.lostpersonproject.R.id.iv_title);
    }

    private void onClick() {
        ll_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                mUri = Uri.fromFile(new File(picPath));
                //为拍摄的图片指定一个存储的路径
                cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, mUri);
                startActivityForResult(cameraIntent, 0);
            }
        });

        ll_note.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, LoggingActivity.class);
                startActivity(intent);
            }
        });
        iv_title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                DataTools.putParams(MainActivity.this,"method","sessionId","条件","fileds","orderBy",user);

             UserBean userBean = DataTools.getUserBean("");
             Log.e("zj","userBean = "+userBean.toString());
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {

            if (requestCode == 0) {

                // 刷新在系统相册中显示
                try {
                    MediaStore.Images.Media.insertImage(getContentResolver(),
                            MediaStore.Images.Media.getBitmap(MainActivity.this.getContentResolver(), mUri),
                            getResources().getString(com.yiqikeji.lostpersonproject.R.string.app_name), "");

                    Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
                    intent.setData(mUri);
                    sendBroadcast(intent);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                String[] names = mUri.getPath().split("/");
                IMAGE_FILE_NAME = names[names.length - 1];
                if (!IMAGE_FILE_NAME.toLowerCase().endsWith(".jpg") && !IMAGE_FILE_NAME.toLowerCase().endsWith(".png")) {
                    IMAGE_FILE_NAME = IMAGE_FILE_NAME + ".png";
                }

                Intent intent2 = new Intent(MainActivity.this, SearchActivity.class);
                intent2.putExtra("picPath", picPath);
                intent2.putExtra("imageName", IMAGE_FILE_NAME);

                startActivity(intent2);

            }
        }
    }
}
