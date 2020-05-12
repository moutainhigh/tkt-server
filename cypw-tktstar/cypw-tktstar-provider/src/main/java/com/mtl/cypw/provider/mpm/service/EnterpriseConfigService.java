package com.mtl.cypw.provider.mpm.service;

import com.google.gson.Gson;
import com.mtl.cypw.common.enums.ErrorCode;
import com.mtl.cypw.common.exception.BusinessException;
import com.mtl.cypw.domain.payment.config.*;
import com.mtl.cypw.domain.payment.enums.PayTypeEnum;
import com.mtl.cypw.mpm.model.EnterprisePayType;
import com.mtl.cypw.mpm.service.EnterprisePayTypeService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author tang.
 * @date 2020/1/15.
 */
@Service
@Slf4j
public class EnterpriseConfigService {

    @Resource
    EnterprisePayTypeService enterprisePayTypeServiceImpl;

    public WxConfigure getWxConfigure(Integer enterpriseId) throws BusinessException {
        if (enterpriseId == null) {
            throw new BusinessException(ErrorCode.ERROR_COMMON_PARAMETER.getMsg(), ErrorCode.ERROR_COMMON_PARAMETER.getCode());
        }
        EnterprisePayType enterprisePayType = enterprisePayTypeServiceImpl.getEnterprisePayType(enterpriseId, PayTypeEnum.WECHAT_PAY);
        if (enterprisePayType != null && StringUtils.isNotEmpty(enterprisePayType.getConfigJson())) {
            WxConfigure configure = (new Gson()).fromJson(enterprisePayType.getConfigJson(), WxConfigure.class);
            if (configure.checkParam()) {
                return configure;
            }
        }
        log.error("微信支付，商户配置错误,enterpriseId:{}", enterpriseId);
        throw new BusinessException(ErrorCode.ERROR_PAY_CONFIG.getMsg(), ErrorCode.ERROR_PAY_CONFIG.getCode());
    }

    public CmbConfigure getCmbConfigure(Integer enterpriseId) throws BusinessException {
        if (enterpriseId == null) {
            throw new BusinessException(ErrorCode.ERROR_COMMON_PARAMETER.getMsg(), ErrorCode.ERROR_COMMON_PARAMETER.getCode());
        }
        EnterprisePayType enterprisePayType = enterprisePayTypeServiceImpl.getEnterprisePayType(enterpriseId, PayTypeEnum.CMB_PAY);
        if (enterprisePayType != null && StringUtils.isNotEmpty(enterprisePayType.getConfigJson())) {
            CmbConfigure configure = (new Gson()).fromJson(enterprisePayType.getConfigJson(), CmbConfigure.class);
            if (configure.checkParam()) {
                return configure;
            }
        }
        log.error("招行支付，商户配置错误,enterpriseId:{}", enterpriseId);
        throw new BusinessException(ErrorCode.ERROR_PAY_CONFIG.getMsg(), ErrorCode.ERROR_PAY_CONFIG.getCode());
    }

    public CcbConfigure getCcbConfigure(Integer enterpriseId) throws BusinessException {
        if (enterpriseId == null) {
            throw new BusinessException(ErrorCode.ERROR_COMMON_PARAMETER.getMsg(), ErrorCode.ERROR_COMMON_PARAMETER.getCode());
        }
        EnterprisePayType enterprisePayType = enterprisePayTypeServiceImpl.getEnterprisePayType(enterpriseId, PayTypeEnum.CCB_PAY);
        if (enterprisePayType != null && StringUtils.isNotEmpty(enterprisePayType.getConfigJson())) {
            CcbConfigure configure = (new Gson()).fromJson(enterprisePayType.getConfigJson(), CcbConfigure.class);
            if (configure.checkParam()) {
                return configure;
            }
        }
        log.error("建行支付，商户配置错误,enterpriseId:{}", enterpriseId);
        throw new BusinessException(ErrorCode.ERROR_PAY_CONFIG.getMsg(), ErrorCode.ERROR_PAY_CONFIG.getCode());
    }

    public PayPalConfigure getPayPalConfigure(Integer enterpriseId) throws BusinessException {
        if (enterpriseId == null) {
            throw new BusinessException(ErrorCode.ERROR_COMMON_PARAMETER.getMsg(), ErrorCode.ERROR_COMMON_PARAMETER.getCode());
        }
        EnterprisePayType enterprisePayType = enterprisePayTypeServiceImpl.getEnterprisePayType(enterpriseId, PayTypeEnum.PAY_PAL);
        if (enterprisePayType != null && StringUtils.isNotEmpty(enterprisePayType.getConfigJson())) {
            PayPalConfigure configure = (new Gson()).fromJson(enterprisePayType.getConfigJson(), PayPalConfigure.class);
            if (configure.checkParam()) {
                return configure;
            }
        }
        log.error("PayPal支付，商户配置错误,enterpriseId:{}", enterpriseId);
        throw new BusinessException(ErrorCode.ERROR_PAY_CONFIG.getMsg(), ErrorCode.ERROR_PAY_CONFIG.getCode());
    }

    public AliConfigure getAliConfigure(Integer enterpriseId) throws BusinessException {
        if (enterpriseId == null) {
            throw new BusinessException(ErrorCode.ERROR_COMMON_PARAMETER.getMsg(), ErrorCode.ERROR_COMMON_PARAMETER.getCode());
        }
        EnterprisePayType enterprisePayType = enterprisePayTypeServiceImpl.getEnterprisePayType(enterpriseId, PayTypeEnum.ALI_PAY);
        if (enterprisePayType != null && StringUtils.isNotEmpty(enterprisePayType.getConfigJson())) {
            AliConfigure configure = (new Gson()).fromJson(enterprisePayType.getConfigJson(), AliConfigure.class);
            if (configure.checkParam()) {
                return configure;
            }
        }
        log.error("Ali支付，商户配置错误,enterpriseId:{}", enterpriseId);
        throw new BusinessException(ErrorCode.ERROR_PAY_CONFIG.getMsg(), ErrorCode.ERROR_PAY_CONFIG.getCode());
    }
}
