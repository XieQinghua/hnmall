package com.your.mall.activity;

import android.os.Bundle;
import android.webkit.WebView;
import android.widget.TextView;

import com.your.mall.R;
import com.your.mall.base.BaseSwipeBackActivity;

/**
 * Created by xieqinghua.
 * 创建时间：2016/9/2
 * 类描述：用户注册协议
 * 修改备注：
 */
public class RegisterAgreementActivity extends BaseSwipeBackActivity {
    private TextView tv_title;
    private WebView wap_agreement;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_agreement);
        tv_title = (TextView) findViewById(R.id.tv_title_center);
        tv_title.setText("用户注册协议");
        wap_agreement = (WebView) findViewById(R.id.wap_agreement);
        String customHtml ="<style>\n" +
                "    p{ margin-top:3px; margin-bottom:3px; }\n" +
                "</style>\n" +
                "<br>\n" +
                "<p style=\"line-height: normal; margin-top: 6px; margin-bottom: 6px;\">\n" +
                "    <span style=\"color: rgb(0, 0, 0); font-family: 微软雅黑,Microsoft YaHei; font-size: 14px;font-weight:bold;\">\n" +
                "        隐私政策\n" +
                "    </span>\n" +
                "</p>\n" +
                "<p style=\"line-height: 1em; margin-top: 6px;\">\n" +
                "    <span style=\"color: rgb(0, 0, 0); font-family: 宋体, SimSun; font-size: 12px;\">\n" +
                "        友阿农博汇尊重并保护所有使用服务用户的个人隐私权。为了给您提供更准确、更有个性化的服务，友阿农博汇会按照本隐私权政策的规定使用和披露您的个人信息。但友阿农博汇将以高度的勤勉、审慎义务对待这些信息。除本隐私权政策另有规定外，在未征得您事先许可的情况下，友阿农博汇不会将这些信息对外披露或向第三方提供。友阿农博汇会不时更新本隐私权政策。您在同意友阿农博汇服务使用协议之时，即视为您已经同意本隐私权政策全部内容。本隐私权政策属于友阿农博汇服务使用协议不可分割的一部分。&nbsp;\n" +
                "    </span>\n" +
                "</p>\n" +
                "<p style=\"margin-top: 6px; margin-bottom: 6px;\">\n" +
                "    <span style=\"color: rgb(0, 0, 0); font-family: 微软雅黑,Microsoft YaHei; font-size: 14px;font-weight:bold;\">\n" +
                "        1.&nbsp;适用范围&nbsp;\n" +
                "    </span>\n" +
                "</p>\n" +
                "<p style=\"line-height: 1em;\">\n" +
                "    <span style=\"color: rgb(0, 0, 0); font-family: 宋体, SimSun; font-size: 12px;\">\n" +
                "        a)&nbsp;在您注册友阿农博汇帐号时，您根据友阿农博汇要求提供的个人注册信息；&nbsp;\n" +
                "    </span>\n" +
                "</p>\n" +
                "<p style=\"line-height: 1em;\">\n" +
                "    <span style=\"color: rgb(0, 0, 0); font-family: 宋体, SimSun; font-size: 12px;\">\n" +
                "        b)&nbsp;在您使用友阿农博汇网络服务，或访问友阿农博汇平台网页时，友阿农博汇自动接收并记录的您的浏览器和计算机上的信息，包括但不限于您的\n" +
                "        <span style=\"color: rgb(0, 0, 0); font-family: Calibri; font-size: 12px;\">\n" +
                "            IP\n" +
                "        </span>\n" +
                "        地址、浏览器的类型、使用的语言、访问日期和时间、软硬件特征信息及您需求的网页记录等数据；&nbsp;\n" +
                "    </span>\n" +
                "</p>\n" +
                "<p style=\"line-height: 1em;\">\n" +
                "    <span style=\"color: rgb(0, 0, 0); font-family: 宋体, SimSun; font-size: 12px;\">\n" +
                "        c)&nbsp;友阿农博汇通过合法途径从商业伙伴处取得的用户个人数据。&nbsp;\n" +
                "    </span>\n" +
                "</p>\n" +
                "<p style=\"line-height: 1em;\">\n" +
                "    <span style=\"color: rgb(0, 0, 0); font-family: 宋体, SimSun; font-size: 12px;\">\n" +
                "        您了解并同意，以下信息不适用本隐私权政策：&nbsp;\n" +
                "    </span>\n" +
                "</p>\n" +
                "<p style=\"line-height: 1em;\">\n" +
                "    <span style=\"color: rgb(0, 0, 0); font-family: 宋体, SimSun; font-size: 12px;\">\n" +
                "        a)&nbsp;您在使用友阿农博汇平台提供的搜索服务时输入的关键字信息；&nbsp;\n" +
                "    </span>\n" +
                "</p>\n" +
                "<p style=\"line-height: 1em;\">\n" +
                "    <span style=\"color: rgb(0, 0, 0); font-family: 宋体, SimSun; font-size: 12px;\">\n" +
                "        b)&nbsp;友阿农博汇收集到的您在友阿农博汇发布的有关信息数据，包括但不限于参与活动、成交信息及评价详情；&nbsp;\n" +
                "    </span>\n" +
                "</p>\n" +
                "<p style=\"line-height: 1em;\">\n" +
                "    <span style=\"color: rgb(0, 0, 0); font-family: 宋体, SimSun; font-size: 12px;\">\n" +
                "        c)&nbsp;违反法律规定或违反友阿农博汇规则行为及特品汇已对您采取的措施。&nbsp;\n" +
                "    </span>\n" +
                "</p>\n" +
                "<p style=\"margin-top: 6px; margin-bottom: 6px;\">\n" +
                "    <span style=\"color: rgb(0, 0, 0); font-family: 微软雅黑,Microsoft YaHei; font-size: 14px;font-weight:bold;\">\n" +
                "        2.&nbsp;信息使用&nbsp;\n" +
                "    </span>\n" +
                "</p>\n" +
                "<p style=\"line-height: 1em;\">\n" +
                "    <span style=\"color: rgb(0, 0, 0); font-family: 宋体, SimSun; font-size: 12px;\">\n" +
                "        a)&nbsp;友阿农博汇不会向任何无关第三方提供、出售、出租、分享或交易您的个人信息，除非事先得到您的许可，或该第三方和友阿农博汇（含友阿农博汇关联公司）单独或共同为您提供服务，且在该服务结束后，其将被禁止访问包括其以前能够访问的所有这些资料。&nbsp;\n" +
                "    </span>\n" +
                "</p>\n" +
                "<p style=\"line-height: 1em;\">\n" +
                "    <span style=\"color: rgb(0, 0, 0); font-family: 宋体, SimSun; font-size: 12px;\">\n" +
                "        b)&nbsp;友阿农博汇亦不允许任何第三方以任何手段收集、编辑、出售或者无偿传播您的个人信息。任何友阿农博汇平台用户如从事上述活动，一经发现，友阿农博汇有权立即终止与该用户的服务协议。&nbsp;\n" +
                "    </span>\n" +
                "</p>\n" +
                "<p style=\"line-height: 1em;\">\n" +
                "    <span style=\"color: rgb(0, 0, 0); font-family: 宋体, SimSun; font-size: 12px;\">\n" +
                "        c)&nbsp;为服务用户的目的，友阿农博汇可能通过使用您的个人信息，向您提供您感兴趣的信息，包括但不限于向您发出产品和服务信息，或者与友阿农博汇合作伙伴共享信息以便他们向您发送有关其产品和服务的信息（后者需要您的事先同意）。\n" +
                "    </span>\n" +
                "</p>\n" +
                "<p style=\"margin-top: 6px; margin-bottom: 6px;\">\n" +
                "    <span style=\"color: rgb(0, 0, 0); font-family: 微软雅黑, &quot;Microsoft YaHei&quot;; font-size: 14px;font-weight:bold;\">\n" +
                "        3.&nbsp;信息披露&nbsp;\n" +
                "    </span>\n" +
                "</p>\n" +
                "<p style=\"line-height: 1em;\">\n" +
                "    <span style=\"color: rgb(0, 0, 0); font-family: 宋体, SimSun; font-size: 12px;\">\n" +
                "        在如下情况下，友阿农博汇将依据您的个人意愿或法律的规定全部或部分的披露您的个人信息：&nbsp;\n" +
                "    </span>\n" +
                "</p>\n" +
                "<p style=\"line-height: 1em;\">\n" +
                "    <span style=\"color: rgb(0, 0, 0); font-family: 宋体, SimSun; font-size: 12px;\">\n" +
                "        a)&nbsp;经您事先同意，向第三方披露；&nbsp;\n" +
                "    </span>\n" +
                "</p>\n" +
                "<p style=\"line-height: 1em;\">\n" +
                "    <span style=\"color: rgb(0, 0, 0); font-family: 宋体, SimSun; font-size: 12px;\">\n" +
                "        b)&nbsp;为提供您所要求的产品和服务，而必须和第三方分享您的个人信息；&nbsp;\n" +
                "    </span>\n" +
                "</p>\n" +
                "<p style=\"line-height: 1em;\">\n" +
                "    <span style=\"color: rgb(0, 0, 0); font-family: 宋体, SimSun; font-size: 12px;\">\n" +
                "        c)&nbsp;根据法律的有关规定，或者行政或司法机构的要求，向第三方或者行政、司法机构披露；\n" +
                "    </span>\n" +
                "</p>\n" +
                "<p style=\"line-height: 1em;\">\n" +
                "    <span style=\"color: rgb(0, 0, 0); font-family: 宋体, SimSun; font-size: 12px;\">\n" +
                "        d)&nbsp;如您出现违反中国有关法律、法规或者友阿农博汇服务协议或相关规则的情况，需要向第三方披露；&nbsp;&nbsp;\n" +
                "    </span>\n" +
                "</p>\n" +
                "<p style=\"line-height: 1em;\">\n" +
                "    <span style=\"color: rgb(0, 0, 0); font-family: 宋体, SimSun; font-size: 12px;\">\n" +
                "        e)&nbsp;如您是适格的知识产权投诉人并已提起投诉，应被投诉人要求，向被投诉人披露，以便双方处理可能的权利纠纷；\n" +
                "    </span>\n" +
                "</p>\n" +
                "<p style=\"line-height: 1em;\">\n" +
                "    <span style=\"color: rgb(0, 0, 0); font-family: 宋体, SimSun; font-size: 12px;\">\n" +
                "        f)&nbsp;在友阿农博汇平台上创建的某一交易中，如交易任何一方履行或部分履行了交易义务并提出信息披露请求的，友阿农博汇有权决定向该用户提供其交易对方的联络方式等必要信息，以促成交易的完成或纠纷的解决。&nbsp;&nbsp;\n" +
                "    </span>\n" +
                "</p>\n" +
                "<p style=\"line-height: 1em;\">\n" +
                "    <span style=\"color: rgb(0, 0, 0); font-family: 宋体, SimSun; font-size: 12px;\">\n" +
                "        g)&nbsp;其它友阿农博汇根据法律、法规或者网站政策认为合适的披露。&nbsp;\n" +
                "    </span>\n" +
                "</p>\n" +
                "<p style=\"line-height: 1.75em; margin-top: 6px; margin-bottom: 6px;\">\n" +
                "    <span style=\"color: rgb(0, 0, 0);\">\n" +
                "        <span style=\"color: rgb(0, 0, 0); font-family: 宋体, SimSun; font-size: 12px;\">\n" +
                "        </span>\n" +
                "        <span style=\"color: rgb(0, 0, 0); font-family: 微软雅黑,Microsoft YaHei; font-size: 14px;font-weight:bold;\">\n" +
                "            4.&nbsp;信息存储和交换&nbsp;&nbsp;\n" +
                "        </span>\n" +
                "    </span>\n" +
                "</p>\n" +
                "<p style=\"line-height: 1em;\">\n" +
                "    <span style=\"color: rgb(0, 0, 0); font-family: 宋体, SimSun; font-size: 12px;\">\n" +
                "        友阿农博汇收集的有关您的信息和资料将保存在友阿农博汇及（或）其关联公司的服务器上，这些信息和资料可能传送至您所在国家、地区或友阿农博汇收集信息和资料所在地的境外并在境外被访问、存储和展示。&nbsp;\n" +
                "    </span>\n" +
                "</p>\n" +
                "<p style=\"margin-top: 6px; margin-bottom: 6px;\">\n" +
                "    <span style=\"color: rgb(0, 0, 0); font-family: 微软雅黑, &quot;Microsoft YaHei&quot;; font-size: 14px;font-weight:bold;\">\n" +
                "        5.&nbsp;Cookie的使用&nbsp;\n" +
                "    </span>\n" +
                "</p>\n" +
                "<p style=\"line-height: 1em;\">\n" +
                "    <span style=\"color: rgb(0, 0, 0); font-family: 宋体, SimSun; font-size: 12px;\">\n" +
                "        a)&nbsp;在您未拒绝接受\n" +
                "        <span style=\"color: rgb(0, 0, 0); font-family: Calibri; font-size: 12px;\">\n" +
                "            cookies\n" +
                "        </span>\n" +
                "        的情况下，友阿农博汇会在您的计算机上设定或取用\n" +
                "        <span style=\"color: rgb(0, 0, 0); font-family: Calibri; font-size: 12px;\">\n" +
                "            cookies\n" +
                "        </span>\n" +
                "        ，以便您能登录或使用依赖于\n" +
                "        <span style=\"color: rgb(0, 0, 0); font-family: Calibri; font-size: 12px;\">\n" +
                "            cookies\n" +
                "        </span>\n" +
                "        的友阿农博汇平台服务或功能。友阿农博汇使用\n" +
                "        <span style=\"color: rgb(0, 0, 0); font-family: Calibri; font-size: 12px;\">\n" +
                "            cookies\n" +
                "        </span>\n" +
                "        可为您提供更加周到的个性化服务，包括推广服务。&nbsp;\n" +
                "    </span>\n" +
                "</p>\n" +
                "<p style=\"line-height: 1em;\">\n" +
                "    <span style=\"color: rgb(0, 0, 0); font-family: 宋体, SimSun; font-size: 12px;\">\n" +
                "        <span style=\"color: rgb(0, 0, 0); font-family: Calibri; font-size: 12px;\">\n" +
                "            b)&nbsp;\n" +
                "        </span>\n" +
                "        您有权选择接受或拒绝接受\n" +
                "        <span style=\"color: rgb(0, 0, 0); font-family: Calibri; font-size: 12px;\">\n" +
                "            cookies\n" +
                "        </span>\n" +
                "        。您可以通过修改浏览器设置的方式拒绝接受\n" +
                "        <span style=\"color: rgb(0, 0, 0); font-family: Calibri; font-size: 12px;\">\n" +
                "            cookies\n" +
                "        </span>\n" +
                "        。但如果您选择拒绝接受\n" +
                "        <span style=\"color: rgb(0, 0, 0); font-family: Calibri; font-size: 12px;\">\n" +
                "            cookies\n" +
                "        </span>\n" +
                "        ，则您可能无法登录或使用依赖于\n" +
                "        <span style=\"color: rgb(0, 0, 0); font-family: Calibri; font-size: 12px;\">\n" +
                "            cookies\n" +
                "        </span>\n" +
                "        的友阿农博汇网络服务或功能。&nbsp;\n" +
                "    </span>\n" +
                "</p>\n" +
                "<p style=\"line-height: 1em;\">\n" +
                "    <span style=\"color: rgb(0, 0, 0); font-family: 宋体, SimSun; font-size: 12px;\">\n" +
                "        c)&nbsp;通过友阿农博汇所设\n" +
                "        <span style=\"color: rgb(0, 0, 0); font-family: Calibri; font-size: 12px;\">\n" +
                "            cookies\n" +
                "        </span>\n" +
                "        所取得的有关信息，将适用本政策。\n" +
                "    </span>\n" +
                "</p>\n" +
                "<p style=\"margin-top: 6px; margin-bottom: 6px;\">\n" +
                "    <span style=\"color: rgb(0, 0, 0); font-family: 微软雅黑, &quot;Microsoft YaHei&quot;; font-size: 14px;font-weight:bold;\">\n" +
                "        6.&nbsp;信息安全&nbsp;&nbsp;\n" +
                "    </span>\n" +
                "</p>\n" +
                "<p style=\"line-height: 1em;\">\n" +
                "    <span style=\"color: rgb(0, 0, 0); font-family: 宋体, SimSun; font-size: 12px;\">\n" +
                "        a)&nbsp;友阿农博汇帐号均有安全保护功能，请妥善保管您的用户名及密码信息。友阿农博汇将通过对用户密码进行加密等安全措施确保您的信息不丢失，不被滥用和变造。尽管有前述安全措施，但同时也请您注意在信息网络上不存在“完善的安全措施”。&nbsp;&nbsp;\n" +
                "    </span>\n" +
                "</p>\n" +
                "<p style=\"line-height: 1em;\">\n" +
                "    <span style=\"color: rgb(0, 0, 0); font-family: 宋体, SimSun; font-size: 12px;\">\n" +
                "        b)&nbsp;在使用友阿农博汇网络服务进行网上交易时，您不可避免的要向交易对方或潜在的交易对方披露自己的个人信息，如联络方式或者邮政地址。请您妥善保护自己的个人信息，仅在必要的情形下向他人提供。如您发现自己的个人信息泄密，尤其是友阿农博汇用户名及密码发生泄露，请您立即联络友阿农博汇客服，以便友阿农博汇采取相应措施。\n" +
                "    </span>\n" +
                "</p>\n" +
                "<p>\n" +
                "    <span style=\"font-family: Calibri; font-size: 12px;\">\n" +
                "        &nbsp;\n" +
                "    </span>\n" +
                "</p>\n" +
                "<p>\n" +
                "    &nbsp;\n" +
                "</p>";
        wap_agreement.loadDataWithBaseURL(null, customHtml, "text/html", "gb2312", null);
    }
}
