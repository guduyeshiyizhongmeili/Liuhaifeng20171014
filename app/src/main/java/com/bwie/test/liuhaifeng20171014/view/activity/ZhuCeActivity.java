package com.bwie.test.liuhaifeng20171014.view.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.bwie.test.liuhaifeng20171014.MainActivity;
import com.bwie.test.liuhaifeng20171014.R;
import com.bwie.test.liuhaifeng20171014.presenter.RegisterPresenter;
import com.bwie.test.liuhaifeng20171014.view.iview.RegisterView;

import java.io.IOException;

import okhttp3.Call;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.L;

public class ZhuCeActivity extends AppCompatActivity implements RegisterView{
    private EditText mEtMobile;
    /**
     * 密码
     */
    private EditText mEtPass;

    /**
     * 注册完成
     */

    private RegisterPresenter registerPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhu_ce);
        initView();
        initdata();

    }
    private void initdata() {
        registerPresenter=new RegisterPresenter(this);
    }

    private void initView() {
        mEtMobile = (EditText) findViewById(R.id.et_mobile);
        mEtPass = (EditText) findViewById(R.id.et_pass);



    }


    @Override
    public void nameError(String msg) {

    }

    @Override
    public void passError(String msg) {

    }

    @Override
    public void registerSuccess(String code, String msg) {











    }

    @Override
    public void registerFail(String code, String msg) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(ZhuCeActivity.this,"注册失败",Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void failure(Call call, IOException e) {

    }
    public void over(View v){
        registerPresenter.regist(mEtMobile.getText().toString(),mEtPass.getText().toString());
        Intent intent=new Intent(ZhuCeActivity.this,MainActivity.class);
        startActivity(intent);

    }
}
