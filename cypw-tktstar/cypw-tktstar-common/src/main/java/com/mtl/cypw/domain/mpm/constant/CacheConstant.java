package com.mtl.cypw.domain.mpm.constant;

/**
 * @author tang.
 * @date 2019/12/16.
 */
public class CacheConstant {

    /**
     * 缓存中验证码的key
     *
     * @param phone
     * @return
     */
    public static String getVerifyCodeKey(String phone) {

        return "verify_code_" + phone;
    }

    public static String getEnterpriseKeyById(Integer enterpriseId) {
        return "ENTERPRISE_INFO_ID_" + enterpriseId;
    }

    public static String getEnterpriseKeyByTenanId(String tenantId) {
        return "ENTERPRISE_INFO_TENANT_ID_" + tenantId;
    }

    public static String getEnterpriseKeyByDomain(String domain) {
        return "ENTERPRISE_INFO_DOMAIN_" + domain;
    }

}
