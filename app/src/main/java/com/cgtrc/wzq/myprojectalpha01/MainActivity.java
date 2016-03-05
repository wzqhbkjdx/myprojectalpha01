package com.cgtrc.wzq.myprojectalpha01;

import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;

import com.cgtrc.wzq.myprojectalpha01.ui.activity.BaseActivity2;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity2 {

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
        //setupDrawer();
        //initNavigationView();
        Log.i("MainActivity","初始化侧滑菜单");
//        replace(ContentFragment.MENU_NEWS);
        Log.i("MainActivity","替换fragment");
    }

//    private void replace(String type){
//       replaceFragment(ContentFragment.newInstance(type),type);
//    }


//    private void initNavigationView() {
//        navView.inflateMenu(R.menu.nav_menu);
//        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(MenuItem item) {
//
//                return true;
//            }
//        });
//    }

    /**
     * 设置侧滑菜单
     */
//    private void setupDrawer() {
//        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,mToolBar,
//                R.string.navigation_drawer_open,R.string.navigation_drawer_close);
//        drawerLayout.addDrawerListener(toggle);
//        toggle.syncState();
//    }



    @Override
    protected int getLayoutId() {
        layoutId = R.layout.activity_main;
        return layoutId;
    }

    @Override
    protected void initData() {

    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu_about,menu);
//        return true;
//    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        int id = item.getItemId();
//        if(id ==  R.id.action_about){
//            Intent intent = new Intent(this, AboutActivity.class);
//            startActivity(intent);
//        }
//        return super.onOptionsItemSelected(item);
//    }
//
//    @Override
//    public void onBackPressed() {
//        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
//            drawerLayout.closeDrawer(GravityCompat.START);
//        } else {
//            super.onBackPressed();
//        }
//    }
}
