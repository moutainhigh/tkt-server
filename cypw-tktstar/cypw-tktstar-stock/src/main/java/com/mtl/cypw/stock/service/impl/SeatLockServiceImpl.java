package com.mtl.cypw.stock.service.impl;

import com.google.common.collect.Lists;
import com.mtl.cypw.common.enums.ErrorCode;
import com.mtl.cypw.common.utils.JsonUtils;
import com.mtl.cypw.domain.show.param.SeatLockSpec;
import com.mtl.cypw.domain.show.param.SeatUnLockSpec;
import com.mtl.cypw.domain.stock.enums.SeatRecordStatusEnum;
import com.mtl.cypw.domain.stock.param.SeatLockParam;
import com.mtl.cypw.domain.stock.param.SeatReleaseParam;
import com.mtl.cypw.show.service.SeatService;
import com.mtl.cypw.stock.exception.StockSeatException;
import com.mtl.cypw.stock.model.StockSeatRecord;
import com.mtl.cypw.stock.repository.StockSeatRecordRepository;
import com.mtl.cypw.stock.service.SeatLockService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Johnathon.Yuan
 * @date 2020-01-03 20:06
 */
@Slf4j
@Component
public class SeatLockServiceImpl implements SeatLockService {

    @Autowired
    private StockSeatRecordRepository stockSeatRecordRepository;

    @Autowired
    private SeatService seatService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean lockSeats(SeatLockParam request) {
        StockSeatRecord seatRecord = buildSeatRecord(request);
        stockSeatRecordRepository.save(seatRecord);
        SeatLockSpec spec = SeatLockSpec.builder()
                .sellType(request.getSellType())
                .eventId(request.getEventId())
                .lockId(seatRecord.getId())
                .seatIds(Lists.newArrayList(request.getSeatIds()))
                .enterpriseId(request.getEnterpriseId())
                .build();
        int lockSeatTotal = seatService.lockSeatsBySpec(spec);
        if (lockSeatTotal != request.getSeatIds().size()) {
            log.error("锁定座位数量不一致, request = {}", JsonUtils.toJson(request));
            throw new StockSeatException("锁定座位数量不一致",  ErrorCode.ERROR_ORDER_LOCK_SEAT_CAN_NOT_LOCKED.getCode());
        }
        return true;
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean unlockSeats(SeatReleaseParam request) {
        StockSeatRecord seatRecord = stockSeatRecordRepository.findOneByOrderId(request.getOrderId());
        if (seatRecord == null) {
            return false;
        }
        if (SeatRecordStatusEnum.ALREADY_RELEASED.getCode() == seatRecord.getStatus()) {
            return false;
        }
        SeatUnLockSpec spec = SeatUnLockSpec.builder()
                .lockId(seatRecord.getId())
                .enterpriseId(request.getEnterpriseId())
                .sellType(request.getSellType())
                .build();
        int unlockSeatTotal = seatService.unlockSeatsBySpec(spec);
        if (unlockSeatTotal == 0) {
            log.error("释放座位失败, request = {}", JsonUtils.toJson(request));
            throw new StockSeatException("释放座位失败",  ErrorCode.ERROR_ORDER_LOCK_SEAT_CAN_NOT_LOCKED.getCode());
        }
        return true;
    }

    private StockSeatRecord buildSeatRecord(SeatLockParam request) {
        StockSeatRecord seatRecord = new StockSeatRecord();
        seatRecord.setStatus(SeatRecordStatusEnum.ALREADY_ORDER.getCode());
        seatRecord.setMemberId(request.getMemberId());
        seatRecord.setEventId(request.getEventId());
        seatRecord.setOrderId(request.getOrderId());
        seatRecord.setEnterpriseId(request.getEnterpriseId());
        if (CollectionUtils.isNotEmpty(request.getSeatIds())) {
            seatRecord.setSeatInfo(JsonUtils.toJson(request.getSeatIds()));
        }
        return seatRecord;
    }
}
