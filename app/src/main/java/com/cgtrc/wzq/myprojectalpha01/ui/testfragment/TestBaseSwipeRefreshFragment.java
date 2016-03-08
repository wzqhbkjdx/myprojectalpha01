package com.cgtrc.wzq.myprojectalpha01.ui.testfragment;

import android.support.annotation.CheckResult;
import android.support.v4.widget.SwipeRefreshLayout;

import com.cgtrc.wzq.myprojectalpha01.R;
import com.cgtrc.wzq.myprojectalpha01.presenter.BasePresenter;
import com.cgtrc.wzq.myprojectalpha01.ui.view.ISwipeRefreshView;

import butterknife.Bind;

/**
 * Created by bym on 16/3/8.
 */
public abstract class TestBaseSwipeRefreshFragment <P extends BasePresenter> extends TestBaseFragment<P> implements ISwipeRefreshView {

    @Bind(R.id.swipe_refresh)
    protected SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void initViews() {
        initSwipeLayout();
    }

    private void initSwipeLayout() {
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary,R.color.colorPrimaryDark,R.color.colorAccent);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if(prepareRefresh()){
                    onRefreshStarted();
                } else {
                    hideRefresh();
                }
            }
        });
    }

    protected abstract void onRefreshStarted();

    @Override
    public void hideRefresh() {
        swipeRefreshLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(swipeRefreshLayout != null) {
                    swipeRefreshLayout.setRefreshing(false);
                }
            }
        },1000);
    }

    /**
     * check data status
     * @return return true indicate it should load data really else indicate don't refresh
     */
    protected boolean prepareRefresh() {
        return true;
    }

    @Override
    public void showRefresh() {
        swipeRefreshLayout.setRefreshing(true);
    }

    @CheckResult
    public boolean isRefreshing() {
        return swipeRefreshLayout.isRefreshing();
    }

    @Override
    public void getDataFinish() {
        hideRefresh();
    }
}
