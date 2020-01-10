package com.your.mall.application;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.telephony.TelephonyManager;
import android.webkit.CookieManager;

import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.tencent.bugly.crashreport.CrashReport;
import com.your.mall.R;

import org.xutils.x;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

/**
 * Created by xieqinghua.
 * 创建时间：2016/8/30
 * 类描述：APP主程序
 * 修改备注：
 */
public class MallApplication extends Application {
    public static Context applicationContext;
    private static MallApplication instance;
    // 共享变量
    private Handler appHandler = null;
    private ArrayList<Activity> activities = new ArrayList<Activity>();

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        applicationContext = this;
        instance = this;
        CrashReport.initCrashReport(this, "900053677", false);
//        CrashReport.testJavaCrash();
        x.Ext.init(this);
        x.Ext.setDebug(true); // 是否输出debug日志，release改为false
        /**
         * 初始化ImageLoader
         */
        initImageLoader();
    }


    public static MallApplication getInstance() {
        return instance;
    }

    public static Context getContext() {
        return applicationContext;
    }

    // set方法
    public void setHandler(Handler handler) {
        this.appHandler = handler;
    }

    // get方法
    public Handler getHandler() {
        return appHandler;
    }
    /**
     * 初始化ImageLoader
     */
    private void initImageLoader() {
        //缓存设置
        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
                .cacheInMemory()  //1.8.6包使用时候，括号里面传入参数true
                .cacheOnDisc()    //同上
                .build();
        //参数配置
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getContext())
                .defaultDisplayImageOptions(defaultOptions)
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .denyCacheImageMultipleSizesInMemory()
                .discCacheFileNameGenerator(new Md5FileNameGenerator())
                .tasksProcessingOrder(QueueProcessingType.LIFO)
                .build();
        ImageLoader.getInstance().init(config);
    }

    /**
     * 获取设备uuid
     *
     * @return
     */
    public String getMyUUID() {
        final TelephonyManager tm = (TelephonyManager) getBaseContext().getSystemService(Context.TELEPHONY_SERVICE);
        final String tmDevice, tmSerial, tmPhone, androidId;
        tmDevice = "" + tm.getDeviceId();
        tmSerial = "" + tm.getSimSerialNumber();
        androidId = "" + android.provider.Settings.Secure.getString(getContentResolver(), android.provider.Settings.Secure.ANDROID_ID);
        UUID deviceUuid = new UUID(androidId.hashCode(), ((long) tmDevice.hashCode() << 32) | tmSerial.hashCode());
        String uuid = deviceUuid.toString();
        return uuid;
    }

    /**
     * 获取当前时间
     *
     * @return 14位时间戳（20160830091212）
     */
    public String getCurrentTime() {
        DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        String time = df.format(System.currentTimeMillis());
        return time;
    }

    /**
     * 获取当前时间的十位时间戳
     *
     * @return String类型
     */
    public String getTensTimestamp() {
        String time = String.valueOf(System.currentTimeMillis()).toString().substring(0, 10);
        return time;
    }

    /**
     * 时间戳转换成时间（例：2016.08.09）
     *
     * @param timestamp 10位long类型时间戳
     * @return
     */
    public String getStringTime(String timestamp) {
        String time = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        long timestamp1;
        if (!timestamp.equals("")) {
            timestamp1 = Long.valueOf(timestamp) * 1000L;
        } else {
            timestamp1 = 1000L;
        }
        time = sdf.format((new Date(timestamp1)));
        return time;
    }

    /**
     * 获取当前应用程序的版本号
     *
     * @return versionName
     */
    public String getVersion() {
        String st = getResources().getString(R.string.version_number_is_wrong);
        PackageManager pm = getPackageManager();
        try {
            PackageInfo packinfo = pm.getPackageInfo(getPackageName(), 0);
            String version = packinfo.versionName;
            return version;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return st;
        }
    }

    /**
     * @param context
     * @param autoLogin    是否登录标识
     * @param openId
     * @param mobileNumber
     * @param passWord
     * @param accessToken
     * @param userId
     * @param userName
     * @param regTime
     * @param cartNum      购物车数量
     */
    public void saveLoginParam(Context context,
                               boolean autoLogin,
                               String openId,
                               String mobileNumber,
                               String passWord,
                               String accessToken,
                               String userId,
                               String userName,
                               String regTime,
                               String cartNum) {
        SharedPreferences sp = context.getSharedPreferences("loginParam", Activity.MODE_PRIVATE);
        sp.edit().putBoolean("autoLogin", autoLogin)
                .putString("openId", openId)
                .putString("mobileNumber", mobileNumber)
                .putString("passWord", passWord)
                .putString("accessToken", accessToken)
                .putString("userId", userId)
                .putString("userName", userName)
                .putString("regTime", regTime)
                .putString("cartNum", cartNum)
                .commit();
    }

    //登录开关，（true已登录，false未登录，保存到SharedPreferences）
    private Boolean autoLogin = false;
    private String openId;
    private String mobileNumber;
    private String passWord;
    private String accessToken;
    private String userId;
    private String userName;
    private String regTime;
    private String cartNum;

    public Boolean getAutoLogin(Context context) {
        SharedPreferences sp = context.getSharedPreferences("loginParam", Activity.MODE_PRIVATE);
        autoLogin = sp.getBoolean("autoLogin", false);
        return autoLogin;
    }

    public void setAutoLogin(Context con, boolean autoLogin) {
        this.autoLogin = autoLogin;
        SharedPreferences sp = con.getSharedPreferences("loginParam", Activity.MODE_PRIVATE);
        sp.edit().putBoolean("autoLogin", autoLogin).commit();
    }

    public String getOpenId(Context context) {
        SharedPreferences sp = context.getSharedPreferences("loginParam", Activity.MODE_PRIVATE);
        openId = sp.getString("openId", "");
        return openId;
    }

    public String getMobileNumber(Context context) {
        SharedPreferences sp = context.getSharedPreferences("loginParam", Activity.MODE_PRIVATE);
        mobileNumber = sp.getString("mobileNumber", "");
        return mobileNumber;
    }

    public String getPassWord(Context context) {
        SharedPreferences sp = context.getSharedPreferences("loginParam", Activity.MODE_PRIVATE);
        passWord = sp.getString("passWord", "");
        return passWord;
    }

    /**
     * 保存密码
     *
     * @param con
     * @param passWord
     */
    public void setPassWord(Context con, String passWord) {
        this.passWord = passWord;
        SharedPreferences sp = con.getSharedPreferences("loginParam", Activity.MODE_PRIVATE);
        sp.edit().putString("passWord", passWord).commit();
    }

    public String getAccessToken(Context context) {
        SharedPreferences sp = context.getSharedPreferences("loginParam", Activity.MODE_PRIVATE);
        accessToken = sp.getString("accessToken", "");
        return accessToken;
    }

    public String getUserId(Context context) {
        SharedPreferences sp = context.getSharedPreferences("loginParam", Activity.MODE_PRIVATE);
        userId = sp.getString("userId", "");
        return userId;
    }

    public String getUserName(Context context) {
        SharedPreferences sp = context.getSharedPreferences("loginParam", Activity.MODE_PRIVATE);
        userName = sp.getString("userName", "");
        return userName;
    }

    public String getRegTime(Context context) {
        SharedPreferences sp = context.getSharedPreferences("loginParam", Activity.MODE_PRIVATE);
        regTime = sp.getString("regTime", "");
        return regTime;
    }

    public void setCartNum(Context con, String cartNum) {
        this.cartNum = cartNum;
        SharedPreferences sp = con.getSharedPreferences("loginParam", Activity.MODE_PRIVATE);
        sp.edit().putString("cartNum", cartNum).commit();
    }

    public String getCartNum(Context context) {
        SharedPreferences sp = context.getSharedPreferences("loginParam", Activity.MODE_PRIVATE);
        cartNum = sp.getString("cartNum", "0");
        return cartNum;
    }


    private String cookieString;

    /**
     * 保存Cookie
     *
     * @param context
     * @param cookieString
     */
    public void setCookie(Context context, String cookieString) {
        this.cookieString = cookieString;
        SharedPreferences sp = context.getSharedPreferences("Cookie", Activity.MODE_PRIVATE);
        sp.edit().putString("cookieString", cookieString).commit();
    }

    public String getCookie(Context context) {
        SharedPreferences sp = context.getSharedPreferences("Cookie", Activity.MODE_PRIVATE);
        cookieString = sp.getString("cookieString", "");
        return cookieString;
    }

    /**
     * 清除保存的用户数据，注销登录需调用此方法
     *
     * @param context
     */
    public void clearSaveParam(Context context) {
        SharedPreferences sp = context.getSharedPreferences("loginParam", Activity.MODE_PRIVATE);
        sp.edit().clear();
        sp.edit().commit();
    }

    /**
     * 清除Cookie
     *
     * @param context
     */
    public void clearCookie(Context context) {
        SharedPreferences sp = context.getSharedPreferences("Cookie", Activity.MODE_PRIVATE);
        sp.edit().clear();
        sp.edit().commit();
        CookieManager.getInstance().removeSessionCookie();
    }
}