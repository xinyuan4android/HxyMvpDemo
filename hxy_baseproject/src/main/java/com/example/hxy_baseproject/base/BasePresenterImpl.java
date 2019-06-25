package com.example.hxy_baseproject.base;

import com.google.gson.JsonObject;

import rx.Subscription;

/**
 * 描述：presenter的基础实现类
 * 作者：hxy on  2017/9/28 11:12.
 *
 * @param <V> 视图接口对象(view) 具体业务各自继承自IBaseView
 */

public abstract class BasePresenterImpl<V extends IBaseView> implements IBasePresenter, IBaseRequestCallBack {
    protected V view;
    protected Subscription mSubscription;

    public BasePresenterImpl(V view) {
        this.view = view;
    }

    @Override
    public void unSubscribe() {
        if (mSubscription != null && !mSubscription.isUnsubscribed()) {
            mSubscription.unsubscribe();
        }
        view = null;
        System.gc();
    }

    @Override
    public void beforeRequest() {
        if (view != null) {
            view.showLoadingProgress();
        }
    }


    @Override
    public void requestError(String errorMsg, int requestCode) {
//        if (view != null) {
//            view.dismissLoadingProgress();
//        }
    }

    @Override
    public void requestError(BaseBean bean, int requestCode) {
//        if (view != null) {
//            view.dismissLoadingProgress();
//        }
    }

    @Override
    public void requestError(int responseCode, String errorMsg, int requestCode) {

    }

    @Override
    public void requestComplete() {
        if (view != null) {
            view.dismissLoadingProgress();
        }
    }

    @Override
    public void requestSuccess(JsonObject data, int requestCode) {
//        if (view != null) {
//            view.dismissLoadingProgress();
//        }
    }

    @Override
    public void requestSuccess(JsonObject data, int requestCode, boolean isDismiss) {
//        if (view != null) {
//            if (isDismiss) {
//                view.dismissLoadingProgress();
//            }
//        }

    }

    @Override
    public V getView() {
        if (view != null) {
            return view;
        }
        return null;
    }
}
