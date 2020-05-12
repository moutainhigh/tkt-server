package com.mtl.cypw.api.mall.endpoint;

import com.juqitech.request.QueryRequest;
import com.juqitech.response.TMultiResult;
import com.juqitech.response.TPageResult;
import com.juqitech.response.TSingleResult;
import com.mtl.cypw.domain.mall.dto.MerchandiseDTO;
import com.mtl.cypw.domain.mall.dto.MerchandiseSpecDTO;
import com.mtl.cypw.domain.mall.param.MerchandiseQueryParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author Johnathon.Yuan
 * @date 2020-03-02 21:27
 */
public interface MerchandiseApi {

    String Merchandise_API = "MerchandiseEndpoint";

    @PostMapping("/endpoint/v1/mall/page_query_goods_by_enterprise")
    TPageResult<MerchandiseDTO> pageQueryGoodsByEnterprise(@RequestBody QueryRequest<MerchandiseQueryParam> request);

    @PostMapping("/endpoint/v1/mall/find_goods_detail_by_merchandiseId")
    TSingleResult<MerchandiseDTO> findGoodsDetailByMerchandiseId(@RequestBody QueryRequest<MerchandiseQueryParam> request);

    @PostMapping("/endpoint/v1/mall/find_goods_spec_by_merchandiseId")
    TMultiResult<MerchandiseSpecDTO> findGoodsSpecByMerchandiseId(@RequestBody QueryRequest<MerchandiseQueryParam> request);

}
