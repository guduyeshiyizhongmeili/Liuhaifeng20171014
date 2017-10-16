package com.bwie.test.liuhaifeng20171014.model.utils;

import com.bwie.test.liuhaifeng20171014.model.bean.LoginBean;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 刘海峰.16:09
 */

public class LoginModel {

    public void login(String mobile,String pwd){
        OkHttpClient okHttpClient = new OkHttpClient();
        FormBody.Builder builder = new FormBody.Builder();
        builder.add("mobile",mobile);
        builder.add("password",pwd);
        FormBody body = builder.build();

        Request request = new Request.Builder().post(body).url(Api.LOGIN_API).build();

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                iLogin.failure(call,e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                if (response!=null&&response.isSuccessful()){
                    String result = response.body().string();

                    System.out.println("result======="+result);
                    Gson gson=new Gson();
               LoginBean bean=gson.fromJson(result, LoginBean.class);
                    if(bean.getCode().equals("0")){
                        iLogin.loginSuccess("result.code","result.msg") ;


                    }
                    else{
                    iLogin.loginFail("result.code","result.msg");

                }



                }
            }
        });

    }
    private ILogin iLogin;
    public void setiLogin(ILogin iLogin) {
        this.iLogin = iLogin;
    }
    public interface ILogin{
        void loginSuccess(String code, String msg);
        void loginFail(String code, String msg);
        void failure(Call call, IOException e);
    }


}
