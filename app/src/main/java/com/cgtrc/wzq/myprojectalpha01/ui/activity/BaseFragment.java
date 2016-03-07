package com.cgtrc.wzq.myprojectalpha01.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cgtrc.wzq.myprojectalpha01.ProjectApp;

import butterknife.ButterKnife;
import io.realm.Realm;

/**
 * Created by bym on 16/3/3.
 */
public abstract class BaseFragment extends Fragment {
    protected View rootView;
    protected int layoutId;
    protected Realm realm;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(rootView == null){
            initLayoutId();
            rootView = inflater.inflate(layoutId,container,false);
            ButterKnife.bind(this,rootView);//将rootView绑定到fragment上
            initViews();
        }
        alwaysInit();
        return rootView;
    }

    private void alwaysInit() {
        ButterKnife.bind(this,rootView);
    }

    @Override
    public void onStart() {
        super.onStart();
        realm = Realm.getDefaultInstance();//得到Realm数据库实例
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ProjectApp.getWatcher(getActivity()).watch(this); //Application实例产生一个RefWatcher进行内存泄漏监测
        realm.close();
    }

    public boolean isLive(){
        return getActivity() != null && getActivity().isDestroyed();
    }

    protected abstract void initData();

    protected abstract void initViews();

    public abstract void initLayoutId();
}
