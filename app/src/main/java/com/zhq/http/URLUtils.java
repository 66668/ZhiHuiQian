package com.zhq.http;

/**
 * URL类
 */

public class URLUtils {
    //根目录
    public final static String API_BASE_URL = "http://101.201.72.112:8080/openapi/";//阿里云正式

    
    /**
     * 收藏
     */
    public static final String LOGIN = "User/LoginByPassword";

    /**
     * 会议记录接口
     */
    public static final String MAINDATA = "ConferInfo/GetConferInfoforAndroid";
    /**
     * 会议记录接口
     */
    public static final String CONFERENCELIST = "EmployeeAndroid/GetEmployeeAndroid";
    /**
     * 关注
     */
    public static final String COLLECT = "IsCare/updateIsCare";
    /**
     * 取消关注
     */
    public static final String UNCOLLECT = "clearCare/clearCares";


}
