package com.zhq.http;


import com.zhq.bean.CommonBean;
import com.zhq.bean.ConferencePersonBean;
import com.zhq.bean.LoginBean;
import com.zhq.bean.MainBean;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by jingbin on 16/11/21.
 * 网络请求类（一个接口一个方法）
 * <p>
 * 注:使用Observable<BaseBean<AddnewReturnBean>> AddnewEmployee(@Field("Obj") String bean);形式时出现gson解析bug,没发用泛型，后续解决
 */

public interface MyHttpService {
    class Builder {

        /**
         * @return
         */
        public static MyHttpService getHttpServer() {
            return HttpUtils.getInstance().getServer(MyHttpService.class);
        }
    }

    /**
     * 1登录
     *
     * @return
     */
    @FormUrlEncoded
    @POST(URLUtils.LOGIN)
    Observable<CommonBean<LoginBean>> login(
            @Field("adminUserName") String co
            , @Field("username") String user
            , @Field("password") String ps);

    /**
     * 2会议
     *
     * @return
     */
    @FormUrlEncoded
    @POST(URLUtils.MAINDATA)
    Observable<CommonBean<MainBean>> getMainData(
            @Field("iMaxTime") String iMaxTime
            , @Field("iMinTime") String iMinTime
            , @Field("pageSize") int pageSize
            , @Field("storeID") String storeID);


    /**
     * 3会议人员接口
     *
     * @return
     */
    @FormUrlEncoded
    @POST(URLUtils.CONFERENCELIST)
    Observable<CommonBean<ConferencePersonBean>> getConferenceData(
            @Field("iMaxTime") String iMaxTime
            , @Field("iMinTime") String iMinTime
            , @Field("pageSize") String pageSize
            , @Field("ConferenceID") String ConferenceID);

    /**
     * 4关注
     *
     * @return
     */
    @FormUrlEncoded
    @POST(URLUtils.COLLECT)
    Observable<CommonBean<String>> postCare(
            @Field("EmployeeID") String EmployeeID);

    /**
     * 5取消关注
     *
     * @return
     */
    @FormUrlEncoded
    @POST(URLUtils.UNCOLLECT)
    Observable<CommonBean<String>> postCareless(
            @Field("EmployeeID") String EmployeeID);


}