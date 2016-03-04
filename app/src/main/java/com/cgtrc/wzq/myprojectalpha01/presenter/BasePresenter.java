package com.cgtrc.wzq.myprojectalpha01.presenter;

import android.app.Activity;

import com.cgtrc.wzq.myprojectalpha01.core.FuLi;
import com.cgtrc.wzq.myprojectalpha01.core.MainFactory;
import com.cgtrc.wzq.myprojectalpha01.ui.view.IBaseView;

/**
 * Created by bym on 16/3/3.
 */
public class BasePresenter<GV extends IBaseView> {

    protected GV mView;
    protected Activity context;
    public static final FuLi mFuLi = MainFactory.getFuLiInstance();

    public BasePresenter(Activity context, GV view){
        this.context = context;
        this.mView = view;
    }

}
