package com.zhq.presenter;

import android.content.Context;

import com.zhq.inter_face.OnMainListener;
import com.zhq.module.MainModleImpl;


/**
 */

public class MainPresenterImpl implements OnMainListener {
    OnMainListener view;
    Context context;
    MainModleImpl modle;

    public MainPresenterImpl(Context context, OnMainListener view) {
        this.view = view;
        this.context = context;
        modle = new MainModleImpl();
    }

    public void pGetData(String maxTime, String minTime, int size, String storeID) {
        modle.mGetMainData(maxTime, minTime, size, storeID, this);
    }

    public void pMoreData(String maxTime, String minTime, int size, String storeID) {
        modle.mMoreData(maxTime, minTime, size, storeID, this);
    }

    public void pRefreshData(String maxTime, String minTime, int size, String storeID) {
        modle.mRefreshData(maxTime, minTime, size, storeID, this);
    }


    @Override
    public void onListSuccess(String code, String msg, Object obj) {
        view.onListSuccess(code, msg, obj);
    }

    @Override
    public void onListFailed(String code, String msg, Exception e) {
        view.onListFailed(code, msg, e);
    }

    @Override
    public void onRefreshSuccess(String code, String msg, Object obj) {
        view.onRefreshSuccess(code, msg, obj);
    }

    @Override
    public void onRefreshFailed(String code, String msg, Exception e) {
        view.onRefreshFailed(code, msg, e);
    }

    @Override
    public void onMoreSuccess(String code, String msg, Object obj) {
        view.onMoreSuccess(code, msg, obj);
    }

    @Override
    public void onMoreFailed(String code, String msg, Exception e) {
        view.onMoreFailed(code, msg, e);
    }
}
