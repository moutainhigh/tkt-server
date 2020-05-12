package com.mtl.cypw.api.order.endpoint;

import com.juqitech.request.GenericRequest;
import com.juqitech.request.IdRequest;
import com.juqitech.response.TSingleResult;
import com.mtl.cypw.domain.order.param.BindExpressNoParam;
import com.mtl.cypw.domain.order.param.OrderCancelParam;
import com.mtl.cypw.domain.order.param.OrderPaidParam;
import com.mtl.cypw.domain.order.param.UserCreateOrderParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * @author Johnathon.Yuan
 * @date 2019-11-23 11:53
 */
public interface OrderApi {

    /**
     * Order 的管理 RPC 服务本地 Bean 名称
     */
    String ORDER_API = "OrderEndpoint";

    /**
     * 创建 订单
     * @param request
     * @return orderId
     */
    @RequestMapping(value = "/endpoint/v1/order/create", method = RequestMethod.POST, consumes = APPLICATION_JSON_VALUE)
    TSingleResult<Integer> create(@RequestBody GenericRequest<UserCreateOrderParam> request);

    /**
     * 支付 订单（确认）
     * @param request
     * @return boolean
     */
    @RequestMapping(value = "/endpoint/v1/order/confirm", method = RequestMethod.POST, consumes = APPLICATION_JSON_VALUE)
    TSingleResult<Boolean> paid(@RequestBody GenericRequest<OrderPaidParam> request);


    /**
     * 取消 订单
     * @param request
     * @return boolean
     */
    @RequestMapping(value = "/endpoint/v1/order/cancel", method = RequestMethod.POST, consumes = APPLICATION_JSON_VALUE)
    TSingleResult<Boolean> cancel(@RequestBody GenericRequest<OrderCancelParam> request);

    /**
     * 核销 订单
     * @param request
     * @return boolean
     */
    @RequestMapping(value = "/endpoint/v1/order/consume_by_id", method = RequestMethod.POST, consumes = APPLICATION_JSON_VALUE)
    TSingleResult<Boolean> consumeById(@RequestBody IdRequest request);

    /**
     * 同步 订单
     * @param request
     * @return boolean
     */
    @RequestMapping(value = "/endpoint/v1/order/sync_by_id", method = RequestMethod.POST, consumes = APPLICATION_JSON_VALUE)
    TSingleResult<Boolean> syncById(@RequestBody IdRequest request);

    /**
     * 删除/隐藏 订单
     * @param request
     * @return boolean
     */
    @RequestMapping(value = "/endpoint/v1/order/delete_by_id", method = RequestMethod.POST, consumes = APPLICATION_JSON_VALUE)
    TSingleResult<Boolean> deleteById(@RequestBody IdRequest request);

    /**
     * 确认收货
     * @param request 订单id
     * @return boolean
     */
    @RequestMapping(value = "/endpoint/v1/order/confirm_receipt", method = RequestMethod.POST, consumes = APPLICATION_JSON_VALUE)
    TSingleResult<Boolean> confirmReceipt(@RequestBody IdRequest request);

    /**
     * 绑定物流单号
     * @param request 绑定参数
     * @return boolean
     */
    @RequestMapping(value = "/endpoint/v1/order/bind_express_no", method = RequestMethod.POST, consumes = APPLICATION_JSON_VALUE)
    TSingleResult<Boolean> bindExpressNo(@RequestBody GenericRequest<BindExpressNoParam> request);
}
