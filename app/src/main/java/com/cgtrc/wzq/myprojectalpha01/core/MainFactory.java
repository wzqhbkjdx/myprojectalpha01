package com.cgtrc.wzq.myprojectalpha01.core;

/**
 * Created by bym on 16/3/3.
 */
public class MainFactory {
    /**
     * 数据主机地址
     */
    public static final String HOST = "http://gank.avosapps.com/api";

    private static FuLi mFuLi;

    protected static final Object monitor = new Object();

    public static FuLi getFuLiInstance(){
        synchronized(monitor){
            if(mFuLi == null){
                mFuLi = new MainRetrofit().getService();
            }
            return mFuLi;
        }
    }
}
