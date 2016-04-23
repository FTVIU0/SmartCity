package com.nlte.smartcity.utils;

import android.content.Context;

/**缓存工具类（例一：以url为文件名，以json作为文件内容；（推荐）例二：以url为key，以json为value）
 * 把json数据保存到本地
 * Created by Nlte
 * 2016/4/24 0024.
 */
public class CacheUtils {
    /*写缓存：
    *   以url为key，以json为value，写到本地*/
    public static void setCache(Context context, String url, String json){
        /*
        * 缓存也可以写到本地文件中，以url为文件名，以json作为文件内容*/
        SharePreferenceUtil.putString(context, url, json);
    }
    /*读缓存*/
    public static String getCache(Context context, String url){
        return SharePreferenceUtil.getString(context, url, null);
    }
}
