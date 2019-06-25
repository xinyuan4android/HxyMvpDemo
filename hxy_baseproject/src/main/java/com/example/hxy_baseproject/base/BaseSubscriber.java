package com.example.hxy_baseproject.base;

import com.example.hxy_baseproject.utils.ActivityStack;
import com.example.hxy_baseproject.utils.LogUtils;
import com.example.hxy_baseproject.utils.ReceiverUtils;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.net.ConnectException;

import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.adapter.rxjava.HttpException;
import rx.Subscriber;

/**
 * 网络请求回调基类,在这对返回错误做统一处理
 *
 * @author hxy
 * @since 2017/9/28 11:22
 */
public class BaseSubscriber extends Subscriber<JsonObject> {

    private IBaseRequestCallBack requestCallBack;
    /**
     * 自定义请求码
     */
    private int requestCode;

    private boolean isShowLoadingDialog = true;

    public BaseSubscriber(IBaseRequestCallBack requestCallBack) {
        this.requestCallBack = requestCallBack;
    }

    public BaseSubscriber(IBaseRequestCallBack requestCallBack, int requestCode) {
        this.requestCallBack = requestCallBack;
        this.requestCode = requestCode;
    }

    public BaseSubscriber(IBaseRequestCallBack requestCallBack, int requestCode, boolean isShowLoadingDialog) {
        this.requestCallBack = requestCallBack;
        this.requestCode = requestCode;
        this.isShowLoadingDialog = isShowLoadingDialog;
    }

    @Override
    public void onStart() {
        super.onStart();
        if (requestCallBack != null) {
            if (isShowLoadingDialog) {
                requestCallBack.beforeRequest();
            }
        }
    }

    @Override
    public void onCompleted() {
//        if (requestCallBack != null) {
//            requestCallBack.requestComplete();
//        }
    }

    @Override
    public void onError(Throwable e) {
        requestCallBack.requestComplete();
        if (e instanceof ConnectException) {
            //没有连接网络
            requestCallBack.requestError("网络连接异常，请检查网络", requestCode);
            return;
        }

        if (e instanceof HttpException) {
            try {
                Response<?> response = ((HttpException) e).response();
                int code = response.code();
                ResponseBody body = response.errorBody();
                switch (code) {
                    case 400:
                        //请求错误
                    case 401:
                        //未授权，请重新登录
                    case 403:
                        //拒绝访问
                    case 404:
                        //请求出错
                    case 408:
                        //请求超时
                    case 500:
                        //服务器错误
                    case 501:
                        //服务未实现
                    case 502:
                        //网络错误
                    case 503:
                        //服务不可用
                    case 504:
                        //网络超时
                    case 505:
                        //HTTP版本不受支持
                        break;
                }
                LogUtils.e("错误日志-----:" + body.string());
            } catch (IOException IOe) {
                IOe.printStackTrace();
            }
        }
        if (requestCallBack != null) {
            requestCallBack.requestError(e.getMessage(), requestCode);
            // 网络请求的错误信息统一处理
        }
    }

    @Override
    public void onNext(JsonObject t) {
        requestCallBack.requestComplete();
        if (requestCallBack != null) {
            LogUtils.e("data" + t);
            BaseBean bean = new Gson().fromJson(t, BaseBean.class);
            int resultCode = bean.getCode();
            String message = bean.getMsg();

            if (resultCode == 200) {
                //请求成功
//                    requestCallBack.requestSuccess(t);
                requestCallBack.requestSuccess(t, requestCode, true);
                requestCallBack.requestSuccess(t, requestCode);
            } else if (resultCode == -300) {
                ReceiverUtils.logoutChanged(ActivityStack.getMActivityStack().get(ActivityStack.getMActivityStack().size() - 1));
                requestCallBack.requestError("登录身份过期，请重新登录", requestCode);
            } else {
                requestCallBack.requestError(message, requestCode);
                requestCallBack.requestError(resultCode, message, requestCode);
            }
        }
    }
}

