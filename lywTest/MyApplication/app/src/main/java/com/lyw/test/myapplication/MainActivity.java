package com.lyw.test.myapplication;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.lyw.test.myapplication.Service.TesstService;
import com.lyw.test.myapplication.utils.DateUtils;
import com.lyw.test.myapplication.utils.NetWorkUtils;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView mTvLogcat;

    BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals("TesstService")) {
                StringBuffer buffer = new StringBuffer();
                buffer.append(mTvLogcat.getText());
                buffer.append(intent.getStringExtra("TesstService"));
                mTvLogcat.setText(buffer);
            }
            if (action.equals("LocationManager")) {
                Log.i("TesstService", "接受定位广播");
                String str = intent.getStringExtra("LocationManager");
                shake(str);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initview();
        regiserReceiver();
        awakenService();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
    }

    private void initview() {
        setContentView(R.layout.activity_main);
        mTvLogcat = findViewById(R.id.textView);
        findViewById(R.id.button).setOnClickListener(this);
    }

    private void regiserReceiver() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("TesstService");
        intentFilter.addAction("LocationManager");
        registerReceiver(receiver, intentFilter);
    }

    private void awakenService() {
        Intent intent = new Intent(this, TesstService.class);
        PendingIntent pi = PendingIntent.getService(this, 1, intent, 0);
        AlarmManager alarm = (AlarmManager) getSystemService(Service.ALARM_SERVICE);
        if (alarm != null) {
            // 最少60秒一次
            alarm.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + 1000, 60000, pi);
        }
    }

    private void shake(String address) {
        Vibrator mVibrator01 = (Vibrator) getApplication().getSystemService(Service.VIBRATOR_SERVICE);
        mVibrator01.vibrate(new long[]{100, 10, 100, 1000}, -1);
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("\n" + DateUtils.getCurrentStr() + ":");
        stringBuffer.append(" " + address);
        if (NetWorkUtils.isNetworkAvailable(this)) {
            Log.i("TesstService", "网络可用");
            stringBuffer.append("网络可用");
        } else {
            Log.i("TesstService", "网络不可用");
            stringBuffer.append("网络不可用");
        }
        sendBroadCastReceiver(stringBuffer.toString());
    }

    private void sendBroadCastReceiver(String text) {
        Intent intent = new Intent();
        intent.setAction("TesstService");
        intent.putExtra("TesstService", text);
        getApplication().sendBroadcast(intent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button:
                Timer time = new Timer();
                TimerTask timerTask = new TimerTask() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(MainActivity.this, TesstService.class);
                        startService(intent);
                    }
                };
                time.schedule(timerTask, 1000, 10000);
                break;
        }
    }
}
