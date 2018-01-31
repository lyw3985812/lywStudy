package com.lyw.greendaotest.core;

import android.app.Application;

/**
 * Created by Administrator on 2018/1/29 0029.
 */

public class GreenDaoAppilcation extends Application {
    public static GreenDaoAppilcation mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }

    public static GreenDaoAppilcation getInstance(){
        return mInstance;
    }
}
