package com.cgtrc.wzq.myprojectalpha01;

import android.app.Application;
import android.content.Context;

import com.orhanobut.logger.LogLevel;
import com.orhanobut.logger.Logger;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by bym on 16/3/4.
 */
public class ProjectApp extends Application {

    private RefWatcher refWatcher; //内存泄漏监测
    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        refWatcher = LeakCanary.install(this);
        context = this;
        Logger.init();
        setupRealm();//配置Realm数据库

        //只有调试模式下 才启用日志输出
        if(BuildConfig.DEBUG){
            Logger.init("Gank").hideThreadInfo().setMethodCount(0);
        }else{
            Logger.init("Gank").setLogLevel(LogLevel.NONE);
        }
    }

    private void setupRealm() {
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder(this).build();
        Realm.setDefaultConfiguration(realmConfiguration);
    }

    public static RefWatcher getWatcher(Context context) {
        ProjectApp application = (ProjectApp) context.getApplicationContext();
        return application.refWatcher;
    }
}
