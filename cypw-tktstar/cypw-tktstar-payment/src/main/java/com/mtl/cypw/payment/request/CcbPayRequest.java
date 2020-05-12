package com.mtl.cypw.payment.request;

import com.mtl.cypw.common.utils.MD5Util;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

/**
 * @author tang.
 * @date 2020/1/7.
 */
@Data
public class CcbPayRequest {

    public static final String CCB_PAY_URL = "https://ibsbjstar.ccb.com.cn/CCBIS/ccbMain?";
    /**
     * 商户代码
     * 由建行统一分配
     */
    private String MERCHANTID;
    /**
     * 商户柜台代码
     * 由建行统一分配
     */
    private String POSID;
    /**
     * 分行代码
     * 由建行统一分配
     */
    private String BRANCHID;
    /**
     * 定单号
     * 最长30位
     */
    private String ORDERID;
    /**
     * 付款金额
     */
    private String PAYMENT;
    /**
     * 币种
     * 缺省为01－人民币
     * （只支持人民币支付）
     */
    private String CURCODE = "01";
    /**
     * 备注1
     * 非必填
     */
    private String REMARK1;
    /**
     * 备注2
     * 非必填
     */
    private String REMARK2;
    /**
     * 交易码
     * 由建行统一分配为520100
     */
    private String TXCODE = "520100";
    /**
     * MAC校验域
     * 采用标准MD5算法
     */
    private String MAC;
    /**
     * 接口类型
     * 1- 防钓鱼接口
     */
    private String TYPE = "1";
    /**
     * 公钥后30位
     */
    private String PUB;
    /**
     * 网关类型
     */
    private String GATEWAY;
    /**
     * 客户端IP
     * 非必填
     */
    private String CLIENTIP;
    /**
     * 客户注册信息
     * 中文需使用escape编码
     * 非必填
     */
    private String REGINFO;
    /**
     * 商品信息
     * 中文需使用escape编码
     * 非必填
     */
    private String PROINFO;
    /**
     * 商户URL
     * 非必填
     */
    private String REFERER;
    /**
     * 分期期数
     * 非必填
     */
    private String INSTALLNUM;
    /**
     * 客户端标识
     * 非必填
     */
    private String THIRDAPPINFO;
    /**
     * TIMEOUT
     * 订单超时时间
     * 非必填
     */
    private String TIMEOUT;
    /**
     * 银行代码
     * 非必填
     */
    private String ISSINSCODE;
    /**
     * 不允许信用卡
     * Y:不允许信用卡 N或其他：允许信用卡
     * 非必填
     */
    private String NoCredit;
    /**
     * 不允许借记卡
     * Y:不允许借记卡 N或其他：允许借记卡
     * 非必填
     */
    private String NoDebit;
    /**
     * 客户姓名
     * 中文需使用escape编码
     * 非必填
     */
    private String USERNAME;
    /**
     * 客户证件号码
     * 非必填
     */
    private String IDNUMBER;

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("MERCHANTID=" + MERCHANTID);
        sb.append("&POSID=" + POSID);
        sb.append("&BRANCHID=" + BRANCHID);
        sb.append("&ORDERID=" + ORDERID);
        sb.append("&PAYMENT=" + PAYMENT);
        sb.append("&CURCODE=" + CURCODE);
        sb.append("&TXCODE=" + TXCODE);

        if (StringUtils.isNotEmpty(REMARK1)) {
            sb.append("&REMARK1=" + REMARK1);
        } else {
            sb.append("&REMARK1=");
        }
        if (StringUtils.isNotEmpty(REMARK2)) {
            sb.append("&REMARK2=" + REMARK2);
        } else {
            sb.append("&REMARK2=");
        }
        sb.append("&TYPE=" + TYPE);
        sb.append("&PUB=" + PUB);
        if (StringUtils.isNotEmpty(GATEWAY)) {
            sb.append("&GATEWAY=" + GATEWAY);
        } else {
            sb.append("&GATEWAY=");
        }
        if (StringUtils.isNotEmpty(CLIENTIP)) {
            sb.append("&CLIENTIP=" + CLIENTIP);
        } else {
            sb.append("&CLIENTIP=");
        }
        if (StringUtils.isNotEmpty(REGINFO)) {
            sb.append("&REGINFO=" + REGINFO);
        } else {
            sb.append("&REGINFO=");
        }
        if (StringUtils.isNotEmpty(PROINFO)) {
            sb.append("&PROINFO=" + PROINFO);
        } else {
            sb.append("&PROINFO=");
        }
        if (StringUtils.isNotEmpty(REFERER)) {
            sb.append("&REFERER=" + REFERER);
        } else {
            sb.append("&REFERER=");
        }
        if (StringUtils.isNotEmpty(INSTALLNUM)) {
            sb.append("&INSTALLNUM=" + INSTALLNUM);
        }
        if (StringUtils.isNotEmpty(THIRDAPPINFO)) {
            sb.append("&THIRDAPPINFO=" + THIRDAPPINFO);
        }
        if (StringUtils.isNotEmpty(TIMEOUT)) {
            sb.append("&TIMEOUT=" + TIMEOUT);
        }
        if (StringUtils.isNotEmpty(ISSINSCODE)) {
            sb.append("&ISSINSCODE=" + ISSINSCODE);
        }
        if (StringUtils.isNotEmpty(NoCredit)) {
            sb.append("&NoCredit=" + NoCredit);
        }
        if (StringUtils.isNotEmpty(NoDebit)) {
            sb.append("&NoDebit=" + NoDebit);
        }
        if (StringUtils.isNotEmpty(USERNAME)) {
            sb.append("&USERNAME=" + USERNAME);
        }
        if (StringUtils.isNotEmpty(IDNUMBER)) {
            sb.append("&IDNUMBER=" + IDNUMBER);
        }
        sb.append("&MAC=" + MD5Util.md5(sb.toString()));
        String str = sb.toString();
        str = str.replace("&PUB=" + PUB, "");
        return str;
    }
}
