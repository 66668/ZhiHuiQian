package com.zhq.module;

import com.zhq.bean.CommonBean;
import com.zhq.bean.ConferencePersonBean;
import com.zhq.http.MyHttpService;
import com.zhq.inter_face.OnConferenceListener;
import com.zhq.utils.MLog;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 会议
 */

public class ConferenceModleImpl {
    /**
     * ========================================================================================================================
     * ===========================================================关注=============================================================
     * ========================================================================================================================
     */

    /**
     * 关注
     *
     * @param listener
     */
    public void mPostCare(String EmployeeID, final OnConferenceListener listener) {
        MLog.d("main：", "EmployeeID=" + EmployeeID);

        MyHttpService.Builder.getHttpServer().postCare(EmployeeID)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CommonBean<String>>() {
                    @Override
                    public void onCompleted() {
                        MLog.d("关注--onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        MLog.e("关注--onError:" + e.toString());
                        listener.onCareFailed("-1", "异常", (Exception) e);
                    }

                    @Override
                    public void onNext(CommonBean<String> bean) {
                        MLog.d("关注-onNext");

                        //处理返回结果
                        if (bean.getCode().contains("1")) {
                            listener.onCareSuccess(bean.getCode(), bean.getMessage(), bean.getResult());
                        } else {
                            MLog.e(bean.getCode(), bean.getMessage());
                            listener.onCareFailed(bean.getCode(), bean.getMessage(), new Exception(bean.getMessage()));
                        }
                    }

                });
    }

    /**
     * 取消关注
     *
     * @param listener
     */
    public void mPostCareless(String EmployeeID, final OnConferenceListener listener) {
        MLog.d("main：", "EmployeeID=" + EmployeeID);

        MyHttpService.Builder.getHttpServer().postCareless(EmployeeID)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CommonBean<String>>() {
                    @Override
                    public void onCompleted() {
                        MLog.d("关注--onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        MLog.e("关注--关注失败异常onError:" + e.toString());
                        listener.onCarelessFailed("-1", "异常", (Exception) e);
                    }

                    @Override
                    public void onNext(CommonBean<String> bean) {
                        MLog.d("关注-onNext");

                        //处理返回结果
                        if (bean.getCode().contains("1")) {

                            listener.onCarelessSuccess(bean.getCode(), bean.getMessage(), bean.getResult());
                        } else {
                            MLog.e(bean.getCode(), bean.getMessage());
                            listener.onCarelessFailed(bean.getCode(), bean.getMessage(), new Exception(bean.getMessage()));
                        }
                    }

                });
    }
    /**
     * ========================================================================================================================
     * ===========================================================上拉下拉=============================================================
     * ========================================================================================================================
     */
    /**
     * 获取信息
     *
     * @param listener
     */
    public void mGetConferenceData(String maxTime, String minTime, int size, String ConferenceID, final OnConferenceListener listener) {
        MLog.d("main：", "maxTime=" + maxTime, "minTime=" + minTime, "size=" + size);

        MyHttpService.Builder.getHttpServer().getConferenceData(maxTime, minTime, size, ConferenceID)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CommonBean<ConferencePersonBean>>() {
                    @Override
                    public void onCompleted() {
                        MLog.d("会议--onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        MLog.e("会议--onError:" + e.toString());
                        listener.onListFailed("-1", "异常", (Exception) e);
                    }

                    @Override
                    public void onNext(CommonBean<ConferencePersonBean> bean) {
                        MLog.d("会议-onNext");

                        //处理返回结果
                        if (bean.getCode().contains("1")) {
                            MLog.d(bean.getCode(), bean.getMessage());
                            listener.onListSuccess(bean.getCode(), bean.getMessage(), bean.getResult());
                        } else {
                            MLog.d(bean.getCode(), bean.getMessage());
                            listener.onListFailed(bean.getCode(), bean.getMessage(), new Exception(bean.getMessage()));
                        }
                    }

                });
    }

    /**
     * @param listener
     */
    public void mMoreData(String maxTime, String minTime, int size, String ConferenceID, final OnConferenceListener listener) {
        MLog.d("main：", "maxTime=" + maxTime, "minTime=" + minTime, "size=" + size);

        MyHttpService.Builder.getHttpServer().getConferenceData(maxTime, minTime, size, ConferenceID)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CommonBean<ConferencePersonBean>>() {
                    @Override
                    public void onCompleted() {
                        MLog.d("会议more--onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        MLog.e("会议more--关注失败异常onError:" + e.toString());
                        listener.onMoreFailed("-1", "异常", (Exception) e);
                    }

                    @Override
                    public void onNext(CommonBean<ConferencePersonBean> bean) {
                        MLog.d("会议more-onNext");

                        //处理返回结果
                        if (bean.getCode().contains("1")) {

                            listener.onMoreSuccess(bean.getCode(), bean.getMessage(), bean.getResult().getObj());
                        } else {
                            MLog.e(bean.getCode(), bean.getMessage());
                            listener.onMoreFailed(bean.getCode(), bean.getMessage(), new Exception(bean.getMessage()));
                        }
                    }

                });
    }

    /**
     * @param listener
     */
    public void mRefreshData(String maxTime, String minTime, int size, String ConferenceID, final OnConferenceListener listener) {
        MLog.d("main：", "maxTime=" + maxTime, "minTime=" + minTime, "size=" + size);

        MyHttpService.Builder.getHttpServer().getConferenceData(maxTime, minTime, size, ConferenceID)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CommonBean<ConferencePersonBean>>() {
                    @Override
                    public void onCompleted() {
                        MLog.d("会议refresh--onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        MLog.e("会议refresh--异常onError:" + e.toString());
                        listener.onRefreshFailed("-1", "异常", (Exception) e);
                    }

                    @Override
                    public void onNext(CommonBean<ConferencePersonBean> bean) {
                        MLog.d("会议refresh-onNext");

                        //处理返回结果
                        if (bean.getCode().contains("1")) {
                            listener.onRefreshSuccess(bean.getCode(), bean.getMessage(), bean.getResult().getObj());
                        } else {
                            MLog.e("返回数据错误：", bean.getMessage());
                            listener.onRefreshFailed(bean.getCode(), bean.getMessage(), new Exception(bean.getMessage()));
                        }
                    }

                });
    }

}
