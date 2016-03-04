package com.cgtrc.wzq.myprojectalpha01.interf;

import com.cgtrc.wzq.myprojectalpha01.module.NewsItem;

/**
 * Created by bym on 16/3/4.
 */
public interface NewsView <T extends NewsItem>{
    void showProgress();
    void addNews(T news);
    void hideProgress();
    void loadFailed(String msg);
}
