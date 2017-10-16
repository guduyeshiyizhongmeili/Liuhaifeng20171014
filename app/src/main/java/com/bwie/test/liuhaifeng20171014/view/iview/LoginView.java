package com.bwie.test.liuhaifeng20171014.view.iview;

import java.io.IOException;

import okhttp3.Call;

/**
 * 刘海峰.19:09
 */

public interface LoginView {
    void showProgressbar();

    void hideProgressbar();

    void nameError(String msg);

    void passError(String msg);

    void loginSuccess(String code, String msg);

    void loginFail(String code, String msg);

    void failure(Call call, IOException e);

}

