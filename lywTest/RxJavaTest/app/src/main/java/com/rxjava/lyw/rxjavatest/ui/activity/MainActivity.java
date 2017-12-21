package com.rxjava.lyw.rxjavatest.ui.activity;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.rxjava.lyw.rxjavatest.R;
import com.rxjava.lyw.rxjavatest.ui.adapter.XRecyclerViewAdapter;
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
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private int i = 0;
    private ImageView mImageView;
    private XRecyclerView mRecyclerView;
    private XRecyclerViewAdapter mAdapter;
    private String[] imgUrls = {"http://h.hiphotos.baidu.com/image/pic/item/8b82b9014a90f603b42b9c8d3012b31bb151ed65.jpg",
            "http://g.hiphotos.baidu.com/image/pic/item/d439b6003af33a87477d93d0cf5c10385243b5ec.jpg",
            "http://e.hiphotos.baidu.com/image/pic/item/95eef01f3a292df50c0a3bdfb5315c6035a8732b.jpg",
            "http://g.hiphotos.baidu.com/image/pic/item/94cad1c8a786c917013053f3c03d70cf3ac757cc.jpg",
            "http://b.hiphotos.baidu.com/image/pic/item/d0c8a786c9177f3ea6f7381e7acf3bc79e3d56e6.jpg",
            "http://b.hiphotos.baidu.com/image/pic/item/38dbb6fd5266d016a88d98e29e2bd40734fa3567.jpg",
            "http://g.hiphotos.baidu.com/image/pic/item/80cb39dbb6fd52664e313cdea218972bd50736e5.jpg",
            "http://a.hiphotos.baidu.com/image/pic/item/838ba61ea8d3fd1ff91466293a4e251f95ca5f80.jpg",
            "http://c.hiphotos.baidu.com/image/pic/item/b219ebc4b74543a940fbfc0617178a82b8011413.jpg"};

    private Subscriber<Bitmap> subscriber = new Subscriber<Bitmap>() {
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
    };

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
                    BitmapUtils.loadPicRx(imgUrls[i], subscriber);
                    i++;
                    return;
                }
                i = 0;
            }
        });
        initRecyclerView();
    }

    private void initRecyclerView() {
        mRecyclerView = findViewById(R.id.xrecyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setRefreshProgressStyle(ProgressStyle.BallGridBeat);
        mRecyclerView.setLoadingMoreProgressStyle(ProgressStyle.Pacman);
        View head = LayoutInflater.from(this).inflate(R.layout.head_main, (ViewGroup) findViewById(android.R.id.content), false);
        mRecyclerView.addHeaderView(head);
        mRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                Toast.makeText(MainActivity.this, "下拉刷新", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLoadMore() {
                Toast.makeText(MainActivity.this, "加载更多", Toast.LENGTH_SHORT).show();
            }
        });
        mAdapter = new XRecyclerViewAdapter(imgUrls);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

        }
    }
}
