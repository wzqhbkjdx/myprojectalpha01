package com.cgtrc.wzq.myprojectalpha01;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.cgtrc.wzq.myprojectalpha01.ui.activity.AboutActivity;
import com.cgtrc.wzq.myprojectalpha01.ui.activity.BaseActivity;
import com.cgtrc.wzq.myprojectalpha01.ui.activity.ContentFragment;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {

    @Bind(R.id.nav_view)
    NavigationView navView;
    @Bind(R.id.drawer_layout)
    DrawerLayout drawerLayout;

    public DrawerLayout getDrawerLayout() {
        return drawerLayout;
    }


    @Override
    protected void initView() {
        super.initView();
        ButterKnife.bind(this);
        initToolbar();
        setupDrawer();
        initNavigationView();
        Log.i("MainActivity","初始化侧滑菜单");
        replace(ContentFragment.MENU_NEWS);
        Log.i("MainActivity","替换fragment");
    }

    /**
     * 初始化工具栏
     */
    @Override
    protected void initToolbar() {
        if(toolbar == null){
            throw new NullPointerException("please add a Toolbar in your layout.");
        }
        setSupportActionBar(toolbar);
    }

    /**
     * 切换fragment
     * @param type
     */
    private void replace(String type){
       replaceFragment(ContentFragment.newInstance(type),type);
    }

    /**
     * 初始化侧滑菜单
     */
    private void initNavigationView() {
        navView.inflateMenu(R.menu.nav_menu);
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {

                return true;
            }
        });
    }

    /**
     * 设置侧滑菜单
     */
    private void setupDrawer() {
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,
                R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }


    /**
     * 获得布局的id
     * @return
     */
    @Override
    protected int getLayoutId() {
        layoutId = R.layout.activity_main;
        return layoutId;
    }

    /**
     * 初始化presenter
     */
    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initData() {

    }

    /**
     * 创建右上角的菜单
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_about,menu);
        return true;
    }

    /**
     * 右上角菜单的点击事件
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id ==  R.id.action_about){
            Intent intent = new Intent(this, AboutActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * 点击返回键的时候,如果侧滑菜单处于打开状态,则关闭侧滑菜单
     */
    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}
