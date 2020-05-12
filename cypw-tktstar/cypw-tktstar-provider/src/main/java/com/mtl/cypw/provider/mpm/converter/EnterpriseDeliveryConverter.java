package com.mtl.cypw.provider.mpm.converter;

import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.mtl.cypw.domain.mpm.dto.EnterpriseDeliveryDTO;
import com.mtl.cypw.domain.order.enums.DeliverTypeEnum;
import com.mtl.cypw.mpm.model.EnterpriseTemplate;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

/**
 * @author tang.
 * @date 2019/12/20.
 */
@Component
public class EnterpriseDeliveryConverter {

    public List<EnterpriseDeliveryDTO> toDto(EnterpriseTemplate pojo) {
        if (pojo == null || StringUtils.isBlank(pojo.getMerchantDeliveryTypes())) {
            return Collections.emptyList();
        }
        List<EnterpriseDeliveryDTO> dtoList = Lists.newArrayList();
        List<String> types = Splitter.on(",").splitToList(pojo.getMerchantDeliveryTypes());
        for (String typeId : types) {
            if (StringUtils.isBlank(typeId)) {
                continue;
            }
            int code = Integer.parseInt(typeId);
            EnterpriseDeliveryDTO deliveryDTO = new EnterpriseDeliveryDTO();
            deliveryDTO.setDeliverType(DeliverTypeEnum.getObject(code));
            if (DeliverTypeEnum.EXPRESS.getCode() == code) {
                deliveryDTO.setTips(pojo.getMerchantDeliveryCompany());
                deliveryDTO.setExpressFee(pojo.getMerchantDeliveryFee());
                deliveryDTO.setFreeShippingRestrict(pojo.getMerchantDeliveryRestrict());
            }
            dtoList.add(deliveryDTO);
        }
        return dtoList;
    }
}
