package com.lyw.greendaotest.manager;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.lyw.greendaotest.DaoMaster;
import com.lyw.greendaotest.DaoSession;
import com.lyw.greendaotest.core.GreenDaoAppilcation;

/**
 * Created by Administrator on 2018/1/29 0029.
 */

public class DaoManager {
    private static final String DB_NAME = "lyw.db";
    private volatile static DaoManager mDaoManager;//多线程访问
    private static DaoMaster.DevOpenHelper mHelper;
    private static DaoMaster mDaoMaster;
    private static DaoSession mDaoSession;
    private static SQLiteDatabase db;
    private Context context;

    private DaoManager() {
        init(GreenDaoAppilcation.getInstance());
    }

    /**
     * 初始化Context对象
     *
     * @param context
     */
    private void init(Context context) {
        this.context = context;
    }


    /**
     * 使用DCL单例模式获得操控数据库对象。
     *
     * @return
     */
    public static DaoManager getInstance() {
        if (mDaoManager == null) {
            synchronized (DaoManager.class) {
                if (mDaoManager == null) {
                    mDaoManager = new DaoManager();
                }
            }
        }
        return mDaoManager;
    }

    public DaoMaster getDaoMaster() {
        if (mDaoMaster == null) {
            mHelper = new DaoMaster.DevOpenHelper(context, DB_NAME, null);
            mDaoMaster = new DaoMaster(mHelper.getWritableDatabase());
        }
        return mDaoMaster;
    }

    /**
     * 完成对数据库的增删查找
     * @return
     */
    public DaoSession getDaoSession(){
        if (null == mDaoSession){
            if (null == mDaoMaster){
                mDaoMaster = getDaoMaster();
            }
            mDaoSession = mDaoMaster.newSession();
        }
        return mDaoSession;
    }
}
