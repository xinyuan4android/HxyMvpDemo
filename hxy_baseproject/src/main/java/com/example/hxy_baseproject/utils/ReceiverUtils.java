package com.example.hxy_baseproject.utils;

import android.app.Activity;
import android.content.Intent;

/**
 * Created by iningke on 2016/7/12.
 */
public class ReceiverUtils {

    public static final String flag = "com.youji.zzh";


    /**
     * 登出变化
     */
    public static void logoutChanged(Activity activity) {
        Intent intent = new Intent();  //Itent就是我们要发送的内容
        intent.putExtra("data", 0);
        intent.setAction(flag);   //设置你这个广播的action，只有和这个action一样的接受者才能接受者才能接收广播
        activity.sendBroadcast(intent);   //发送广播
    }

    /**
     * 登录变化
     */
    public static void loginChanged(Activity activity) {
        Intent intent = new Intent();  //Intent就是我们要发送的内容
        intent.putExtra("data", 1);
        intent.setAction(flag);   //设置你这个广播的action，只有和这个action一样的接受者才能接受者才能接收广播
        activity.sendBroadcast(intent);   //发送广播
    }


    /**
     * 还款成功
     */
    public static void repaymentSuccess(Activity activity) {
        Intent intent = new Intent();  //Itent就是我们要发送的内容
        intent.putExtra("data", 2);
        intent.setAction(flag);   //设置你这个广播的action，只有和这个action一样的接受者才能接受者才能接收广播
        activity.sendBroadcast(intent);   //发送广播
    }

    /**
     * 借款成功-提交审核
     */
    public static void borrowChange(Activity activity) {
        Intent intent = new Intent();  //Itent就是我们要发送的内容
        intent.putExtra("data", 3);
        intent.setAction(flag);   //设置你这个广播的action，只有和这个action一样的接受者才能接受者才能接收广播
        activity.sendBroadcast(intent);   //发送广播
    }

    /**
     * 消息未读、已读
     */
    public static void messageUpdate(Activity activity) {
        Intent intent = new Intent();  //Itent就是我们要发送的内容
        intent.putExtra("data", 4);
        intent.setAction(flag);   //设置你这个广播的action，只有和这个action一样的接受者才能接受者才能接收广播
        activity.sendBroadcast(intent);   //发送广播
    }

    /**
     * 更新用户信息
     */
    public static void updateUserInfo(Activity activity) {
        //Itent就是我们要发送的内容
        Intent intent = new Intent();
        intent.putExtra("data", 5);
        //设置你这个广播的action，只有和这个action一样的接受者才能接受者才能接收广播
        intent.setAction(flag);
        //发送广播
        activity.sendBroadcast(intent);
    }

    /**
     * 定制行业发生变化
     */
    public static void customIndustry(Activity activity) {
        //Itent就是我们要发送的内容
        Intent intent = new Intent();
        intent.putExtra("data", 6);
        //设置你这个广播的action，只有和这个action一样的接受者才能接受者才能接收广播
        intent.setAction(flag);
        //发送广播
        activity.sendBroadcast(intent);
    }

}
