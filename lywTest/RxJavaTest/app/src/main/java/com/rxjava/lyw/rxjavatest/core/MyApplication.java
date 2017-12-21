package com.rxjava.lyw.rxjavatest.core;

import android.app.Application;

/***
 * 用Rxjava实现下载图片、并显示
 */
public class MyApplication extends Application {
    private static MyApplication mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }

    public static MyApplication getInstance() {
        return mInstance;
    }
}
