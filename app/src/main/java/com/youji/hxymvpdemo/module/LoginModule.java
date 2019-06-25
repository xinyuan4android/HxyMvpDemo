package com.youji.hxymvpdemo.module;

import com.example.hxy_baseproject.base.IBaseRequestCallBack;

import rx.Subscription;

/**
 * Created by hxy on  2019/6/25.
 */
public interface LoginModule {
    Subscription login(String userName, String password, IBaseRequestCallBack callBack);
}
