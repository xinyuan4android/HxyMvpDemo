package com.youji.hxymvpdemo;

import android.widget.EditText;

import com.example.hxy_baseproject.base.BaseActivity;
import com.example.hxy_baseproject.utils.UIUtils;
import com.youji.hxymvpdemo.presenter.LoginPresenterImpl;
import com.youji.hxymvpdemo.view.LoginView;

import butterknife.Bind;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity<LoginPresenterImpl> implements LoginView {

    @Bind(R.id.etUserName)
    EditText etUserName;
    @Bind(R.id.etPassword)
    EditText etPassword;

    @Override
    public int setLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {
        mPresenter = new LoginPresenterImpl(this);
    }

    @Override
    public String getUserName() {
        return etUserName.getText().toString();
    }

    @Override
    public String getPassword() {
        return etPassword.getText().toString();
    }

    @Override
    public void loginSuccess() {
        UIUtils.showToast("登录成功");
        //可以做登录成功操作
    }

    @Override
    public void loginError(String errorMsg) {
        UIUtils.showToast(errorMsg);
        //登录失败之后的操作
    }


    @OnClick(R.id.btnLogin)
    public void onViewClicked() {
        //点击登录按钮
        mPresenter.login();
    }
}
