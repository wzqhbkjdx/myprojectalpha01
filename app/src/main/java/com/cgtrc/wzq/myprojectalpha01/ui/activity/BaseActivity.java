package com.cgtrc.wzq.myprojectalpha01.ui.activity;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.cgtrc.wzq.myprojectalpha01.R;
import com.cgtrc.wzq.myprojectalpha01.presenter.BasePresenter;
import com.umeng.analytics.MobclickAgent;

import butterknife.ButterKnife;

/**
 * Created by bym on 16/3/3.
 */
public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity {

    protected Toolbar mToolBar;

    protected int layoutId = R.layout.activity_base;

    //the presenter of this Activity
    protected P mPresenter;

    //TODO use Dagger2 instance Presenter
    protected abstract void initPresenter();

    protected abstract int getLayout();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initViews();
    }

    protected void initViews(){
        setContentView(layoutId);
        ButterKnife.bind(this);
        initPresenter(); //初始化presenter
        checkPresenterIsNull(); //检查presenter是否为空
        initToolBar(); //检查toolbar是否为空并初始化
        //以后还可以初始化数据库什么的 但需要先在Application中创建实例
    }

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);//友盟数据统计
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }

    private void checkPresenterIsNull() {
//        if(mPresenter == null) {
//            throw new IllegalStateException("please init mPresenter in initPresenter() method ");
//        }
    }

    final private void initToolBar() {
        mToolBar = (Toolbar) findViewById(R.id.toolbar);
        if(mToolBar == null){
            throw new NullPointerException("please add a Toolbar in your layout.");
        }
        setSupportActionBar(mToolBar);
    }

    /**
     * set the id of menu
     * @return if values is less then zero ,and the activity will not show menu
     */
    protected int getMenuRes() {
        return -1;
    }

    /**
     * 创建option菜单
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if(getMenuRes() < 0)
            return true;
        getMenuInflater().inflate(getMenuRes(),menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case android.R.id.home:
                //don't use finish() and use onBackPressed() will be a good practice , trust me !
                onBackPressed();//用back键的点击效果替代home键的点击效果
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void setTitle(String strTitle,boolean showHome){
        setTitle(strTitle);
        getSupportActionBar().setDisplayShowHomeEnabled(showHome);
        getSupportActionBar().setDisplayHomeAsUpEnabled(showHome);
    }

    public void replaceFragment(Fragment fragment, String tag){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content_main,fragment,tag);
        transaction.commit();
    }
}
