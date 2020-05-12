package com.mtl.cypw.provider.order.converter;

import com.juqitech.converter.DataConverter;
import com.mtl.cypw.domain.order.dto.OrderTransactionSnapshotDTO;
import com.mtl.cypw.order.model.OrderSnapshot;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author Johnathon.Yuan
 * @date 2019-11-25 13:55
 */
@Slf4j
@Component
public class OrderSnapshotConverter extends DataConverter<OrderSnapshot, OrderTransactionSnapshotDTO> {

    @Override
    public OrderTransactionSnapshotDTO convert(OrderSnapshot object) {
        if (object == null) {
            return null;
        }
        OrderTransactionSnapshotDTO dto = new OrderTransactionSnapshotDTO();
        dto.setOrderId(object.getOrderId());
        dto.setOrderSnapshotId(object.getId());
        dto.setTheatreName(object.getTheatreName());
        dto.setTheatreAddress(object.getTheatreAddress());
        dto.setVenueId(object.getVenueId());
        dto.setVenueName(object.getVenueName());
        dto.setProjectId(object.getProductId());
        dto.setProjectName(object.getProductName());
        dto.setEventId(object.getEventId());
        dto.setEventName(object.getEventName());
        dto.setShowName(object.getShowName());
        dto.setDetailPosterUrl(object.getDetailPosterUrl());
        dto.setListPosterUrl(object.getListPosterUrl());
        dto.setEnterpriseId(object.getEnterpriseId());
        dto.setProgramNotice(object.getProgramNotice());
        dto.setCreateTime(object.getCreateTime());
        return dto;
    }

}
