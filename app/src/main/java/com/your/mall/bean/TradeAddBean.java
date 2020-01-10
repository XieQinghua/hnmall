package com.your.mall.bean;


public class TradeAddBean {


    /**
     * msg : 订单创建成功
     * code : 0
     * data : {"url":"http://nbh.e9448.com/index.php/wap/paycenter.html?payment_id=xxxx&merge=1"}
     */

    private String msg;
    private String code;
    /**
     * url : http://nbh.e9448.com/index.php/wap/paycenter.html?payment_id=xxxx&merge=1
     */

    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private String url;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
