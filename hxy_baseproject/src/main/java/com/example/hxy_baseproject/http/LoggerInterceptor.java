package com.example.hxy_baseproject.http;

import android.text.TextUtils;

import com.example.hxy_baseproject.utils.LogUtils;

import java.io.IOException;
import java.nio.charset.Charset;

import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;

/**
 * log拦截器
 *
 * @author hxy
 * @since 2017/11/1 11:00
 */
public class LoggerInterceptor implements Interceptor {
    public static final String TAG = "";
    private String tag;
    private boolean showResponse;

    public LoggerInterceptor(String tag, boolean showResponse) {
        if (TextUtils.isEmpty(tag)) {
            tag = TAG;
        }
        this.showResponse = showResponse;
        this.tag = tag;
    }

    public LoggerInterceptor(String tag) {
        this(tag, false);
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        logForRequest(request);
        Response response = chain.proceed(request);
        return logForResponse(response);
    }

    private Response logForResponse(Response response) {
        try {
            //===>response log
            LogUtils.e(tag + "========response'log=======");
            Response.Builder builder = response.newBuilder();
            Response clone = builder.build();
            LogUtils.e(tag + "url : " + clone.request().url());
            LogUtils.e(tag + "code : " + clone.code());
            LogUtils.e(tag + "protocol : " + clone.protocol());
            if (!TextUtils.isEmpty(clone.message())) {
                LogUtils.e(tag + "message : " + clone.message());
            }

            if (showResponse) {
                ResponseBody body = clone.body();
                if (body != null) {
                    MediaType mediaType = body.contentType();
                    if (mediaType != null) {
                        LogUtils.e(tag + "responseBody's contentType : " + mediaType.toString());
                        if (isText(mediaType)) {
                            String resp = body.string();
                            LogUtils.e(tag + "responseBody's content : ");
                            LogUtils.json(resp);

                            body = ResponseBody.create(mediaType, resp);
                            return response.newBuilder().body(body).build();
                        } else {
                            LogUtils.e(tag + "responseBody's content : " + " maybe [file part] , too large too print , ignored!");
                        }
                    }
                }
            }

            LogUtils.e(tag + "========response'log=======end");
        } catch (Exception e) {
//            e.printStackTrace();
        }

        return response;
    }

    private void logForRequest(Request request) {
        try {
            String url = request.url().toString();
            Headers headers = request.headers();

            LogUtils.e(tag + "========request'log=======");
            LogUtils.e(tag + "method : " + request.method());
            LogUtils.e(tag + "url : " + url);
            if (headers != null && headers.size() > 0) {
                LogUtils.e(tag + "headers : " + headers.toString());
            }
            RequestBody requestBody = request.body();
            if (requestBody != null) {
                MediaType mediaType = requestBody.contentType();
                if (mediaType != null) {
                    LogUtils.e(tag + "requestBody's contentType : " + mediaType.toString());
                    if (isText(mediaType)) {
                        LogUtils.e(tag + "requestBody's content : " + bodyToString(request));
                    } else {
                        LogUtils.e(tag + "requestBody's content : " + " maybe [file part] , too large too print , ignored!");
                    }
                }
            }
            LogUtils.e(tag + "========request'log=======end");
        } catch (Exception e) {
//            e.printStackTrace();
        }
    }

    private boolean isText(MediaType mediaType) {
        if (mediaType.type() != null && mediaType.type().equals("text")) {
            return true;
        }
        if (mediaType.subtype() != null) {
            if (mediaType.subtype().equals("json") ||
                    mediaType.subtype().equals("xml") ||
                    mediaType.subtype().equals("html") ||
                    mediaType.subtype().equals("webviewhtml") ||
                    mediaType.subtype().equals("x-www-form-urlencoded")
                    ) {
                return true;
            }
        }
        return false;
    }

    private String bodyToString(final Request request) {
        try {
            final Request copy = request.newBuilder().build();
            final Buffer buffer = new Buffer();
            copy.body().writeTo(buffer);
            return buffer.readString(Charset.forName("UTF-8"));
        } catch (final IOException e) {
            return "something error when show requestBody.";
        }
    }
}
