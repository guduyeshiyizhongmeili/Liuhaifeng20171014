package com.bwie.test.liuhaifeng20171014.view.iview;

import java.io.IOException;

import okhttp3.Call;

/**
 * 刘海峰.13:24
 */

public interface RegisterView {
    void nameError(String msg);
    void passError(String msg);
    void registerSuccess(String code, String msg);

    void registerFail(String code, String msg);

    void failure(Call call, IOException e);

}
