package com.mtl.cypw.payment.service;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.mtl.cypw.common.utils.BeanUtil;
import com.mtl.cypw.common.utils.DateUtil;
import com.mtl.cypw.common.utils.MoneyUtils;
import com.mtl.cypw.common.utils.SignUtil;
import com.mtl.cypw.domain.payment.config.CmbConfigure;
import com.mtl.cypw.domain.payment.param.RefundRequestParam;
import com.mtl.cypw.payment.constant.CmbConstants;
import com.mtl.cypw.payment.request.CmbPayRequest;
import com.mtl.cypw.payment.request.data.CmbRefundReqData;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author tang.
 * @date 2020/2/25.
 */
@Service
@Slf4j
public class CmbRefundService {

    private String signType = "SHA-256";

    @Value("${cmb.url.env}")
    private String cmbUrlEnv;

    @Resource
    private CmbBaseService cmbBaseService;

    public String refund(RefundRequestParam param, CmbConfigure configure) {
        String url = CmbConstants.getRefundUrl(cmbUrlEnv);
        String jsonRequestData = combineRefundData(param, configure);
        String html = cmbBaseService.restPost(url, jsonRequestData);
        return html;
    }

    public String combineRefundData(RefundRequestParam param, CmbConfigure configure) {
        CmbPayRequest request = new CmbPayRequest();
        CmbRefundReqData reqData = new CmbRefundReqData();
        reqData.setDateTime(DateUtil.parseDateToStr(new Date(), DateUtil.DATE_TIME_FORMAT_YYYYMMDDHHMISS));
        reqData.setDate(DateUtil.parseDateToStr(new Date(), DateUtil.DATE_FORMAT_YYYYMMDD));
        reqData.setBranchNo(configure.getBranchNo());
        reqData.setMerchantNo(configure.getMerchantNo());
        reqData.setOrderNo(param.getOrderNo());
        reqData.setAmount(MoneyUtils.getMoneyByCent(param.getRefundAmount()).toString());
        reqData.setRefundSerialNo(param.getOrderRefundNo());
        request.setSignType(signType);
        request.setReqData(reqData);
        request.setSign(SignUtil.getSignByCmb(BeanUtil.converterMap(reqData), configure.getMerchantKey()));
        return JSONObject.toJSONString(request, SerializerFeature.WriteNullStringAsEmpty);
    }

}
