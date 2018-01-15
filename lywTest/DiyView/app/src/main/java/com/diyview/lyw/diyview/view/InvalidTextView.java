package com.diyview.lyw.diyview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

/**
 * Created by Administrator on 2018/1/15 0015.
 */

public class InvalidTextView extends android.support.v7.widget.AppCompatTextView {
    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    public InvalidTextView(Context context) {
        this(context, null);
    }

    public InvalidTextView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public InvalidTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initDraw();
    }

    private void initDraw() {
        mPaint.setColor(Color.RED);
        mPaint.setStrokeWidth(1.5f);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = getWidth();
        int height = getHeight();
        canvas.drawLine(0, height / 2, width, height / 2, mPaint);
    }
}
