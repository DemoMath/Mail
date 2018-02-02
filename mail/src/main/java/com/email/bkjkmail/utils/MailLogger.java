package com.email.bkjkmail.utils;

import android.util.Log;

/**
 * Created by wudi on 2018/1/30.
 * 打印日志类
 */
public class MailLogger {

    private static final String LOG_TAG = "MAIL----->";

    private static final boolean IS_LOG = true;

    public static void loge (String msg) {
        if (IS_LOG) {
            Log.e(LOG_TAG,msg);
        }
    }

    public static void logi (String msg) {
        if (IS_LOG) {
            Log.i(LOG_TAG,msg);
        }
    }

    public static void logd (String msg) {
        if (IS_LOG) {
            Log.d(LOG_TAG,msg);
        }
    }

    public static void logv (String msg) {
        if (IS_LOG) {
            Log.v(LOG_TAG,msg);
        }
    }

    public static void logw (String msg) {
        if (IS_LOG) {
            Log.w(LOG_TAG,msg);
        }
    }
}
