package com.cgtrc.wzq.myprojectalpha01.utils;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.view.View;

import com.cgtrc.wzq.myprojectalpha01.ProjectApp;

/**
 * Created by bym on 16/3/4.
 */
public class UiUtils  {
    private static Context context = ProjectApp.context;

    public static void showSnack(View rootView,int textId){
        Snackbar.make(rootView,context.getString(textId),Snackbar.LENGTH_SHORT).show();

    }
    public static void showSnackLong(View rootView, int textId){
        Snackbar.make(rootView,context.getString(textId),Snackbar.LENGTH_LONG).show();
    }
}
