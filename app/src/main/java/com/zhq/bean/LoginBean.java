package com.zhq.bean;

import java.io.Serializable;



public class LoginBean implements Serializable {
    private String StoreUserId;
    private String StoreID;

    public String getStoreUserId() {
        return StoreUserId;
    }

    public void setStoreUserId(String storeUserId) {
        StoreUserId = storeUserId;
    }

    public String getStoreID() {
        return StoreID;
    }

    public void setStoreID(String storeID) {
        StoreID = storeID;
    }
}
