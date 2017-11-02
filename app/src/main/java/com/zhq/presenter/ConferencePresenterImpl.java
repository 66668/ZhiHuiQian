package com.zhq.presenter;

import android.content.Context;

import com.zhq.inter_face.OnConferenceListener;
import com.zhq.module.ConferenceModleImpl;


/**
 */

public class ConferencePresenterImpl implements OnConferenceListener {
    OnConferenceListener view;
    Context context;
    ConferenceModleImpl modle;

    public ConferencePresenterImpl(Context context, OnConferenceListener view) {
        this.view = view;
        this.context = context;
        modle = new ConferenceModleImpl();
    }

    public void pGetData(String maxTime, String minTime, int size, String storeID) {
        modle.mGetConferenceData(maxTime, minTime, size, storeID, this);
    }

    public void pMoreData(String maxTime, String minTime, int size, String storeID) {
        modle.mMoreData(maxTime, minTime, size, storeID, this);
    }

    public void pRefreshData(String maxTime, String minTime, int size, String storeID) {
        modle.mRefreshData(maxTime, minTime, size, storeID, this);
    }

    public void pPostCare(String EmployeeID) {
        modle.mPostCare(EmployeeID, this);
    }

    public void pPostCareless(String EmployeeID) {
        modle.mPostCareless(EmployeeID, this);
    }

    //
    @Override
    public void onListSuccess(String code, String msg, Object obj) {
        view.onListSuccess(code, msg, obj);
    }

    @Override
    public void onListFailed(String code, String msg, Exception e) {
        view.onListFailed(code, msg, e);
    }

    //
    @Override
    public void onRefreshSuccess(String code, String msg, Object obj) {
        view.onRefreshSuccess(code, msg, obj);
    }

    @Override
    public void onRefreshFailed(String code, String msg, Exception e) {
        view.onRefreshFailed(code, msg, e);
    }

    //
    @Override
    public void onMoreSuccess(String code, String msg, Object obj) {
        view.onMoreSuccess(code, msg, obj);
    }

    @Override
    public void onMoreFailed(String code, String msg, Exception e) {
        view.onMoreFailed(code, msg, e);
    }

    //
    @Override
    public void onCareSuccess(String code, String msg, Object obj) {
        view.onCareSuccess(code, msg, obj);
    }

    @Override
    public void onCareFailed(String code, String msg, Exception e) {
        view.onCareFailed(code, msg, e);
    }

    //
    @Override
    public void onCarelessSuccess(String code, String msg, Object obj) {
        view.onCarelessSuccess(code, msg, obj);
    }

    @Override
    public void onCarelessFailed(String code, String msg, Exception e) {
        view.onCarelessFailed(code, msg, e);

    }
}
