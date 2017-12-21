package com.lyw.test.myapplication.utils;

import android.content.Intent;
import android.util.Log;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.lyw.test.myapplication.core.MyApplication;

/**
 * author: Lyw
 * created on: 2017/12/14 17:12
 * description:定位管理
 */
public class LocationManager {
    private static LocationManager mInstance;
    private BDLocation mLocation;
    public LocationClient mLocationClient = null;

    /**
     * 定位监听
     */
    private BDLocationListener myListener = new BDLocationListener() {
        @Override
        public void onReceiveLocation(BDLocation bdLocation) {
            Log.i("LocationService", "定位返回马:" + bdLocation.getLocType());
            sendBroadCastReceiver();
            mLocation = bdLocation;
            mLocationClient.stop();
        }
    };

    private LocationManager() {
        initLocation();
    }

    public static LocationManager getInstance() {
        if (mInstance == null) {
            synchronized (LocationManager.class) {
                if (mInstance == null) {
                    mInstance = new LocationManager();
                }
            }
        }
        return mInstance;
    }

    /**
     * 初始化LBSAPI
     */
    private void initLocation() {
        LocationClientOption option = new LocationClientOption();
        mLocationClient = new LocationClient(MyApplication.getInstance());
        //声明LocationClient类
        mLocationClient.registerLocationListener(myListener);
        option.setLocationMode(LocationClientOption.LocationMode.Battery_Saving);
        //可选，设置定位模式，默认高精度
        //LocationMode.Hight_Accuracy：高精度；
        //LocationMode. Battery_Saving：低功耗；
        //LocationMode. Device_Sensors：仅使用设备；

        option.setCoorType("bd09ll");
        //可选，设置返回经纬度坐标类型，默认gcj02
        //gcj02：国测局坐标；
        //bd09ll：百度经纬度坐标；
        //bd09：百度墨卡托坐标；
        //海外地区定位，无需设置坐标类型，统一返回wgs84类型坐标
        option.setIsNeedAddress(true);
        option.setScanSpan(1000);
        //可选，设置发起定位请求的间隔，int类型，单位ms
        //如果设置为0，则代表单次定位，即仅定位一次，默认为0
        //如果设置非0，需设置1000ms以上才有效

        option.setOpenGps(true);
        //可选，设置是否使用gps，默认false
        //使用高精度和仅用设备两种定位模式的，参数必须设置为true

        option.setLocationNotify(true);
        //可选，设置是否当GPS有效时按照1S/1次频率输出GPS结果，默认false

        option.setIgnoreKillProcess(false);
        //可选，定位SDK内部是一个service，并放到了独立进程。
        //设置是否在stop的时候杀死这个进程，默认（建议）不杀死，即setIgnoreKillProcess(true)

        option.SetIgnoreCacheException(false);
        //可选，设置是否收集Crash信息，默认收集，即参数为false

        option.setWifiCacheTimeOut(5 * 60 * 1000);
        //可选，7.2版本新增能力
        //如果设置了该接口，首次启动定位时，会先判断当前WiFi是否超出有效期，若超出有效期，会先重新扫描WiFi，然后定位

        option.setEnableSimulateGps(false);
        //可选，设置是否需要过滤GPS仿真结果，默认需要，即参数为false

        mLocationClient.setLocOption(option);
        //mLocationClient为第二步初始化过的LocationClient对象
        //需将配置好的LocationClientOption对象，通过setLocOption方法传递给LocationClient对象使用
        //更多LocationClientOption的配置，请参照类参考中LocationClientOption类的详细说明
    }

    public void startLocation() {
        mLocationClient.start();
        Log.i("LocationService", "开始定位");
    }

    private void sendBroadCastReceiver() {
        Intent intent = new Intent();
        intent.setAction("LocationManager");
        MyApplication.getInstance().sendBroadcast(intent);
        Log.i("LocationService", "发送定位广播");
    }

    public BDLocation getLocation() {
        return mLocation;
    }
}