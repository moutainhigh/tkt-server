package com.mtl.cypw.payment.service;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.mtl.cypw.common.utils.BeanUtil;
import com.mtl.cypw.common.utils.DateUtil;
import com.mtl.cypw.common.utils.SignUtil;
import com.mtl.cypw.domain.payment.config.CmbConfigure;
import com.mtl.cypw.payment.constant.CmbConstants;
import com.mtl.cypw.payment.request.CmbPayRequest;
import com.mtl.cypw.payment.request.data.CmbPubKeyData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.Map;

/**
 * @author tang.
 * @date 2020/2/25.
 */
@Service
@Slf4j
public class CmbBaseService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${cmb.url.env}")
    private String cmbUrlEnv;

    public String getPubKey(CmbConfigure configure) {
        String url = CmbConstants.getPubKeyUrl(cmbUrlEnv);
        String jsonRequestData = combinePubKey(configure);
        String result = restPost(url, jsonRequestData);
        Map map = JSONObject.parseObject(result);
        if (map == null) {
            log.error("获取公钥失败");
        }
        Map rspDataMap = JSONObject.parseObject(map.get("rspData") + "");
        if (rspDataMap == null) {
            log.error("获取公钥失败");
        }
        return rspDataMap.get("fbPubKey") + "";
    }

    private String combinePubKey(CmbConfigure configure) {
        CmbPayRequest request = new CmbPayRequest();
        CmbPubKeyData reqData = new CmbPubKeyData();
        reqData.setDateTime(DateUtil.parseDateToStr(new Date(), DateUtil.DATE_TIME_FORMAT_YYYYMMDDHHMISS));
        reqData.setTxCode("FBPK");
        reqData.setBranchNo(configure.getBranchNo());
        reqData.setMerchantNo(configure.getMerchantNo());
        request.setSignType("SHA-256");
        request.setReqData(reqData);
        request.setSign(SignUtil.getSignByCmb(BeanUtil.converterMap(reqData), configure.getMerchantKey()));
        return JSONObject.toJSONString(request, SerializerFeature.WriteNullStringAsEmpty);
    }


    public String restPost(String url, String jsonRequestData) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, Object> map = new LinkedMultiValueMap();
        map.add("charset", "UTF-8");
        map.add("jsonRequestData", jsonRequestData);
        HttpEntity<MultiValueMap<String, Object>> request = new HttpEntity(map, headers);
        ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);
        String body = response.getBody();
        return body.toString();
    }
}
