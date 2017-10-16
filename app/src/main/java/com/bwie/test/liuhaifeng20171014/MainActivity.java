package com.bwie.test.liuhaifeng20171014;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.bwie.test.liuhaifeng20171014.presenter.LoginPresenter;
import com.bwie.test.liuhaifeng20171014.view.activity.PersonalActivity;
import com.bwie.test.liuhaifeng20171014.view.activity.ZhuCeActivity;
import com.bwie.test.liuhaifeng20171014.view.iview.LoginView;

import java.io.IOException;

import okhttp3.Call;



public class MainActivity extends AppCompatActivity implements LoginView{

    /**
     * 手机号
     */
    private EditText mMobileEt, mPassEt;

    private ProgressBar mProgressbar;
    private LoginPresenter loginPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();

    }

    private void initView() {
        mMobileEt = (EditText) findViewById(R.id.et_mobile);
        mPassEt = (EditText) findViewById(R.id.et_pass);
        mProgressbar = (ProgressBar) findViewById(R.id.progressbar_login);

    }
    private void initData() {
        loginPresenter =new LoginPresenter(this);
    }

    @Override
    public void showProgressbar() {

    }

    @Override
    public void hideProgressbar() {

    }

    @Override
    public void nameError(String msg) {

    }

    @Override
    public void passError(String msg) {

    }

    @Override
    public void loginSuccess(String code, String msg) {
        Intent intent=new Intent(MainActivity.this, PersonalActivity.class);
        startActivity(intent);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                Toast.makeText(MainActivity.this,"登录成功",Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void loginFail(String code, String msg) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                Toast.makeText(MainActivity.this,"登录失败",Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void failure(Call call, IOException e) {

    }
    public void login(View v) {

        loginPresenter.login(mMobileEt.getText().toString(),mPassEt.getText().toString());


//        new LoginModel().login(mMobileEt.getText().toString(), mPassEt.getText().toString());

    }
    public void register(View v){
        startActivity(new Intent(MainActivity.this,ZhuCeActivity.class));
    }


}
