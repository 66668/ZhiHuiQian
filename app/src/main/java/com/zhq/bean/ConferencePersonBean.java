package com.zhq.bean;

import java.io.Serializable;
import java.util.List;


public class ConferencePersonBean implements Serializable {
    private int EmpCount;
    private int SignCount;
    private int NosignCount;
    private String ConferenceName;
    private String Location;
    private List<PersonBean> obj;

    public int getEmpCount() {
        return EmpCount;
    }

    public void setEmpCount(int empCount) {
        EmpCount = empCount;
    }

    public int getSignCount() {
        return SignCount;
    }

    public void setSignCount(int signCount) {
        SignCount = signCount;
    }

    public int getNosignCount() {
        return NosignCount;
    }

    public void setNosignCount(int nosignCount) {
        NosignCount = nosignCount;
    }

    public String getConferenceName() {
        return ConferenceName;
    }

    public void setConferenceName(String conferenceName) {
        ConferenceName = conferenceName;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public List<PersonBean> getObj() {
        return obj;
    }

    public void setObj(List<PersonBean> obj) {
        this.obj = obj;
    }

    public class PersonBean implements Serializable {
        private String EmployeeID;
        private String EmployeeName;
        private String SignTime;
        private int IsSign;
        private String ImagePathYun;
        private String CreateTime;
        private int IsCare;

        public int getIsCare() {
            return IsCare;
        }

        public void setIsCare(int isCare) {
            IsCare = isCare;
        }

        public String getCreateTime() {
            return CreateTime;
        }

        public void setCreateTime(String createTime) {
            CreateTime = createTime;
        }

        public String getEmployeeID() {
            return EmployeeID;
        }

        public void setEmployeeID(String employeeID) {
            EmployeeID = employeeID;
        }

        public String getEmployeeName() {
            return EmployeeName;
        }

        public void setEmployeeName(String employeeName) {
            EmployeeName = employeeName;
        }

        public String getSignTime() {
            return SignTime;
        }

        public void setSignTime(String signTime) {
            SignTime = signTime;
        }

        public int getIsSign() {
            return IsSign;
        }

        public void setIsSign(int isSign) {
            IsSign = isSign;
        }

        public String getImagePathYun() {
            return ImagePathYun;
        }

        public void setImagePathYun(String imagePathYun) {
            ImagePathYun = imagePathYun;
        }
    }
}
