package com.rxjava.lyw.rxjavatest;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.rxjava.lyw.rxjavatest.utils.BitmapUtils;

import java.net.HttpURLConnection;

import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/***
 * 用Rxjava实现下载图片、并显示
 */
public class MainActivity extends AppCompatActivity {
    private int i = 0;
    private ImageView mImageView;
    private String[] imgUrls = {"http://h.hiphotos.baidu.com/image/pic/item/8b82b9014a90f603b42b9c8d3012b31bb151ed65.jpg",
            "http://g.hiphotos.baidu.com/image/pic/item/d439b6003af33a87477d93d0cf5c10385243b5ec.jpg",
            "http://e.hiphotos.baidu.com/image/pic/item/95eef01f3a292df50c0a3bdfb5315c6035a8732b.jpg",
            "http://g.hiphotos.baidu.com/image/pic/item/94cad1c8a786c917013053f3c03d70cf3ac757cc.jpg",
            "http://b.hiphotos.baidu.com/image/pic/item/d0c8a786c9177f3ea6f7381e7acf3bc79e3d56e6.jpg",
            "http://b.hiphotos.baidu.com/image/pic/item/38dbb6fd5266d016a88d98e29e2bd40734fa3567.jpg",
            "http://g.hiphotos.baidu.com/image/pic/item/80cb39dbb6fd52664e313cdea218972bd50736e5.jpg",
            "http://a.hiphotos.baidu.com/image/pic/item/838ba61ea8d3fd1ff91466293a4e251f95ca5f80.jpg",
            "http://c.hiphotos.baidu.com/image/pic/item/b219ebc4b74543a940fbfc0617178a82b8011413.jpg"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mImageView = findViewById(R.id.img_pic);
        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (i < imgUrls.length) {
                    loadPic(imgUrls[i]);
                    i++;
                    return;
                }
                i = 0;
            }
        });
    }

    private void loadPic(final String url) {
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
                .subscribe(new Subscriber<Bitmap>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Bitmap bitmap) {
                        mImageView.setImageBitmap(bitmap);
                    }
                });
    }

}
