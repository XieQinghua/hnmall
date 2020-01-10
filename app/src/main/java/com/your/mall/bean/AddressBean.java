package com.your.mall.bean;

import java.util.List;

/**
 * Created by xieqinghua.
 * 创建时间：2016/10/8
 * 类描述：用户收货地址列表返回json
 * 修改备注：
 */
public class AddressBean {

    /**
     * msg : 地址获取成功
     * code : 0
     * data : {"list":[{"address_id":87852,"user_id":154548,"consignee":"谢庆华","p_name":"湖南省","c_name":"长沙市","e_name":"天心区","p_id":"430000","c_id":"430100","e_id":"430103","addr":"创谷国家广告产业园A2栋4楼","zip_code":"","tel":"","mobile":"15200917596","def_addr":"1"},{"address_id":87853,"user_id":154548,"consignee":"啊啊啊","p_name":"北京市","c_name":"东城区","e_name":"","p_id":"110100","c_id":"110101","e_id":"","addr":"12345","zip_code":"","tel":"","mobile":"15200917597","def_addr":"0"},{"address_id":87854,"user_id":154548,"consignee":"嘻嘻嘻","p_name":"北京市","c_name":"朝阳区","e_name":"","p_id":"","c_id":"","e_id":"","addr":"654321？¥@hc","zip_code":"","tel":"","mobile":"15200917598","def_addr":"0"},{"address_id":86915,"user_id":154548,"consignee":"阿西吧","p_name":"北京市","c_name":"朝阳区","e_name":"","p_id":"110100","c_id":"110105","e_id":"","addr":"一二三四五","zip_code":"123456","tel":"","mobile":"15212345678","def_addr":"0"}],"nowcount":4}
     */

    private String msg;
    private String code;
    /**
     * list : [{"address_id":87852,"user_id":154548,"consignee":"谢庆华","p_name":"湖南省","c_name":"长沙市","e_name":"天心区","p_id":"430000","c_id":"430100","e_id":"430103","addr":"创谷国家广告产业园A2栋4楼","zip_code":"","tel":"","mobile":"15200917596","def_addr":"1"},{"address_id":87853,"user_id":154548,"consignee":"啊啊啊","p_name":"北京市","c_name":"东城区","e_name":"","p_id":"110100","c_id":"110101","e_id":"","addr":"12345","zip_code":"","tel":"","mobile":"15200917597","def_addr":"0"},{"address_id":87854,"user_id":154548,"consignee":"嘻嘻嘻","p_name":"北京市","c_name":"朝阳区","e_name":"","p_id":"","c_id":"","e_id":"","addr":"654321？¥@hc","zip_code":"","tel":"","mobile":"15200917598","def_addr":"0"},{"address_id":86915,"user_id":154548,"consignee":"阿西吧","p_name":"北京市","c_name":"朝阳区","e_name":"","p_id":"110100","c_id":"110105","e_id":"","addr":"一二三四五","zip_code":"123456","tel":"","mobile":"15212345678","def_addr":"0"}]
     * nowcount : 4
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
        private int nowcount;
        /**
         * address_id : 87852
         * user_id : 154548
         * consignee : 谢庆华
         * p_name : 湖南省
         * c_name : 长沙市
         * e_name : 天心区
         * p_id : 430000
         * c_id : 430100
         * e_id : 430103
         * addr : 创谷国家广告产业园A2栋4楼
         * zip_code :
         * tel :
         * mobile : 15200917596
         * def_addr : 1
         */

        private List<ListBean> list;

        public int getNowcount() {
            return nowcount;
        }

        public void setNowcount(int nowcount) {
            this.nowcount = nowcount;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            private int address_id;
            private int user_id;
            private String consignee;
            private String p_name;
            private String c_name;
            private String e_name;
            private String p_id;
            private String c_id;
            private String e_id;
            private String addr;
            private String zip_code;
            private String tel;
            private String mobile;
            private String def_addr;

            public int getAddress_id() {
                return address_id;
            }

            public void setAddress_id(int address_id) {
                this.address_id = address_id;
            }

            public int getUser_id() {
                return user_id;
            }

            public void setUser_id(int user_id) {
                this.user_id = user_id;
            }

            public String getConsignee() {
                return consignee;
            }

            public void setConsignee(String consignee) {
                this.consignee = consignee;
            }

            public String getP_name() {
                return p_name;
            }

            public void setP_name(String p_name) {
                this.p_name = p_name;
            }

            public String getC_name() {
                return c_name;
            }

            public void setC_name(String c_name) {
                this.c_name = c_name;
            }

            public String getE_name() {
                return e_name;
            }

            public void setE_name(String e_name) {
                this.e_name = e_name;
            }

            public String getP_id() {
                return p_id;
            }

            public void setP_id(String p_id) {
                this.p_id = p_id;
            }

            public String getC_id() {
                return c_id;
            }

            public void setC_id(String c_id) {
                this.c_id = c_id;
            }

            public String getE_id() {
                return e_id;
            }

            public void setE_id(String e_id) {
                this.e_id = e_id;
            }

            public String getAddr() {
                return addr;
            }

            public void setAddr(String addr) {
                this.addr = addr;
            }

            public String getZip_code() {
                return zip_code;
            }

            public void setZip_code(String zip_code) {
                this.zip_code = zip_code;
            }

            public String getTel() {
                return tel;
            }

            public void setTel(String tel) {
                this.tel = tel;
            }

            public String getMobile() {
                return mobile;
            }

            public void setMobile(String mobile) {
                this.mobile = mobile;
            }

            public String getDef_addr() {
                return def_addr;
            }

            public void setDef_addr(String def_addr) {
                this.def_addr = def_addr;
            }
        }
    }
}
