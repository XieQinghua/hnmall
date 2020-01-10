package com.your.mall.bean;

import java.util.List;

/**
 * Created by xieqinghua.
 * 创建时间：2016/10/26
 * 类描述：
 * 修改备注：
 */
public class AreaBean {

    /**
     * id : 110100
     * value : 北京市
     * parentId : 110000
     * children : [{"id":"110101","value":"东城区","parentId":"110100"},{"id":"110102","value":"西城区","parentId":"110100"},{"id":"110103","value":"崇文区","parentId":"110100"},{"id":"110104","value":"宣武区","parentId":"110100"},{"id":"110105","value":"朝阳区","parentId":"110100"},{"id":"110106","value":"丰台区","parentId":"110100"},{"id":"110107","value":"石景山区","parentId":"110100"},{"id":"110108","value":"海淀区","parentId":"110100"},{"id":"110109","value":"门头沟区","parentId":"110100"},{"id":"110111","value":"房山区","parentId":"110100"},{"id":"110112","value":"通州区","parentId":"110100"},{"id":"110113","value":"顺义区","parentId":"110100"},{"id":"110114","value":"昌平区","parentId":"110100"},{"id":"110115","value":"大兴区","parentId":"110100"},{"id":"110116","value":"怀柔区","parentId":"110100"},{"id":"110117","value":"平谷区","parentId":"110100"},{"id":"110228","value":"密云县","parentId":"110100"},{"id":"110229","value":"延庆县","parentId":"110100"},{"id":"110230","value":"其它区","parentId":"110100"}]
     */

    private String id;
    private String value;
    private String parentId;
    /**
     * id : 110101
     * value : 东城区
     * parentId : 110100
     */

    private List<ChildrenBean> children;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public List<ChildrenBean> getChildren() {
        return children;
    }

    public void setChildren(List<ChildrenBean> children) {
        this.children = children;
    }

    public static class ChildrenBean {
        private String id;
        private String value;
        private String parentId;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public String getParentId() {
            return parentId;
        }

        public void setParentId(String parentId) {
            this.parentId = parentId;
        }
    }
}
