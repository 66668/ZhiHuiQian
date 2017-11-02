package com.zhq.inter_face;

/**
 */

public interface OnConferenceListener {

    void onListSuccess(String code, String msg, Object obj);

    void onListFailed(String code, String msg, Exception e);

    void onRefreshSuccess(String code, String msg, Object obj);

    void onRefreshFailed(String code, String msg, Exception e);

    void onMoreSuccess(String code, String msg, Object obj);

    void onMoreFailed(String code, String msg, Exception e);

    void onCareSuccess(String code, String msg, Object obj);

    void onCareFailed(String code, String msg, Exception e);

    void onCarelessSuccess(String code, String msg, Object obj);

    void onCarelessFailed(String code, String msg, Exception e);
}
