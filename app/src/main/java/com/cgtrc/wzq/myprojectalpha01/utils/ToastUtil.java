package com.cgtrc.wzq.myprojectalpha01.utils;

import android.content.Context;
import android.widget.Toast;

import com.cgtrc.wzq.myprojectalpha01.ProjectApp;

/**
 * Created by bym on 16/3/8.
 */
public class ToastUtil {

    Context mContext;

    private ToastUtil() {
    }

    private static void show(Context context, int resId, int duration) {
        Toast.makeText(context, resId, duration).show();
    }

    private static void show(Context context, String message, int duration) {
        Toast.makeText(context, message, duration).show();
    }

    public static void showShort(int resId) {
        Toast.makeText(ProjectApp.context, resId, Toast.LENGTH_SHORT).show();
    }

    public static void showShort(String message) {
        Toast.makeText(ProjectApp.context, message, Toast.LENGTH_SHORT).show();
    }

    public static void showLong(int resId) {
        Toast.makeText(ProjectApp.context, resId, Toast.LENGTH_LONG).show();
    }

    public static void showLong(String message) {
        Toast.makeText(ProjectApp.context, message, Toast.LENGTH_LONG).show();
    }

    public static void showLongLong(String message) {
        showLong(message);
        showLong(message);
    }

    public static void showLongLong(int resId) {
        showLong(resId);
        showLong(resId);
    }

    public static void showLongLongLong(int resId) {
        showLong(resId);
        showLong(resId);
        showShort(resId);
    }

    public static void showLongLongLong(String message) {
        showLong(message);
        showLong(message);
        showShort(message);
    }
}
