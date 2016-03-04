package com.cgtrc.wzq.myprojectalpha01.ui.view;

/**
 * Created by bym on 16/3/3.
 */
public interface ISwipeRefreshView extends IBaseView{

    void getDataFinish();
    void showEmptyView();
    void showErrorView(Throwable throwable);
    void showRefresh();
    void hideRefresh();
}
