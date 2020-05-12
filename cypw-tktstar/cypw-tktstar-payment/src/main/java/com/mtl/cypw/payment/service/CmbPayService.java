package com.mtl.cypw.payment.service;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.mtl.cypw.common.enums.ErrorCode;
import com.mtl.cypw.common.exception.BusinessException;
import com.mtl.cypw.common.utils.BeanUtil;
import com.mtl.cypw.common.utils.DateUtil;
import com.mtl.cypw.common.utils.MoneyUtils;
import com.mtl.cypw.common.utils.SignUtil;
import com.mtl.cypw.domain.payment.config.CmbConfigure;
import com.mtl.cypw.domain.payment.param.CmbPayRequestParam;
import com.mtl.cypw.payment.constant.CmbConstants;
import com.mtl.cypw.payment.request.CmbPayRequest;
import com.mtl.cypw.payment.request.data.CmbAppPayReqData;
import com.mtl.cypw.payment.request.data.CmbH5PayReqData;
import com.mtl.cypw.payment.request.data.CmbSearchOrderData;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author tang.
 * @date 2019/12/30.
 */
@Service
@Slf4j
public class CmbPayService {

    @Resource
    private CmbBaseService cmbBaseService;

    @Value("${cmb.url.env}")
    private String cmbUrlEnv;

    public String h5Pay(CmbPayRequestParam param, CmbConfigure configure) {
        String url = CmbConstants.getGatewayUrl_H5(cmbUrlEnv);
        String jsonRequestData = combineH5PayData(param, configure);
        String html = cmbBaseService.restPost(url, jsonRequestData);
        Document doc = Jsoup.parse(html);
        Elements body = doc.select("form");
        return body.toString();
    }

    public Boolean checkSign(String result, CmbConfigure configure) {
        try {
            if (SignUtil.checkCmbSign(result, cmbBaseService.getPubKey(configure))) {
                log.info("验签成功");
                return true;
            } else {
                throw new BusinessException(ErrorCode.ERROR_PAY_SIGN.getMsg(), ErrorCode.ERROR_PAY_SIGN.getCode());
            }
        } catch (Exception e) {
            log.error("招行支付回调异常，{}", e.getMessage());
            throw new BusinessException(ErrorCode.ERROR_PAY_SIGN.getMsg(), ErrorCode.ERROR_PAY_SIGN.getCode());
        }
    }

    public String combineH5PayData(CmbPayRequestParam param, CmbConfigure configure) {
        CmbPayRequest request = new CmbPayRequest();
        CmbH5PayReqData reqData = new CmbH5PayReqData();
        reqData.setDateTime(DateUtil.parseDateToStr(new Date(), DateUtil.DATE_TIME_FORMAT_YYYYMMDDHHMISS));
        reqData.setDate(DateUtil.parseDateToStr(new Date(), DateUtil.DATE_FORMAT_YYYYMMDD));
        reqData.setBranchNo(configure.getBranchNo());
        reqData.setMerchantNo(configure.getMerchantNo());
        reqData.setOrderNo(param.getOrderNo());
        reqData.setAmount(MoneyUtils.getMoneyByCent(param.getOrderAmount()).toString());
        reqData.setExpireTimeSpan(param.getExpireTimeSpan());
        reqData.setPayNoticeUrl(param.getNotifyUrl());
        reqData.setReturnUrl(param.getQuitUrl());
        request.setSignType(param.getSignType());
        request.setReqData(reqData);
        request.setSign(SignUtil.getSignByCmb(BeanUtil.converterMap(reqData), configure.getMerchantKey()));
        return JSONObject.toJSONString(request, SerializerFeature.WriteNullStringAsEmpty);
    }

    public String combineAppPayData(CmbPayRequestParam param, CmbConfigure configure) {
        CmbPayRequest request = new CmbPayRequest();
        CmbAppPayReqData reqData = new CmbAppPayReqData();
        reqData.setDateTime(DateUtil.parseDateToStr(new Date(), DateUtil.DATE_TIME_FORMAT_YYYYMMDDHHMISS));
        reqData.setDate(DateUtil.parseDateToStr(new Date(), DateUtil.DATE_FORMAT_YYYYMMDD));
        reqData.setBranchNo(configure.getBranchNo());
        reqData.setMerchantNo(configure.getMerchantNo());
        reqData.setOrderNo(param.getOrderNo());
        reqData.setAmount(MoneyUtils.getMoneyByCent(param.getOrderAmount()).toString());
        reqData.setExpireTimeSpan(param.getExpireTimeSpan());
        reqData.setPayNoticeUrl(param.getNotifyUrl());
        reqData.setReturnUrl(param.getQuitUrl());
        request.setSignType(param.getSignType());
        request.setReqData(reqData);
        request.setSign(SignUtil.getSignByCmb(BeanUtil.converterMap(reqData), configure.getMerchantKey()));
        return JSONObject.toJSONString(request, SerializerFeature.WriteNullStringAsEmpty);
    }

    public String getOrder(CmbPayRequestParam param, CmbConfigure configure) {
        String url = CmbConstants.getOrderUrl(cmbUrlEnv);
        String jsonRequestData = getOrderData(param, configure);
        String body = cmbBaseService.restPost(url, jsonRequestData);
        return body;
    }

    private String getOrderData(CmbPayRequestParam param, CmbConfigure configure) {
        CmbPayRequest request = new CmbPayRequest();
        CmbSearchOrderData reqData = new CmbSearchOrderData();
        reqData.setDateTime(DateUtil.parseDateToStr(new Date(), DateUtil.DATE_TIME_FORMAT_YYYYMMDDHHMISS));
        reqData.setDate(DateUtil.parseDateToStr(new Date(), DateUtil.DATE_FORMAT_YYYYMMDD));
        reqData.setBranchNo(configure.getBranchNo());
        reqData.setMerchantNo(configure.getMerchantNo());
        reqData.setOrderNo(param.getOrderNo());
        reqData.setType("B");
        request.setSignType(param.getSignType());
        request.setReqData(reqData);
        request.setSign(SignUtil.getSignByCmb(BeanUtil.converterMap(reqData), configure.getMerchantKey()));
        return JSONObject.toJSONString(request, SerializerFeature.WriteNullStringAsEmpty);
    }

}
