package com.example.hxy_baseproject.utils;

/**
 * Created by iningke on 2016/8/26.
 */

import android.app.Activity;

import java.util.Stack;


/**
 *  Activity 管理栈
 *
 */
public class ActivityStack {
    private static Stack<Activity> mActivityStack = new Stack<Activity>();
    private static ActivityStack instance = new ActivityStack();

    private ActivityStack() {
    }

    public static Stack<Activity> getMActivityStack() {
        return mActivityStack;
    }

    public static ActivityStack getScreenManager() {
        return instance;
    }

    // 弹出当前activity并销毁
    public void popActivity(Activity activity) {
        if (activity != null && !mActivityStack.empty()) {
            activity.finish();
            mActivityStack.pop();
        }
    }

    // 将当前Activity推入栈中
    public void pushActivity(Activity activity) {
        LogUtils.e("pushActivity" + activity.getClass().getName());
        mActivityStack.add(activity);
    }

    // 退出栈中所有Activity
    public void clearAllActivity() {
        while (!mActivityStack.isEmpty()) {
            Activity activity = mActivityStack.pop();
            if (activity != null) {
                LogUtils.e("clearActivity" + activity.getClass().getName());
                activity.finish();
            }
        }
    }

}
