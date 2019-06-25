package com.youji.hxymvpdemo.presenter;

import android.text.TextUtils;

import com.example.hxy_baseproject.base.BasePresenterImpl;
import com.example.hxy_baseproject.utils.UIUtils;
import com.google.gson.JsonObject;
import com.youji.hxymvpdemo.Api;
import com.youji.hxymvpdemo.module.LoginModule;
import com.youji.hxymvpdemo.module.LoginModuleImpl;
import com.youji.hxymvpdemo.view.LoginView;

/**
 * Created by hxy on  2019/6/25.
 */
public class LoginPresenterImpl extends BasePresenterImpl<LoginView> implements LoginPresenter {
    private LoginModule module;

    public LoginPresenterImpl(LoginView view) {
        super(view);
        module = new LoginModuleImpl();
    }

    @Override
    public void login() {
        String password = view.getPassword();
        String userName = view.getUserName();
        if (TextUtils.isEmpty(userName)) {
            UIUtils.showToast("请输入用户名");
            return;
        }
        if (TextUtils.isEmpty(password)) {
            UIUtils.showToast("请输入密码");
            return;
        }
        module.login(userName, password, this);
    }

    /**
     * 接口成功回调
     *
     * @param data
     * @param requestCode
     */
    @Override
    public void requestSuccess(JsonObject data, int requestCode) {
        super.requestSuccess(data, requestCode);
        if (view == null) {
            return;
        }
        switch (requestCode) {
            case Api.RequestCode_Login:
                view.loginSuccess();
                break;
            default:
                break;
        }
    }

    /**
     * 接口失败回调
     *
     * @param errorMsg
     * @param requestCode
     */
    @Override
    public void requestError(String errorMsg, int requestCode) {
        super.requestError(errorMsg, requestCode);
        if (view == null) {
            return;
        }
        switch (requestCode) {
            case Api.RequestCode_Login:
                view.loginError(errorMsg);
                break;
            default:
                break;
        }
    }
}
