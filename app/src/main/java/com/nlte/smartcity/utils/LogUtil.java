package com.nlte.smartcity.utils;

import android.util.Log;

/**日志输出工具类
 * Created by NLTE on 2016/3/21 0021.
 */
public class LogUtil {
    private static boolean is_debug = true;//开发阶段默认为true， 发布阶段为false
    public static void i(String tag, String msg){
        if (is_debug){
            Log.i(tag, msg);
        }
    }
    public static void v(String tag, String msg){
        if (is_debug){
            Log.v(tag, msg);
        }
    }
    public static void d(String tag, String msg){
        if (is_debug){
            Log.d(tag, msg);
        }
    }
    public static void e(String tag, String msg){
        if (is_debug){
            Log.e(tag, msg);
        }
    }
    public static void w(String tag, String msg){
        if (is_debug){
            Log.w(tag, msg);
        }
    }
}
