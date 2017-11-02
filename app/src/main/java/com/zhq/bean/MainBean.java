package com.zhq.bean;

import java.io.Serializable;
import java.util.List;


public class MainBean implements Serializable {
    private int conferCount;
    private String NewestConfer;
    private List<ConferenceItemBean> obj;

    public List<ConferenceItemBean> getObj() {
        return obj;
    }

    public void setObj(List<ConferenceItemBean> obj) {
        this.obj = obj;
    }

    public int getConferCount() {
        return conferCount;
    }

    public void setConferCount(int conferCount) {
        this.conferCount = conferCount;
    }

    public String getNewestConfer() {
        return NewestConfer;
    }

    public void setNewestConfer(String newestConfer) {
        NewestConfer = newestConfer;
    }
    public class ConferenceItemBean implements Serializable {
        private String ConferenceID;
        private String ConferenceName;
        private String CreateTime;

        public String getCreateTime() {
            return CreateTime;
        }

        public void setCreateTime(String createTime) {
            CreateTime = createTime;
        }

        public String getConferenceID() {
            return ConferenceID;
        }

        public void setConferenceID(String conferenceID) {
            ConferenceID = conferenceID;
        }

        public String getConferenceName() {
            return ConferenceName;
        }

        public void setConferenceName(String conferenceName) {
            ConferenceName = conferenceName;
        }
    }
}
