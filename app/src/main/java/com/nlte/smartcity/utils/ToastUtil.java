package com.nlte.smartcity.utils;

import android.content.Context;
import android.widget.Toast;

/**Toast工具类
 * Created by NLTE on 2016/3/14 0014.
 */
public class ToastUtil {
    public static void show(Context context, String text){
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
    }
}
