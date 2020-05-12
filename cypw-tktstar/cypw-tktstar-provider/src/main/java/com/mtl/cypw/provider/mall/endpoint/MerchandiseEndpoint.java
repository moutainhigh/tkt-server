package com.mtl.cypw.provider.mall.endpoint;

import com.juqitech.request.QueryRequest;
import com.juqitech.response.ResultBuilder;
import com.juqitech.response.TMultiResult;
import com.juqitech.response.TPageResult;
import com.juqitech.response.TSingleResult;
import com.juqitech.response.paging.Pagination;
import com.mtl.cypw.api.mall.endpoint.MerchandiseApi;
import com.mtl.cypw.domain.mall.dto.MerchandiseDTO;
import com.mtl.cypw.domain.mall.dto.MerchandiseSpecDTO;
import com.mtl.cypw.domain.mall.param.MerchandiseQueryParam;
import com.mtl.cypw.mall.model.Merchandise;
import com.mtl.cypw.mall.service.MerchandiseService;
import com.mtl.cypw.provider.mall.converter.MerchandiseConverter;
import com.mtl.cypw.provider.mall.converter.MerchandiseSpecConverter;
import com.mtl.cypw.show.pojo.EventPrice;
import com.mtl.cypw.show.service.EventPriceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Johnathon.Yuan
 * @date 2020-03-04 15:55
 */
@RestController
@Slf4j
public class MerchandiseEndpoint implements MerchandiseApi {

    @Autowired
    private EventPriceService eventPriceService;

    @Autowired
    private MerchandiseService merchandiseService;

    @Autowired
    private MerchandiseConverter merchandiseConverter;

    @Autowired
    private MerchandiseSpecConverter merchandiseSpecConverter;

    @Override
    public TPageResult<MerchandiseDTO> pageQueryGoodsByEnterprise(QueryRequest<MerchandiseQueryParam> request) {
        MerchandiseQueryParam param = request.getParam();
        Pagination pagination = request.buildPagination();
        List<Merchandise> merchandises = merchandiseService.pageQuery(param, pagination);
        return ResultBuilder.succTPage(merchandiseConverter.batchConvert(merchandises), pagination);
    }

    @Override
    public TSingleResult<MerchandiseDTO> findGoodsDetailByMerchandiseId(QueryRequest<MerchandiseQueryParam> request) {
        MerchandiseQueryParam param = request.getParam();
        Merchandise merchandise = merchandiseService.getDetail(param.getMerchandiseId(), param.getEnterpriseId());
        return ResultBuilder.succTSingle(merchandiseConverter.convertDetail(merchandise));
    }

    @Override
    public TMultiResult<MerchandiseSpecDTO> findGoodsSpecByMerchandiseId(QueryRequest<MerchandiseQueryParam> request) {
        MerchandiseQueryParam param = request.getParam();
        List<EventPrice> prices = eventPriceService.findPricesByProductIdWithMall(param.getMerchandiseId());
        return ResultBuilder.succTMulti(merchandiseSpecConverter.batchConvert(prices));
    }
}
