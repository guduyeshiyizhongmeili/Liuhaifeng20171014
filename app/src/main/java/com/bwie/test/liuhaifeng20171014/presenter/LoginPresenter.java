package com.bwie.test.liuhaifeng20171014.presenter;

import android.text.TextUtils;

import com.bwie.test.liuhaifeng20171014.model.utils.LoginModel;
import com.bwie.test.liuhaifeng20171014.view.iview.LoginView;

import java.io.IOException;

import okhttp3.Call;

/**
 * 刘海峰.17:11
 */

public class LoginPresenter implements LoginModel.ILogin{
    private LoginModel loginModel;
    private LoginView loginView;


    public LoginPresenter(LoginView loginView) {

        this.loginView = loginView;
        loginModel = new LoginModel();
        loginModel.setiLogin(this);

    }

    public void login(String mobile, String pass) {

        loginView.showProgressbar();

        if (TextUtils.isEmpty(mobile)) {
            loginView.nameError("用户名不能为空");
            return;
        }
        if (TextUtils.isEmpty(pass)) {
            loginView.passError("密码不能为空");
            return;
        }

        loginModel.login(mobile, pass);

    }

    @Override
    public void loginSuccess(String code, String msg) {
        loginView.loginSuccess(code, msg);
        loginView.hideProgressbar();

    }

    @Override
    public void loginFail(String code, String msg) {
        loginView.loginFail(code, msg);
        loginView.hideProgressbar();

    }

    @Override
    public void failure(Call call, IOException e) {
        loginView.failure(call, e);
        loginView.hideProgressbar();
    }
}

