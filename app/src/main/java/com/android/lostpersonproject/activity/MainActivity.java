package com.android.lostpersonproject.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.android.lostpersonproject.R;
import com.android.lostpersonproject.base.BaseActivity;

import java.io.File;

public class MainActivity extends BaseActivity {

    private String sdPath;//SD卡的路径
    private String picPath;//图片存储路径
    private LinearLayout ll_camera,ll_note;
    private ImageView iv_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ll_camera = findViewById(R.id.ll_camera);
        ll_note= findViewById(R.id.ll_note);
        iv_title = findViewById(R.id.iv_title);

        File appDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM) + "/image");
        if (!appDir.exists()) {
            appDir.mkdirs();
        }
        sdPath =  Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM) + "/image";
        picPath = sdPath + "/" + "temp.png";
        Log.e("sdPath1",sdPath);


        ll_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                                Uri uri = Uri.fromFile(new File(picPath));
                //为拍摄的图片指定一个存储的路径
                cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
                startActivityForResult(cameraIntent, 0);
            }
        });

        ll_note.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,LoggingActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {

            if (requestCode == 0) {

                Intent intent = new Intent(MainActivity.this,SearchActivity.class);
                intent.putExtra("picPath",picPath);
                startActivity(intent);
//
//                /**
//                 * 这种方法是通过内存卡的路径进行读取图片，所以的到的图片是拍摄的原图
//                 */
//                FileInputStream fis = null;
//                try {
//                    Log.e("sdPath2",picPath);
//                    //把图片转化为字节流
//                    fis = new FileInputStream(picPath);
//                    //把流转化图片
//                    Bitmap bitmap = BitmapFactory.decodeStream(fis);
//                    iv_title.setImageBitmap(bitmap);
//                    Intent intent = new Intent(Main2Activity.this,SearchActivity.class);
//                    intent.putExtra("picPath",picPath);
//                    startActivity(intent);
//
//                    Log.e("zj","3333");
//
//                } catch (FileNotFoundException e) {
//                    e.printStackTrace();
//                }finally{
//                    try {
//                        fis.close();//关闭流
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
            }


        }
    }
}
