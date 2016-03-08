package com.cgtrc.wzq.myprojectalpha01.presenter;

import android.app.Activity;

import com.cgtrc.wzq.myprojectalpha01.BuildConfig;
import com.cgtrc.wzq.myprojectalpha01.data.GankData;
import com.cgtrc.wzq.myprojectalpha01.data.entity.Gank;
import com.cgtrc.wzq.myprojectalpha01.ui.view.IMainView;
import com.cgtrc.wzq.myprojectalpha01.utils.AndroidUtils;
import com.umeng.update.UmengUpdateAgent;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;

/**
 * Created by bym on 16/3/4.
 */
public class NewsDataPresenter extends BasePresenter<IMainView> {

    private static final int DAY_OF_MILLISECOND = 24 * 60 * 60 * 1000;
    private Date mCurrentDate;
    private List<Gank> mGankList = new ArrayList<>();
    private int mCountOfGetMoreDataEmpty = 0;

    //if execute getDataMore method more than once ,this flag will be true else false
    private boolean hasLoadMoreData = false;

    public NewsDataPresenter(Activity context, IMainView view) {
        super(context, view);
    }

    /**
     * 用友盟服务检查更新
     */
    public void checkAutoUpdateByUmeng(){
        if(context.getIntent().getSerializableExtra("BUNDLE_GANK") == null){
            UmengUpdateAgent.setUpdateCheckConfig(BuildConfig.DEBUG);
            //check update even in 2g/3g/4g condition
            UmengUpdateAgent.setUpdateOnlyWifi(false);
            UmengUpdateAgent.update(context);
        }
    }

    /**
     * 获取版本信息,显示更新对话框并保存当前版本信息
     */
    public void checkVersionInfo() {
        String currentVersion = AndroidUtils.getAppVersion(context);
        String localVersionName = AndroidUtils.getLocalVersion(context);
        if(!localVersionName.equals(currentVersion)){
            //本地存储的版本信息和当前获取的版本信息不同,显示一个从html转化过来的对话框
            mView.showChangeLogInfo("changelog.html");
            //将当前笨笨存储其阿里
            AndroidUtils.setCurrentVersion(context, currentVersion);
        }
    }

    /**
     * 是否应该加载更多数据
     * @return
     */
    public boolean shouldRefillData() {
        return !hasLoadMoreData;
    }

    public void getData(final Date date) {

        mCurrentDate = date;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        mFuLi.getGankData(year,month,day)
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Func1<GankData, GankData.Result>(){

                    @Override
                    public GankData.Result call(GankData gankData) {
                        return gankData.result;
                    }
                })
                .map(new Func1<GankData.Result, List<Gank>>(){
                    @Override
                    public List<Gank> call(GankData.Result result) {
                        return addAllResults(result);
                    }
                })
                .subscribe(new Subscriber<List<Gank>>(){

                    @Override
                    public void onCompleted() {
                        // after get data complete, need put off time one day
                        mCurrentDate = new Date(date.getTime()-DAY_OF_MILLISECOND);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(List<Gank> ganks) {
                        // some day the data will be return empty like sunday, so we need get after day data
                        if (ganks.isEmpty()) {
                            getData(new Date(date.getTime()-DAY_OF_MILLISECOND));
                        } else {
                            mCountOfGetMoreDataEmpty = 0;
                            mView.fillData(ganks);
                        }
                        mView.getDataFinish();
                    }
                });
    }

    public void getDataMore(){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(mCurrentDate);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH)+1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        mFuLi.getGankData(year,month,day)
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Func1<GankData, GankData.Result>() {
                    @Override
                    public GankData.Result call(GankData gankData) {
                        return gankData.result;
                    }
                })
                .map(new Func1<GankData.Result, List<Gank>>() {
                    @Override
                    public List<Gank> call(GankData.Result result) {
                        return addAllResults(result);
                    }
                })
                .subscribe(new Subscriber<List<Gank>>() {
                    @Override
                    public void onCompleted() {
                        // after get data complete, need put off time one day
                        mCurrentDate = new Date(mCurrentDate.getTime()-DAY_OF_MILLISECOND);
                        // now user has execute getMoreData so this flag will be set true
                        //and now when user pull down list we would not refill data
                        hasLoadMoreData = true;
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(List<Gank> ganks) {
                        //when this day is weekend , the list will return empty(weekend has not gank info,the editors need rest)
                        if (ganks.isEmpty()) {
                            //record count of empty day
                            mCountOfGetMoreDataEmpty += 1;
                            //if empty day is more than five,it indicate has no more data to show
                            if(mCountOfGetMoreDataEmpty>=5){
                                mView.hasNoMoreData();
                            }else{
                                // we need look forward data
                                getDataMore();
                            }
                        } else {
                            mCountOfGetMoreDataEmpty = 0;
                            mView.appendMoreDataToView(ganks);
                        }
                        mView.getDataFinish();
                    }
                });

    }

    private List<Gank> addAllResults(GankData.Result results){
        mGankList.clear();
        if(results.androidList != null)
            mGankList.addAll(results.androidList);
        if(results.iOSList != null)
            mGankList.addAll(results.iOSList);
        if(results.appList != null)
            mGankList.addAll(results.appList);
        if(results.拓展资源List != null)
            mGankList.addAll(results.拓展资源List);
        if (results.瞎推荐List != null)
            mGankList.addAll(results.瞎推荐List);
        if (results.休息视频List != null)
            mGankList.addAll(results.休息视频List);
        // make meizi data is in first position 即 recyclerView 的 header
        if (results.妹纸List != null)
            mGankList.addAll(0, results.妹纸List);
        return mGankList;
    }
}
