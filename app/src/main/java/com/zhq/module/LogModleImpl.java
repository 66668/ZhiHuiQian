package com.zhq.module;

import com.zhq.bean.CommonBean;
import com.zhq.bean.LoginBean;
import com.zhq.http.MyHttpService;
import com.zhq.inter_face.OnCommonListener;
import com.zhq.utils.MLog;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 登录相关 m层实现
 */

public class LogModleImpl {

    /**
     * 登录
     *
     * @param listener
     */
    public void mLogin(String co, String userPhone, String ps, final OnCommonListener listener) {
        MLog.d("登录参数：", "co=" + co, "user_phone=" + userPhone, "ps=" + ps);

        MyHttpService.Builder.getHttpServer().login(co, userPhone, ps)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CommonBean<LoginBean>>() {
                    @Override
                    public void onCompleted() {
                        MLog.d("登录--onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        MLog.e("登录--登录失败异常onError:" + e.toString());
                        listener.onDataFailed("-1", "异常", (Exception) e);
                    }

                    @Override
                    public void onNext(CommonBean<LoginBean> bean) {
                        MLog.d("登录-onNext");

                        //处理返回结果
                        if (bean.getCode().contains("0")) {

                            listener.onDataSuccess(bean.getCode(), bean.getMessage(), bean.getResult());
                        } else {
                            if (bean.getCode().contains("1")) {

                                listener.onDataSuccess(bean.getCode(), bean.getMessage(), bean.getResult());
                            } else {
                                MLog.e("返回数据错误：", bean.getMessage());
                                listener.onDataFailed(bean.getCode(), bean.getMessage(), new Exception(bean.getMessage()));
                            }
                        }
                    }

                });
    }

}
