package com.mtl.cypw.order.service.impl;

import com.mtl.cypw.common.utils.JsonUtils;
import com.mtl.cypw.domain.order.enums.ConsumeStatusEnum;
import com.mtl.cypw.domain.order.enums.HandleStatusEnum;
import com.mtl.cypw.order.exception.lock.LockInventoryException;
import com.mtl.cypw.order.mapper.OrderInventoryConsumeRecordMapper;
import com.mtl.cypw.order.model.Order;
import com.mtl.cypw.order.model.OrderInventoryConsumeRecord;
import com.mtl.cypw.order.service.OrderInventoryConsumeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

/**
 * @author Johnathon.Yuan
 * @date 2019-11-28 10:27
 */
@Slf4j
@Service
public class OrderInventoryConsumeServiceImpl implements OrderInventoryConsumeService {

    @Autowired
    private OrderInventoryConsumeRecordMapper orderInventoryConsumeRecordMapper;

    @Override
    public OrderInventoryConsumeRecord update(OrderInventoryConsumeRecord consumeRecord) {
        consumeRecord.setVersion(consumeRecord.getVersion() + 1);
        orderInventoryConsumeRecordMapper.updateByPrimaryKeySelective(consumeRecord);
        return consumeRecord;
    }

    @Override
    public OrderInventoryConsumeRecord insert(OrderInventoryConsumeRecord consumeRecord) {
        consumeRecord.setVersion(1);
        orderInventoryConsumeRecordMapper.insertSelective(consumeRecord);
        return consumeRecord;
    }


    @Override
    public OrderInventoryConsumeRecord findOne(Integer recordId) {
        return orderInventoryConsumeRecordMapper.selectByPrimaryKey(recordId);
    }

    @Override
    public OrderInventoryConsumeRecord findOneByOrderId(Integer orderId) {
        Example example = new Example(OrderInventoryConsumeRecord.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("orderId", orderId);
        return orderInventoryConsumeRecordMapper.selectOneByExample(example);
    }

    @Override
    public Order orderInventory(Order order) {
        if (order == null
                || order.getId() == null
                || order.getInventoryConsumeRecord() == null
                || order.getInventoryConsumeRecord().brandNew()) {
            log.error("Order inventory error, order = {}", JsonUtils.toJson(order));
            throw new LockInventoryException("消耗订单库存参数错误");
        }
        OrderInventoryConsumeRecord inventoryConsumeRecord = order.getInventoryConsumeRecord();
        OrderInventoryConsumeRecord selfConsumeRecord = findOne(inventoryConsumeRecord.getId());
        if (selfConsumeRecord.getConsumeStatus() == ConsumeStatusEnum.ALREADY_ORDER.getCode()
                && order.getId().equals(selfConsumeRecord.getOrderId())) {
            inventoryConsumeRecord.setOrderId(order.getId());
            return order;
        }
        if (selfConsumeRecord.getConsumeStatus() != ConsumeStatusEnum.ALREADY_LOCKED.getCode()) {
            log.error("Order inventory consume status error, order = {}", JsonUtils.toJson(order));
            throw new LockInventoryException("消耗订单库存状态错误");
        }
        try {
            selfConsumeRecord.setOrderId(order.getId());
            selfConsumeRecord.setConsumeStatus(ConsumeStatusEnum.ALREADY_ORDER.getCode());
            if (order.isIncludeSeat()) {
                selfConsumeRecord.setHandleStatus(HandleStatusEnum.WAIT_HANDLE.getCode());
            }
            orderInventoryConsumeRecordMapper.updateByPrimaryKeySelective(selfConsumeRecord);
        } catch (Exception e) {
            log.error("Order inventory consume save error, order = {}", JsonUtils.toJson(order), e);
            throw new LockInventoryException("消耗订单库存参数错误");
        }
        inventoryConsumeRecord.setOrderId(order.getId());
        return order;
    }
}
