package com.bwie.test.liuhaifeng20171014.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bwie.test.liuhaifeng20171014.R;
import com.bwie.test.liuhaifeng20171014.model.bean.InformationBean;
import com.bwie.test.liuhaifeng20171014.model.utils.Api;
import com.google.gson.Gson;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class PersonalActivity extends AppCompatActivity implements View.OnClickListener {

    /**
     * 个人信息
     */
    private TextView mTextView2;
    /**
     * 头像
     */
    private TextView mTextView3;
    private ImageView mImageView;
    private ImageView mImageView2;
    private LinearLayout mLine1;
    /**
     * 用户名
     */
    private TextView mTextView4;
    /**
     * 111
     */
    private TextView mTextView5;
    private LinearLayout mLine2;
    /**
     * 昵称
     */
    private TextView mTextView6;
    /**
     * kson
     */
    private TextView mTextView7;
    private ImageView mImageView3;
    private LinearLayout mLine3;
    /**
     * 退出登录
     */
    private Button mButton;
    /**
     * 跳转到商品搜索页
     */
    private Button mButton2;
    private RelativeLayout mActivityPersonal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal);
        initView();


    }

    private void initView() {
        mTextView2 = (TextView) findViewById(R.id.textView2);
        mTextView2.setOnClickListener(this);
        mTextView3 = (TextView) findViewById(R.id.textView3);
        mTextView3.setOnClickListener(this);
        mImageView = (ImageView) findViewById(R.id.imageView);
        mImageView.setOnClickListener(this);
        mImageView2 = (ImageView) findViewById(R.id.imageView2);
        mImageView2.setOnClickListener(this);
        mLine1 = (LinearLayout) findViewById(R.id.line1);
        mLine1.setOnClickListener(this);
        mTextView4 = (TextView) findViewById(R.id.textView4);
        mTextView4.setOnClickListener(this);
        mTextView5 = (TextView) findViewById(R.id.textView5);
        mTextView5.setOnClickListener(this);
        mLine2 = (LinearLayout) findViewById(R.id.line2);
        mLine2.setOnClickListener(this);
        mTextView6 = (TextView) findViewById(R.id.textView6);
        mTextView6.setOnClickListener(this);
        mTextView7 = (TextView) findViewById(R.id.textView7);
        mTextView7.setOnClickListener(this);
        mImageView3 = (ImageView) findViewById(R.id.imageView3);
        mImageView3.setOnClickListener(this);
        mLine3 = (LinearLayout) findViewById(R.id.line3);
        mLine3.setOnClickListener(this);
        mButton = (Button) findViewById(R.id.button);
        mButton.setOnClickListener(this);
        mButton2 = (Button) findViewById(R.id.button2);
        mButton2.setOnClickListener(this);
        mActivityPersonal = (RelativeLayout) findViewById(R.id.activity_personal);
        mActivityPersonal.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.textView2:
                break;
            case R.id.textView3:
                break;
            case R.id.imageView:
                File file = null;
                if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {

                    File dir = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/kson/");
                    if (!dir.exists()) {
                        dir.mkdirs();
                    }
                    //此处的文件是你本地存储的文件，为了演示方便，写死
                    file = new File(dir, "003.jpg");//第一种获取文件写法
//            File file1  = new File(dir.getPath()+"t.jpg");//第二种获取文件方法
                    if (!file.exists()) {
                        try {
                            file.createNewFile();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }

                Map<String, Object> params = new HashMap<>();
                params.put("uid", "372");
                if (file != null) {
                    params.put("file", file);
                    System.out.println("filepath:" + file.getPath());
                }
                //上传文件
                upload(params);
                //获取用户信息
                getUserInfo();
Glide.with(PersonalActivity.this).load(file).into(mImageView);
                break;
            case R.id.imageView2:
                break;
            case R.id.line1:
                break;
            case R.id.textView4:

                break;
            case R.id.textView5:
                mTextView5.setText("13542111111");
                break;
            case R.id.line2:
                break;
            case R.id.textView6:

                break;
            case R.id.textView7:
                mTextView7.setText("haha");
                break;
            case R.id.imageView3:
                break;
            case R.id.line3:
                break;
            case R.id.button:
                break;
            case R.id.button2:
                Intent intent=new Intent(PersonalActivity.this,SearchActivity.class);
                startActivity(intent);
                break;
            case R.id.activity_personal:
                break;
        }

    }
    public void getUserInfo() {
        //post请求方式一：application/x-www-form-urlencoded（只能上传string类型的参数，不能上传文件）
        OkHttpClient okHttpClient = new OkHttpClient();
        FormBody.Builder builder = new FormBody.Builder();
        builder.add("uid", "372");

        Request request = new Request.Builder().post(builder.build()).url(Api.GETUSERINFO).build();

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(okhttp3.Call call, IOException e) {

            }

            @Override
            public void onResponse(okhttp3.Call call, Response response) throws IOException {
                if (response.isSuccessful()) {

                    System.out.println("getuserinfo:"+response.body().string());

                }
            }
        });
    }

    /**
     * post方式二：stirng类型参数和上传文件参数
     */
    public void upload(Map<String, Object> params) {
        //post请求方式二：multipart/form-data(不仅能够上传string类型的参数，还可以上传文件（流的形式，file）)
        OkHttpClient okHttpClient1 = new OkHttpClient();


        MultipartBody.Builder builder1 = new MultipartBody.Builder();
        builder1.setType(MultipartBody.FORM);
        for (Map.Entry<String, Object> stringObjectEntry : params.entrySet()) {
            String key = stringObjectEntry.getKey();
            Object value = stringObjectEntry.getValue();
            if (value instanceof File) {//如果请求的值是文件
                File file = (File) value;
                //MediaType.parse("application/octet-stream")以二进制的形式上传文件
                builder1.addFormDataPart(key, ((File) value).getName(), RequestBody.create(MediaType.parse("application/octet-stream"), file));
            } else {//如果请求的值是string类型
                builder1.addFormDataPart(key, value.toString());
            }
        }


        Request request1 = new Request.Builder().post(builder1.build()).url(Api.UPLOAD).build();

        okHttpClient1.newCall(request1).enqueue(new Callback() {
            @Override
            public void onFailure(okhttp3.Call call, IOException e) {

            }

            @Override
            public void onResponse(okhttp3.Call call, Response response) throws IOException {

                if (response.isSuccessful()){
                    System.out.println("fileuploadsuccess：" + response.body().string());
                }
            }
        });
    }

}
