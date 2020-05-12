package com.mtl.cypw.order.service;

import com.google.common.collect.Maps;
import com.mtl.cypw.common.utils.JsonUtils;
import com.mtl.cypw.domain.mpm.constant.SmsKeyword;
import com.mtl.cypw.domain.mpm.constant.SmsTemplate;
import com.mtl.cypw.domain.mpm.param.SmsSendParam;
import com.mtl.cypw.domain.order.enums.DeliverTypeEnum;
import com.mtl.cypw.mpm.model.Enterprise;
import com.mtl.cypw.mpm.service.EnterpriseService;
import com.mtl.cypw.mpm.service.SmsService;
import com.mtl.cypw.order.model.Order;
import com.mtl.cypw.order.model.OrderDelivery;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.Map;

/**
 * @author Johnathon.Yuan
 * @date 2019-12-23 17:48
 */
@Slf4j
@Component
public class OrderSmsEntrance {

    @Autowired
    private SmsService smsServiceImpl;

    @Autowired
    private EnterpriseService enterpriseServiceImpl;

    public void sendPaymentSuccessMessage(Order order) {
        if (order.isSystemOrder()) {
            return;
        }
        Assert.notNull(order, "order is null");
        Assert.isTrue(order.isPaid(), "order not paid");
        SmsSendParam param = new SmsSendParam();
        param.setBizId(order.getId().toString());
        param.setCellphone(order.getMobileNo());
        param.setProduct(SmsKeyword.COMM_PRODUCT);
        param.setCountryCode(SmsKeyword.COMM_COUNTRYCODE);
        param.setEnterpriseId(order.getEnterpriseId());
        Map<String, String> variables = Maps.newHashMap();
        if (order.isTicket()) {
            param.setTemplateCode(SmsTemplate.SMS_ORDER_PAY_SUCCESS);
            variables.put(SmsKeyword.KEY_SHOW_NAME, order.getProductName());
            variables.put(SmsKeyword.KEY_SHOW_TIME, order.getEventName());
            variables.put(SmsKeyword.KEY_ORDER_QTY, String.valueOf(order.getOrderTickets().size()));
        } else if (order.isGoods()) {
            param.setTemplateCode(SmsTemplate.SMS_VENUE_ORDER_PAY_SUCCESS);
        }
        param.setVariables(variables);
        log.info("send payment success msg, content[{}]", JsonUtils.toJson(param));
        smsServiceImpl.sendSms(param);
    }

    public void sendTicketSuccessMessage(Order order) {
        if (order.isSystemOrder() || order.isGoods()) {
            return;
        }
        Assert.notNull(order, "order is null");
        Assert.isTrue(order.isPaid(), "order not paid");
        Assert.isTrue(order.getDelivery() != null, "order delivery error");
        SmsSendParam param = new SmsSendParam();
        param.setBizId(order.getId().toString());
        param.setCellphone(order.getMobileNo());
        param.setProduct(SmsKeyword.COMM_PRODUCT);
        param.setCountryCode(SmsKeyword.COMM_COUNTRYCODE);
        param.setEnterpriseId(order.getEnterpriseId());
        Map<String, String> variables = Maps.newHashMap();
        if (order.isTicket()) {
            variables.put(SmsKeyword.KEY_SHOW_NAME, order.getProductName());
            variables.put(SmsKeyword.KEY_SHOW_TIME, order.getEventName());
            variables.put(SmsKeyword.KEY_ORDER_QTY, String.valueOf(order.getOrderTickets().size()));
            OrderDelivery delivery = order.getDelivery();
            if (DeliverTypeEnum.OFFLINE.getCode() == delivery.getDeliverType()) {
                param.setTemplateCode(SmsTemplate.SMS_ORDER_VENUE);
                variables.put(SmsKeyword.KEY_VENUE_NAME, order.getOrderSnapshot().getTheatreName());
            } else if (DeliverTypeEnum.SPOT_PICKING.getCode() == delivery.getDeliverType()) {
                param.setTemplateCode(SmsTemplate.SMS_ORDER_OFFLINE);
                variables.put(SmsKeyword.KEY_OFFLINE_ADDRESS, delivery.getLocaleAddress());
                variables.put(SmsKeyword.KEY_OFFLINE_CONTACT, delivery.getLocaleContact());
            } else if (DeliverTypeEnum.E_TICKET.getCode() == delivery.getDeliverType()) {
                param.setTemplateCode(SmsTemplate.SMS_ORDER_EXPRESS_PAY_ONLINE);
            }
            param.setVariables(variables);
            log.info("send ticket success msg, content[{}]", JsonUtils.toJson(param));
            smsServiceImpl.sendSms(param);
        }
    }

    public void sendOrderDeliveryNoticeMessage(Order order) {
        if (order.isSystemOrder()) {
            return;
        }
        Assert.notNull(order, "order is null");
        Assert.isTrue(order.isPaid(), "order not paid");
        Assert.isTrue(order.getDelivery() != null, "order delivery error");
        Assert.isTrue(order.getDelivery().getDeliverType() == DeliverTypeEnum.EXPRESS.getCode(), "order delivery error");

        SmsSendParam param = new SmsSendParam();
        param.setBizId(order.getId().toString());
        param.setCellphone(order.getMobileNo());
        param.setProduct(SmsKeyword.COMM_PRODUCT);
        param.setCountryCode(SmsKeyword.COMM_COUNTRYCODE);
        param.setEnterpriseId(order.getEnterpriseId());
        Map<String, String> variables = Maps.newHashMap();
        if (order.isTicket()) {
            param.setTemplateCode(SmsTemplate.SMS_ORDER_DELIVERY);
            variables.put(SmsKeyword.KEY_SHOW_NAME, order.getProductName());
            variables.put(SmsKeyword.KEY_SHOW_TIME, order.getEventName());
            variables.put(SmsKeyword.KEY_ORDER_QTY, String.valueOf(order.getOrderTickets().size()));
            variables.put(SmsKeyword.KEY_EXPRESS, order.getDelivery().getExpressNo());
            variables.put(SmsKeyword.KEY_DELIVERY_COMPANY, order.getDelivery().getExpressCompany());
        } else if (order.isGoods()) {
            Enterprise enterprise = enterpriseServiceImpl.getEnterpriseById(order.getEnterpriseId());
            param.setTemplateCode(SmsTemplate.SMS_ORDER_TICKET_ACCEPTED);
            variables.put(SmsKeyword.KEY_EXPRESS, order.getDelivery().getExpressNo());
            variables.put(SmsKeyword.KEY_DELIVERY_COMPANY, order.getDelivery().getExpressCompany());
            variables.put(SmsKeyword.KEY_VENDOR_NAME, enterprise.getEnterpriseName());
        }
        param.setVariables(variables);
        log.info("bind expressNo success msg, content[{}]", JsonUtils.toJson(param));
        smsServiceImpl.sendSms(param);
    }

    /**
     * 绑定物流号成功发短信
     * @param order 订单实体类参数
     */
    public void sendBindExpressNoSuccessMessage(Order order) {
        if (order.isSystemOrder()) {
            return;
        }
        Assert.notNull(order, "order is null");
        Assert.isTrue(order.isDelivering(), "order not delivering");
        SmsSendParam param = new SmsSendParam();
        param.setBizId(order.getId().toString());
        param.setCellphone(order.getMobileNo());
        param.setProduct(SmsKeyword.COMM_PRODUCT);
        param.setCountryCode(SmsKeyword.COMM_COUNTRYCODE);
        param.setTemplateCode(SmsTemplate.SMS_ORDER_PAY_SUCCESS);
        param.setEnterpriseId(order.getEnterpriseId());
        Map<String, String> variables = Maps.newHashMap();
        variables.put(SmsKeyword.KEY_SHOW_NAME, order.getProductName());
        variables.put(SmsKeyword.KEY_SHOW_TIME, order.getEventName());
        if (order.isTicket()) {
            variables.put(SmsKeyword.KEY_ORDER_QTY, String.valueOf(order.getOrderTickets().size()));
        }
        param.setVariables(variables);
        log.info("send bindExpressNo success msg, content[{}]", JsonUtils.toJson(param));
        smsServiceImpl.sendSms(param);
    }
}
