package com.mtl.cypw.provider.order.converter;

import com.juqitech.converter.DataConverter;
import com.mtl.cypw.common.utils.MoneyUtils;
import com.mtl.cypw.domain.order.dto.OrderDeliveryDTO;
import com.mtl.cypw.domain.order.enums.DeliverStatusEnum;
import com.mtl.cypw.domain.order.enums.DeliverTypeEnum;
import com.mtl.cypw.domain.order.enums.IdentityTypeEnum;
import com.mtl.cypw.domain.ticket.enums.FetchStatusEnum;
import com.mtl.cypw.order.model.OrderDelivery;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.BooleanUtils;
import org.springframework.stereotype.Component;

/**
 * @author Johnathon.Yuan
 * @date 2019-11-25 13:55
 */
@Slf4j
@Component
public class OrderDeliveryConverter extends DataConverter<OrderDelivery, OrderDeliveryDTO> {

    @Override
    public OrderDeliveryDTO convert(OrderDelivery object) {
        if (object == null) {
            return null;
        }
        OrderDeliveryDTO dto = new OrderDeliveryDTO();
        dto.setId(String.valueOf(object.getId()));
        dto.setDeliveryId(object.getId());
        dto.setOrderId(object.getOrderId());
        dto.setDeliverType(DeliverTypeEnum.getObject(object.getDeliverType()));
        dto.setDeliveryStatus(DeliverStatusEnum.getObject(object.getDeliveryStatus()));
        dto.setAddresseeName(object.getAddresseeName());
        dto.setAddresseeMobile(object.getAddresseeMobile());
        dto.setNeedIdcard(BooleanUtils.toBooleanObject(object.getNeedIdcard()));
        dto.setIdcardType(IdentityTypeEnum.getObject(object.getIdcardType()));
        dto.setAddresseeIdcard(object.getAddresseeIdcard());
        dto.setDeliverTime(object.getDeliverTime());
        dto.setDeliveredTime(object.getDeliveredTime());
        dto.setLocaleContact(object.getLocaleContact());
        dto.setLocaleAddress(object.getLocaleAddress());
        dto.setExpressNo(object.getExpressNo());
        dto.setExpressFee(MoneyUtils.getMoneyByCent(object.getExpressFee()));
        dto.setExpressCompany(object.getExpressCompany());
        dto.setProvinceName(object.getProvinceName());
        dto.setCityName(object.getCityName());
        dto.setDistrictName(object.getDistrictName());
        dto.setDetailedAddress(object.getDetailedAddress());
        dto.setPostCode(object.getPostCode());
        dto.setFetchStatus(FetchStatusEnum.getObject(object.getFetchStatus()));
        dto.setFetchedTime(object.getFetchedTime());
        dto.setEnterpriseId(object.getEnterpriseId());
        return dto;
    }

}
