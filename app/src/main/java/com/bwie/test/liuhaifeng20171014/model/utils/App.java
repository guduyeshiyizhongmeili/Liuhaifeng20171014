package com.bwie.test.liuhaifeng20171014.model.utils;

import android.app.Application;

import org.xutils.BuildConfig;
import org.xutils.x;

/**
 * Created by dell on 2017/10/4.
 */
public class App extends Application {
           @Override
           public void onCreate() {
               super.onCreate();
               x.Ext.init(this);
               x.Ext.setDebug(BuildConfig.DEBUG);

           }

}
