package com.mtl.cypw.provider.mpm.endpoint;

import com.juqitech.request.GenericRequest;
import com.juqitech.request.QueryRequest;
import com.juqitech.response.ResultBuilder;
import com.juqitech.response.TMultiResult;
import com.juqitech.response.TPageResult;
import com.juqitech.response.TSingleResult;
import com.juqitech.response.paging.Pagination;
import com.mtl.cypw.api.mpm.endpoint.BuryingPointApi;
import com.mtl.cypw.common.util.ThreadPoolTaskUtil;
import com.mtl.cypw.domain.mpm.dto.BuryingPointDTO;
import com.mtl.cypw.domain.mpm.param.BuryingPointParam;
import com.mtl.cypw.domain.mpm.param.BuryingPointQueryParam;
import com.mtl.cypw.mpm.model.BuryingPointInfo;
import com.mtl.cypw.mpm.service.BuryingPointService;
import com.mtl.cypw.provider.mpm.converter.BuryingPointConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author tang.
 * @date 2020/1/19.
 */
@RestController
@Slf4j
public class BuryingPointEndpoint implements BuryingPointApi {

    @Resource
    private BuryingPointService buryingPointServiceImpl;

    @Resource
    private BuryingPointConverter converter;

    @Override
    public TPageResult<BuryingPointDTO> findBuryingPointPageList(QueryRequest<BuryingPointQueryParam> request) {
        BuryingPointQueryParam query = request.getParam();
        Pagination pagination = request.buildPagination();
        List<BuryingPointInfo> list = buryingPointServiceImpl.findBuryingPointInfoList(query, pagination);
        return ResultBuilder.succTPage(converter.toDto(list), pagination);
    }

    @Override
    public TMultiResult<BuryingPointDTO> findBuryingPointList(QueryRequest<BuryingPointQueryParam> request) {
        BuryingPointQueryParam query = request.getParam();
        List<BuryingPointInfo> list = buryingPointServiceImpl.findBuryingPointInfoList(query);
        return ResultBuilder.succTMulti(converter.toDto(list));
    }

    @Override
    public TSingleResult<Boolean> addBuryingPoint(GenericRequest<BuryingPointParam> request) {
        ThreadPoolTaskUtil.execute(() -> buryingPointServiceImpl.addBuryingPointInfo(converter.toEntity(request.getParam())));
        return ResultBuilder.succTSingle(true);
    }

    @Override
    public TSingleResult<Boolean> deleteBuryingPoint(GenericRequest<BuryingPointParam> request) {
        buryingPointServiceImpl.deleteBuryingPoint(request.getParam());
        return ResultBuilder.succTSingle(true);
    }
}
