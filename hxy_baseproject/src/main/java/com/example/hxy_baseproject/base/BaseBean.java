package com.example.hxy_baseproject.base;

import java.io.Serializable;

/**
 * @author :  hxy
 * @since :  2017/11/1 13:35.
 */

public class BaseBean implements Serializable {
    private int code;
    private String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}
