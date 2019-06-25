package com.example.hxy_baseproject.base;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;

import com.example.hxy_baseproject.R;
import com.example.hxy_baseproject.http.LoadingDialog;
import com.example.hxy_baseproject.utils.ActivityStack;
import com.example.hxy_baseproject.utils.LogUtils;

import butterknife.ButterKnife;

/**
 * 描述：activity基类
 * 作者：hxy on  2017/9/30 9:17.
 */

public abstract class BaseActivity<T extends IBasePresenter> extends AppCompatActivity implements IBaseView, DialogInterface.OnKeyListener {

    protected T mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        beforeSetContentView();
        setContentView(setLayoutId());
        ButterKnife.bind(this);
        ActivityStack.getScreenManager().pushActivity(this);
        initView();
        initData();
        LogUtils.e("-------------------onCreate-------------------" + this);
    }


    /**
     * 设置布局Id
     *
     * @return activity布局的layoutId
     */
    public abstract
    @LayoutRes
    int setLayoutId();

    /**
     * 控件的初始化
     */
    protected abstract void initView();


    protected void beforeSetContentView() {
    }

    protected void initData() {

    }

    @Override
    public void showLoadingProgress() {
        // 实现网络加载的动画效果
        showLoadingDialog(null);
    }

    @Override
    public void dismissLoadingProgress() {
        // 实现网络加载的动画效果
        dismissLoadingDialog();
    }

    @Override
    public Context getContext() {
        return this;
    }

    /**
     * 加载dialog
     */
    LoadingDialog dialog;

    public void showLoadingDialog(DialogInterface.OnDismissListener listener) {
        if (dialog == null) {
            dialog = new LoadingDialog(this, R.style.selectorDialog);
            dialog.setOnKeyListener(this);
        }
        if (!isFinishing()) {
            dialog.showDialog(listener);
        }
    }

    private boolean isCanDismissLoadingProgress = false;

    public boolean isCanDismissLoadingProgress() {
        return isCanDismissLoadingProgress;
    }

    public void setCanDismissLoadingProgress(boolean canDissmissLoadingProgress) {
        isCanDismissLoadingProgress = canDissmissLoadingProgress;
    }

    @Override
    public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            if (this.dialog != null && this.dialog.isShowing() && isCanDismissLoadingProgress) {
                LogUtils.e("activity on key down");
                //如果按返回键时，网络加载的动画正在显示，则关闭动画 并且停止此次网络请求
                dialog.dismiss();
                if (mPresenter != null) {
                    mPresenter.unSubscribe();
                }
                return false;
            }
        }
        return true;
    }

    public void dismissLoadingDialog() {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        LogUtils.e("-------------------onPause-------------------" + this);
//        UIUtils.toastCancel();
    }

    @Override
    protected void onDestroy() {
        ButterKnife.unbind(this);
        ActivityStack.getScreenManager().popActivity(this);
        if (mPresenter != null) {
            mPresenter.unSubscribe();
            mPresenter = null;
            System.gc();
        }
        LogUtils.e("-------------------onDestroy-------------------" + this);
        if (!"SplashActivity".equals(getClass().getSimpleName())) {
//            UIUtils.toastCancel();
        }
        super.onDestroy();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return super.onKeyDown(keyCode, event);
    }
}
