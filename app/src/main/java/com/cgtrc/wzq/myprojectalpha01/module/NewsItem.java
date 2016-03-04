package com.cgtrc.wzq.myprojectalpha01.module;

import com.cgtrc.wzq.myprojectalpha01.interf.NewsItemInterf;

import java.util.List;

/**
 * implement this means it's a item of a newsList
 * Created by bym on 16/3/3.
 */
public class NewsItem implements NewsItemInterf {

    private String url;
    private int id;
    private int type;
    private String title;
    private String content;
    private String pubDate;
    private List<String> imageUrls;

    public NewsItem(){

    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public List<String> getImageUrls() {
        return imageUrls;
    }

    public void setImageUrls(List<String> imageUrls) {
        this.imageUrls = imageUrls;
    }
}
