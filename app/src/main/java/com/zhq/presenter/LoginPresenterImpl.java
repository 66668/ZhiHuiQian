package com.zhq.presenter;

import android.content.Context;

import com.zhq.inter_face.OnCommonListener;
import com.zhq.module.LogModleImpl;


/**
 */

public class LoginPresenterImpl implements OnCommonListener {
    OnCommonListener view;
    Context context;
    LogModleImpl modle;

    public LoginPresenterImpl(Context context, OnCommonListener view) {
        this.view = view;
        this.context = context;
        modle = new LogModleImpl();
    }

    public void pLogin(String co, String userPhone, String ps) {
        modle.mLogin(co, userPhone, ps, this);
    }


    @Override
    public void onDataSuccess(String code, String msg, Object obj) {
        view.onDataSuccess(code, msg, obj);

    }

    @Override
    public void onDataFailed(String code, String msg, Exception e) {
        view.onDataFailed(code, msg, e);
    }
}
