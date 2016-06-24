package com.oasis.something.util;

import com.orhanobut.logger.Logger;

/**
 * Created by liling on 2016/6/24.
 */
public class L   {

    public static boolean isDebug = true;

    public static void json(String tag,String msg){
        if (isDebug)
            Logger.t(tag).json(msg);
    }

    public static void v(String tag, String msg) {
        if (isDebug)
            Logger.t(tag).v(msg);
    }



    public static void d(String tag, String msg) {
        if (isDebug)
            Logger.t(tag).d(msg);
    }



    public static void i(String tag, String msg) {
        if (isDebug)
            Logger.t(tag).i(msg);
    }



    public static void w(String tag, String msg) {
        if (isDebug)
            Logger.t(tag).w(msg);
    }



    public static void e(String tag, String msg) {
        if (isDebug)
            Logger.t(tag).e(msg);
    }


}
