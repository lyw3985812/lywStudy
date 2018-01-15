package com.diyview.lyw.diyview.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.diyview.lyw.diyview.R;

/**
 * Created by Administrator on 2018/1/15 0015.
 */

public class RectView extends View {
    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private int mColor;

    public RectView(Context context) {
        this(context, null);
    }

    public RectView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RectView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray mTypedArray = null;
        if (attrs != null) {
            mTypedArray = context.obtainStyledAttributes(attrs, R.styleable.RectView);
        }

        if (mTypedArray != null) {
            mColor = mTypedArray.getColor(R.styleable.RectView_rect_color, Color.RED);
            mTypedArray.recycle();
        }
        initDraw();
    }

    private void initDraw() {
        mPaint.setColor(mColor);
        mPaint.setStrokeWidth(1.5f);
    }

    /**
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthSpecMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightSpecMode = MeasureSpec.getMode(heightMeasureSpec);
        int widthSpecSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSpecSize = MeasureSpec.getSize(heightMeasureSpec);
        /*
         * 解决 wrap_content效果 与 math_partent效果一致。
         * 原因：view的onMeasure() 执行
         * setMeasuredDimension(getDefaultSize(getSuggestedMinimumWidth(), widthMeasureSpec),
         * getDefaultSize(getSuggestedMinimumHeight(), heightMeasureSpec));
         *  -----> getDefaultSize()方法，specMode :AT_MOST 与 EXACTLY 返回的 size都 = MeasureSpec.getSize();
         *  注意：setMeasuredDimension();接受参数单位是px;
         */
        if (widthSpecMode == MeasureSpec.AT_MOST && heightSpecMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(600, 600);
        } else if (widthSpecMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(600, heightSpecSize);
        } else if (heightSpecMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(widthSpecSize, 600);
        }
    }

    /**
     * 这里对padding属性处理
     *
     * @param canvas
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        /**
         * 这里对padding属性处理
         */
        int paddingLeft = getPaddingLeft();
        int paddingRight = getPaddingRight();
        int paddingTop = getPaddingTop();
        int paddingBottom = getPaddingBottom();
        int width = getWidth() - paddingLeft - paddingRight;
        int height = getHeight() - paddingTop - paddingBottom;
        canvas.drawRect(0 + paddingLeft, 0 + paddingTop, width + paddingLeft, height + paddingTop, mPaint);
    }
}
