package com.mtl.cypw.provider.show.endpoint;

import com.juqitech.request.QueryRequest;
import com.juqitech.response.ResultBuilder;
import com.juqitech.response.TMultiResult;
import com.juqitech.response.TPageResult;
import com.juqitech.response.TSingleResult;
import com.mtl.cypw.api.show.endpoint.EventPriceApi;
import com.mtl.cypw.common.enums.ErrorCode;
import com.mtl.cypw.common.enums.SkuTypeEnum;
import com.mtl.cypw.domain.show.dto.CountDTO;
import com.mtl.cypw.domain.show.dto.EventPriceDTO;
import com.mtl.cypw.domain.show.enums.SeatStatusEnum;
import com.mtl.cypw.domain.show.query.EventQuery;
import com.mtl.cypw.provider.show.converter.EventPriceConverter;
import com.mtl.cypw.show.pojo.Event;
import com.mtl.cypw.show.pojo.EventPrice;
import com.mtl.cypw.show.service.EventPriceService;
import com.mtl.cypw.show.service.EventService;
import com.mtl.cypw.show.service.SeatService;
import com.mtl.cypw.stock.model.Stock;
import com.mtl.cypw.stock.repository.StockRepository;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author tang.
 * @date 2019/11/22.
 */
@RestController
public class EventPriceEndpoint implements EventPriceApi {

    @Resource
    EventPriceService eventPriceService;

    @Resource
    EventService eventService;

    @Resource
    EventPriceConverter eventPriceConverter;

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private SeatService seatService;

    @Override
    public TPageResult<EventPriceDTO> searchEventPriceList(QueryRequest<EventQuery> request) {
        List<EventPrice> list = eventPriceService.searchEventPriceList(request.getParam());
        addStock(list);
        return ResultBuilder.succTPage(eventPriceConverter.toDto(list), request.buildPagination());
    }

    @Override
    public TSingleResult<EventPriceDTO> searchMinEventPriceByProgramId(QueryRequest<EventQuery> request) {
        EventQuery query = request.getParam();
        if (query == null) {
            return ResultBuilder.failTSingle(ErrorCode.ERROR_SHOW_PARAMETER.getCode(), ErrorCode.ERROR_SHOW_PARAMETER.getMsg());
        }
        List<Event> eventList = eventService.searchEventList(query);
        if (eventList == null || eventList.size() == 0) {
            return ResultBuilder.failTSingle(ErrorCode.ERROR_SHOW_PARAMETER.getCode(), ErrorCode.ERROR_SHOW_PARAMETER.getMsg());
        }
        List<Integer> eventIdList = new ArrayList<>();
        eventList.forEach(n -> eventIdList.add(n.getEventId()));
        EventPrice eventPrice = eventPriceService.searchMinEventPriceByEventId(eventIdList);
        addStock(eventPrice);
        return ResultBuilder.succTSingle(eventPriceConverter.toDto(eventPrice));
    }

    @Override
    public TSingleResult<EventPriceDTO> searchMinEventPriceByEventId(QueryRequest<EventQuery> request) {
        EventQuery query = request.getParam();
        if (query == null || query.getEventId() == null) {
            return ResultBuilder.failTSingle(ErrorCode.ERROR_SHOW_PARAMETER.getCode(), ErrorCode.ERROR_SHOW_PARAMETER.getMsg());
        }
        EventPrice eventPrice = eventPriceService.searchMinEventPriceByEventId(query.getEventId());
        addStock(eventPrice);
        return ResultBuilder.succTSingle(eventPriceConverter.toDto(eventPrice));
    }

    @Override
    public TMultiResult<EventPriceDTO> searchMinEventPriceByProgramIds(QueryRequest<EventQuery> request) {
        EventQuery query = request.getParam();
        if (query == null || query.getProgramIds() == null || query.getProgramIds().size() == 0) {
            return ResultBuilder.failTMulti(ErrorCode.ERROR_SHOW_PARAMETER.getCode(), ErrorCode.ERROR_SHOW_PARAMETER.getMsg());
        }
        List<EventPriceDTO> eventPrices = eventPriceService.searchMinEventPriceByProgramIds(query.getProgramIds());
        return ResultBuilder.succTMulti(eventPrices);
    }

    private void addStock(EventPrice eventPrice) {
        if (eventPrice == null) {
            return;
        }
        Event event = eventService.findOneById(eventPrice.getEventId());
        if (event == null) {
            return;
        }
        if (event.getIsSeat() == 0) {
            Stock stock = stockRepository.findOneBySkuIdAndType(SkuTypeEnum.TICKET, eventPrice.getPriceId());
            if (stock != null) {
                eventPrice.setStockQty(stock.getTotalStock() - stock.getSellingStock());
                eventPrice.setSoldQty(stock.getSellingStock());
                eventPrice.setTotalQty(stock.getTotalStock());
            }
        } else {
            CountDTO dto = seatService.searchSeatCount(eventPrice.getPriceId(), SeatStatusEnum.CAN_BE_SOLD.getCode());
            if (dto != null) {
                eventPrice.setStockQty(dto.getCount());
            }
        }
    }

    private void addStock(List<EventPrice> eventPrices) {
        if (CollectionUtils.isEmpty(eventPrices)) {
            return;
        }
        List<Integer> eventIds = new ArrayList<>();
        eventPrices.forEach(n -> eventIds.add(n.getEventId()));
        List<Event> eventList = eventService.findByIds(eventIds);
        Map<Integer, Event> eventMap = new HashMap<>();
        eventList.forEach(n -> eventMap.put(n.getEventId(), n));

        List<Integer> ticketStockIds = new ArrayList<>();
        Map<Integer, Stock> ticketStockMap = new HashMap<>();
        List<Integer> seatTicketStockIds = new ArrayList<>();
        Map<Integer, CountDTO> seatTicketStockMap = new HashMap<>();

        eventPrices.forEach(n -> {
            Event event = eventMap.get(n.getEventId());
            if (event != null) {
                if (event.getIsSeat() == 0) {
                    ticketStockIds.add(n.getPriceId());
                } else if (event.getIsSeat() == 1) {
                    seatTicketStockIds.add(n.getPriceId());
                }
            }
        });
        if (CollectionUtils.isNotEmpty(ticketStockIds)) {
            List<Stock> stocks = stockRepository.findOneBySkuIdAndType(SkuTypeEnum.TICKET, ticketStockIds);
            if (CollectionUtils.isNotEmpty(stocks)) {
                stocks.forEach(n -> ticketStockMap.put(n.getPriceId(), n));
            }
        }

        if (CollectionUtils.isNotEmpty(seatTicketStockIds)) {
            List<CountDTO> stocks = seatService.searchSeatCount(seatTicketStockIds, SeatStatusEnum.CAN_BE_SOLD.getCode());
            if (CollectionUtils.isNotEmpty(stocks)) {
                stocks.forEach(n -> seatTicketStockMap.put(n.getId(), n));
            }
        }

        eventPrices.forEach(n -> {
            Event event = eventMap.get(n.getEventId());
            if (event != null) {
                if (event.getIsSeat() == 0) {
                    Stock stock = ticketStockMap.get(n.getPriceId());
                    if (stock != null) {
                        n.setStockQty(stock.getTotalStock() - stock.getSellingStock());
                        n.setSoldQty(stock.getSellingStock());
                        n.setTotalQty(stock.getTotalStock());
                    }
                } else if (event.getIsSeat() == 1) {
                    CountDTO dto = seatTicketStockMap.get(n.getPriceId());
                    if (dto != null) {
                        n.setStockQty(dto.getCount());
                    }
                }
            }
        });
    }
}
