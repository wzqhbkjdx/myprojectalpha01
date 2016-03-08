package com.cgtrc.wzq.myprojectalpha01.ui.activity;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;

import com.cgtrc.wzq.myprojectalpha01.R;

import butterknife.Bind;

/**
 * Created by bym on 16/3/3.
 */
public abstract class RecyclerFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener{
    @Bind(R.id.list)
    RecyclerView recyclerView;
    @Bind(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void initViews() {
        recyclerView.setHasFixedSize(true);
        swipeRefreshLayout.setColorSchemeColors(R.color.colorPrimary,R.color.colorPrimaryDark, R.color.colorAccent);
        swipeRefreshLayout.setOnRefreshListener(this);
    }

    public void changeProgress(final boolean refreshState){
        if(null != swipeRefreshLayout){
            swipeRefreshLayout.post(new Runnable() {
                @Override
                public void run() {
                    swipeRefreshLayout.setRefreshing(refreshState);
                }
            });
        }
    }



    public RecyclerView getRecyclerView() {
        return recyclerView;
    }
}
