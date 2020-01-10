package com.your.mall.bean;


import java.util.List;

public class LogiInfoBean {

    /**
     * msg : 物流信息获取成功
     * code : 0
     * data : [{"AcceptStation":"到长沙市【长沙八一路分部】","AcceptTime":"2016-05-09 14:49:44"}]
     */

    private String msg;
    private String code;
    /**
     * AcceptStation : 到长沙市【长沙八一路分部】
     * AcceptTime : 2016-05-09 14:49:44
     */

    private List<DataBean> data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        private String AcceptStation;
        private String AcceptTime;

        public String getAcceptStation() {
            return AcceptStation;
        }

        public void setAcceptStation(String AcceptStation) {
            this.AcceptStation = AcceptStation;
        }

        public String getAcceptTime() {
            return AcceptTime;
        }

        public void setAcceptTime(String AcceptTime) {
            this.AcceptTime = AcceptTime;
        }
    }
}
