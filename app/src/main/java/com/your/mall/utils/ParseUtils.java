package com.your.mall.utils;

import com.google.gson.Gson;
import com.your.mall.bean.AddCartBean;
import com.your.mall.bean.AddressBean;
import com.your.mall.bean.BargainBean;
import com.your.mall.bean.BargainDetailsBean;
import com.your.mall.bean.CenterBean;
import com.your.mall.bean.CenterPintuanCjDetailsBean;
import com.your.mall.bean.CenterPintuanDetailsBean;
import com.your.mall.bean.CollectGoodsBean;
import com.your.mall.bean.CollectShopBean;
import com.your.mall.bean.CouponBean;
import com.your.mall.bean.GoodsDetailsBean;
import com.your.mall.bean.GoodsDetailsBean1;
import com.your.mall.bean.GoodsListBean;
import com.your.mall.bean.LogiInfoBean;
import com.your.mall.bean.LoginBean;
import com.your.mall.bean.LoginTepinBean;
import com.your.mall.bean.MyBargainBean;
import com.your.mall.bean.MyPintuanBean;
import com.your.mall.bean.OrderDetailsBean;
import com.your.mall.bean.OrderListBean;
import com.your.mall.bean.OrderTallyBean;
import com.your.mall.bean.PintuanBean;
import com.your.mall.bean.PintuanCjDetailsBean;
import com.your.mall.bean.PintuanCjPrizeBean;
import com.your.mall.bean.PintuanDetailsBean;
import com.your.mall.bean.RegisterBean;
import com.your.mall.bean.ShareInfoBean;
import com.your.mall.bean.ShoppingCartBean;
import com.your.mall.bean.TotalBean;
import com.your.mall.bean.TradeAddBean;
import com.your.mall.bean.ZujiBean;

/**
 * Created by xieqinghua.
 * 创建时间：2016/9/22
 * 类描述：json解析工具
 * 修改备注：
 */
public class ParseUtils {

    private static Gson gson = new Gson();

    /**
     * sso用户登录
     *
     * @param json
     * @return
     * @throws IllegalStateException
     */
    public static LoginBean parseLoginBean(String json) throws IllegalStateException {
        LoginBean loginBean = gson.fromJson(json, LoginBean.class);
        return loginBean;
    }

    /**
     * sso用户注册
     *
     * @param json
     * @return
     * @throws IllegalStateException
     */
    public static RegisterBean parseRegisterBean(String json) throws IllegalStateException {
        RegisterBean registerBean = gson.fromJson(json, RegisterBean.class);
        return registerBean;
    }

    /**
     * 登录服务平台返回用户参数
     *
     * @param json
     * @return
     * @throws IllegalStateException
     */
    public static LoginTepinBean parseLoginTepinBean(String json) throws IllegalStateException {
        LoginTepinBean loginTepinBean = gson.fromJson(json, LoginTepinBean.class);
        return loginTepinBean;
    }

    /**
     * 商品列表
     *
     * @param json
     * @return
     * @throws IllegalStateException
     */
    public static GoodsListBean parseGoodsListBean(String json) throws IllegalStateException {
        GoodsListBean goodsListBean = gson.fromJson(json, GoodsListBean.class);
        return goodsListBean;
    }

    /**
     * 商品详情
     *
     * @param json
     * @return
     * @throws IllegalStateException
     */
    public static GoodsDetailsBean parseGoodsDetailsBean(String json) throws IllegalStateException {
        GoodsDetailsBean goodsDetailsBean = gson.fromJson(json, GoodsDetailsBean.class);
        return goodsDetailsBean;
    }

    /**
     * 商品详情（当返回spec字段有内容时解析）
     *
     * @param json
     * @return
     * @throws IllegalStateException
     */
    public static GoodsDetailsBean1 parseGoodsDetailsBean1(String json) throws IllegalStateException {
        GoodsDetailsBean1 goodsDetailsBean1 = gson.fromJson(json, GoodsDetailsBean1.class);
        return goodsDetailsBean1;
    }

    /**
     * 收货地址
     *
     * @param json
     * @return
     * @throws IllegalStateException
     */
    public static AddressBean parseAddressBean(String json) throws IllegalStateException {
        AddressBean addressBean = gson.fromJson(json, AddressBean.class);
        return addressBean;
    }

    /**
     * 会员中心首页
     *
     * @param json
     * @return
     * @throws IllegalStateException
     */
    public static CenterBean parseCenterBean(String json) throws IllegalStateException {
        CenterBean centerBean = gson.fromJson(json, CenterBean.class);
        return centerBean;
    }

    /**
     * 用户中心我的足迹
     *
     * @param json
     * @return
     * @throws IllegalStateException
     */
    public static ZujiBean parseZujiBean(String json) throws IllegalStateException {
        ZujiBean zujiBean = gson.fromJson(json, ZujiBean.class);
        return zujiBean;
    }

    /**
     * 收藏店铺列表
     *
     * @param json
     * @return
     * @throws IllegalStateException
     */
    public static CollectShopBean parseCollectShopBean(String json) throws IllegalStateException {
        CollectShopBean collectShopBean = gson.fromJson(json, CollectShopBean.class);
        return collectShopBean;
    }

    /**
     * 收藏商品列表
     *
     * @param json
     * @return
     * @throws IllegalStateException
     */
    public static CollectGoodsBean parseCollectGoodsBean(String json) throws IllegalStateException {
        CollectGoodsBean collectGoodsBean = gson.fromJson(json, CollectGoodsBean.class);
        return collectGoodsBean;
    }

    /**
     * 优惠券
     *
     * @param json
     * @return
     * @throws IllegalStateException
     */
    public static CouponBean parseCouponBean(String json) throws IllegalStateException {
        CouponBean couponBean = gson.fromJson(json, CouponBean.class);
        return couponBean;
    }

    /**
     * 加入购物车
     *
     * @param json
     * @return
     * @throws IllegalStateException
     */
    public static AddCartBean parseAddCartBean(String json) throws IllegalStateException {
        AddCartBean addCartBean = gson.fromJson(json, AddCartBean.class);
        return addCartBean;
    }

    /**
     * 订单结算
     *
     * @param json
     * @return
     * @throws IllegalStateException
     */
    public static OrderTallyBean parseOrderTallyBean(String json) throws IllegalStateException {
        OrderTallyBean orderTallyBean = gson.fromJson(json, OrderTallyBean.class);
        return orderTallyBean;
    }

    /**
     * 提交订单
     *
     * @param json
     * @return
     * @throws IllegalStateException
     */
    public static TradeAddBean parseTradeAddBean(String json) throws IllegalStateException {
        TradeAddBean tradeAddBean = gson.fromJson(json, TradeAddBean.class);
        return tradeAddBean;
    }

    /**
     * 订单详情
     *
     * @param json
     * @return
     * @throws IllegalStateException
     */
    public static OrderDetailsBean parseOrderDetailsBean(String json) throws IllegalStateException {
        OrderDetailsBean orderDetailsBean = gson.fromJson(json, OrderDetailsBean.class);
        return orderDetailsBean;
    }

    /**
     * 物流信息
     *
     * @param json
     * @return
     * @throws IllegalStateException
     */
    public static LogiInfoBean parseLogiInfoBean(String json) throws IllegalStateException {
        LogiInfoBean logiInfoBean = gson.fromJson(json, LogiInfoBean.class);
        return logiInfoBean;
    }

    /**
     * 购物车金额
     *
     * @param json
     * @return
     * @throws IllegalStateException
     */
    public static TotalBean parseTotalBean(String json) throws IllegalStateException {
        TotalBean totalBean = gson.fromJson(json, TotalBean.class);
        return totalBean;
    }

    /**
     * 购物车列表
     *
     * @param json
     * @return
     * @throws IllegalStateException
     */
    public static ShoppingCartBean parseShoppingCartBean(String json) throws IllegalStateException {
        ShoppingCartBean shoppingCartBean = gson.fromJson(json, ShoppingCartBean.class);
        return shoppingCartBean;
    }

    /**
     * 订单列表
     *
     * @param json
     * @return
     * @throws IllegalStateException
     */
    public static OrderListBean parseOrderListBean(String json) throws IllegalStateException {
        OrderListBean orderListBean = gson.fromJson(json, OrderListBean.class);
        return orderListBean;
    }

    /**
     * 拼团首页
     *
     * @param json
     * @return
     * @throws IllegalStateException
     */
    public static PintuanBean parsePintuanBean(String json) throws IllegalStateException {
        PintuanBean pintuanBean = gson.fromJson(json, PintuanBean.class);
        return pintuanBean;
    }

    /**
     * 拼团抽奖详情
     *
     * @param json
     * @return
     * @throws IllegalStateException
     */
    public static PintuanCjDetailsBean parsePintuanCjDetailsBean(String json) throws IllegalStateException {
        PintuanCjDetailsBean pintuanCjDetailsBean = gson.fromJson(json, PintuanCjDetailsBean.class);
        return pintuanCjDetailsBean;
    }

    /**
     * 拼团抽奖中奖人
     *
     * @param json
     * @return
     * @throws IllegalStateException
     */
    public static PintuanCjPrizeBean parsePintuanCjPrizeBean(String json) throws IllegalStateException {
        PintuanCjPrizeBean pintuanCjPrizeBean = gson.fromJson(json, PintuanCjPrizeBean.class);
        return pintuanCjPrizeBean;
    }

    /**
     * 拼团详情
     *
     * @param json
     * @return
     * @throws IllegalStateException
     */
    public static PintuanDetailsBean parsePintuanDetailsBean(String json) throws IllegalStateException {
        PintuanDetailsBean pintuanDetailsBean = gson.fromJson(json, PintuanDetailsBean.class);
        return pintuanDetailsBean;
    }

    /**
     * 会员中心我的拼团
     *
     * @param json
     * @return
     * @throws IllegalStateException
     */
    public static MyPintuanBean parseMyPintuanBean(String json) throws IllegalStateException {
        MyPintuanBean myPintuanBean = gson.fromJson(json, MyPintuanBean.class);
        return myPintuanBean;
    }

    /**
     * 会员中心拼团抽奖详情
     *
     * @param json
     * @return
     * @throws IllegalStateException
     */
    public static CenterPintuanCjDetailsBean parseCenterPintuanCjDetailsBean(String json) throws IllegalStateException {
        CenterPintuanCjDetailsBean centerPintuanCjDetailsBean = gson.fromJson(json, CenterPintuanCjDetailsBean.class);
        return centerPintuanCjDetailsBean;
    }

    /**
     * 会员中心拼团详情
     *
     * @param json
     * @return
     * @throws IllegalStateException
     */
    public static CenterPintuanDetailsBean parseCenterPintuanDetailsBean(String json) throws IllegalStateException {
        CenterPintuanDetailsBean centerPintuanDetailsBean = gson.fromJson(json, CenterPintuanDetailsBean.class);
        return centerPintuanDetailsBean;
    }

    /**
     * 砍价商品
     *
     * @param json
     * @return
     * @throws IllegalStateException
     */
    public static BargainBean parseBargainBean(String json) throws IllegalStateException {
        BargainBean bargainBean = gson.fromJson(json, BargainBean.class);
        return bargainBean;
    }

    /**
     * 砍价详情
     *
     * @param json
     * @return
     * @throws IllegalStateException
     */
    public static BargainDetailsBean parseBargainDetailsBean(String json) throws IllegalStateException {
        BargainDetailsBean bargainDetailsBean = gson.fromJson(json, BargainDetailsBean.class);
        return bargainDetailsBean;
    }

    /**
     * 砍价分享信息
     *
     * @param json
     * @return
     * @throws IllegalStateException
     */
    public static ShareInfoBean parseShareInfoBean(String json) throws IllegalStateException {
        ShareInfoBean shareInfoBean = gson.fromJson(json, ShareInfoBean.class);
        return shareInfoBean;
    }

    /**
     * 会员中心我的砍价
     *
     * @param json
     * @return
     * @throws IllegalStateException
     */
    public static MyBargainBean parseMyBargainBean(String json) throws IllegalStateException {
        MyBargainBean myBargainBean = gson.fromJson(json, MyBargainBean.class);
        return myBargainBean;
    }
}
