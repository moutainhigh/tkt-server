package com.mtl.cypw.api.order.endpoint;

import com.juqitech.request.IdRequest;
import com.juqitech.request.QueryRequest;
import com.juqitech.response.TPageResult;
import com.juqitech.response.TSingleResult;
import com.mtl.cypw.domain.order.dto.OrderDTO;
import com.mtl.cypw.domain.order.param.OrderQueryParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * @author Johnathon.Yuan
 * @date 2019-11-23 11:53
 */
public interface OrderQueryApi {

    /**
     * Order 的查询 RPC 服务本地 Bean 名称
     */
    String ORDER_QUERY_API = "OrderQueryEndpoint";

    /**
     * 根据 ID 查询详情
     * @param request
     * @return dto
     */
    @RequestMapping(value = "/endpoint/v1/order/find_by_id", method = RequestMethod.POST, consumes = APPLICATION_JSON_VALUE)
    TSingleResult<OrderDTO> findById(@RequestBody IdRequest request);

    /**
     * 分页查询列表
     * @param request
     * @return dto
     */
    @RequestMapping(value = "/endpoint/v1/order/find_by_page", method = RequestMethod.POST, consumes = APPLICATION_JSON_VALUE)
    TPageResult<OrderDTO> pageQuery(@RequestBody QueryRequest<OrderQueryParam> request);

    /**
     * 查询订单数量
     * @param request
     * @return Integer
     */
    @RequestMapping(value = "/endpoint/v1/order/count", method = RequestMethod.POST, consumes = APPLICATION_JSON_VALUE)
    TSingleResult<Integer> countQuery(@RequestBody QueryRequest<OrderQueryParam> request);
}
