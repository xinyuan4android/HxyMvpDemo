package com.youji.hxymvpdemo;

import com.google.gson.JsonObject;

import java.util.Map;

import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by hxy on  2019/6/25.
 */
public interface Api {
    int RequestCode_Login = 1;

    @POST("login")
    @FormUrlEncoded
    Observable<JsonObject> login(@FieldMap Map<String, String> map);
}
