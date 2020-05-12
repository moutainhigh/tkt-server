package com.mtl.cypw.api.stock.endpoint;

import com.juqitech.request.GenericRequest;
import com.juqitech.request.IdRequest;
import com.juqitech.request.QueryRequest;
import com.juqitech.response.TPageResult;
import com.juqitech.response.TSingleResult;
import com.mtl.cypw.domain.stock.dto.ReserveFormDTO;
import com.mtl.cypw.domain.stock.dto.ReserveSeatDetailDTO;
import com.mtl.cypw.domain.stock.param.ReserveFormParam;
import com.mtl.cypw.domain.stock.param.ReserveQuerySpec;
import com.mtl.cypw.domain.stock.param.ReserveSeatParam;
import com.mtl.cypw.domain.stock.param.ReserveSeatQuerySpec;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author Johnathon.Yuan
 * @date 2020-01-20 12:27
 */
public interface ReserveApi {

    /**
     * Reserve 的查询 RPC 服务本地 Bean 名称
     */
    String RESERVE_API = "ReserveEndpoint";

    @PostMapping("/endpoint/v1/reserve/save")
    TSingleResult<Integer> save(@RequestBody GenericRequest<ReserveFormParam> request);

    @PostMapping("/endpoint/v1/reserve/reserveSeats")
    TSingleResult<Boolean> reserveSeats(@RequestBody GenericRequest<ReserveSeatParam> request);

    @PostMapping("/endpoint/v1/reserve/cancelReserved")
    TSingleResult<Boolean> cancelReserved(@RequestBody IdRequest idRequest);

    @PostMapping("/endpoint/v1/reserve/reserveTickets")
    TSingleResult<Boolean> reserveTickets(@RequestBody GenericRequest<ReserveSeatParam> request);

    @PostMapping("/endpoint/v1/reserve/releaseReserveSeats")
    TSingleResult<Boolean> releaseReserveSeats(@RequestBody GenericRequest<ReserveSeatParam> request);

    @PostMapping("/endpoint/v1/reserve/find_by_id")
    TSingleResult<ReserveFormDTO> findById(@RequestBody IdRequest idRequest);

    @PostMapping("/endpoint/v1/reserve/find_by_serial_number")
    TSingleResult<ReserveFormDTO> findBySerialNo(@RequestBody IdRequest idRequest);

    @PostMapping("/endpoint/v1/reserve/find_form_by_page")
    TPageResult<ReserveFormDTO> formPageQuery(@RequestBody QueryRequest<ReserveQuerySpec> request);

    @PostMapping("/endpoint/v1/reserve/find_seat_detail_by_page")
    TPageResult<ReserveSeatDetailDTO> seatDetailsPageQuery(@RequestBody QueryRequest<ReserveSeatQuerySpec> request);

}
