package com.cgtrc.wzq.myprojectalpha01.ui.testfragment;

import android.content.Context;
import android.graphics.ColorFilter;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.cgtrc.wzq.myprojectalpha01.core.GankCategory;
import com.cgtrc.wzq.myprojectalpha01.data.entity.Gank;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by bym on 16/3/8.
 */
public class TestMainListAdapter extends RecyclerView.Adapter<TestMainListAdapter.ViewHolderItem> {

    private List<Gank> mGankList;
    private Context context;
    private static IClickMainItem mIClickItem;
    private static ColorFilter mColorFilter;

    public TestMainListAdapter(Context context){
        context = context;
        mGankList = new ArrayList<>();
        mGankList.add(getDefGankGirl());

        float[] array = new float[] {
                1,0,0,0,-70,
                0,1,0,0,-70,
                0,0,1,0,-70,
                0,0,0,1,0,
        };
        mColorFilter = new ColorMatrixColorFilter(new ColorMatrix(array));
    }

    /**
     * add data append to history data*
     * @param data new data
     */
    public void update(List<Gank> data) {
        formatGankData(data);
        notifyDataSetChanged();
    }

    /**
     * filter list and add category entity into list
     * @param data source data
     */
    private void formatGankData(List<Gank> data) {

    }


    @Override
    public ViewHolderItem onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(ViewHolderItem holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    /**
     * ViewHolderItem is a parent class for different item
     */
    abstract static class ViewHolderItem extends RecyclerView.ViewHolder {
        public ViewHolderItem(View itemView) {
            super(itemView);
        }
        abstract void bindItem(Context context, Gank gank);
    }

    public interface IClickMainItem {
        /**
         * click gank girl info item
         * @param gank
         * @param viewImage
         * @param viewText
         */
        void onClickGankItemGirl(Gank gank,View viewImage,View viewText);
        /**
         * click gank normal info item
         * @param gank
         * @param view
         */
        void onClickGankItemNormal(Gank gank, View view);
    }

    private Gank getDefGankGirl(){
        Gank gank = new Gank();
        gank.publishedAt = new Date(System.currentTimeMillis());
        gank.url = "empty";
        gank.type = GankCategory.福利.name();
        return gank;
    }



}
