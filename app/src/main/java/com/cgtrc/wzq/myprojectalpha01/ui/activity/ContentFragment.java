package com.cgtrc.wzq.myprojectalpha01.ui.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

import com.cgtrc.wzq.myprojectalpha01.R;
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

    }

    private void initFragments() {
        menuType = getArguments().getString(Constants.TYPE);
        if(MENU_NEWS.equals(menuType)){
            String[] titles = new String[] {
                getString(R.string.dynamic), getString(R.string.surrounding), getString(R.string.futrue)
            };
            this.titles = Arrays.asList(titles);
            for(int i = 0; i < titles.length; i++){
                recyclerFragmentsList.add(new NewsFragment());
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
