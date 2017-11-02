package com.zhq.module;

import com.zhq.bean.CommonBean;
import com.zhq.bean.MainBean;
import com.zhq.http.MyHttpService;
import com.zhq.inter_face.OnMainListener;
import com.zhq.utils.MLog;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 主界面
 */

public class MainModleImpl {

    /**
     * 获取信息
     *
     * @param listener
     */
    public void mGetMainData(String maxTime, String minTime, int size, String storeID, final OnMainListener listener) {
        MLog.d("main：", "maxTime=" + maxTime, "minTime=" + minTime, "size=" + size);

        MyHttpService.Builder.getHttpServer().getMainData(maxTime, minTime, size, storeID)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CommonBean<MainBean>>() {
                    @Override
                    public void onCompleted() {
                        MLog.d("登录--onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        MLog.e("登录--登录失败异常onError:" + e.toString());
                        listener.onListFailed("-1", "异常", (Exception) e);
                    }

                    @Override
                    public void onNext(CommonBean<MainBean> bean) {
                        MLog.d("登录-onNext");

                        //处理返回结果
                        if (bean.getCode().contains("0")) {

                            listener.onListSuccess(bean.getCode(), bean.getMessage(), bean.getResult());
                        } else {
                            if (bean.getCode().contains("1")) {

                                listener.onListSuccess(bean.getCode(), bean.getMessage(), bean.getResult());
                            } else {
                                MLog.e("返回数据错误：", bean.getMessage());
                                listener.onListFailed(bean.getCode(), bean.getMessage(), new Exception(bean.getMessage()));
                            }
                        }
                    }

                });
    }

    /**
     *
     * @param listener
     */
    public void mMoreData(String maxTime, String minTime, int size, String storeID, final OnMainListener listener) {
        MLog.d("main：", "maxTime=" + maxTime, "minTime=" + minTime, "size=" + size);

        MyHttpService.Builder.getHttpServer().getMainData(maxTime, minTime, size, storeID)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CommonBean<MainBean>>() {
                    @Override
                    public void onCompleted() {
                        MLog.d("登录--onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        MLog.e("登录--登录失败异常onError:" + e.toString());
                        listener.onMoreFailed("-1", "异常", (Exception) e);
                    }

                    @Override
                    public void onNext(CommonBean<MainBean> bean) {
                        MLog.d("登录-onNext");

                        //处理返回结果
                        if (bean.getCode().contains("0")) {

                            listener.onMoreSuccess(bean.getCode(), bean.getMessage(), bean.getResult().getObj());
                        } else {
                            if (bean.getCode().contains("1")) {

                                listener.onMoreSuccess(bean.getCode(), bean.getMessage(), bean.getResult().getObj());
                            } else {
                                MLog.e("返回数据错误：", bean.getMessage());
                                listener.onMoreFailed(bean.getCode(), bean.getMessage(), new Exception(bean.getMessage()));
                            }
                        }
                    }

                });
    }

    /**
     *
     * @param listener
     */
    public void mRefreshData(String maxTime, String minTime, int size, String storeID, final OnMainListener listener) {
        MLog.d("main：", "maxTime=" + maxTime, "minTime=" + minTime, "size=" + size);

        MyHttpService.Builder.getHttpServer().getMainData(maxTime, minTime, size, storeID)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CommonBean<MainBean>>() {
                    @Override
                    public void onCompleted() {
                        MLog.d("登录--onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        MLog.e("登录--登录失败异常onError:" + e.toString());
                        listener.onRefreshFailed("-1", "异常", (Exception) e);
                    }

                    @Override
                    public void onNext(CommonBean<MainBean> bean) {
                        MLog.d("登录-onNext");

                        //处理返回结果
                        if (bean.getCode().contains("0")) {

                            listener.onRefreshSuccess(bean.getCode(), bean.getMessage(), bean.getResult().getObj());
                        } else {
                            if (bean.getCode().contains("0")) {

                                listener.onRefreshSuccess(bean.getCode(), bean.getMessage(), bean.getResult().getObj());
                            } else {
                                MLog.e("返回数据错误：", bean.getMessage());
                                listener.onRefreshFailed(bean.getCode(), bean.getMessage(), new Exception(bean.getMessage()));
                            }
                        }
                    }

                });
    }

}
