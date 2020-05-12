package com.mtl.cypw.domain.mpm.param;

import com.juqitech.request.BaseParam;
import lombok.Data;

/**
 * @author tang.
 * @date 2019/11/26.
 */
@Data
public class EnterpriseQueryParam extends BaseParam {

    private Integer enterpriseId;

    private String tenantId;

    private String domainName;

    private String appId;

    /**
     * 企业缩写
     */
    private String enterpriseAbbr;

    @Override
    public void checkParam() {

    }
}
