package com.cgtrc.wzq.myprojectalpha01.ui.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.cgtrc.wzq.myprojectalpha01.R;
import com.cgtrc.wzq.myprojectalpha01.presenter.BasePresenter;

import butterknife.Bind;

/**
 * Created by bym on 16/3/5.
 */
public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity {

    @Bind(R.id.toolbar)
    protected Toolbar toolbar;

    protected int layoutId;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initData();
    }

    /**
     * 子类覆盖该方法,必须调用父类 super.initView()
     */
    protected void initView(){
        getLayoutId();
        setContentView(layoutId);
        //ButterKnife.bind(this);
        //initToolbar();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    abstract protected void initToolbar();

    private void checkPresenterIsNull() {
//        if(mPresenter == null) {
//            throw new IllegalStateException("please init mPresenter in initPresenter() method ");
//        }
    }

    public void replaceFragment(Fragment fragment, String tag) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content_main,fragment,tag);
        transaction.commit();
    }

    protected abstract int getLayoutId();

    protected abstract void initPresenter();

    protected abstract void initData();


}
