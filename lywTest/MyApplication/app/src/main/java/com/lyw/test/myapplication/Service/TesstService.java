package com.lyw.test.myapplication.Service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.lyw.test.myapplication.utils.LocationManager;

/**
 * author: Auser
 * created on: 2017/12/14 10:11
 * description:
 */
public class TesstService extends Service {

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        LocationManager.getInstance().startLocation();
        return flags;
    }


    //    @Override
//    protected void onHandleIntent(@Nullable Intent intent) {
//        Timer time = new Timer();
//        TimerTask  task = new TimerTask() {
//            @Override
//            public void run() {
//                mVibrator01 = ( Vibrator ) getApplication().getSystemService(Service.VIBRATOR_SERVICE);
//                mVibrator01.vibrate( new long[]{100,10,100,1000},-1);
//            }
//        };
//        time.schedule(task,1000);
//    }

}