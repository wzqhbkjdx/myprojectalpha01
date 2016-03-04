package com.cgtrc.wzq.myprojectalpha01.data;

import com.cgtrc.wzq.myprojectalpha01.data.entity.Gank;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by bym on 16/3/3.
 */
public class GankData extends BaseData{

    public List<String> category;
    public Result result;

    public class Result{
        @SerializedName("Android") public List<Gank> androidList;
        @SerializedName("休息视频") public List<Gank> 休息视频List;
        @SerializedName("iOS") public List<Gank> iOSList;
        @SerializedName("福利") public List<Gank> 妹纸List;
        @SerializedName("拓展资源") public List<Gank> 拓展资源List;
        @SerializedName("瞎推荐") public List<Gank> 瞎推荐List;
        @SerializedName("App") public List<Gank> appList;
    }
}
