package superlyw.scrollertest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import superlyw.scrollertest.view.ScrollerText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ScrollerText mTv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTv = findViewById(R.id.tv_scroll);
        mTv.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_scroll:
                mTv.smoothScrollTo(-800,0);
                break;
        }
    }
}
