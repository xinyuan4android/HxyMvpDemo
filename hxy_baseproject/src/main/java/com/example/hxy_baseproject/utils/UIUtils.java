package com.example.hxy_baseproject.utils;

import android.app.Activity;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.example.hxy_baseproject.BaseApp;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * 创建者：gaonan
 * 时间：2015/11/13 16:33
 * 类描述：
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class UIUtils {
    /**
     * 全局上下文对象
     *
     * @return 上下文
     */
    public static Context getContext() {
        return BaseApp.getApplication();
    }

    public static Thread getMainThread() {
        return BaseApp.getMainThread();
    }

    public static long getMainThreadId() {
        return BaseApp.getMainThreadId();
    }

    /**
     * dip转换px
     */
    public static int dip2px(float dip) {
        final float scale = getContext().getResources().getDisplayMetrics().density;
        return (int) (dip * scale + 0.5f);
    }

    /**
     * pxz转换dip
     */
    public static int px2dip(int px) {
        final float scale = getContext().getResources().getDisplayMetrics().density;
        return (int) (px / scale + 0.5f);
    }

    /**
     * convert sp to its equivalent px
     * <p>
     * 将sp转换为px
     */
    public static int sp2px(float spValue) {
        final float fontScale = getContext().getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    /**
     * convert px to its equivalent sp
     * <p>
     * 将px转换为sp
     */
    public static int px2sp(float pxValue) {
        final float fontScale = getContext().getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }

    /**
     * 获取主线程的handler
     */
    public static Handler getHandler() {
        // 获得主线程的looper
        Looper mainLooper = BaseApp.getMainThreadLooper();
        // 获取主线程的handler
        Handler handler = new Handler(mainLooper);
        return handler;
    }

    /**
     * 延时在主线程执行runnable
     */
    public static boolean postDelayed(Runnable runnable, long delayMillis) {
        return getHandler().postDelayed(runnable, delayMillis);
    }

    /**
     * 在主线程执行runnable
     */
    public static boolean post(Runnable runnable) {
        return getHandler().post(runnable);
    }

    /**
     * 从主线程looper里面移除runnable
     */
    public static void removeCallbacks(Runnable runnable) {
        getHandler().removeCallbacks(runnable);
    }

    public static View inflate(int resId) {
        return LayoutInflater.from(getContext()).inflate(resId, null);
    }

    /**
     * 获取资源
     */
    public static Resources getResources() {
        return getContext().getResources();
    }

    /**
     * 获取文字
     */
    public static String getString(int resId) {
        try {
            return getResources().getString(resId);
        } catch (Exception e) {
            return resId + "";
        }

    }

    /**
     * 获取文字数组
     */
    public static String[] getStringArray(int resId) {
        return getResources().getStringArray(resId);
    }

    /**
     * 获取dimen
     */
    public static int getDimens(int resId) {
        return getResources().getDimensionPixelSize(resId);
    }

    /**
     * 获取drawable
     */
    public static Drawable getDrawable(int resId) {
        return getResources().getDrawable(resId);
    }

    /**
     * 获取颜色
     */
    public static int getColor(int resId) {
        return getResources().getColor(resId);
    }

    /**
     * 获取颜色选择器
     */
    public static ColorStateList getColorStateList(int resId) {
        return getResources().getColorStateList(resId);
    }

    public static boolean isRunInMainThread() {
        return android.os.Process.myTid() == getMainThreadId();
    }

    public static void runInMainThread(Runnable runnable) {
        if (isRunInMainThread()) {
            runnable.run();
        } else {
            post(runnable);
        }
    }

    /**
     * 对toast的简易封装。线程安全，可以在非UI线程调用。
     */
    public static void showToastSafe(final int resId) {
        showToastSafe(getString(resId));
    }

    /**
     * 对toast的简易封装。线程安全，可以在非UI线程调用。
     */
    public static void showToastSafe(final Object str) {
        if (str == null) {
            return;
        }
        if (isRunInMainThread()) {
            showToast(str.toString());
        } else {
            post(new Runnable() {
                @Override
                public void run() {
                    showToast(str.toString());
                }
            });
        }
    }

    /**
     * 对toast的简易封装。线程安全，可以在非UI线程调用。
     */
    public static void showToastSafeLong(final Object str) {
        if (str == null) {
            return;
        }
        if (isRunInMainThread()) {
            showToastLong(str.toString());
        } else {
            post(new Runnable() {
                @Override
                public void run() {
                    showToastLong(str.toString());
                }
            });
        }
    }

    /**
     * 通知栏高度
     *
     * @param context
     * @return
     */
    // public static int getNotifacationBarHeight(Context context) {
    // DisplayMetrics outMetrics = new DisplayMetrics();
    // ((Activity)
    // context).getWindowManager().getDefaultDisplay().getMetrics(outMetrics);
    // int totalHeight = outMetrics.heightPixels;
    // Rect frame = new Rect();
    // ((BaseActivity)
    // GlobalParams.BASE_CONTEXT).getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
    // int noNotiBarHeight = frame.height();
    // return totalHeight - noNotiBarHeight;
    // }

    /**
     * 屏幕宽度
     *
     * @param context
     *
     * @return
     */
    public static int getDisplayWidth(Context context) {
        DisplayMetrics outMetrics = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay()
                .getMetrics(outMetrics);
        return outMetrics.widthPixels;
    }

    //屏幕宽度
    public static int getDisplayHeight(Context context) {
        DisplayMetrics outMetrics = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay()
                .getMetrics(outMetrics);
        return outMetrics.heightPixels;
    }

    //屏幕密度
    public static float getDisplayDensity(Context context) {
        DisplayMetrics outMetrics = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay()
                .getMetrics(outMetrics);
        return outMetrics.density;
    }

    //获取状态栏的高度,得到的值是像素
    public static int getStatusBarHeight(Context context) {
        int result = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }
//    private static ExToast toast;

    private static ToastUtil toastUtil;

    public static void showToast(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        if (toastUtil == null) {
            toastUtil = new ToastUtil();
        }
        toastUtil.Short(str).show();
//        if (str == null) {
//            return;
//        }
//        if (toast == null) {
//            toast = ExToast.makeText(getContext(), "", ExToast.LENGTH_SHORT);
//        }
//        toast.setText(str);
//        toast.setDuration(ExToast.LENGTH_SHORT);
//        toast.show();
    }

    public static void showToastLong(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        if (toastUtil == null) {
            toastUtil = new ToastUtil();
        }
        toastUtil.Long(str).show();
    }

    public static void toastCancel() {
        if (toastUtil != null) {
            toastUtil.getToast().cancel();
        }
//        if (toast != null) {
//            toast.hide();
//        }

    }

    /*
     * public static String toDBC(String input) { char[] c =
     * input.toCharArray(); for (int i = 0; i < c.length; i++) { if (c[i] ==
     * 12288) { c[i] = (char) 32; continue; } if (c[i] > 65280 && c[i] < 65375)
     * c[i] = (char) (c[i] - 65248); } return new String(c); }
     */


    /**
     * 隐藏小键盘的方法
     */
    public static void goneKeyboard(Context activity, View editText) {
        InputMethodManager imm = (InputMethodManager) activity
                .getSystemService(Context.INPUT_METHOD_SERVICE);// 控制小键盘的那个类
        imm.hideSoftInputFromWindow(editText.getWindowToken(), 0); // 强制隐藏键盘
    }

//    /**
//     * 多线程执行任务
//     * @param runTask 执行的任务
//     */
//    public static void startTaskInThreadPool(Runnable runTask){
//        ThreadManager.getInstance().createHelper().execute(runTask);
//    }

    /**
     * 加载本地图片
     * http://bbs.3gstdy.com
     *
     * @param url
     *
     * @return
     */
    public static Bitmap getLocalBitmap(String url) {
        try {
            FileInputStream fis = new FileInputStream(url);
            return BitmapFactory.decodeStream(fis);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}