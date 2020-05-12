package com.mtl.cypw.provider.order.converter;

import com.juqitech.converter.DataConverter;
import com.mtl.cypw.common.utils.MoneyUtils;
import com.mtl.cypw.domain.order.dto.OrderRefundDTO;
import com.mtl.cypw.order.model.OrderRefund;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.BooleanUtils;
import org.springframework.stereotype.Component;

/**
 * @author Johnathon.Yuan
 * @date 2019-11-25 13:55
 */
@Slf4j
@Component
public class OrderRefundConverter extends DataConverter<OrderRefund, OrderRefundDTO> {

    @Override
    public OrderRefundDTO convert(OrderRefund object) {
        if (object == null) {
            return null;
        }
        OrderRefundDTO dto = new OrderRefundDTO();
        dto.setId(String.valueOf(object.getId()));
        dto.setRefundId(object.getId());
        dto.setOrderId(object.getOrderId());
        dto.setRefundType(object.getRefundType());
        dto.setRefundStatus(object.getRefundStatus());
        dto.setApplyType(object.getApplyType());
        dto.setDutyType(object.getDutyType());
        dto.setFinishTime(object.getFinishTime());
        dto.setOperatorId(object.getOperatorId());
        dto.setOperatorName(object.getOperatorName());
        dto.setReasonType(object.getReasonType());
        dto.setReasonNote(object.getReasonNote());
        dto.setRefundAmount(MoneyUtils.getMoneyByCent(object.getRefundAmount()));
        dto.setRollbackStock(BooleanUtils.toBooleanObject(object.getRollbackStock()));
        dto.setEnterpriseId(object.getEnterpriseId());
        dto.setCreateTime(object.getCreateTime());
        return dto;
    }

}
