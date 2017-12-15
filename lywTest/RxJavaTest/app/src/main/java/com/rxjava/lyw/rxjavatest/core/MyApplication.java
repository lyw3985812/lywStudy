package com.rxjava.lyw.rxjavatest.core;

import android.app.Application;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.rxjava.lyw.rxjavatest.BuildConfig;
import com.rxjava.lyw.rxjavatest.R;

import org.xutils.x;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/***
 * 用Rxjava实现下载图片、并显示
 */
public class MyApplication extends Application {
    private static MyApplication mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        x.Ext.init(this);
        x.Ext.setDebug(BuildConfig.DEBUG);
    }

    public MyApplication getInstance(){
        return mInstance;
    }
}
