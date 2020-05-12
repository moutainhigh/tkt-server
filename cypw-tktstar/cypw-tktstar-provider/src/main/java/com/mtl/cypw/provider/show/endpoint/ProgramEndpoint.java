package com.mtl.cypw.provider.show.endpoint;

import com.alibaba.fastjson.JSONObject;
import com.juqitech.request.QueryRequest;
import com.juqitech.response.ResultBuilder;
import com.juqitech.response.TPageResult;
import com.juqitech.response.TSingleResult;
import com.juqitech.response.paging.Pagination;
import com.mtl.cypw.api.show.client.ProgramApiClient;
import com.mtl.cypw.common.enums.ErrorCode;
import com.mtl.cypw.domain.show.dto.FetchTicketWayDTO;
import com.mtl.cypw.domain.show.dto.ProgramCheckInDTO;
import com.mtl.cypw.domain.show.dto.ProgramDTO;
import com.mtl.cypw.domain.show.dto.aggregate.ProgramAggregateDTO;
import com.mtl.cypw.domain.show.query.ProgramQuery;
import com.mtl.cypw.provider.show.converter.FetchTicketWayConverter;
import com.mtl.cypw.provider.show.converter.ProgramAggregateConverter;
import com.mtl.cypw.provider.show.converter.ProgramCheckInConverter;
import com.mtl.cypw.provider.show.converter.ProgramConverter;
import com.mtl.cypw.show.pojo.FetchTicketWay;
import com.mtl.cypw.show.pojo.Program;
import com.mtl.cypw.show.pojo.ProgramCheckIn;
import com.mtl.cypw.show.pojo.aggregate.ProgramAggregate;
import com.mtl.cypw.show.service.FetchTicketWayService;
import com.mtl.cypw.show.service.ProgramService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author tang.
 * @date 2019/11/20.
 */
@RestController
@Slf4j
public class ProgramEndpoint implements ProgramApiClient {

    @Resource
    ProgramService programService;

    @Autowired
    ProgramConverter programConverter;

    @Resource
    FetchTicketWayService fetchTicketWayService;

    @Autowired
    FetchTicketWayConverter fetchTicketWayConverter;

    @Autowired
    ProgramAggregateConverter aggregateConverter;

    @Autowired
    ProgramCheckInConverter programCheckInConverter;

    @Override
    public TPageResult<ProgramDTO> searchProgramList(@RequestBody QueryRequest<ProgramQuery> request) {
        Pagination pagination = request.buildPagination();
        List<ProgramDTO> list = programConverter.toDto(programService.searchProgramList(request.getParam(), pagination));
        addFetchTicketWay(list);
        TPageResult<ProgramDTO> result = ResultBuilder.succTPage(list, pagination);
        log.debug("演出列表查询结果,result:{}", JSONObject.toJSONString(result.getPagination()));
        return result;
    }

    @Override
    public TSingleResult<ProgramDTO> getProgramById(@RequestBody QueryRequest<ProgramQuery> request) {
        ProgramQuery query = request.getParam();
        if (query == null || query.getProgramId() == null) {
            return ResultBuilder.failTSingle(ErrorCode.ERROR_SHOW_PARAMETER.getCode(), ErrorCode.ERROR_SHOW_PARAMETER.getMsg());
        }
        ProgramDTO dto = programConverter.toDto(programService.getProgramById(query.getProgramId()));
        List<FetchTicketWay> wayList = fetchTicketWayService.findListByProgramId(query.getProgramId());
        dto.setFetchTicketWayList(fetchTicketWayConverter.toDto(wayList));
        TSingleResult result = ResultBuilder.succTSingle(dto);
        return result;
    }

    private void addFetchTicketWay(List<ProgramDTO> list) {
        if (list != null && list.size() != 0) {
            List<Integer> programIds = new ArrayList<>();
            list.forEach(n -> programIds.add(n.getProgramId()));
            List<FetchTicketWayDTO> wayList = fetchTicketWayConverter.toDto(fetchTicketWayService.findListByProgramIds(programIds));
            if (wayList != null) {
                Map<Integer, List<FetchTicketWayDTO>> map = new HashMap<>();
                wayList.forEach(n -> {
                    List<FetchTicketWayDTO> programWayList = map.get(n.getProgramId());
                    if (programWayList == null || programWayList.size() == 0) {
                        programWayList = new ArrayList<>();
                    }
                    programWayList.add(n);
                    map.put(n.getProgramId(), programWayList);
                });
                list.forEach(n -> n.setFetchTicketWayList(map.get(n.getProgramId())));
            }
        }
    }

    /**
     * 后台查询演出列表.
     * @param request 请求参数
     * @return TPageResult参数
     */
    @Override
    public TPageResult<ProgramAggregateDTO> searchBackendProgramList(QueryRequest<ProgramQuery> request) {
        ProgramQuery query = request.getParam();
        if (query == null || query.getEnterpriseId() == null) {
            return ResultBuilder.failTPage(ErrorCode.ERROR_SHOW_PARAMETER.getCode(), ErrorCode.ERROR_SHOW_PARAMETER.getMsg());
        }
        Pagination pagination = request.buildPagination();
        List<ProgramAggregate> list = programService.searchBackendProgramList(request.getParam(), pagination);
        List<ProgramAggregateDTO> resultList = (List<ProgramAggregateDTO>) aggregateConverter.toDto(list);
        TPageResult<ProgramAggregateDTO> result = ResultBuilder.succTPage(resultList, pagination);
        return result;
    }

    /**
     * 后台获取演出详情
     * @param request 参数
     * @return TSingleResult
     */
    @Override
    public TSingleResult<ProgramDTO> getBackendProgramById(QueryRequest<ProgramQuery> request) {
        ProgramQuery query = request.getParam();
        if (query == null || query.getProgramId() == null) {
            return ResultBuilder.failTSingle(ErrorCode.ERROR_SHOW_PARAMETER.getCode(), ErrorCode.ERROR_SHOW_PARAMETER.getMsg());
        }
        Program program = programService.getProgramById(query.getProgramId());
        if (null == program) {
            return ResultBuilder.failTSingle(ErrorCode.ERROR_SHOW.getCode(), ErrorCode.ERROR_SHOW.getMsg());

        }
        ProgramDTO dto = programConverter.toDto(program);
        List<FetchTicketWay> wayList = fetchTicketWayService.findListByProgramId(query.getProgramId());
        if (CollectionUtils.isEmpty(wayList)) {
            wayList = fetchTicketWayService.initTickeWayList(program);
        }
        dto.setFetchTicketWayList(fetchTicketWayConverter.toDto(wayList));
        List<ProgramCheckIn> checkInList = programService.getCheckInfoByProgramId(query.getProgramId());
        if (CollectionUtils.isEmpty(checkInList)) {
            checkInList = programService.initCheckInList();
        }
        dto.setProgramCheckInList((List<ProgramCheckInDTO>) programCheckInConverter.toDto(checkInList));
        TSingleResult result = ResultBuilder.succTSingle(dto);
        return result;
    }
}
