package com.mtl.cypw.payment.response;

import com.mtl.cypw.common.utils.MD5Util;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

/**
 * @author tang.
 * @date 2020/1/13.
 */
@Data
public class CcbPayNoticeResponse {
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
     */
    private String REMARK1;
    /**
     * 备注2
     */
    private String REMARK2;
    /**
     * 账户类型
     * 有值时参与验签
     */
    private String ACC_TYPE;
    /**
     * 成功－Y，失败－N
     */
    private String SUCCESS;
    /**
     * 接口类型
     * 1- 防钓鱼接口
     */
    private String TYPE = "1";
    /**
     * 商户URL
     */
    private String REFERER;
    /**
     * 客户端IP
     */
    private String CLIENTIP;
    /**
     * 系统记账日期
     * 商户主管在商户后台设置返回记账日期时返回该字且参与验签， 字段格式为YYYYMMDD（如20100907），未设置时无此字段返回且不参与验签
     */
    private String ACCDATE;
    /**
     * 支付账户信息
     * 业务人员在ECTIP后台设置返回账户信息的开关且支付成功时将返回账户加密信息且该字段参与验签，否则无此字段返回且不参与验签，格式如下：“姓名|账号”
     */
    private String USRMSG;
    /**
     * 分期期数
     * 从商户传送的信息中获得，当分期期数为空或无此字段上送时，无此字段返回且不参与验签，否则有此字段返回且参与验签。
     */
    private String INSTALLNUM;
    /**
     * 错误信息
     * 该值默认返回为空，商户无需处理，仅需参与验签即可。当有分期期数返回时，则有ERRMSG字段返回且参与验签，否则无此字段返回且不参与验签。
     */
    private String ERRMSG;
    /**
     * 客户加密信息
     * 业务人员在ECTIP后台设置客户信息加密返回的开关且该字段参与验签，否则无此字段返回且不参与验签，格式如下：“证件号密文|手机号密文”。该字段不可解密。
     */
    private String USRINFO;
    /**
     * 优惠金额
     * 客户实际支付的金额。仅对配置了商户号的商户返回，参与验签。其他商户不返回该字段，不参与验签。
     */
    private String DISCOUNT;
    /**
     * 数字签名
     */
    private String SIGN;

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("&POSID=" + POSID);
        sb.append("&BRANCHID=" + BRANCHID);
        sb.append("&ORDERID=" + ORDERID);
        sb.append("&PAYMENT=" + PAYMENT);
        sb.append("&CURCODE=" + CURCODE);

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
        if (StringUtils.isNotEmpty(ACC_TYPE)) {
            sb.append("&ACC_TYPE=" + ACC_TYPE);
        }
        sb.append("&SUCCESS=" + SUCCESS);
        sb.append("&TYPE=" + TYPE);
        if (StringUtils.isNotEmpty(REFERER)) {
            sb.append("&REFERER=" + REFERER);
        } else {
            sb.append("&REFERER=");
        }
        if (StringUtils.isNotEmpty(CLIENTIP)) {
            sb.append("&CLIENTIP=" + CLIENTIP);
        } else {
            sb.append("&CLIENTIP=");
        }
        if (StringUtils.isNotEmpty(ACCDATE)) {
            sb.append("&ACCDATE=" + ACCDATE);
        }
        if (StringUtils.isNotEmpty(USRMSG)) {
            sb.append("&USRMSG=" + USRMSG);
        }
        if (StringUtils.isNotEmpty(INSTALLNUM)) {
            sb.append("&INSTALLNUM=" + INSTALLNUM);
        }
        if (StringUtils.isNotEmpty(ERRMSG)) {
            sb.append("&ERRMSG=" + ERRMSG);
        }
        if (StringUtils.isNotEmpty(USRINFO)) {
            sb.append("&USRINFO=" + USRINFO);
        }
        if (StringUtils.isNotEmpty(DISCOUNT)) {
            sb.append("&DISCOUNT=" + DISCOUNT);
        }
        return MD5Util.md5(sb.toString());
    }

    public Boolean checkSign() {
        String sign = this.toString();
        return this.SIGN.equals(sign);
    }
}
