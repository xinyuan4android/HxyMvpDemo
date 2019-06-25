package com.example.hxy_baseproject.base;

import com.google.gson.JsonObject;

/**
 * 描述：
 * 作者：hxy on  2017/9/28 10:44.
 */

public interface IBaseRequestCallBack {
    /**
     * 请求之前调用,可以做一些加载动画之类的操作
     */
    void beforeRequest();

    /**
     * 请求错误调用
     *
     * @param errorMsg
     * @param requestCode
     */
    void requestError(String errorMsg, int requestCode);

    /**
     * 接口请求错误回调
     *
     * @param responseCode 响应码
     * @param errorMsg     错误信息
     * @param requestCode  请求码
     */
    void requestError(int responseCode, String errorMsg, int requestCode);

    /**
     * 请求错误回调
     *
     * @param bean
     * @param requestCode
     */
    void requestError(BaseBean bean, int requestCode);

    /**
     * 请求完成调用
     */
    void requestComplete();

    /**
     * 请求成功调用
     *
     * @param data        数据
     * @param requestCode
     */
    void requestSuccess(JsonObject data, int requestCode);

    /**
     * 请求成功调用
     *
     * @param data
     * @param requestCode
     * @param isDismiss   是否隐藏loading框
     */
    void requestSuccess(JsonObject data, int requestCode, boolean isDismiss);
}
