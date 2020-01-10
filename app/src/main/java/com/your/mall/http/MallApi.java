package com.your.mall.http;

/**
 * Created by xieqinghua.
 * 创建时间：2016/8/31
 * 类描述：
 * 修改备注：
 */

/**
 * 6.1、公共验证规则
 * 简要描述：
 * •	公共验证规则
 * 所有请求URL：
 * 请求方式：
 * •	POST
 * 参数：
 * 参数名	必选	类型	说明
 * p	是	string	时间日期	当前日期年月日时分秒 如20160125101010
 * k	是	string	验证key	md5（第一个参数名+p+appkey）
 * uuid	是	string	设备唯一标识码	uuid imei
 * client_source	是	string	请求来源	ios or android
 * 返回参数说明
 * 参数名	类型	说明
 * result	string	21 请求的HTTP METHOD不支持，请检查是否选择了正确POST/GET方式 22时间验证错误 23签名验证错误
 * 备注
 * appkey=uaEJcD8l
 * 所有接口都带以上三两个参数
 * 其中k的第一个参数名说明：所有请求过来的参数根据字母升序后去第一个参数名，p k参数除外
 * •	更多返回错误代码请看首页的错误代码描述
 * 6.2、公共返回参数说明
 * 简要描述：
 * •	公共code码说明
 * 请求所有URL：
 * 请求方式：
 * •	POST / GET
 * 返回参数：
 * 参数名	必选	类型	说明
 * code	是	string	返回码
 * data	是	array	数据集
 * msg	否	string	状态信息
 * 返回示例
 * {
 * "code": 0,
 * "msg": "获取成功",
 * "data":[]
 * }
 * 返回参数说明
 * 参数名	类型	说明
 * code	string	21:请求的HTTP METHOD不支持，请检查是否选择了正确的POST/GET方式 22:签名验证错误! 23:日期验证错误 24:未接受到任何值 -2:缺少必备参数 -1：处理数据异常 0：处理数据成功
 * msg	string	code码对应的状态信息
 * data	array	数据集
 * 备注
 */
public class MallApi {

    /**
     * -----------------------------------------------------------------------------------
     * --------------------------------------环境切换--------------------------------------
     * -----------------------------------------------------------------------------------
     */

    public static final String NBH_hnmall_URL = "www.hnmall.com/wap"; //农博汇Web端App官网首页

//    public static final String SERVER_URL = "http://nbh.e9448.com/mobile";   //农博汇 开发环境
    public static final String SERVER_URL = "http://www.hnmall.com/mobile"; //农博汇 发布环境

//    public static final String SERVER_WEB_URL = "http://nbh.e9448.com/wap";//农博汇WEB 开发环境
    public static final String SERVER_WEB_URL = "http://www.hnmall.com/wap"; //农博汇WEB 发布环境

//    public static final String SERVER_PUBLIC_URL = "http://sns.e9448.com";//友阿公用 开发环境
//    public static final String SERVER_PUBLIC_URL  ="http://sns.tepin.com"; //友阿公用 发布环境

//    public static final String SERVER_PAYMENT_URL = "http://nbh.e9448.com/wap"; //web支付链接 开发环境
    public static final String SERVER_PAYMENT_URL = "http://www.hnmall.com/wap"; //web支付链接 发布环境

    public static final String SERVER_PREFIX = "http://hnmall.e9448.com/mobile";//旧的服务器地址

    public static final String APP_KEY = "YxVZRPuYLg";//公共验证规则appkey

    //1.1、注册
//    public static final String APP_SIGNUP = SERVER_URL + "/app_signup.html";
    //1.2、登录
//    public static final String APP_LOGIN = SERVER_URL + "/app_login.html";
    //1.3、修改密码
//    public static final String APP_MDF_PWD = SERVER_URL + "/app_mdf_pwd.html";
    //1.4、退出登录
    public static final String APP_LOGOUT = SERVER_URL + "/app_logout.html";
    //1.5、用户收货地址列表
    public static final String APP_ADDRESS = SERVER_URL + "/app_address.html";
    //1.6、用户收货地址删除
    public static final String APP_ADDRESS_DEL = SERVER_URL + "/app_address_del.html";
    //1.7、添加修改会员收货地址
    public static final String APP_ADDRESS_ADD = SERVER_URL + "/app_address_add.html";
    //1.8、省份城市地区查询
//    public static final String APP_ADDRESS_AREA = SERVER_URL + "/app_address_area.html";
    //1.9、设置默认收货地址
    public static final String APP_ADDRESS_SET_DEF = SERVER_URL + "/app_address_set_def.html";
    //1.10、找回密码
//    public static final String APP_FINDPWD = SERVER_URL + "/app_findpwd.html";
    //1.11、校验验证码
//    public static final String APP_CHECKCODER = SERVER_URL + "/app_checkcoder.html";
    //1.12、发送验证码
//    public static final String APP_MONILECODE = SERVER_URL + "/app_mobilecode.html";
    //1.13、登录服务平台
    public static final String APP_LOGIN_TEPIN = SERVER_URL + "/app_login_tepin.html";
    //1.14、用户中心我的足迹
    public static final String APP_MEMBER_ZUJI = SERVER_URL + "/app_member_zuji.html";
    //1.15、用户中心删除我的足迹
    public static final String APP_MEMBER_ZUJI_DEL = SERVER_URL + "/app_member_zuji_del.html";
    //1.16、会员中心首页接口
    public static final String APP_MEMBER_INDEX = SERVER_URL + "/app_member_index.html";
    //2.1、收藏商品
    public static final String APP_COLLECT_ADD = SERVER_URL + "/app_collect_add.html";
    //2.2、收藏商品列表
    public static final String APP_COLLECT_LIST = SERVER_URL + "/app_collect_list.html";
    //2.3、取消收藏商品
    public static final String APP_COLLECT_DEL = SERVER_URL + "/app_collect_del.html";
    //2.4、搜索
    public static final String APP_SEARCH = SERVER_URL + "/app_search.html";
    //2.5、商品详情
    public static final String APP_GOODS = SERVER_URL + "/app_goods.html";
    //3.1、加入购物车
    public static final String APP_CART_ADD = SERVER_URL + "/app_cart_add.html";
    //3.2、购物车列表
    public static final String APP_CART_LIST = SERVER_URL + "/app_cart_list.html";
    //3.3、删除购物车
    public static final String APP_CART_DEL = SERVER_URL + "/app_cart_del.html";
    //3.4、更新购物车
    public static final String APP_CART_UPDATE = SERVER_URL + "/app_cart_update.html";
    //3.5、结算购物车
    public static final String APP_CART_CHECKOUT = SERVER_URL + "/app_cart_checkout.html";
    //3.6、更新购物车金额
    public static final String APP_CART_TOTAL = SERVER_URL + "/app_cart_total.html";
    //3.7、使用优惠券
    public static final String APP_CART_USER_COUPON = SERVER_URL + "/app_cart_user_coupon.html";
    //3.8、使用余额
    public static final String APP_CART_USER_DEPOSIT = SERVER_URL + "/app_cart_user_deposit.html";
    //4.1、优惠券列表
    public static final String APP_COUPON_LIST = SERVER_URL + "/app_coupon_list.html";
    //4.2、提交订单
    public static final String APP_TRADE_ADD = SERVER_URL + "/app_trade_add.html";
    //4.3、创建评价
    public static final String APP_MEMBER_RATE_ADD = SERVER_URL + "/app_member_rate_add.html";
    //4.4、订单列表
    public static final String APP_TRADELIST = SERVER_URL + "/app_tradelist.html";
    //4.5、取消订单
    public static final String APP_TRADE_CANCEL = SERVER_URL + "/app_trade_cancel.html";
    //4.6、确认收货
    public static final String APP_TRADE_CONFIRM = SERVER_URL + "/app_trade_confirm.html";
    //4.7、获取物流信息
    public static final String APP_TRADE_GETTRACK = SERVER_URL + "/app_trade_gettrack.html";
    //4.8、订单详情
    public static final String APP_TRADEDETAIL = SERVER_URL + "/app_tradedetail.html";
    //5.1、收藏店铺列表
    public static final String APP_SHOPSCOLLECT_LIST = SERVER_URL + "/app_shopscollect_list.html";
    //5.2、店铺收藏
    public static final String APP_SHOPSCOLLECT_ADD = SERVER_URL + "/app_shopscollect_add.html";
    //5.3、取消收藏店铺
    public static final String APP_SHOPSCOLLECT_DEL = SERVER_URL + "/app_shopscollect_del.html";
    /**
     * 砍价接口
     **/
    //7.1、砍价活动首页
    public static final String APP_BARGAIN = SERVER_URL + "/app_bargain.html";
    //7.2、会员中心砍价列表
    public static final String APP_MEMBER_BARGAIN = SERVER_URL + "/app_member_bargain.html";
    //7.3、砍价详情页接口
    public static final String APP_BARGAIN_VIEW = SERVER_URL + "/app_bargain_view.html";
    //7.4、发起砍价
    public static final String APP_BARGAIN_START = SERVER_URL + "/app_bargain_start.html";
    //7.5、砍价结果页/分享页
    public static final String APP_BARGAIN_SHARE = SERVER_URL + "/app_bargain_share.html";
    /**
     * 拼团接口
     **/
    //9.1、拼团抽奖首页接口
    public static final String APP_PINGTUANCJ = SERVER_URL + "/app_pintuancj.html";
    //9.2、拼团抽奖-开团内容页
    public static final String APP_KAITUANCJ = SERVER_URL + "/app_kaituancj_view.html";
    //9.3、拼团抽奖-开团参团接口
    public static final String APP_PINGTUANCJ_VIEW = SERVER_URL + "/app_pintuancj_view.html";
    //9.4、拼团抽奖-中奖页接口
    public static final String APP_PINGTUANCJ_PRIZE = SERVER_URL + "/app_pintuancj_prize.html";
    //9.5、拼团内容页
    public static final String APP_KAITUAN_VIEW = SERVER_URL + "/app_kaituan_view.html";
    //9.6、拼团开团接口
    public static final String APP_PINGTUAN_VIEW = SERVER_URL + "/app_pintuan_view.html";
    //9.7、会员中心拼团接口
    public static final String APP_MEMBER_PINTUAN = SERVER_URL + "/app_member_pintuan.html";

    /**
     * -----------------------------------------------------------------------------------
     * --------------------------------------四个平台接口----------------------------------
     * -----------------------------------------------------------------------------------
     */

//    public static final String YOUA_APP_KEY = "421f63f922064b1aaa3583599e9dcf35";//服务平台每个项目的appKey 签名密钥  开发环境
    public static final String YOUA_APP_KEY = "c3295f62efae4d9ca808240048d929fa";//服务平台每个项目的appKey 签名密钥  发布环境

//    public static final String SSO_SERVER_URL = "http://sns.e9448.com";   //用户系统开发环境
    public static final String SSO_SERVER_URL = "http://sns.tepin.com"; //用户系统发布环境

    /**
     * 一、用户登录
     */
    public static final String SSO_LOGIN = SSO_SERVER_URL + "/sns/sso/v1/login";

    /**
     * 二、用户注册
     */
    //注册
    public static final String SSO_REGISTER = SSO_SERVER_URL + "/sns/sso/v1/register";
    //获取注册验证码，验证手机唯一性
    public static final String SSO_REGISTERSMS = SSO_SERVER_URL + "/sns/sms/v1/sendRegisterSms";
    //判断注册验证码是否正确
    public static final String SSO_VERIFYCODE = SSO_SERVER_URL + "/sns/sso/v1/verifyCode";

    /**
     * 三、修改密码
     */
    public static final String SSO_CHANGE_PERSONAGE_PASSWORD = SSO_SERVER_URL + "/sns/sso/v1/changePersonagePassword";

    /**
     * 四、找回密码
     */
    //获取找回密码验证码
    public static final String SSO_LOST_PASSWORDSMS = SSO_SERVER_URL + "/sns/sms/v1/sendLostpasswordSms";
    //判断验证码是否正确
    public static final String SSO_LOST_PASSWORD = SSO_SERVER_URL + "/sns/sso/v1/lostPassword";
    //重置密码
    public static final String SSO_CHANGE_PASSWORD = SSO_SERVER_URL + "/sns/sso/v1/changePassword";

}
