package com.cgtrc.wzq.myprojectalpha01.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.cgtrc.wzq.myprojectalpha01.MainActivity;
import com.cgtrc.wzq.myprojectalpha01.R;
import com.cgtrc.wzq.myprojectalpha01.adapter.NewsRecyclerListAdapter;
import com.cgtrc.wzq.myprojectalpha01.interf.NewsPresenter;
import com.cgtrc.wzq.myprojectalpha01.interf.NewsView;
import com.cgtrc.wzq.myprojectalpha01.interf.OnListFragmentInteract;
import com.cgtrc.wzq.myprojectalpha01.module.NewsItem;
import com.cgtrc.wzq.myprojectalpha01.presenter.NewsDataPresenter;
import com.cgtrc.wzq.myprojectalpha01.utils.Constants;
import com.cgtrc.wzq.myprojectalpha01.utils.UiUtils;

/**
 * Created by bym on 16/3/3.
 */
public class NewsFragment extends RecyclerFragment implements NewsView{

    private FragmentActivity context;
    private LinearLayoutManager layoutManager;
    private NewsRecyclerListAdapter adapter;
    private NewsPresenter presenter;


    public static NewsFragment newInstance(String type){
        Bundle args = new Bundle();
        args.putString(Constants.TYPE,type);
        NewsFragment fragment = new NewsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void initLayoutId() {
        layoutId = R.layout.fragment_recycler;
    }

    public RecyclerView getRecyclerView(){
        return recyclerView;
    }

    @Override
    protected void initViews() {
        super.initViews();
        context = getActivity();
        layoutManager = new LinearLayoutManager(context);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new NewsRecyclerListAdapter(context, new OnListFragmentInteract() {
            @Override
            public void onListFragmentInteraction(RecyclerView.ViewHolder holder) {
                if(holder instanceof NewsRecyclerListAdapter.ViewHolder){
                    NewsRecyclerListAdapter.ViewHolder viewHolder = (NewsRecyclerListAdapter.ViewHolder)holder;
                    Intent intent = new Intent(getActivity(),NewDetailActivity.class);
                    //点击新闻条目,启动新闻详情页 后续补充



                }
            }
        });

        recyclerView.setAdapter(adapter);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                int lastVisibleItem = layoutManager.findLastVisibleItemPosition();

                if (newState == RecyclerView.SCROLL_STATE_IDLE
                        && lastVisibleItem + 1 == adapter.getItemCount()
                        && adapter.isHasFooter()) {
                    presenter.loadBefore();
                }
            }
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    protected void initData() {
        presenter = new NewsDataPresenter();
        initBanner();
        onRefresh();
    }

    private void initBanner() {

    }


    @Override
    public void onRefresh() {
        adapter.clear();
        presenter.loadNews();
    }

    @Override
    public void showProgress() {
        changeProgress(true);
    }

    @Override
    public void addNews(NewsItem news) {
        adapter.addNews(news);
    }

    @Override
    public void hideProgress() {
        changeProgress(false);
    }

    @Override
    public void loadFailed(String msg) {
        if(isLive()){
            UiUtils.showSnack(((MainActivity)getActivity()).getDrawerLayout(),R.string.load_fail);
        }
    }
}
