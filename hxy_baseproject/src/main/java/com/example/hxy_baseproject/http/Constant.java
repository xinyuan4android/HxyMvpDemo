package com.example.hxy_baseproject.http;

/**
 * Created by lts on 2017/8/28.
 * Function: 一些常量类
 * Update:
 */

public final class Constant {

    public static final String LOCALE = "locale";
    public static final String VIDEO_NAME = "video_name";
    public static final String VIDEO_KEY = "video_key";

    private Constant() {
    }


    //TODO 服务器地址
    public static final String BASE_URL = "http://api.zhangzhonghua.cn";
    public static final String BASE_QCR360_URL = "http://qiancheng.zhangzhonghua.cn";
//    public static final String BASE_URL = "http://tapi.zhangzhonghua.cn";

    public static final String HOST_URL = BASE_URL + "/";//线上正式服务器
    public static final String TEST_URL = BASE_URL + "/";//线上测试服务器
    public static final String QCR360_URL = BASE_QCR360_URL + "/";//线上测试服务器

    public static final String URL_IMAGE = "http://static.zhangzhonghua.cn";//
    public static final String URL_H5 = "http://static.zhangzhonghua.cn/";//

//    public static final String HOST_URL = "http://192.168.31.230:8080/";// 本地服务器


    //    public static final String QiNiu_Image_Base_Url = "http://ozpd4g3lv.bkt.clouddn.com/";
    public static final String QiNiu_Image_Base_Url = "http://oxr53zrxa.bkt.clouddn.com/";
    public static final String image_base_url = "https://image.tmdb.org/t/p/";
    public static final String backdrop_size = "w300";
    public static final String logo_size = "w185";
    public static final String logUrl = image_base_url + logo_size;
    public static final String backgoundUrl = image_base_url + backdrop_size;
    public static final String movie_id = "movie_id";
//    public static final String LANGUGE = App.getContext().getResources().getString(R.string.language);


    /**
     * Please replace this with a valid API key which is enabled for the
     * YouTube Data API v3 service. Go to the
     * <a href="https://console.developers.google.com/">Google Developers Console</a>
     * to register a new developer key.
     */
    public static final String YOUTUBE_KEY = "AIzaSyAo7ytUfuG25wAfIxyIxx865FZrIhkTJWI";
    //community
    public static final String communityMessageList = HOST_URL + "api/posts/getPostsList";
    public static final String login_temporary = HOST_URL + "/api/account/doLogin";
    public static final String communityPraise = HOST_URL + "/api/posts/addPostsPraise";
    public static final String communityComment = HOST_URL + "/api/posts/addPostsReply";
    public static final String uploadImage = HOST_URL + "/api/upload/uploadImg";
    public static final String pubishImage = HOST_URL + "/api/posts/addPosts";
    public static final String deletePosts = HOST_URL + "/api/posts/deletePosts";
    public static final String addIntegral = HOST_URL + "/api/posts/deletePosts";

}
