package com.bwie.test.liuhaifeng20171014.presenter;

import android.text.TextUtils;

import com.bwie.test.liuhaifeng20171014.model.utils.RegisterModel;
import com.bwie.test.liuhaifeng20171014.view.iview.RegisterView;

import java.io.IOException;

import okhttp3.Call;

/**
 * 刘海峰.13:22
 */

public class RegisterPresenter implements RegisterModel.IRegister{
    private RegisterModel registerModel;
    private RegisterView registerView;
    public RegisterPresenter(RegisterView registerView) {

        this.registerView = registerView;
        registerModel = new RegisterModel();
        registerModel.setiRegister(this);

    }
    public void regist(String mobile,String pass){
        if (TextUtils.isEmpty(mobile)) {
            registerView.nameError("用户名不能为空");
            return;
        }
        if (TextUtils.isEmpty(pass)) {
            registerView.passError("密码不能为空");
            return;
        }

        registerModel.register(mobile,pass);
    }

    @Override
    public void registerSuccess(String code, String msg) {

    }

    @Override
    public void registerFail(String code, String msg) {

    }

    @Override
    public void failure(Call call, IOException e) {

    }
}
