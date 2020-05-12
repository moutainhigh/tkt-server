package com.mtl.cypw.mpm.service;

import com.mtl.cypw.domain.payment.enums.PayTypeEnum;
import com.mtl.cypw.mpm.model.EnterprisePayType;

import java.util.List;

/**
 * @author tang.
 * @date 2019/12/3.
 */
public interface EnterprisePayTypeService {
    List<EnterprisePayType> getEnterprisePayType(Integer enterpriseId);

    EnterprisePayType getEnterprisePayType(Integer enterpriseId, PayTypeEnum payTypeEnum);
}
