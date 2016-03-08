package com.cgtrc.wzq.myprojectalpha01.ui.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.cgtrc.wzq.myprojectalpha01.R;
import com.umeng.analytics.MobclickAgent;

import butterknife.Bind;
import io.realm.Realm;

/**
 * Created by bym on 16/3/5.
 */
public abstract class BaseActivity extends AppCompatActivity {

    @Bind(R.id.toolbar)
    protected Toolbar toolbar;

    protected Realm realm;

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
        realm = Realm.getDefaultInstance();
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (null != realm) {
            realm.close();
        }
        //是否需要对Activity进行内存泄露监测,需要进一步考量
//        ProjectApp.getWatcher(getActivity()).watch(this); //Application实例产生一个RefWatcher进行内存泄漏监测
    }

    abstract protected void initToolbar();



    public void replaceFragment(Fragment fragment, String tag) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content_main,fragment,tag);
        transaction.commit();
    }

    protected abstract int getLayoutId();


    protected abstract void initData();


}
