package com.rxjava.lyw.rxjavatest.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class BitmapUtils {

    public static Bitmap getNetWorkBitmap(String urlString)  {
        URL imgUrl = null;
        Bitmap bitmap = null;
        InputStream is = null;
        try {
            imgUrl = new URL(urlString);
            // 使用HttpURLConnection打开连接
            HttpURLConnection urlConn = (HttpURLConnection) imgUrl.openConnection();
            urlConn.setDoInput(true);
            urlConn.connect();
            // 将得到的数据转化成InputStream
             is = urlConn.getInputStream();
            // 将InputStream转换成Bitmap
            bitmap = BitmapFactory.decodeStream(is);
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            System.out.println("[getNetWorkBitmap->]MalformedURLException");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("[getNetWorkBitmap->]IOException");
            e.printStackTrace();
        }finally {
            try {
                if(is != null){
                    is.close();
                }
            }catch (IOException e){
                System.out.println("[getNetWorkBitmap->]IOException");
                e.printStackTrace();
            }
        }
        return bitmap;
    }

    /**
     * 用RxJava请求URL，返回BitMap；
     * @param url
     * @param subscriber
     */
    public static void loadPicRx(final String url, Subscriber subscriber) {
        Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext(url);
            }
        }).map(new Func1<String, Bitmap>() {
            @Override
            public Bitmap call(String s) {
                Bitmap bitmap = BitmapUtils.getNetWorkBitmap(s);
                return bitmap;
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }
}
