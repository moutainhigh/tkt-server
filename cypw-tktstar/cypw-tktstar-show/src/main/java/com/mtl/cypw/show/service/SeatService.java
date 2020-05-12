package com.mtl.cypw.show.service;

import com.google.common.collect.Lists;
import com.juqitech.service.utils.DateUtils;
import com.mtl.cypw.domain.show.dto.CountDTO;
import com.mtl.cypw.domain.show.enums.SeatReservedOperationEnum;
import com.mtl.cypw.domain.show.enums.SeatStatusEnum;
import com.mtl.cypw.domain.show.param.SeatLockSpec;
import com.mtl.cypw.domain.show.param.SeatQuerySpec;
import com.mtl.cypw.domain.show.param.SeatReservedOperationSpec;
import com.mtl.cypw.domain.show.param.SeatUnLockSpec;
import com.mtl.cypw.domain.stock.enums.SeatSellTypeEnum;
import com.mtl.cypw.show.mapper.EventSeatMapper;
import com.mtl.cypw.show.pojo.EventSeat;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

/**
 * @author Johnathon.Yuan
 * @date 2020-01-06 16:15
 */
@Slf4j
@Service
public class SeatService {

    @Autowired
    private EventSeatMapper eventSeatMapper;

    public List<EventSeat> findBySpec(SeatQuerySpec spec) {
        Example example = new Example(EventSeat.class);
        Example.Criteria criteria = example.createCriteria();
        if (spec.getEventId() != null) {
            criteria.andEqualTo("eventId", spec.getEventId());
        }
        if (spec.getZoneId() != null) {
            criteria.andEqualTo("zoneId", spec.getZoneId());
        }
        if (spec.getLockId() != null) {
            criteria.andEqualTo("lockId", spec.getLockId());
        }
        if (spec.getReserveId() != null) {
            criteria.andEqualTo("reserveId", spec.getReserveId());
        }
        if (CollectionUtils.isNotEmpty(spec.getSeatIds())) {
            criteria.andIn("seatId", spec.getSeatIds());
        }
        if (CollectionUtils.isNotEmpty(spec.getSeatStatusList())) {
            criteria.andIn("seatStatus", spec.getSeatStatusList());
        }
        if (spec.getPriceId() != null) {
            criteria.andEqualTo("priceId", spec.getPriceId());
        }
        return eventSeatMapper.selectByExample(example);
    }

    public int reserveSeatsBySpec(SeatReservedOperationSpec spec) {
        SeatQuerySpec seatQuerySpec = new SeatQuerySpec();
        seatQuerySpec.setEventId(spec.getEventId());
        seatQuerySpec.setSeatIds(Lists.newArrayList(spec.getSeatIds()));
        if (SeatReservedOperationEnum.RESERVE.getCode() == spec.getOperation().getCode()) {
            seatQuerySpec.setSeatStatusList(Lists.newArrayList(SeatStatusEnum.CAN_BE_SOLD.getCode()));
        } else if (SeatReservedOperationEnum.RELEASED.getCode() == spec.getOperation().getCode()) {
            seatQuerySpec.setSeatStatusList(Lists.newArrayList(SeatStatusEnum.RESERVED.getCode()));
        }
        List<EventSeat> seats = this.findBySpec(seatQuerySpec);
        if (CollectionUtils.isEmpty(seats)) {
            return 0;
        }
        Date current = DateUtils.now();
        for (EventSeat seat : seats) {
            if (SeatReservedOperationEnum.RESERVE.getCode() == spec.getOperation().getCode()) {
                seat.setSeatStatus(SeatStatusEnum.RESERVED.getCode());
                seat.setReserveId(spec.getReserveId());
                seat.setReserveDate(current);
            } else if (SeatReservedOperationEnum.RELEASED.getCode() == spec.getOperation().getCode()) {
                seat.setSeatStatus(SeatStatusEnum.CAN_BE_SOLD.getCode());
                seat.setReserveId(0);
                seat.setReserveId(null);
            }
            seat.setUpdateDate(current);
            eventSeatMapper.updateByPrimaryKey(seat);
        }
        return seats.size();
    }

    public int lockSeatsBySpec(SeatLockSpec spec) {
        SeatQuerySpec seatQuerySpec = new SeatQuerySpec();
        /**
         * TODO: 线下出票兼容
         */
        seatQuerySpec.setIsOnline(true);
        if (spec.getEnterpriseId() != null) {

        }
        if (spec.getEventId() != null) {
            seatQuerySpec.setEventId(spec.getEventId());
        }
        if (CollectionUtils.isNotEmpty(spec.getSeatIds())) {
            seatQuerySpec.setSeatIds(spec.getSeatIds());
        }
        if (SeatSellTypeEnum.SALE.getCode() == spec.getSellType().getCode()) {
            seatQuerySpec.setSeatStatusList(Lists.newArrayList(SeatStatusEnum.CAN_BE_SOLD.getCode()));
        } else if (SeatSellTypeEnum.RESERVE.getCode() == spec.getSellType().getCode()) {
            seatQuerySpec.setSeatStatusList(Lists.newArrayList(SeatStatusEnum.RESERVED.getCode()));
        } else {
            seatQuerySpec.setSeatStatusList(Lists.newArrayList(SeatStatusEnum.CAN_BE_SOLD.getCode(), SeatStatusEnum.RESERVED.getCode()));
        }
        List<EventSeat> seats = this.findBySpec(seatQuerySpec);
        if (CollectionUtils.isEmpty(seats)) {
            return 0;
        }
        if (seats.size() != spec.getSeatIds().size()) {
            return 0;
        }
        Date current = DateUtils.now();
        for (EventSeat seat : seats) {
            seat.setSeatStatus(SeatStatusEnum.HAS_BEEN_SOLD.getCode());
            seat.setLockId(spec.getLockId());
            seat.setLockDate(current);
            seat.setUpdateDate(current);
            // 行级锁改造
        }
        eventSeatMapper.bulkUpdateByExampleSelective(seats);
        return seats.size();
    }

    public int unlockSeatsBySpec(SeatUnLockSpec spec) {
        SeatQuerySpec seatQuerySpec = new SeatQuerySpec();
        /**
         * TODO: 线下出票兼容
         */
        seatQuerySpec.setIsOnline(true);
        seatQuerySpec.setLockId(spec.getLockId());
        seatQuerySpec.setSeatStatusList(Lists.newArrayList(SeatStatusEnum.HAS_BEEN_SOLD.getCode()));
        if (spec.getEnterpriseId() != null) {

        }
        List<EventSeat> seats = this.findBySpec(seatQuerySpec);
        if (CollectionUtils.isEmpty(seats)) {
            return 0;
        }
        Date current = DateUtils.now();
        for (EventSeat seat : seats) {
            if (SeatSellTypeEnum.RESERVE.getCode() == spec.getSellType().getCode()) {
                seat.setSeatStatus(SeatStatusEnum.RESERVED.getCode());
            } else {
                seat.setSeatStatus(SeatStatusEnum.CAN_BE_SOLD.getCode());
            }
            seat.setLockId(0);
            seat.setLockDate(null);
            seat.setUpdateDate(current);
            eventSeatMapper.updateByPrimaryKey(seat);
        }
        return seats.size();
    }

    public List<CountDTO> searchSeatCount(List<Integer> priceIds, Integer seatStatus) {
        if (CollectionUtils.isEmpty(priceIds)) {
            return null;
        }
        return searchSeatCount(null, priceIds, seatStatus, null);
    }

    public CountDTO searchSeatCount(Integer priceId, Integer seatStatus) {
        if (priceId == null) {
            return null;
        }
        List<CountDTO> list = searchSeatCount(priceId, null, seatStatus, null);
        if (CollectionUtils.isNotEmpty(list)) {
            return list.get(0);
        }
        return null;
    }

    private List<CountDTO> searchSeatCount(Integer priceId, List<Integer> priceIds, Integer seatStatus, List<Integer> seatStatusList) {
        return eventSeatMapper.searchSeatCount(priceId, priceIds, seatStatus, seatStatusList);
    }
}
