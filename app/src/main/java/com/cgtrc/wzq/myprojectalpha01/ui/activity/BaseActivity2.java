package com.cgtrc.wzq.myprojectalpha01.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.cgtrc.wzq.myprojectalpha01.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by bym on 16/3/5.
 */
public abstract class BaseActivity2 extends AppCompatActivity {

    @Bind(R.id.toolbar)
    protected Toolbar toolbar;

    protected int layoutId;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    /**
     * 子类覆盖该方法,必须调用父类 super.initView()
     */
    protected void initView(){
        getLayoutId();
        setContentView(layoutId);
        ButterKnife.bind(this);
        initToolbar();
    }

    final private void initToolbar() {
        if(toolbar == null){
            throw new NullPointerException("please add a Toolbar in your layout.");
        }
        setSupportActionBar(toolbar);
    }

    protected abstract int getLayoutId();



    protected abstract void initData();


}
