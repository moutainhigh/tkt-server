package com.mtl.cypw.provider.order.endpoint;

import com.juqitech.request.IdRequest;
import com.juqitech.request.QueryRequest;
import com.juqitech.response.ResultBuilder;
import com.juqitech.response.TPageResult;
import com.juqitech.response.TSingleResult;
import com.juqitech.response.paging.Pagination;
import com.mtl.cypw.api.order.endpoint.OrderQueryApi;
import com.mtl.cypw.domain.order.dto.OrderDTO;
import com.mtl.cypw.domain.order.param.OrderQueryParam;
import com.mtl.cypw.order.model.Order;
import com.mtl.cypw.order.service.OrderQueryService;
import com.mtl.cypw.provider.order.converter.OrderConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Johnathon.Yuan
 * @date 2019-11-25 13:55
 */
@Slf4j
@RestController
public class OrderQueryEndpoint implements OrderQueryApi {

    @Autowired
    private OrderConverter orderConverter;

    @Autowired
    private OrderQueryService orderQueryServiceImpl;

    @Override
    public TSingleResult<OrderDTO> findById(IdRequest request) {
        String id = request.getId();
        Order order = orderQueryServiceImpl.findOneById(Integer.parseInt(id));
        return ResultBuilder.succTSingle(orderConverter.convert(order));
    }

    @Override
    public TPageResult<OrderDTO> pageQuery(QueryRequest<OrderQueryParam> request) {
        OrderQueryParam param = request.getParam();
        Pagination pagination = request.buildPagination();
        List<Order> orders = orderQueryServiceImpl.findPageAll(param, pagination);
        return ResultBuilder.succTPage(orderConverter.batchConvert(orders), pagination);
    }

    @Override
    public TSingleResult<Integer> countQuery(QueryRequest<OrderQueryParam> request) {
        OrderQueryParam param = request.getParam();
        Integer count = orderQueryServiceImpl.countQuery(param);
        return ResultBuilder.succTSingle(count);
    }
}
