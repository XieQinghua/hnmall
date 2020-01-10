package com.your.mall.bean;

import java.util.List;

public class SpecBean {
    private String sku_name;
    /**
     * sku_id : 6608
     * item_id : 6341
     * price : 237.000
     * store : 99
     * valid : true
     * sku_value_name : 浓香型
     */

    private List<SpecSkuBean> specSku;

    public String getSku_name() {
        return sku_name;
    }

    public void setSku_name(String sku_name) {
        this.sku_name = sku_name;
    }

    public List<SpecSkuBean> getSpecSku() {
        return specSku;
    }

    public void setSpecSku(List<SpecSkuBean> specSku) {
        this.specSku = specSku;
    }

    public static class SpecSkuBean {
        private int sku_id;
        private int item_id;
        private String price;
        private int store;
        private boolean valid;
        private String sku_value_name;

        public int getSku_id() {
            return sku_id;
        }

        public void setSku_id(int sku_id) {
            this.sku_id = sku_id;
        }

        public int getItem_id() {
            return item_id;
        }

        public void setItem_id(int item_id) {
            this.item_id = item_id;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public int getStore() {
            return store;
        }

        public void setStore(int store) {
            this.store = store;
        }

        public boolean isValid() {
            return valid;
        }

        public void setValid(boolean valid) {
            this.valid = valid;
        }

        public String getSku_value_name() {
            return sku_value_name;
        }

        public void setSku_value_name(String sku_value_name) {
            this.sku_value_name = sku_value_name;
        }
    }
}