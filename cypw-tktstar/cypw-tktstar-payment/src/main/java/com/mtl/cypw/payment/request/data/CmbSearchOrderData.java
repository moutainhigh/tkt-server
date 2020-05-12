package com.mtl.cypw.payment.request.data;

import lombok.Data;

/**
 * @author tang.
 * @date 2019/11/4.
 */
@Data
public class CmbSearchOrderData {

    /**
     * 请求时间,商户发起该请求的当前时间，精确到秒。
     * 格式：yyyyMMddHHmmss
     */
    private String dateTime;
    /**
     * 商户分行号，4位数字
     */
    private String branchNo;
    /**
     * 商户号，6位数字
     */
    private String merchantNo;
    /**
     * 查询类型:
     * A：按银行订单流水号查询
     * B：按商户订单日期和订单号查询；
     */
    private String type;
    /**
     * 银行订单流水号,type=A时必填
     */
    private String bankSerialNo;
    /**
     * 商户订单日期，格式：yyyyMMdd
     */
    private String date;
    /**
     * type=B时必填商户订单号
     */
    private String orderNo;
    /**
     * 商户结账系统的操作员号
     * 非必填
     */
    private String operatorNo;
}
