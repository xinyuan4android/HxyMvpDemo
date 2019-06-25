package com.example.hxy_baseproject.utils;

import android.content.SharedPreferences;

import com.example.hxy_baseproject.BaseApp;


/**
 * Created by iningke on 2016/7/21.
 */
public class SharePreUtils {
    static SharePreUtils utils;

    private SharePreUtils() {
    }

    public static SharePreUtils getUtils() {
        if (utils == null) {
            utils = new SharePreUtils();
        }
        return utils;
    }

    BaseApp app;

    public void initUtils(BaseApp app) {
        this.app = app;
    }

    public boolean getBooleanCache(String key) {
        SharedPreferences preferences = app.getSharedPreferences("data", BaseApp.MODE_PRIVATE);
        return preferences.getBoolean("key", true);
    }

    public void putBooleanCache(String key, boolean value) {
        SharedPreferences preferences = app.getSharedPreferences("data", BaseApp.MODE_PRIVATE);
        SharedPreferences.Editor edit = preferences.edit();
        edit.putBoolean("key", value);
        apply(edit);
    }

    public int getIntCache(String key) {
        SharedPreferences preferences = app.getSharedPreferences("data", BaseApp.MODE_PRIVATE);
        return preferences.getInt(key, 0);
    }

    public void putIntCache(String key, int value) {
        SharedPreferences preferences = app.getSharedPreferences("data", BaseApp.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(key, value);
        apply(editor);
    }

    public String getStringCache(String key) {
        SharedPreferences preferences = app.getSharedPreferences("data", BaseApp.MODE_PRIVATE);
        return preferences.getString(key, "");
    }


    public void putStringCache(String key, String value) {
        SharedPreferences preferences = app.getSharedPreferences("data", BaseApp.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(key, value);
        apply(editor);
    }

    public float getFloatCache(String key) {
        SharedPreferences preferences = app.getSharedPreferences("data", BaseApp.MODE_PRIVATE);
        return preferences.getFloat(key, 0.0f);
    }

    public void putFloatCache(String key, float value) {
        SharedPreferences preferences = app.getSharedPreferences("data", BaseApp.MODE_PRIVATE);
        SharedPreferences.Editor edit = preferences.edit();
        edit.putFloat(key, value);
        apply(edit);
    }

    public void removeIntCache(String key) {
        SharedPreferences preferences = app.getSharedPreferences("data", BaseApp.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.remove(key);
        apply(editor);
    }

    public void removeStringCache(String key) {
        SharedPreferences preferences = app.getSharedPreferences("data", BaseApp.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.remove(key);
        apply(editor);
    }

    public void clearAllCache() {
        SharedPreferences preferences = app.getSharedPreferences("data", BaseApp.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        apply(editor);
    }

    private void apply(SharedPreferences.Editor editor) {
        editor.apply();
    }

    public void saveLoginPhone(String phone) {
        putStringCache("phone", phone);
    }

    public String getLoginPhone() {
        return getStringCache("phone");
    }

    public void saveBaseImageUrl(String baseImageUrl) {
        putStringCache("baseImageUrl", baseImageUrl);
    }

    public void saveBaseVideoUrl(String baseVideoUrl) {
        putStringCache("baseVideoUrl", baseVideoUrl);
    }

    public void saveSystemImageUrl(String baseVideoUrl) {
        putStringCache("systemImageUrl", baseVideoUrl);
    }

    public String getBaseImageUrl() {
        return getStringCache("baseImageUrl");
    }

    public String getBaseVideoUrl() {
        return getStringCache("baseVideoUrl");
    }

    public String getSystemImageUrl() {
        return getStringCache("systemImageUrl");
    }
}
