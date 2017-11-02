package com.zhq.inter_face;

/**
 * 上拉 下拉 初始获取的统一监听
 */

public interface OnMainListener {

    void onListSuccess(String code, String msg, Object obj);

    void onListFailed(String code, String msg, Exception e);

    void onRefreshSuccess(String code, String msg, Object obj);

    void onRefreshFailed(String code, String msg, Exception e);

    void onMoreSuccess(String code, String msg, Object obj);

    void onMoreFailed(String code, String msg, Exception e);
}
