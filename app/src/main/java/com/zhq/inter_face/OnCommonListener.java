package com.zhq.inter_face;

/**
 * 单一接口act的统一数据调用监听
 */

public interface OnCommonListener {

    void onDataSuccess(String code,String msg,Object obj);

    void onDataFailed(String code, String msg, Exception e);
}
