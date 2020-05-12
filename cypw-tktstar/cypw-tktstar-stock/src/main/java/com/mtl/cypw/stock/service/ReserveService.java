package com.mtl.cypw.stock.service;

import com.juqitech.response.paging.Pagination;
import com.mtl.cypw.domain.stock.param.ReserveFormParam;
import com.mtl.cypw.domain.stock.param.ReserveQuerySpec;
import com.mtl.cypw.domain.stock.param.ReserveSeatQuerySpec;
import com.mtl.cypw.stock.model.ReserveForm;
import com.mtl.cypw.stock.model.ReserveSeatDetail;

import java.util.List;

/**
 * @author Johnathon.Yuan
 * @date 2020-01-19 19:35
 */
public interface ReserveService {

    Integer saveReserveForm(ReserveFormParam param);

    boolean reserveSeats(Integer reserveId, List<Integer> seatIds);

    boolean cancelReserved(Integer reserveId);

    boolean reserveTickets(Integer reserveId, List<Integer> seatIds);

    boolean releaseReserveSeats(Integer reserveId, List<Integer> seatIds);

    ReserveForm findOne(Integer reserveId);

    ReserveForm findOneByReservedNo (String reservedNo);

    List<ReserveForm> findBySpec(ReserveQuerySpec spec, Pagination pagination);

    List<ReserveSeatDetail> findSeatDetailsBySpec(ReserveSeatQuerySpec spec, Pagination pagination);
}
