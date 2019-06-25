package com.example.hxy_baseproject.utils;

import android.os.CountDownTimer;
import android.widget.TextView;


/**
 * 获取验证码倒计时
 *
 * @author hxy
 * @since 2017/10/24 13:52
 */
public class TimeCount extends CountDownTimer {
    private TextView tv_time;
    private String stringAfterCount = "获取验证码";
    private String stringCounting = "s后获取";

    public TimeCount(TextView textView) {
        this(60000, 1000, textView);
    }

    public TimeCount(TextView textView, String stringAfterCount, String stringCounting) {
        this(textView);
        this.stringAfterCount = stringAfterCount;
        this.stringCounting = stringCounting;
    }

    /**
     * @param millisInFuture    总时长
     * @param countDownInterval 时间间隔
     * @param textView
     */
    public TimeCount(long millisInFuture, long countDownInterval, TextView textView) {
        super(millisInFuture, countDownInterval);
        this.tv_time = textView;
    }

    public void onCancel() {
        cancel();
        tv_time.setEnabled(true);
        tv_time.setClickable(true);
    }

    @Override
    public void onFinish() {    //计时完毕时触发
        tv_time.setText(stringAfterCount);
        tv_time.setEnabled(true);
        tv_time.setClickable(true);
    }

    @Override
    public void onTick(long millisUntilFinished) {// 计时过程显示
        tv_time.setText(millisUntilFinished / 1000 + stringCounting);
        tv_time.setClickable(false);
        tv_time.setEnabled(false);
    }
}