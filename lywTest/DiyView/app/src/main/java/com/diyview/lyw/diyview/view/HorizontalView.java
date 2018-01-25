package com.diyview.lyw.diyview.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Scroller;

/**
 * Created by Administrator on 2018/1/16 0016.
 */

public class HorizontalView extends ViewGroup {
    private int lastInterceptX;
    private int lastInterceptY;
    private int lastX;
    private int lastY;
    private int childWidth;
    private Scroller scroller;
    private VelocityTracker tracker;
    private int currentIndex = 0;

    public HorizontalView(Context context) {
        this(context, null);
    }

    public HorizontalView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public HorizontalView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        scroller = new Scroller(getContext());
        tracker = VelocityTracker.obtain();
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int childCount = getChildCount();
        int left = 0;
        View child;
        for (int i = 0; i < childCount; i++) {
            child = getChildAt(i);
            if (child.getVisibility() != View.GONE) {
                int width = child.getMeasuredWidth();
                childWidth = width;
                child.layout(left, 0, left + width, child.getMeasuredHeight());
                left = left + width;
            }
        }
    }

    /**
     * 这里没考虑 HorizontalView 的padding和 子元素的Margin
     *
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(widthMeasureSpec);

        //计算子控件
        measureChildren(widthMeasureSpec, heightMeasureSpec);
        //如果没有子元素，宽高为0；
        if (getChildCount() == 0) {
            setMeasuredDimension(0, 0);
        }
        //宽和高都是AT_MOST,则宽度设置为所有子元素的和，高度设置为第一个子元素的高度
        else if (widthMode == MeasureSpec.AT_MOST && heightMode == MeasureSpec.AT_MOST) {
            View childOne = getChildAt(0);
            int childHeight = childOne.getMeasuredHeight();
            int totalWidth = 0;
            for (int i = 0; i < getChildCount(); i++) {
                totalWidth = totalWidth + getChildAt(i).getMeasuredWidth();
            }
            setMeasuredDimension(totalWidth, childHeight);
        }//宽度是AT_MOST,则宽度为所有子元素宽度和
        else if (widthMode == MeasureSpec.AT_MOST) {
            int totalWidth = 0;
            for (int i = 0; i < getChildCount(); i++) {
                totalWidth = totalWidth + getChildAt(i).getMeasuredWidth();
            }
            setMeasuredDimension(totalWidth, heightSize);
        }//高度为AT_MOST，则高度为第一个子元素高度。
        else if (heightMode == MeasureSpec.AT_MOST) {
            int childHeight = getChildAt(0).getMeasuredHeight();
            setMeasuredDimension(widthSize, childHeight);
        }
    }

    //拦截水平滑动
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        boolean intercept = false;
        int x = (int) ev.getX();
        int y = (int) ev.getY();
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                intercept = false;
                if (!scroller.isFinished()) {
                    scroller.abortAnimation();
                }
                break;
            case MotionEvent.ACTION_MOVE:
                int delatX = x - lastInterceptX;
                int delatY = y - lastInterceptY;
                if (Math.abs(delatX) - Math.abs(delatY) > 0) {
                    intercept = true;
                } else {
                    intercept = false;
                }
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        lastX = x;
        lastY = y;
        lastInterceptX = x;
        lastInterceptY = y;
        return intercept;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE:
                int deltaX = x - lastX;
                scrollBy(-deltaX, 0);
                break;
            case MotionEvent.ACTION_UP:
                int distance = getScrollX() - currentIndex * childWidth;
                if ((Math.abs(distance) > 150 && childWidth > 500) || Math.abs(distance) > childWidth / 2) {
                    if (distance > 0) {
                        currentIndex++;
                    } else {
                        currentIndex--;
                    }
                } else {
                    tracker.computeCurrentVelocity(1000);
                    float xV = tracker.getXVelocity();
                    if (Math.abs(xV) > 50) {
                        if (xV > 0) {
                            currentIndex--;
                        } else {
                            currentIndex++;
                        }
                    }
                }
                currentIndex = currentIndex < 0 ? 0 : currentIndex > getChildCount() - 1 ? getChildCount() - 1 : currentIndex;
                smoothScrollTo(currentIndex * childWidth, 0);
                tracker.clear();
                break;
        }
        lastX = x;
        lastY = y;
        return super.onTouchEvent(event);
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if (scroller.computeScrollOffset()) {
            scrollTo(scroller.getCurrX(), scroller.getCurrY());
            postInvalidate();
        }
    }

    public void smoothScrollTo(int destX, int destY) {
        scroller.startScroll(getScrollX(), getScrollY(), destX - getScrollX(), destY - getScrollY());
        invalidate();
    }
}
