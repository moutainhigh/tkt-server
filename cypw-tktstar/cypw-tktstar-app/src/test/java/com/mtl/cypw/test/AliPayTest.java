package com.mtl.cypw.test;

import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.mtl.cypw.domain.payment.callback.AliPayCallbackParam;
import com.mtl.cypw.domain.payment.config.AliConfigure;
import com.mtl.cypw.domain.payment.param.RefundRequestParam;
import com.mtl.cypw.payment.service.AliPayService;
import com.mtl.cypw.payment.service.AliRefundService;
import com.mtl.cypw.provider.mpm.service.EnterpriseConfigService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * @author tang.
 * @date 2020/3/9.
 */
@Slf4j
public class AliPayTest extends BaseTest {

    @Resource
    AliPayService aliPayService;

    @Resource
    AliRefundService aliRefundService;


    @Resource
    EnterpriseConfigService enterpriseConfigService;

    @Test
    public void checkSign() throws AlipayApiException {
        String paramStr = "{\"gmt_create\":\"2020-03-12 14:14:18\",\"charset\":\"utf-8\",\"seller_email\":\"loveyoku349658923@126.com\",\"subject\":\"票品订单\",\"sign\":\"HVW7+H+9z19x/w6QDKfYZTohs0vRsMaFDiKpoG6H5rbc4eh5bBi4xdUiUrfALcnl59jy9hvo66gQ5XQRdtSW/qQm6T9SKfkA7c7dI+Adr9q1s0t8M0Hdmt+QWzo3yzJk4d7cKoXgOo7zP/g9AABsWA6xTAVaJeBVprdIL66T/rWgNLy94mmmq1Lvdmdgo5+78HL3OYfmCN+gLjqM4fvaAZLArSaEoAbvfRIqWkd+Vf3QK4A/8Sba3K0ar9Bka1oR42lW/EzatCtoXqiUmnxk/jVFyBt6sBVb8slmd0VP6gJKgYbdlZVT0c8RwUGpOotilBhPalMiTPIN0jqrGjjUtw==\",\"buyer_id\":\"2088602004983624\",\"invoice_amount\":\"0.01\",\"notify_id\":\"2020031200222141419083621455658339\",\"fund_bill_list\":\"[{\\\"amount\\\":\\\"0.01\\\",\\\"fundChannel\\\":\\\"PCREDIT\\\"}]\",\"notify_type\":\"trade_status_sync\",\"trade_status\":\"TRADE_SUCCESS\",\"receipt_amount\":\"0.01\",\"buyer_pay_amount\":\"0.01\",\"app_id\":\"2021001140685860\",\"sign_type\":\"RSA2\",\"seller_id\":\"2088731171566533\",\"gmt_payment\":\"2020-03-12 14:14:19\",\"notify_time\":\"2020-03-12 14:14:19\",\"version\":\"1.0\",\"out_trade_no\":\"158215900042412032\",\"total_amount\":\"0.01\",\"trade_no\":\"2020031222001483621430710790\",\"auth_app_id\":\"2021001140685860\",\"buyer_logon_id\":\"183****7297\",\"point_amount\":\"0.00\"}";
        AliPayCallbackParam param = JSONObject.toJavaObject(JSONObject.parseObject(paramStr), AliPayCallbackParam.class);

        AliConfigure configure = enterpriseConfigService.getAliConfigure(7);
        Boolean signVerified = aliPayService.checkSign(param, configure);
        if (signVerified) {
            log.error("验签成功");
        } else {
            log.info("验签失败");
        }
    }

    @Test
    public void refund() throws AlipayApiException {
        AliConfigure configure = enterpriseConfigService.getAliConfigure(7);
        RefundRequestParam param = new RefundRequestParam();
        param.setRefundAmount(1L);
        param.setOrderRefundNo("1041_1255");
        param.setOrderNo("158251584090324992");
//        param.setNotifyUrl("https://yuyuandev.fhlun.com/api/pub/v1/notify/ali");
        aliRefundService.refund(param, configure);
    }

    @Test
    public void refundCheckSign() throws AlipayApiException {
        String paramStr = "{\"gmt_create\":\"2020-03-12 16:35:23\",\"charset\":\"utf-8\",\"seller_email\":\"loveyoku349658923@126.com\",\"gmt_payment\":\"2020-03-12 16:35:24\",\"notify_time\":\"2020-03-12 17:20:49\",\"subject\":\"票品订单\",\"gmt_refund\":\"2020-03-12 17:20:48.988\",\"sign\":\"n2qAi5Gf2pwtk4C7WXEQsR4+ahAx2oPflLlnwYCzO2x9+R7qCUVizlHo5PMn35+ydJoFNKrH/7bX0U/B1O+l4csbeNBo98iiJ3fnB1JUhOq51B1sMK2go/1ahP0ljtVIv50HxBwbknJPAotZlqlgk+dGmADOPslocrf+x4bdoABc5hgBrtVhQpdZVEGLPbG4Qboqtq4bor6yh7l6XECIqea4+BvDACOEm/Nf9+odwHk9Mojxot2qMjfPOnXIAgxROnG59kcnBpTXY/uqzLNUsByjgO4N8CVtadFVfqRtV29yNTZrt6j2M1iC/dm+h2MubVmRAbsv6ndZ49KCDeH8KA==\",\"out_biz_no\":\"1041_1255\",\"buyer_id\":\"2088602004983624\",\"version\":\"1.0\",\"notify_id\":\"2020031200222172049083621455445716\",\"notify_type\":\"trade_status_sync\",\"out_trade_no\":\"158251584090324992\",\"total_amount\":\"0.01\",\"trade_status\":\"TRADE_CLOSED\",\"refund_fee\":\"0.01\",\"trade_no\":\"2020031222001483621430710812\",\"auth_app_id\":\"2021001140685860\",\"buyer_logon_id\":\"183****7297\",\"gmt_close\":\"2020-03-12 17:20:48\",\"app_id\":\"2021001140685860\",\"sign_type\":\"RSA2\",\"seller_id\":\"2088731171566533\"}";
        AliPayCallbackParam param = JSONObject.toJavaObject(JSONObject.parseObject(paramStr), AliPayCallbackParam.class);

        AliConfigure configure = enterpriseConfigService.getAliConfigure(7);
        Boolean signVerified = aliPayService.checkSign(param, configure);
        if (signVerified) {
            log.error("验签成功");
        } else {
            log.info("验签失败");
        }
    }

}
