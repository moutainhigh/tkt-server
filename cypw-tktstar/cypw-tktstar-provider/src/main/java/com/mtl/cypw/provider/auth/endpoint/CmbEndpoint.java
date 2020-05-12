package com.mtl.cypw.provider.auth.endpoint;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.juqitech.request.GenericRequest;
import com.juqitech.response.ResultBuilder;
import com.juqitech.response.TSingleResult;
import com.mtl.cypw.api.auth.endpoint.CmbApi;
import com.mtl.cypw.auth.cmb.CmbService;
import com.mtl.cypw.common.enums.ErrorCode;
import com.mtl.cypw.common.exception.BusinessException;
import com.mtl.cypw.common.utils.Base64Util;
import com.mtl.cypw.common.utils.DesUtil;
import com.mtl.cypw.domain.auth.dto.CmbAuthDTO;
import com.mtl.cypw.domain.auth.param.AuthCheckSignParam;
import com.mtl.cypw.domain.auth.request.CmbRequest;
import com.mtl.cypw.domain.auth.response.CmbAuthResponse;
import com.mtl.cypw.domain.payment.config.CmbConfigure;
import com.mtl.cypw.provider.mpm.service.EnterpriseConfigService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author tang.
 * @date 2020/1/15.
 */
@RestController
@Slf4j
public class CmbEndpoint implements CmbApi {

    @Resource
    CmbService cmbService;

    @Resource
    EnterpriseConfigService enterpriseConfigService;

    @Override
    public TSingleResult<CmbAuthResponse> auth(GenericRequest<CmbRequest> request) {
        CmbRequest param = request.getParam();
        if (param == null || param.getEnterpriseId() == null) {
            log.error("招行授权参数异常,request:{}", JSONObject.toJSONString(param));
            return ResultBuilder.failTSingle(ErrorCode.ERROR_COMMON_PARAMETER.getCode(), ErrorCode.ERROR_COMMON_PARAMETER.getMsg());
        }
        CmbConfigure configure = enterpriseConfigService.getCmbConfigure(param.getEnterpriseId());
        CmbAuthResponse response = cmbService.auth(configure);
        return ResultBuilder.succTSingle(response);
    }

    @Override
    public TSingleResult<CmbAuthDTO> checkSign(GenericRequest<AuthCheckSignParam> request) {
        try {
            AuthCheckSignParam param = request.getParam();
            if (param == null || !param.checkParam()) {
                log.error("招行授权验签参数异常,request:{}", JSONObject.toJSONString(request));
                throw new BusinessException(ErrorCode.ERROR_PAY_PARAMETER.getMsg(), ErrorCode.ERROR_PAY_PARAMETER.getCode());
            }
            CmbConfigure configure = enterpriseConfigService.getCmbConfigure(param.getEnterpriseId());
            if (configure == null || StringUtils.isEmpty(configure.getSecretKey())) {
                log.error("商户配置错误 enterpriseId:{}", param.getEnterpriseId());
                throw new BusinessException(ErrorCode.ERROR_COMMON_ENTERPRISE_CONFIG.getMsg(), ErrorCode.ERROR_COMMON_ENTERPRISE_CONFIG.getCode());
            }
            CmbAuthDTO dto = getCmdAuthDTO(param.getResponseStr(), configure.getSecretKey());
            String data = Base64Util.encode(JSONObject.toJSONString(dto.getData())) + "&signature=" + dto.getVerify();
            Boolean b = cmbService.verifySignature(data);
            if (b) {
                log.debug("验签成功");
                return ResultBuilder.succTSingle(dto);
            } else {
                log.error("授权登录验签失败");
                return ResultBuilder.succTSingle(null);
            }
        } catch (BusinessException e) {
            return ResultBuilder.failTSingle(e.getCode(), e.getMessage());
        } catch (Exception e) {
            log.error("授权登录异常,e:{}", e.getMessage());
            return ResultBuilder.failTSingle(ErrorCode.ERROR_PAY.getCode(), ErrorCode.ERROR_PAY.getMsg());
        }
    }

    public CmbAuthDTO getCmdAuthDTO(String encodeParam, String cmbDesKey) {
        String data = DesUtil.decrypt(cmbDesKey, Base64Util.decode(encodeParam));
        CmbAuthDTO dto = (new Gson()).fromJson(data, CmbAuthDTO.class);
        return dto;
    }
}
