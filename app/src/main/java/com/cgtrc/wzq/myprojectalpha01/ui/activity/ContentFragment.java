package com.cgtrc.wzq.myprojectalpha01.ui.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;

import com.cgtrc.wzq.myprojectalpha01.R;
import com.cgtrc.wzq.myprojectalpha01.module.NewsItem;
import com.cgtrc.wzq.myprojectalpha01.utils.Constants;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.Bind;

/**
 * Created by bym on 16/3/3.
 */
public class ContentFragment extends BaseFragment {

    @Bind(R.id.pager)
    ViewPager viewPager;
    @Bind(R.id.tabs)
    TabLayout tabs;

    public static final String MENU_NEWS = "news";
    public static final String MENU_PIC = "pic";
    public static final String MENU_SECRET = "knowledge";


    private List<RecyclerFragment> recyclerFragmentsList = new ArrayList<>();
    private List<String> titles;
    private NewsTabPageAdapter adapter;
    private String menuType;

    public static ContentFragment newInstance(String type){
        Bundle args = new Bundle();
        args.putString(Constants.TYPE,type);
        ContentFragment fragment = new ContentFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void initLayoutId() {
        layoutId = R.layout.fragment_layout;
    }

    @Override
    protected void initViews() {
        adapter = new NewsTabPageAdapter(getChildFragmentManager());
        initFragments();
        viewPager.setAdapter(adapter);
    }

    private void initFragments() {
        menuType = getArguments().getString(Constants.TYPE);
        Log.i("ContentFragment",menuType);
        if(MENU_NEWS.equals(menuType)){
            String[] titles = new String[] {
                getString(R.string.dynamic), getString(R.string.surrounding), getString(R.string.futrue)
            };
            this.titles = Arrays.asList(titles);
            for(int i = 0; i < titles.length; i++){
                NewsItem newsItem = new NewsItem();
                NewsFragment newsFragment = NewsFragment.newInstance(menuType);

//                newsFragment.addNews(newsItem); //fragment attach到父控件的时候,才会生成adapter,所以此时的adapter为空,所以出现空指针异常
                recyclerFragmentsList.add(newsFragment); //fragment嵌套fragment
            }
        } else {

        }

        adapter.setFragments(recyclerFragmentsList,titles);
    }

    @Override
    protected void initData() {

    }





    public static class NewsTabPageAdapter extends FragmentStatePagerAdapter {

        private List<RecyclerFragment> fragments;
        private List<String> titles;

        public NewsTabPageAdapter(FragmentManager fm){
            super(fm);
        }

        public void setFragments(List<RecyclerFragment> fragments, List<String> titles){
            this.fragments = fragments;
            this.titles = titles;
        }

        public NewsTabPageAdapter(FragmentManager fm,List<RecyclerFragment> fragments, List<String> titles) {
            super(fm);
            this.fragments = fragments;
            this.titles = titles;
        }


        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles.get(position);
        }
    }
}
