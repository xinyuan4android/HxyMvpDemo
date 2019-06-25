package com.youji.hxymvpdemo.module;

import com.example.hxy_baseproject.base.BaseSubscriber;
import com.example.hxy_baseproject.base.IBaseRequestCallBack;
import com.example.hxy_baseproject.http.HostType;
import com.example.hxy_baseproject.http.RetrofitManager;
import com.youji.hxymvpdemo.Api;

import java.util.HashMap;
import java.util.Map;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by hxy on  2019/6/25.
 */
public class LoginModuleImpl implements LoginModule {

    @Override
    public Subscription login(String userName, String password, IBaseRequestCallBack callBack) {
        //网络请求
        Map<String, String> map = new HashMap<>();
        map.put("userName", userName);
        map.put("password", password);
        return RetrofitManager.getInstance(HostType.MAIN_HOST)
                .getRetrofit()
                .create(Api.class)
                .login(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber(callBack, Api.RequestCode_Login));
    }
}
