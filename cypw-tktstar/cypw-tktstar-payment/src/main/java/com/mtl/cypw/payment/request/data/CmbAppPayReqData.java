package com.mtl.cypw.payment.request.data;

import lombok.Data;

/**
 * @author tang.
 * @date 2019/10/11.
 */
@Data
public class CmbAppPayReqData {
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
     * 加密类型
     */
    private String encrypType;
    /**
     * 加密数据
     */
    private String encrypData;

    /**
     * 二级商户编码
     */
    private String subMerchantNo;
    /**
     * 二级商户名称
     */
    private String subMerchantName;
    /**
     * 二级商户类别编码
     */
    private String subMerchantTPCode;
    /**
     * 二级商户类别名称
     */
    private String subMerchantTPName;
}
