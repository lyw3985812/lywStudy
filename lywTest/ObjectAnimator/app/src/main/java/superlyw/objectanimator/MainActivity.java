package superlyw.objectanimator;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.annotation.TargetApi;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import superlyw.objectanimator.view.MyView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private MyView mTvTest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTvTest = findViewById(R.id.tv_test);
        mTvTest.setOnClickListener(this);
    }

    @TargetApi(Build.VERSION_CODES.M)
    private void doAnimator(){
        ObjectAnimator objectAnimator = ObjectAnimator.ofInt(mTvTest,"backgrounds",getColor(R.color.colorPrimary));
        objectAnimator.setDuration(300);
        objectAnimator.start();
    }

    /**
     * after(Animator anim):将现有动画插入到传入的动画后执行
     * before(Animator anim):将现有动画插入到传入的动画前执行
     * with(Animator anim)：将现有动画和传入的动画同时执行
     */
    @TargetApi(Build.VERSION_CODES.M)
    private void doAnimatorSet(){
        ObjectAnimator objectAnimator = ObjectAnimator.ofInt(mTvTest,"backgrounds",getColor(R.color.colorPrimary));
        objectAnimator.setDuration(3000);
        ObjectAnimator objectAnimator1 = ObjectAnimator.ofFloat(mTvTest,"y",600);
        ObjectAnimator objectAnimator2 = ObjectAnimator.ofFloat(mTvTest,"x",800);
        objectAnimator1.setDuration(3000);
        objectAnimator2.setDuration(3000);
        AnimatorSet set = new AnimatorSet();
        set.play(objectAnimator).after(objectAnimator1).with(objectAnimator2);
        set.start();
    }

    /**
     * PropertyValuesHolder 只能一起执行
     */
    @TargetApi(Build.VERSION_CODES.M)
    private void doPropertyValuesHolder(){
        PropertyValuesHolder objectAnimator = PropertyValuesHolder.ofInt("backgrounds",getColor(R.color.colorPrimary));
        PropertyValuesHolder objectAnimator1 = PropertyValuesHolder.ofFloat("y",600);
        PropertyValuesHolder objectAnimator2 = PropertyValuesHolder.ofFloat("x",800);
        ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(mTvTest,objectAnimator,objectAnimator1,objectAnimator2);
        animator.setDuration(3000);
        animator.start();
    }

    /**
     * 在XML中使用属性动画
     */
    private void doXmlAnimator(){
        Animator animator = AnimatorInflater.loadAnimator(this,R.animator.animator);
        animator.setTarget(mTvTest);
        animator.start();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_test:
//                doAnimator();
//                doAnimatorSet();
//                doPropertyValuesHolder();
                doXmlAnimator();
                break;
        }
    }
}
