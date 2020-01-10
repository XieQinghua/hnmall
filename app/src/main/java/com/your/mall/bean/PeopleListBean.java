package com.your.mall.bean;

/**
 * Created by xieqinghua.
 * 创建时间：2016/11/24
 * 类描述：拼团抽奖中奖人bean
 * 修改备注：
 */
public class PeopleListBean {
    private String user_id;
    private String package_name;
    private String headimgurl;
    private String nickname;
    private String prize_grade;
    private String rank;
    private String activity_goods_title;

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getPackage_name() {
        return package_name;
    }

    public void setPackage_name(String package_name) {
        this.package_name = package_name;
    }

    public String getHeadimgurl() {
        return headimgurl;
    }

    public void setHeadimgurl(String headimgurl) {
        this.headimgurl = headimgurl;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPrize_grade() {
        return prize_grade;
    }

    public void setPrize_grade(String prize_grade) {
        this.prize_grade = prize_grade;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getActivity_goods_title() {
        return activity_goods_title;
    }

    public void setActivity_goods_title(String activity_goods_title) {
        this.activity_goods_title = activity_goods_title;
    }
}
