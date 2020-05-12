package com.mtl.cypw.payment.response.data;

import lombok.Data;

/**
 * @author tang.
 * @date 2019/10/11.
 */
@Data
public class CmbPayNoticeData {
    /**
     * 请求时间
     */
    private String dateTime;
    /**
     * 订单日期
     */
    private String date;
    /**
     * 金额
     */
    private String amount;
    /**
     * 银行受理日期
     */
    private String bankDate;
    /**
     * 订单号
     */
    private String orderNo;
    /**
     * 优惠金额
     */
    private String discountAmount;
    /**
     * 通知类型
     */
    private String noticeType;
    /**
     * 回调HTTP方法
     */
    private String httpMethod;
    /**
     * 支付卡类型
     */
    private String cardType;
    /**
     * 银行通知序号
     */
    private String noticeSerialNo;
    /**
     * 商户附加参数
     */
    private String merchantPara;
    /**
     * 优惠标志
     */
    private String discountFlag;
    /**
     * 银行订单流水号
     */
    private String bankSerialNo;
    /**
     * 回调HTTP地址
     */
    private String noticeUrl;
    /**
     * 商户分行号
     */
    private String branchNo;
    /**
     * 商户号
     */
    private String merchantNo;
}
