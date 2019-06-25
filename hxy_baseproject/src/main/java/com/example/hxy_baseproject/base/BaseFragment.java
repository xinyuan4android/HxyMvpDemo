package com.example.hxy_baseproject.base;


import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import com.example.hxy_baseproject.R;
import com.example.hxy_baseproject.http.LoadingDialog;
import com.example.hxy_baseproject.utils.LogUtils;

import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public abstract class BaseFragment<T extends IBasePresenter> extends Fragment implements IBaseView, DialogInterface.OnKeyListener {
    private View mFragmentRootView;
    protected T mPresenter;

    public BaseFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (mFragmentRootView != null) {
            ViewGroup parent = null;
            ViewParent v = mFragmentRootView.getParent();
            if (v instanceof ViewGroup) {
                parent = (ViewGroup) v;
            }
            if (null != parent) {
                parent.removeView(mFragmentRootView);
            }
        } else {
            mFragmentRootView = inflater.inflate(bindingLayout(), container, false);
            ButterKnife.bind(this, mFragmentRootView);
            initView(mFragmentRootView);
            addListener();
            initData();
        }

//        mFragmentRootView = inflater.inflate(bindingLayout(), container, false);
////        ButterKnife.bind(this, mFragmentRootView);
//        initView(mFragmentRootView);
//        initData();
//        addListener();
        return mFragmentRootView;
    }

    protected void addListener() {


    }

    protected void initData() {

    }

    /**
     * @return 设置Fragment的布局id
     */
    public abstract
    @LayoutRes
    int bindingLayout();

    /**
     * 初始化控件
     *
     * @param mFragmentRootView
     */
    protected abstract void initView(View mFragmentRootView);

    private BaseActivity activity;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.activity = (BaseActivity) activity;
    }

    @Override
    public void showLoadingProgress() {
//        showLoadingDialog(null);
        if (activity != null) {
            activity.showLoadingProgress();
        }
    }

    @Override
    public void dismissLoadingProgress() {
//        dismissLoadingDialog();
        if (activity != null) {
            activity.dismissLoadingProgress();
        }
    }

    /**
     * 加载dialog
     */
    LoadingDialog dialog;

    public void showLoadingDialog(DialogInterface.OnDismissListener listener) {
        if (getContext() == null) {
            return;
        }
        if (dialog == null) {
            dialog = new LoadingDialog(getContext(), R.style.selectorDialog);
            dialog.setOnKeyListener(this);
        }
        dialog.showDialog(listener);
    }

    public void dismissLoadingDialog() {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        LogUtils.e("onDestroyView" + getClass().getSimpleName());
        ViewGroup parent = (ViewGroup) mFragmentRootView.getParent();
        if (mPresenter != null) {
            mPresenter.unSubscribe();
            mPresenter = null;
        }
//        UIUtils.toastCancel();
        if (null != parent) {
            parent.removeView(mFragmentRootView);
//            ButterKnife.unbind(this);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        LogUtils.e("onPause" + getClass().getSimpleName());
//        UIUtils.toastCancel();
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
                LogUtils.e("fragment  on key down");
                //如果按返回键时，网络加载的动画正在显示，则关闭动画 并且停止此次网络请求
                dialog.dismiss();
                if (mPresenter != null) {
                    mPresenter.unSubscribe();
                }
            }
            return false;
        }
        return true;
    }
}
