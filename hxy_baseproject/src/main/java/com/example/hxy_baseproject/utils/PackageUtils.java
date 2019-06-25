package com.example.hxy_baseproject.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import com.example.hxy_baseproject.BaseApp;


/**
 * Created by iningke on 2016/7/7.
 */
public class PackageUtils {
    /**
     * 全局上下文对象
     *
     * @return 上下文
     */
    public static Context getContext() {
        return BaseApp.getApplication();
    }

    public static String getVersion() {
        String version = "1.0.0";
        PackageManager manager = getContext().getPackageManager();
        try {
            PackageInfo packageInfo = manager.getPackageInfo(getContext().getPackageName(), 0);
            version = packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return version;
    }

    public static int getVersionCode() {
        int version = 1;
        PackageManager manager = getContext().getPackageManager();
        try {
            PackageInfo packageInfo = manager.getPackageInfo(getContext().getPackageName(), 0);
            version = packageInfo.versionCode;

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return version;
    }

    public static String getPackageName() {
        String packageName = getContext().getPackageName();
        LogUtils.e("packageName== " + packageName);
        return packageName;
    }
}
