package com.lyw.test.myapplication.utils;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;

import com.lyw.test.myapplication.core.MyApplication;

/**
 * author: Lyw
 * created on: 2017/12/14 17:12
 * description:睡眠唤醒管理
 */

public class ServiceAlarmManager {
    private static ServiceAlarmManager mInstance;
    private AlarmManager alarmManager;
    private PendingIntent pendingIntent;

    private ServiceAlarmManager() {

    }

    public static ServiceAlarmManager getInstance() {
        if (mInstance == null) {
            synchronized (ServiceAlarmManager.class) {
                if (mInstance == null) {
                    mInstance = new ServiceAlarmManager();
                }
            }
        }
        return mInstance;
    }

    /**
     * 启动唤醒睡眠服务
     *
     * @param intent ：只能创SerVice Intent;
     */
    public void awakenService(Intent intent) {
        pendingIntent = PendingIntent.getService(MyApplication.getInstance(), 1, intent, 0);
        alarmManager = (AlarmManager) MyApplication.getInstance().getSystemService(Service.ALARM_SERVICE);
        if (alarmManager != null) {
            // 最少60秒一次
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + 1000, 60000, pendingIntent);
        }
    }

    /**
     * 取消当前Intent
     */
    public void awakenCancel() {
        if (pendingIntent != null && alarmManager != null) {
            alarmManager.cancel(pendingIntent);
        }
    }
}