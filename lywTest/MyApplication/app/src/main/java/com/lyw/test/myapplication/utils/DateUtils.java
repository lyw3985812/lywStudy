package com.lyw.test.myapplication.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * author: Auser
 * created on: 2017/12/14 16:26
 * description:
 */
public class DateUtils {
    public static String getCurrentStr() {
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String t1 = format.format(date);
        return t1;
    }
}