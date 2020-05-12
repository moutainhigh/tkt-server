package com.mtl.cypw.provider.payment.endpoint;

import com.alibaba.fastjson.JSONObject;
import com.juqitech.request.GenericRequest;
import com.juqitech.response.ResultBuilder;
import com.juqitech.response.TSingleResult;
import com.mtl.cypw.api.payment.endpoint.CmbPayApi;
import com.mtl.cypw.common.enums.ErrorCode;
import com.mtl.cypw.common.exception.BusinessException;
import com.mtl.cypw.domain.payment.config.CmbConfigure;
import com.mtl.cypw.domain.payment.enums.CmbPayTypeEnum;
import com.mtl.cypw.domain.payment.enums.PayTypeEnum;
import com.mtl.cypw.domain.payment.param.PayCheckSignParam;
import com.mtl.cypw.domain.payment.param.CmbPayRequestParam;
import com.mtl.cypw.domain.payment.result.CmbPayResult;
import com.mtl.cypw.order.model.Order;
import com.mtl.cypw.order.service.OrderQueryService;
import com.mtl.cypw.payment.constant.CmbConstants;
import com.mtl.cypw.payment.response.CmbPayResponse;
import com.mtl.cypw.payment.service.CmbPayService;
import com.mtl.cypw.provider.mpm.service.EnterpriseConfigService;
import com.mtl.cypw.provider.payment.service.PaymentCallbackService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author tang.
 * @date 2019/12/31.
 */
@RestController
@Slf4j
public class CmbPayEndpoint implements CmbPayApi {

    @Resource
    CmbPayService cmbPayService;

    @Resource
    OrderQueryService orderQueryServiceImpl;

    @Resource
    EnterpriseConfigService enterpriseConfigService;

    @Resource
    private PaymentCallbackService paymentCallbackService;

    @Value("${cmb.url.env}")
    private String cmbUrlEnv;

    @Override
    public TSingleResult<CmbPayResult> unifiedOrder(GenericRequest<CmbPayRequestParam> request) {
        try {
            log.debug("招行支付，request:{}", request);
            CmbPayRequestParam param = request.getParam();
            if (param.checkParam()) {
                try {
                    CmbPayResult cmbPayResult = new CmbPayResult();
                    CmbConfigure configure = enterpriseConfigService.getCmbConfigure(param.getEnterpriseId());
                    String jsonRequestData = "";
                    if (CmbPayTypeEnum.APP.equals(param.getCmbPayTypeEnum())) {
                        jsonRequestData = cmbPayService.combineAppPayData(request.getParam(), configure);
                        cmbPayResult.setGatewayUrl(CmbConstants.getGatewayUrl_APP(cmbUrlEnv));
                    } else if (CmbPayTypeEnum.H5.equals(param.getCmbPayTypeEnum())) {
                        jsonRequestData = cmbPayService.combineH5PayData(request.getParam(), configure);
                        cmbPayResult.setGatewayUrl(CmbConstants.getGatewayUrl_H5(cmbUrlEnv));
                    } else {
                        throw new BusinessException(ErrorCode.ERROR_PAY_TYPE.getMsg(), ErrorCode.ERROR_PAY_TYPE.getCode());
                    }
                    cmbPayResult.setJsonRequestData(jsonRequestData);
                    return ResultBuilder.succTSingle(cmbPayResult);
                } catch (Exception e) {
                    log.error("招行支付异常：{}", e.getMessage());
                    throw new BusinessException(ErrorCode.ERROR_PAY.getMsg(), ErrorCode.ERROR_PAY.getCode());
                }
            } else {
                log.error("招行支付参数异常,request:{}", JSONObject.toJSONString(param));
                throw new BusinessException(ErrorCode.ERROR_PAY_PARAMETER.getMsg(), ErrorCode.ERROR_PAY_PARAMETER.getCode());
            }
        } catch (BusinessException e) {
            log.error("招行支付数据异常：code:{},message:{}", e.getCode(), e.getMessage());
            return ResultBuilder.failTSingle(e.getCode(), e.getMessage());
        } catch (Exception e) {
            log.error("支付异常,e:{}", e.getMessage());
            return ResultBuilder.failTSingle(ErrorCode.ERROR_PAY.getCode(), ErrorCode.ERROR_PAY.getMsg());
        }
    }

    @Override
    public TSingleResult<Boolean> checkSign(GenericRequest<PayCheckSignParam> request) {
        try {
            PayCheckSignParam param = request.getParam();
            if (param == null) {
                log.error("招行支付回调参数异常,request:{}", JSONObject.toJSONString(request));
                throw new BusinessException(ErrorCode.ERROR_PAY_PARAMETER.getMsg(), ErrorCode.ERROR_PAY_PARAMETER.getCode());
            }
            CmbPayResponse response = JSONObject.toJavaObject(JSONObject.parseObject(param.getResponseStr()), CmbPayResponse.class);
            if (response != null) {
                Order order = orderQueryServiceImpl.findOneByOrderNo(response.getNoticeData().getOrderNo());
                if (order != null) {
                    CmbConfigure configure = enterpriseConfigService.getCmbConfigure(order.getEnterpriseId());
                    cmbPayService.checkSign(param.getResponseStr(), configure);
                    order.setTransactionFlowNo(response.getNoticeData().getBankSerialNo());
                    return paymentCallbackService.paid(order, PayTypeEnum.CMB_PAY, param.getResponseStr());
                } else {
                    log.error("没找到订单，orderNo:{}", response.getNoticeData().getOrderNo());
                    throw new BusinessException(ErrorCode.ERROR_PAY_FAIL.getMsg(), ErrorCode.ERROR_PAY_FAIL.getCode());
                }
            } else {
                log.error("订单支付失败");
                throw new BusinessException(ErrorCode.ERROR_PAY_FAIL.getMsg(), ErrorCode.ERROR_PAY_FAIL.getCode());
            }
        } catch (BusinessException e) {
            log.error("支付数据异常：code:{},message:{}", e.getCode(), e.getMessage());
            return ResultBuilder.failTSingle(e.getCode(), e.getMessage());
        } catch (Exception e) {
            log.error("支付回调异常,e:{}", e.getMessage());
            return ResultBuilder.failTSingle(ErrorCode.ERROR_PAY.getCode(), ErrorCode.ERROR_PAY.getMsg());
        }
    }

}
