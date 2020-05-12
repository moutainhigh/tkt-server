package com.mtl.cypw.provider.show.endpoint;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.juqitech.request.QueryRequest;
import com.juqitech.response.ResultBuilder;
import com.juqitech.response.TMultiResult;
import com.juqitech.response.TPageResult;
import com.juqitech.response.paging.Pagination;
import com.mtl.cypw.api.show.endpoint.EventApi;
import com.mtl.cypw.common.enums.ErrorCode;
import com.mtl.cypw.domain.show.dto.EventDTO;
import com.mtl.cypw.domain.show.dto.EventPriceDTO;
import com.mtl.cypw.domain.show.dto.SeatDTO;
import com.mtl.cypw.domain.show.dto.aggregate.EventAggregateDTO;
import com.mtl.cypw.domain.show.dto.aggregate.ProgramAggregateDTO;
import com.mtl.cypw.domain.show.param.SeatQuerySpec;
import com.mtl.cypw.domain.show.query.EventQuery;
import com.mtl.cypw.provider.show.converter.EventConverter;
import com.mtl.cypw.provider.show.converter.EventPriceConverter;
import com.mtl.cypw.provider.show.converter.SeatConverter;
import com.mtl.cypw.show.pojo.Event;
import com.mtl.cypw.show.pojo.EventPrice;
import com.mtl.cypw.show.pojo.EventSeat;
import com.mtl.cypw.show.service.EventPriceService;
import com.mtl.cypw.show.service.EventService;
import com.mtl.cypw.show.service.SeatService;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.Lists;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author tang.
 * @date 2019/11/22.
 */
@RestController
@Slf4j
public class EventEndpoint implements EventApi {

    @Resource
    EventService eventService;

    @Resource
    EventConverter eventConverter;
    @Resource
    EventPriceService eventPriceService;
    @Resource
    SeatService seatService;
    @Resource
    SeatConverter seatConverter;
    @Resource
    EventPriceConverter eventPriceConverter;

    @Override
    public TMultiResult<EventDTO> searchEventList(QueryRequest<EventQuery> request) {
        List<Event> list = eventService.searchEventList(request.getParam());
        return ResultBuilder.succTMulti(eventConverter.toDto(list));
    }

    /**
     * 后台查询演出场次列表.
     * @param request 参数.
     * @return TPageResult
     */
    @Override
    public TPageResult<EventAggregateDTO> searchBackendEventList(QueryRequest<EventQuery> request) {
        EventQuery query = request.getParam();
        if (query == null || query.getEnterpriseId() == null || query.getProgramId() == null) {
            return ResultBuilder.failTPage(ErrorCode.ERROR_SHOW_PARAMETER.getCode(), ErrorCode.ERROR_SHOW_PARAMETER.getMsg());
        }
        Pagination pagination = request.buildPagination();
        PageHelper.startPage(pagination.getOffset(), pagination.getLength());
        List<Event> list = eventService.searchEventList(query);
        List<EventAggregateDTO> resultList = Lists.newArrayList();
        for (Event event:list){
            EventAggregateDTO aggregateDTO = new EventAggregateDTO();
            BeanUtils.copyProperties(event,aggregateDTO);
            List<EventPriceDTO> priceList = eventPriceConverter.toDto(eventPriceService.findPricesByEventId(event.getEventId()));
            aggregateDTO.setPriceList(priceList);
            SeatQuerySpec seatQuerySpec = new SeatQuerySpec();
            seatQuerySpec.setEventId(event.getEventId());
            List<SeatDTO> seatList= seatConverter.toDto(seatService.findBySpec(seatQuerySpec));
            aggregateDTO.setSeatList(seatList);
            resultList.add(aggregateDTO);
        }
        TPageResult<EventAggregateDTO> result = ResultBuilder.succTPage(resultList, pagination);
        log.debug("演出场次列表查询结果,result:{}", JSONObject.toJSONString(result.getPagination()));
        return result;
    }


}
