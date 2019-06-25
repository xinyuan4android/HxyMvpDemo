package com.youji.hxymvpdemo.view;

import com.example.hxy_baseproject.base.IBaseView;

/**
 * Created by hxy on  2019/6/25.
 */
public interface LoginView extends IBaseView {
    String getUserName();

    String getPassword();

    void loginSuccess();

    void loginError(String errorMsg);

}
