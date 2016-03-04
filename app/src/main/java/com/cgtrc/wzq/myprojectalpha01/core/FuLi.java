package com.cgtrc.wzq.myprojectalpha01.core;

import com.cgtrc.wzq.myprojectalpha01.data.GankData;
import com.cgtrc.wzq.myprojectalpha01.data.PrettyGirlData;
import com.cgtrc.wzq.myprojectalpha01.data.entity.休息视频Data;

import retrofit.http.GET;
import retrofit.http.Path;
import rx.Observable;

/**
 * Created by bym on 16/3/3.
 */
public interface FuLi {

    @GET("/data/福利/{pagesize}/{page}")
    Observable<PrettyGirlData> getPrettyGirlData(@Path("pagesize") int pagesize,@Path("page") int page);

    @GET("/data/休息视频/{pagesize}/{page}")
    Observable<休息视频Data> get休息视频Data(@Path("pagesize") int pagesize, @Path("page")int page);

    @GET("/day/{year}/{month}/{day}")
    Observable<GankData> getGankData(@Path("year")int year, @Path("month")int month, @Path("day")int day);
}
