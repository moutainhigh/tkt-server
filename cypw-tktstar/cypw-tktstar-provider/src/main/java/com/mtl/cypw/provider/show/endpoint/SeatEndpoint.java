package com.mtl.cypw.provider.show.endpoint;

import com.juqitech.request.QueryRequest;
import com.juqitech.response.ResultBuilder;
import com.juqitech.response.TPageResult;
import com.juqitech.response.TSingleResult;
import com.mtl.cypw.api.show.endpoint.SeatApi;
import com.mtl.cypw.common.enums.ErrorCode;
import com.mtl.cypw.domain.show.dto.SeatDTO;
import com.mtl.cypw.domain.show.dto.SeatMapDTO;
import com.mtl.cypw.domain.show.param.SeatQuerySpec;
import com.mtl.cypw.domain.show.query.SeatMapQuery;
import com.mtl.cypw.mpm.model.Template;
import com.mtl.cypw.mpm.model.Zone;
import com.mtl.cypw.mpm.service.TemplateService;
import com.mtl.cypw.provider.mpm.converter.TemplateConverter;
import com.mtl.cypw.provider.mpm.converter.ZoneConverter;
import com.mtl.cypw.provider.show.converter.EventConverter;
import com.mtl.cypw.provider.show.converter.EventPriceConverter;
import com.mtl.cypw.provider.show.converter.SeatConverter;
import com.mtl.cypw.show.pojo.Event;
import com.mtl.cypw.show.pojo.EventPrice;
import com.mtl.cypw.show.pojo.EventSeat;
import com.mtl.cypw.show.service.EventPriceService;
import com.mtl.cypw.show.service.EventService;
import com.mtl.cypw.show.service.SeatService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author tang.
 * @date 2019/11/22.
 */
@RestController
public class SeatEndpoint implements SeatApi {

    @Autowired
    private SeatService seatService;

    @Autowired
    private EventService eventService;

    @Autowired
    private TemplateService templateServiceImpl;

    @Autowired
    private EventPriceService eventPriceService;

    @Autowired
    private SeatConverter seatConverter;

    @Autowired
    private EventConverter eventConverter;

    @Autowired
    private ZoneConverter zoneConverter;

    @Autowired
    private TemplateConverter templateConverter;

    @Autowired
    private EventPriceConverter eventPriceConverter;

    @Override
    public TPageResult<SeatDTO> searchSeatList(QueryRequest<SeatQuerySpec> request) {
        SeatQuerySpec spec = request.getParam();
        List<EventSeat> seats = seatService.findBySpec(spec);
        TPageResult<SeatDTO> result = ResultBuilder.succTPage(seatConverter.toDto(seats), request.buildPagination());
        return result;
    }

    @Override
    public TSingleResult<SeatMapDTO> findSeatMap(QueryRequest<SeatMapQuery> request) {
        SeatMapQuery query = request.getParam();
        Event event = eventService.findOneById(query.getEventId());
        if (event == null) {
           return ResultBuilder.failTSingle(ErrorCode.ERROR_COMMON_PARAMETER.getCode(), "场次不存在或已下架");
        }
        if (event.getTemplateId() == null) {
            return ResultBuilder.failTSingle(ErrorCode.ERROR_COMMON_PARAMETER.getCode(), "座位图暂未开放");
        }
        Template template = templateServiceImpl.getTemplate(event.getTemplateId());
        if (template == null) {
            return ResultBuilder.failTSingle(ErrorCode.ERROR_COMMON_PARAMETER.getCode(), "座位图暂未开放");
        }
        List<Zone> zones = templateServiceImpl.findZonesByTemplateId(event.getTemplateId());
        List<EventPrice> prices = eventPriceService.findPricesByEventId(event.getEventId());
        if (CollectionUtils.isEmpty(prices)) {
            return ResultBuilder.failTSingle(ErrorCode.ERROR_COMMON_PARAMETER.getCode(), "还没有可售票品");
        }
        SeatQuerySpec seatQuerySpec = new SeatQuerySpec();
        seatQuerySpec.setEventId(event.getEventId());
        seatQuerySpec.setPriceId(query.getPriceId());
        seatQuerySpec.setZoneId(query.getZoneId());
        seatQuerySpec.setIsOnline(true);
        List<EventSeat> seats = seatService.findBySpec(seatQuerySpec);
        SeatMapDTO seatMap = new SeatMapDTO();
        seatMap.setEvent(eventConverter.toDto(event));
        seatMap.setTemplate(templateConverter.toDto(template));
        seatMap.setPrices(eventPriceConverter.toDto(prices));
        seatMap.setZones(zoneConverter.toDto(zones));
        seatMap.setSeats(seatConverter.toDto(seats));
        return ResultBuilder.succTSingle(seatMap);
    }
}
