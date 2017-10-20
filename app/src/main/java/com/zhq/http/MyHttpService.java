package com.zhq.http;




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
     ********************************************--通用--********************************************************
     */



    //    /**
    //     * 01 登录
    //     *
    //     * @return
    //     */
    //    @FormUrlEncoded
    //    @POST(URLUtils.LOGIN)
    //    //post
    //    Observable<CommonBean<LoginBean>> login(
    //            @Field("adminUserName") String storeName
    //            , @Field("userName") String username
    //            , @Field("password") String password
    //
    //            , @Field("deviceType") String deviceType
    //            , @Field("deviceName") String deviceName
    //            , @Field("MAC") String MAC
    //
    //            , @Field("DeviceSN") String DeviceSN
    //            , @Field("Remark") String Remark
    //            , @Field("DeviceInfo") String DeviceInfo
    //            , @Field("IP") String IP);
    //
    //    /**
    //     * 获取访客记录
    //     * <p>
    //     * pageSize：展示最多记录数量
    //     * timespan：获取今天（1）或者全部（其他）。
    //     * storeID：公司ID
    //     * iMaxTime：最大时间
    //     * iMinTime：最小时间
    //     * employeeID：受访者EmployeeID
    //     * isReceived：是否已接待（1：已接待 0：未接待 空：全部）
    //     *
    //     * @return
    //     */
    //    @FormUrlEncoded
    //    @POST(URLUtils.VISITOR)
    //    //post
    //    Observable<CommonListBean<VisitorBean>> loadVisitor(
    //            @Field("storeID") String storeID
    //            , @Field("employeeID") String employeeID
    //            , @Field("isReceived") String isReceived
    //
    //            , @Field("iMaxTime") String maxTime
    //            , @Field("iMinTime") String minTime
    //
    //            , @Field("timespan") String timespan
    //            , @Field("pageSize") String DeviceSN);
    //
    //    /**
    //     * 地图签到 上传obj参数
    //     */
    //    @FormUrlEncoded
    //    @POST(URLUtils.LOCATION)
    //    //post
    //    Observable<BaseBean> locationAttend(@Field("obj") String jsonStr);
    //
    //    /**
    //     * 修改密码
    //     * post
    //     */
    //    @FormUrlEncoded
    //    @POST(URLUtils.CHANGEPS)
    //    Observable<BaseBean> changeps(
    //            @Field("storeUserID") String storeUserID
    //            , @Field("oldUserPassword") String oldUserPassword
    //            , @Field("newUserPassword") String newUserPassword);
    //
    //
    //    /**
    //     * 检查更新
    //     * get方式
    //     */
    //    @GET(URLUtils.CHECK_UPDATE)
    //    Observable<CommonBean<UpgradeBean>> checkUpdate();
    //
    //    /**
    //     * 退出app
    //     * post
    //     */
    //    @FormUrlEncoded
    //    @POST(URLUtils.QUIT)
    //    Observable<BaseBean> quit(
    //            @Field("storeId") String storeId
    //            , @Field("userName") String userName
    //            , @Field("deviceId") String deviceId);
    //
    //    /**
    //     * 添加访客
    //     * <p>
    //     * 文本和图片上传
    //     * post
    //     *
    //     * @return
    //     */
    //    @Multipart
    //    @POST(URLUtils.ADD_VISITOR)
    //    Observable<BaseBean> addVisitor(
    //            @Part("obj") String obj
    //            , @Part MultipartBody.Part file);
    //
    //    /**
    //     * 修改接待状态
    //     * post
    //     */
    //    @FormUrlEncoded
    //    @POST(URLUtils.CHANGE_VISITOR_STATE)
    //    Observable<BaseBean> isReceived(
    //            @Field("visitorID") String visitorID
    //            , @Field("storeID") String storeID);
    //
    //    /**
    //     * 获取考勤月状态
    //     * post
    //     */
    //    @FormUrlEncoded
    //    @POST(URLUtils.MONTHSTATUS)
    //    Observable<CommonBean<AttendStatusMonthBean>> getAttendStateMonth(
    //            @Field("storeId") String storeId
    //            , @Field("employeeId") String employeeId
    //            , @Field("year") String year
    //            , @Field("month") String month);
    //
    //    /**
    //     * 获取考勤月记录
    //     * post
    //     */
    //    //    @FormUrlEncoded
    //    //    @POST(URLUtils.MONTH_ALL)
    //    //    Observable<CommonListBean<AttendDaysOFMonthBean>> getAttendList(
    //    //            @Field("storeId") String storeId
    //    //            , @Field("employeeId") String employeeId
    //    //            , @Field("year") String year
    //    //            , @Field("month") String month);
    //
    //    /**
    //     * 获取考勤月记录的状态记录
    //     * post
    //     */
    //    @FormUrlEncoded
    //    @POST(URLUtils.MONTH_STATE_ALL)
    //    Observable<CommonListBean<AttendDaysOFMonthStateBean>> getAttendStateList(
    //            @Field("storeId") String storeId
    //            , @Field("employeeId") String employeeId
    //            , @Field("year") String year
    //            , @Field("month") String month);
    //
    //    /**
    //     * 获取考勤月记录的状态记录
    //     * 获取三个月的记录
    //     * post
    //     */
    //    @FormUrlEncoded
    //    @POST(URLUtils.MONTH_STATE_THREE)
    //    Observable<CommonListBean<AttendDaysOFMonthStateBean>> getAttendStateOFTHREE(
    //            @Field("storeId") String storeId
    //            , @Field("employeeId") String employeeId
    //            , @Field("year") String year
    //            , @Field("month") String month);
    //
    //    /**
    //     * 获取日考勤记录
    //     * post
    //     */
    //    @FormUrlEncoded
    //    @POST(URLUtils.DAYOFMONTH)
    //    Observable<CommonBean<AttendDaysOFMonthBean>> getDayDetail(
    //            @Field("storeId") String storeId
    //            , @Field("employeeId") String employeeId
    //            , @Field("year") String year
    //            , @Field("month") String month
    //            , @Field("day") String day);


}