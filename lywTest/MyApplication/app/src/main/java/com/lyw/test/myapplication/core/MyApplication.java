package com.lyw.test.myapplication.core;

import android.app.Application;

/**
 * author: Auser
 * created on: 2017/12/14 17:34
 * description:
 */
public class MyApplication extends Application {
    public static MyApplication mInstance;

    public static MyApplication getInstance() {
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }
}