package com.rxjava.lyw.rxjavatest.ui.adapter;

import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.rxjava.lyw.rxjavatest.R;
import com.rxjava.lyw.rxjavatest.core.MyApplication;
import com.rxjava.lyw.rxjavatest.utils.BitmapUtils;

import rx.Subscriber;

public class XRecyclerViewAdapter extends RecyclerView.Adapter<XRecyclerViewAdapter.ViewHolder> {

    private String[] imgs;

    public XRecyclerViewAdapter(String[] datas) {
        this.imgs = datas;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler, parent, false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mImageview.setImageDrawable(MyApplication.getInstance().getDrawable(R.mipmap.ic_launcher));
        BitmapUtils.loadPicRx(imgs[position], new Subscriber<Bitmap>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Bitmap bitmap) {
                holder.mImageview.setImageBitmap(bitmap);
            }
        });
    }

    @Override
    public int getItemCount() {
        return imgs.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView mImageview;

        public ViewHolder(View view) {
            super(view);
            mImageview = view.findViewById(R.id.img_pic);
        }
    }
}
