package com.mtl.cypw.stock.service.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.juqitech.response.paging.Pagination;
import com.mtl.cypw.common.enums.CommonStateEnum;
import com.mtl.cypw.common.enums.ErrorCode;
import com.mtl.cypw.common.utils.Money;
import com.mtl.cypw.domain.show.enums.SeatReservedOperationEnum;
import com.mtl.cypw.domain.show.enums.SeatStatusEnum;
import com.mtl.cypw.domain.show.param.SeatQuerySpec;
import com.mtl.cypw.domain.show.param.SeatReservedOperationSpec;
import com.mtl.cypw.domain.stock.enums.ReserveSeatStatusEnum;
import com.mtl.cypw.domain.stock.param.ReserveFormParam;
import com.mtl.cypw.domain.stock.param.ReserveQuerySpec;
import com.mtl.cypw.domain.stock.param.ReserveSeatQuerySpec;
import com.mtl.cypw.mpm.model.Zone;
import com.mtl.cypw.show.pojo.EventPrice;
import com.mtl.cypw.show.pojo.EventSeat;
import com.mtl.cypw.show.service.EventPriceService;
import com.mtl.cypw.show.service.EventService;
import com.mtl.cypw.show.service.SeatService;
import com.mtl.cypw.stock.exception.ReserveSeatException;
import com.mtl.cypw.stock.model.ReserveForm;
import com.mtl.cypw.stock.model.ReserveSeatDetail;
import com.mtl.cypw.stock.repository.ReserveFormRepository;
import com.mtl.cypw.stock.service.ReserveService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Johnathon.Yuan
 * @date 2020-01-19 19:50
 */
@Slf4j
@Component
public class ReserveServiceImpl implements ReserveService {

    @Autowired
    private SeatService seatService;

    @Autowired
    private EventService eventService;

    @Autowired
    private EventPriceService eventPriceService;

    @Autowired
    private ReserveFormRepository reserveFormRepository;

    @Override
    public Integer saveReserveForm(ReserveFormParam param) {
        ReserveForm reserve = this.findOne(param.getReservedId());
        if (reserve == null) {
            reserve = new ReserveForm();
        }
        reserve.setId(param.getReservedId());
        reserve.setProgramId(param.getProgramId());
        reserve.setEventId(param.getEventId());
        reserve.setReservedStatus(param.getReservedStatus());
        reserve.setAutoRelease(param.getAutoRelease());
        reserve.setReleaseTime(param.getReleaseTime());
        reserve.setEnterpriseId(param.getEnterpriseId());
        reserve.setReservedName(param.getReservedName());
        reserve.setReservedNo(param.getReservedNo());
        reserve.setReservedTime(param.getReservedTime());
        if (StringUtils.isNotBlank(param.getTargetMobile())) {
            reserve.setTargetMobile(param.getTargetMobile());
        }
        if (StringUtils.isNotBlank(param.getTargetName())) {
            reserve.setTargetName(param.getTargetName());
        }
        if (param.getMemberId() != null) {
            reserve.setMemberId(param.getMemberId());
        }
        reserve.setRemark(param.getRemark());
        return reserveFormRepository.save(reserve);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean reserveSeats(Integer reserveId, List<Integer> seatIds) {
        ReserveForm reserveForm = this.findOne(reserveId);
        if (reserveForm == null || CollectionUtils.isEmpty(seatIds)) {
            return false;
        }
        SeatQuerySpec spec = new SeatQuerySpec();
        spec.setEventId(reserveForm.getEventId());
        spec.setSeatIds(seatIds);
        spec.setSeatStatusList(Lists.newArrayList(SeatStatusEnum.CAN_BE_SOLD.getCode()));
        List<EventSeat> seats = seatService.findBySpec(spec);

        List<Zone> zones = eventService.findTemplateZonesByEventId(reserveForm.getEventId());
        Map<Integer, Zone> zoneMap = zones.stream().collect(Collectors.toMap(Zone :: getZoneId, e -> e));

        List<EventPrice> prices = eventPriceService.findPricesByEventId(reserveForm.getEventId());
        Map<Integer, EventPrice> priceMap = prices.stream().collect(Collectors.toMap(EventPrice :: getPriceId, e -> e));

        int reserveCount = reserveForm.getSeatCount();
        BigDecimal reserveTotalAmount = BigDecimal.valueOf(reserveForm.getTotalAmount());
        Set<Integer> reserveSeatIds = Sets.newHashSet();
        List<ReserveSeatDetail> seatDetails = Lists.newArrayList();
        if (CollectionUtils.isNotEmpty(seats)) {
            for (EventSeat seat : seats) {
                ReserveSeatDetail seatDetail = new ReserveSeatDetail();
                seatDetail.setReserveId(reserveForm.getId());
                seatDetail.setEventId(reserveForm.getEventId());
                EventPrice price = priceMap.get(seat.getPriceId());
                if (price == null) {
                   throw new ReserveSeatException("预留座位票价信息错误", ErrorCode.ERROR_SHOW_RESERVE_SEAT_FAILED.getCode());
                }
                seatDetail.setPriceId(price.getPriceId());
                long priceValue = new Money(price.getPriceValue()).getCent();
                seatDetail.setPriceValue(priceValue);
                Zone zone = zoneMap.get(seat.getZoneId());
                if (zone == null) {
                    throw new ReserveSeatException("预留座位区域信息错误", ErrorCode.ERROR_SHOW_RESERVE_SEAT_FAILED.getCode());
                }
                seatDetail.setZoneId(zone.getZoneId());
                seatDetail.setZoneName(zone.getZoneName());
                seatDetail.setStatus(ReserveSeatStatusEnum.RESERVED.getCode());
                seatDetail.setEnterpriseId(reserveForm.getEnterpriseId());
                reserveCount++;
                reserveTotalAmount = reserveTotalAmount.add(BigDecimal.valueOf(priceValue));
                seatDetails.add(seatDetail);
            }
            reserveForm.setSeatCount(reserveCount);
            reserveForm.setTotalAmount(reserveTotalAmount.longValue());
            reserveFormRepository.save(reserveForm);
            reserveFormRepository.insertSeatDetails(seatDetails);
            SeatReservedOperationSpec operationSpec = SeatReservedOperationSpec.builder()
                    .eventId(reserveForm.getEventId())
                    .operation(SeatReservedOperationEnum.RESERVE)
                    .seatIds(reserveSeatIds)
                    .build();
            seatService.reserveSeatsBySpec(operationSpec);
        }
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean cancelReserved(Integer reserveId) {
        ReserveForm reserveForm = this.findOne(reserveId);
        if (reserveForm == null) {
            return false;
        }
        reserveForm.setReservedStatus(CommonStateEnum.INVALID.getCode());
        reserveFormRepository.save(reserveForm);
        return this.releaseReserveSeats(reserveId, null);
    }

    @Override
    public boolean reserveTickets(Integer reserveId, List<Integer> seatIds) {
        ReserveForm reserveForm = this.findOne(reserveId);
        if (reserveForm == null || CollectionUtils.isEmpty(seatIds)) {
            return false;
        }
        ReserveSeatQuerySpec spec = new ReserveSeatQuerySpec();
        spec.setReserveId(reserveId);
        if (CollectionUtils.isNotEmpty(seatIds)) {
            spec.setSeatIds(seatIds);
        }
        List<ReserveSeatDetail> seatDetails = this.findSeatDetailsBySpec(spec, null);
        for (ReserveSeatDetail seatDetail : seatDetails) {
            seatDetail.setStatus(ReserveSeatStatusEnum.TICKETED.getCode());
        }
        reserveFormRepository.updateSeatDetails(seatDetails);
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean releaseReserveSeats(Integer reserveId, List<Integer> seatIds) {
        ReserveForm reserveForm = this.findOne(reserveId);
        if (reserveForm == null) {
            return false;
        }
        ReserveSeatQuerySpec spec = new ReserveSeatQuerySpec();
        spec.setReserveId(reserveId);
        if (CollectionUtils.isNotEmpty(seatIds)) {
            spec.setSeatIds(seatIds);
        }
        List<ReserveSeatDetail> seatDetails = this.findSeatDetailsBySpec(spec, null);
        int releaseCount = 0;
        BigDecimal releaseTotalAmount = BigDecimal.ZERO;
        Set<Integer> releaseSeatIds = Sets.newHashSet();
        if (CollectionUtils.isNotEmpty(seatDetails)) {
            for (ReserveSeatDetail seatDetail : seatDetails) {
                if (ReserveSeatStatusEnum.RESERVED.getCode() != seatDetail.getStatus()) {
                    continue;
                }
                seatDetail.setStatus(ReserveSeatStatusEnum.RELEASED.getCode());
                releaseTotalAmount = releaseTotalAmount.add(BigDecimal.valueOf(seatDetail.getPriceValue()));
                releaseCount++;
                releaseSeatIds.add(seatDetail.getSeatId());
            }
            reserveForm.setSeatCount(reserveForm.getSeatCount() - releaseCount);
            reserveForm.setTotalAmount(BigDecimal.valueOf(reserveForm.getTotalAmount()).subtract(releaseTotalAmount).longValue());
            reserveFormRepository.save(reserveForm);
            reserveFormRepository.updateSeatDetails(seatDetails);
            SeatReservedOperationSpec operationSpec = SeatReservedOperationSpec.builder()
                    .eventId(reserveForm.getEventId())
                    .reserveId(reserveForm.getId())
                    .operation(SeatReservedOperationEnum.RELEASED)
                    .seatIds(releaseSeatIds)
                    .build();
            seatService.reserveSeatsBySpec(operationSpec);
        }
        return true;
    }

    @Override
    public ReserveForm findOne(Integer reserveId) {
        if (reserveId == null) {
            return null;
        }
        return reserveFormRepository.findOne(reserveId);
    }

    @Override
    public ReserveForm findOneByReservedNo(String reservedNo) {
        if (StringUtils.isBlank(reservedNo)) {
            return null;
        }
        return reserveFormRepository.findOneByReservedNo(reservedNo);
    }

    @Override
    public List<ReserveForm> findBySpec(ReserveQuerySpec spec, Pagination pagination) {
        return reserveFormRepository.findBySpec(spec, pagination);
    }

    @Override
    public List<ReserveSeatDetail> findSeatDetailsBySpec(ReserveSeatQuerySpec spec, Pagination pagination) {
        return reserveFormRepository.findSeatDetailsBySpec(spec, pagination);
    }
}
