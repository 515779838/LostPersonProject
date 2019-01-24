package com.yiqikeji.fsgaryzsrxbd.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yiqikeji.fsgaryzsrxbd.R;
import com.yiqikeji.fsgaryzsrxbd.base.BaseActivity;
import com.yiqikeji.fsgaryzsrxbd.bean.BaseBean;
import com.yiqikeji.fsgaryzsrxbd.bean.OperateRequestBean;
import com.yiqikeji.fsgaryzsrxbd.constant.Constant;
import com.yiqikeji.fsgaryzsrxbd.tool.DataTools;
import com.ycgis.pclient.PService;
import com.yiqikeji.fsgaryzsrxbd.tool.OperateNetTools;
import com.yiqikeji.fsgaryzsrxbd.tool.SPTools;
import com.yiqikeji.fsgaryzsrxbd.tool.StrTools;
import com.yiqikeji.fsgaryzsrxbd.url.Urls;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends BaseActivity {

    private String picPath;//图片存储路径
    private String IMAGE_FILE_NAME;//
    private LinearLayout ll_camera, ll_note;
    private TextView tv_fj, tv_pcs, tv_zrq, tv_jy;

    private ImageView iv_title;
    private ImageView iv_logout;

    private Uri mUri;

    private PService user;

    private File appDir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        onClick();

        user.addAppLog(this, "", "", 1);

//        SPTools.INSTANCE.put(this,Constant.SESSIONID,"1111");

        user = new PService(this);
        Log.e("zj", "user getaddress= " + user.getaddress());
        Log.e("zj", "user getCimPort= " + user.getCimPort());
        Log.e("zj", "user getPassword= " + user.getPassword());
        Log.e("zj", "user getPHONE= " + user.getPHONE());
        //用户ID，警号

        Log.e("zj", "用户ID，警号  = ");
        Log.e("zj", "用户姓名，姓名  = ");
        Log.e("zj", "用户所属单位编码，即12位公安机关单位编码。  = ");
        Log.e("zj", "证书SN，针对于使用安全卡或安全类证书  = ");

        Log.e("zj", "获取登录用户名称  = " + user.getPoliceName());
        Log.e("zj", "民警身份证号 = " + user.getSFZ());
        Log.e("zj", "获取登录用户帐号 = " + user.getUserAccount());
        Log.e("zj", "获取登录用户所在部门名称  = " + user.getZZJGName());
        Log.e("zj", "获取登录用户所在部门编号  = " + user.getZZJGDm());

        tv_fj.setText("" + SPTools.INSTANCE.get(MainActivity.this, Constant.FJ, ""));
        tv_pcs.setText("" + SPTools.INSTANCE.get(MainActivity.this, Constant.PCS, ""));
        tv_zrq.setText("" + SPTools.INSTANCE.get(MainActivity.this, Constant.ZRQ, ""));
        tv_jy.setText("" + SPTools.INSTANCE.get(MainActivity.this, Constant.POLICENAME, ""));

        appDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM) + "/" + Constant.MKDIRNAME);
        if (!appDir.exists()) {
            appDir.mkdirs();
        }
    }

    private void initView() {
        ll_camera = findViewById(R.id.ll_camera);
        ll_note = findViewById(R.id.ll_note);

        tv_fj = findViewById(R.id.tv_fj);
        tv_pcs = findViewById(R.id.tv_pcs);
        tv_zrq = findViewById(R.id.tv_zrq);
        tv_jy = findViewById(R.id.tv_jy);
        iv_title = findViewById(R.id.iv_title);
        iv_logout = findViewById(R.id.iv_logout);
    }

    private void onClick() {
        ll_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                picPath = Constant.SDPATH + "/" + System.currentTimeMillis() + ".jpg";

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


            }
        });
        iv_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("提示");
                builder.setMessage("确定退出登录吗？");
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        net_logout();
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                builder.show();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {

            if (requestCode == 0) {
                showProgressDialog();
//                new Handler().postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
                // 刷新在系统相册中显示
//                        try {
//                            MediaStore.Images.Media.insertImage(getContentResolver(),
//                                    MediaStore.Images.Media.getBitmap(MainActivity.this.getContentResolver(), mUri),
//                                    getResources().getString(com.yiqikeji.zsrxbd.R.string.app_name), "");
//
//                            Log.e("zj","mUri = "+(mUri== null));
//                            Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
//                            intent.setData(mUri);
////                    intent.setData(data.getData());
//                            sendBroadcast(intent);
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                            Log.e("zj","拍照："+e.toString());
//                        }
                Log.e("zj", "mUri = " + (mUri == null));

                String[] names = mUri.getPath().split("/");
                IMAGE_FILE_NAME = names[names.length - 1];
                if (!IMAGE_FILE_NAME.toLowerCase().endsWith(".jpg") && !IMAGE_FILE_NAME.toLowerCase().endsWith(".png")) {
                    IMAGE_FILE_NAME = IMAGE_FILE_NAME + ".png";
                }
                dismissProgressDialog();

                Intent intent2 = new Intent(MainActivity.this, SearchActivity.class);
                intent2.putExtra("picPath", picPath);
                intent2.putExtra("imageName", IMAGE_FILE_NAME);

                startActivity(intent2);
//                    }
//                },1000);
            }
        }
    }


    private void net_logout() {

        ArrayList<OperateRequestBean.Params.Data.Operation> operations = new ArrayList<>();
        OperateRequestBean.Params.Data.Operation operation = new OperateRequestBean.Params.Data.Operation();
        String condition = "policeId='" + SPTools.INSTANCE.get(this, Constant.POLICEID, "") + "' and token = '" + SPTools.INSTANCE.get(MainActivity.this, Constant.TOKEN, "") + "'";
        operation.setOperationId(StrTools.getRandomString(32));
        operation.setCondition(condition);
        operation.setDataObjId(Constant.DataObjId);

        ArrayList<OperateRequestBean.Params.Data.Operation.OperationData> operationDataArrayList = new ArrayList<>();
        OperateRequestBean.Params.Data.Operation.OperationData operationData = new OperateRequestBean.Params.Data.Operation.OperationData();
        ArrayList<OperateRequestBean.Params.Data.Operation.OperationData.FieldValues> fieldValues = new ArrayList<>();
        OperateRequestBean.Params.Data.Operation.OperationData.FieldValues fieldValues1 = new OperateRequestBean.Params.Data.Operation.OperationData.FieldValues();
        fieldValues1.setField("token");
        fieldValues1.setValue("");
        fieldValues.add(fieldValues1);
        operationData.setFieldValues(fieldValues);
        operationDataArrayList.add(operationData);
        operation.setData(operationDataArrayList);

        operations.add(operation);
        String json = DataTools.putOperateParams(MainActivity.this, Constant.UPTOKEN, operations);

        OperateNetTools.net(json, Urls.auth_logout, MainActivity.this, new OperateNetTools.MyCallBack() {
            @Override
            public void getData(BaseBean response) {

//                user.addAppLog(MainActivity.this,"退出登录",getResources().getString(R.string.app_name), 1);
                String userName = (String) SPTools.INSTANCE.get(MainActivity.this, Constant.USERNAME, "");

                SPTools.INSTANCE.clear(MainActivity.this);

                SPTools.INSTANCE.put(MainActivity.this, Constant.USERNAME, userName);

                finish();
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
            }
        });
    }
}
