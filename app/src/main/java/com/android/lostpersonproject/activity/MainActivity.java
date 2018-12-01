package com.android.lostpersonproject.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.lostpersonproject.R;
import com.android.lostpersonproject.base.BaseActivity;
import com.android.lostpersonproject.bean.UserBean;
import com.android.lostpersonproject.constant.Constant;

import java.io.File;
import java.io.IOException;

public class MainActivity extends BaseActivity {

    private String picPath;//图片存储路径
    private String IMAGE_FILE_NAME;//
    private LinearLayout ll_camera, ll_note;
    private TextView tv_fj, tv_pcs, tv_zrq, tv_jy;

    private UserBean userBean;

    private Uri mUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        onClick();
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
        ll_camera = findViewById(R.id.ll_camera);
        ll_note = findViewById(R.id.ll_note);

        tv_fj = findViewById(R.id.tv_fj);
        tv_pcs = findViewById(R.id.tv_pcs);
        tv_zrq = findViewById(R.id.tv_zrq);
        tv_jy = findViewById(R.id.tv_jy);
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
                            getResources().getString(R.string.app_name), "");

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
