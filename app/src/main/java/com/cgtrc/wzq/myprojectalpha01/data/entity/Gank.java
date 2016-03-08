package com.cgtrc.wzq.myprojectalpha01.data.entity;

import com.cgtrc.wzq.myprojectalpha01.core.GankCategory;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by bym on 16/3/3.
 */
public class Gank extends Soul implements Cloneable,Serializable {

    public boolean used;
    public String type;
    public String url;
    public String who;
    public String desc;
    public Date updateAt;
    public Date createdAt;
    public Date publishedAt;

    /**
     * this item is header type of gank or not,if true, this item will show category name
     */
    public boolean Header;
    public boolean is妹子() {
        return type.equals(GankCategory.福利.name());
    }

    @Override
    protected Gank clone() {
        Gank gank = null;
        try {
            gank = (Gank) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return gank;
    }
}
