package com.mtl.cypw.payment.request.data;

import lombok.Data;

/**
 * @author tang.
 * @date 2019/10/11.
 */
@Data
public class CmbH5PayReqData {
    /**
     * 请求时间
     */
    private String dateTime;
    /**
     * 分行号
     */
    private String branchNo;
    /**
     * 商户号
     */
    private String merchantNo;
    /**
     * 订单日期
     */
    private String date;
    /**
     * 订单号
     */
    private String orderNo;
    /**
     * 金额
     */
    private String amount;
    /**
     * 过期时间，单位分钟，非必填
     */
    private String expireTimeSpan;
    /**
     * 支付结果通知地址
     */
    private String payNoticeUrl;
    /**
     * 支付结果通知附件参数，非必填
     */
    private String payNoticePara;
    /**
     * 返回商户地址，非必填
     */
    private String returnUrl;
    /**
     * 获取的客户IP，非必填
     */
    private String clientIP;
    /**
     * 允许支付的卡类型，非必填
     */
    private String cardType;


    /**
     * 客户协议号，非必填
     */
    private String agrNo;
    /**
     * 协议开通请求流水号，开通协议时必填
     */
    private String merchantSerialNo;
    /**
     * 商户用户ID,用于标识商户用户的唯一ID，非必填
     */
    private String userID;
    /**
     * 商户用户的手机号，非必填
     */
    private String mobile;
    /**
     * 经度，非必填
     */
    private String lon;
    /**
     * 纬度，非必填
     */
    private String lat;
    /**
     * 风险等级，非必填
     */
    private String riskLevel;
    /**
     * 成功签约结果通知地址，上送协议号且首次签约，必填。其他情况非必填
     */
    private String signNoticeUrl;
    /**
     * 非必填
     */
    private String signNoticePara;
    /**
     * 非必填
     */
    private String extendInfo;
    /**
     * 非必填
     */
    private String extendInfoEncrypType;

}
