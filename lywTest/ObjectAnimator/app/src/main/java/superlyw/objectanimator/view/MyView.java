package superlyw.objectanimator.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

/**
 * Created by lyw39 on 2017/12/31.
 */

public class MyView extends android.support.v7.widget.AppCompatTextView {

    private int backgrounds;
    public MyView(Context context) {
        this(context, null);
    }

    public MyView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    public int getBackgrounds() {
        return backgrounds;
    }

    public void setBackgrounds(int backgrounds) {
        setBackgroundColor(backgrounds);
    }

    private void initView() {
    }
}
