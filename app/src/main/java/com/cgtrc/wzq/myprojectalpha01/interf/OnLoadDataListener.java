package com.cgtrc.wzq.myprojectalpha01.interf;

import com.cgtrc.wzq.myprojectalpha01.data.BaseData;

/**
 * Created by bym on 16/3/7.
 */
public interface OnLoadDataListener<T extends BaseData> {

    void onLoadSuccess(T news);
    void onFailure(String msg, Exception e);

}
