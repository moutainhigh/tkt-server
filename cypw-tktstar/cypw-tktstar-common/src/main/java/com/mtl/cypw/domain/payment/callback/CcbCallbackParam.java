package com.mtl.cypw.domain.payment.callback;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;

/**
 * @author tang.
 * @date 2020/2/20.
 */
@Data
public class CcbCallbackParam {
    /**
     * 商户柜台代码
     * 由建行统一分配
     */
    private String posId;
    /**
     * 分行代码
     * 由建行统一分配
     */
    private String branchId;
    /**
     * 定单号
     * 最长30位
     */
    private String orderId;
    /**
     * 付款金额
     */
    private String payment;
    /**
     * 币种
     * 缺省为01－人民币
     * （只支持人民币支付）
     */
    private String curCode = "01";
    /**
     * 备注1
     */
    private String remark1;
    /**
     * 备注2
     */
    private String remark2;
    /**
     * 账户类型
     * 有值时参与验签
     */
    private String accType;
    /**
     * 成功标志
     */
    private String success;
    /**
     * 接口类型
     * 1- 防钓鱼接口
     */
    private String type;
    /**
     * 商户URL
     */
    private String referer;
    /**
     * 客户端IP
     */
    private String clientIp;
    /**
     * 系统记账日期
     * 有值时参与验签
     */
    private String accDate;
    /**
     * 支付账户信息
     * 有值时参与验签
     */
    private String usrMsg;
    /**
     * 分期期数
     * 有值时参与验签
     */
    private String installNum;
    /**
     * 错误信息
     * 有值时参与验签
     */
    private String errMsg;
    /**
     * 客户加密信息
     * 有值时参与验签
     */
    private String usrInfo;
    /**
     * 优惠金额
     * 有值时参与验签
     */
    private String discount;
    /**
     * 数字签名
     */
    private String sign;

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("POSID=" + posId);
        sb.append("&BRANCHID=" + branchId);
        sb.append("&ORDERID=" + orderId);
        sb.append("&PAYMENT=" + payment);
        sb.append("&CURCODE=" + curCode);

        if (StringUtils.isNotEmpty(remark1)) {
            sb.append("&REMARK1=" + remark1);
        } else {
            sb.append("&REMARK1=");
        }
        if (StringUtils.isNotEmpty(remark2)) {
            sb.append("&REMARK2=" + remark2);
        } else {
            sb.append("&REMARK2=");
        }
        if (StringUtils.isNotEmpty(accType)) {
            sb.append("&ACC_TYPE=" + accType);
        }
        sb.append("&SUCCESS=" + success);
        sb.append("&TYPE=" + type);
        if (StringUtils.isNotEmpty(referer)) {
            sb.append("&REFERER=" + referer);
        } else {
            sb.append("&REFERER=");
        }
        if (StringUtils.isNotEmpty(clientIp)) {
            sb.append("&CLIENTIP=" + clientIp);
        } else {
            sb.append("&CLIENTIP=");
        }
        if (StringUtils.isNotEmpty(accDate)) {
            sb.append("&ACCDATE=" + accDate);
        }
        if (StringUtils.isNotEmpty(usrMsg)) {
            sb.append("&USRMSG=" + usrMsg);
        }
        if (StringUtils.isNotEmpty(installNum)) {
            sb.append("&INSTALLNUM=" + installNum);
        }
        if (StringUtils.isNotEmpty(errMsg)) {
            sb.append("&ERRMSG=" + errMsg);
        }else if(StringUtils.isNotEmpty(installNum)){
            sb.append("&ERRMSG=" + errMsg);
        }
        if (StringUtils.isNotEmpty(usrInfo)) {
            sb.append("&USRINFO=" + usrInfo);
        }
        if (StringUtils.isNotEmpty(discount)) {
            sb.append("&DISCOUNT=" + discount);
        }
        String str = sb.toString();
        return str;
    }
}
