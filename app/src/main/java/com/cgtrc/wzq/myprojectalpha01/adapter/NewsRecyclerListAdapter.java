package com.cgtrc.wzq.myprojectalpha01.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cgtrc.wzq.myprojectalpha01.R;
import com.cgtrc.wzq.myprojectalpha01.interf.OnListFragmentInteract;
import com.cgtrc.wzq.myprojectalpha01.module.NewsItem;

import java.util.List;

/**
 * Created by bym on 16/3/3.
 */
public class NewsRecyclerListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private boolean showHeader;
    private boolean hasFooter;
    private OnListFragmentInteract listener;
    private final Context context;
    private NewsItem newsItem;
    public static int textGrey;
    public static int textDark;
    public List<NewsItem> newsItems;

    public void setShowHeader(boolean showHeader){
        this.showHeader = showHeader;
    }

    public boolean isHasFooter(){
        return hasFooter;
    }

    public void setHasFooter(boolean hasFooter){
        this.hasFooter = hasFooter;
        notifyDataSetChanged();
    }

    public NewsRecyclerListAdapter(Context context, OnListFragmentInteract listener){
        this.context = context;
        this.listener = listener;
    }

    public void addNews(NewsItem newsItem){
        this.newsItem = newsItem;  //得到新闻条目 对viewHolder进行适配
        notifyDataSetChanged();
    }

    public void clear(){
        showHeader = false;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.fragment_news_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        Context context = holder.itemView.getContext();
        textGrey = ContextCompat.getColor(context, R.color.darker_gray);
        textDark = ContextCompat.getColor(context, android.support.design.R.color.abc_primary_text_material_light);
        if(holder instanceof ViewHolder){
            final ViewHolder viewHolder = (ViewHolder) holder;
            viewHolder.mTitle.setText(context.getString(R.string.hangkong));
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (null != listener) {
                        listener.onListFragmentInteraction(viewHolder);
                    }

                }
            });
        }

    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public int getItemCount() {
//        return newsItems.size();
        return 5;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public final ImageView mImage;
        public final TextView mTitle;
        public final View mItem;
        public NewsItem newsItem;

        public ViewHolder(View itemView) {
            super(itemView);
            mImage = (ImageView) itemView.findViewById(R.id.story_img);
            mTitle = (TextView) itemView.findViewById(R.id.story_title);
            mItem = itemView.findViewById(R.id.story_img);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mTitle.getText() + "'";
        }
    }
}
