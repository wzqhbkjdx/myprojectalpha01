package com.cgtrc.wzq.myprojectalpha01;

import android.app.Application;
import android.content.Context;

/**
 * Created by bym on 16/3/4.
 */
public class ProjectApp extends Application {
    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
    }
}
