package com.example.hxy_baseproject;

import android.app.Application;
import android.os.Looper;

import com.example.hxy_baseproject.utils.SharePreUtils;

/**
 * @author :  hxy
 * @since :  2017/11/1 15:01.
 */

public class BaseApp extends Application {
    private static BaseApp myApp;
    /**
     * 主线程ID
     */
    protected static int mMainThreadId = -1;
    /**
     * 主线程ID
     */
    protected static Thread mMainThread;
    /**
     * 主线程Looper
     */
    protected static Looper mMainLooper;

    @Override
    public void onCreate() {
        super.onCreate();
        mMainThreadId = android.os.Process.myTid();
        mMainThread = Thread.currentThread();
        mMainLooper = getMainLooper();
        myApp = this;
        SharePreUtils.getUtils().initUtils(this);
    }

    public static BaseApp getApplication() {
        return myApp;
    }

    /**
     * 获取主线程ID
     */
    public static int getMainThreadId() {
        return mMainThreadId;
    }

    /**
     * 获取主线程
     */
    public static Thread getMainThread() {

        return mMainThread;
    }

    /**
     * 获取主线程的looperf
     */
    public static Looper getMainThreadLooper() {
        return mMainLooper;
    }
}
