package com.baulin.alexander.weatherapp;

import android.util.Log;

public class Logger {
    static private boolean LOG = BuildConfig.DEBUG;

    public static void i(String tag, String string) {
        if (LOG) Log.i(tag, string);
    }

    public static void e(String tag, String string) {
        if (LOG) Log.e(tag, string);
    }

    public static void d(String tag, String string) {
        if (LOG) Log.d(tag, string);
    }

    public static void v(String tag, String string) {
        if (LOG) Log.v(tag, string);
    }

    public static void w(String tag, String string) {
        if (LOG) Log.w(tag, string);
    }

}
