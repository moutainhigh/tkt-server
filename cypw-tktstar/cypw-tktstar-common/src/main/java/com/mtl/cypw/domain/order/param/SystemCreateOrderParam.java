package com.mtl.cypw.domain.order.param;

import com.mtl.cypw.common.utils.Money;
import com.mtl.cypw.domain.order.enums.DeliverTypeEnum;
import com.mtl.cypw.domain.order.enums.OrderTypeEnum;
import com.mtl.cypw.domain.order.enums.SystemActionTypeEnum;
import com.mtl.cypw.domain.order.enums.SystemGiftTypeEnum;
import com.mtl.cypw.domain.payment.enums.PayTypeEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;

/**
 * @author Johnathon.Yuan
 * @date 2019-11-25 13:57
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SystemCreateOrderParam extends UniformCreateOrderParam {

    /**
     * 系统下单类型
     */
    private SystemActionTypeEnum actionType;
    /**
     * 订单总金额
     */
    private Money totalPrice;
    /**
     * 下单类型
     */
    private OrderTypeEnum orderType;
    /**
     * 支付方式
     */
    private PayTypeEnum payType;
    /**
     * 交付方式
     */
    private DeliverTypeEnum deliverType;
    /**
     * 会员ID
     */
    private Integer memberId;
    /**
     * 接收人姓名
     */
    private String recipientName;
    /**
     * 接收人手机
     */
    private String recipientMobile;
    /**
     * 项目ID
     */
    private Integer projectId;
    /**
     * 场次ID
     */
    private Integer showId;
    /**
     * 商户ID
     */
    private Integer enterpriseId;

    /**
     * 收件人情况
     */
    private RecipientAddressParam recipientAddressParam;

    /**
     * 优惠情况
     */
    private OrderDiscountParam orderDiscountParam;
    /**
     * 赠票情况
     */
    private OrderGiftParam orderGiftParam;
    /**
     * 操作记录
     */
    private SystemRecordParam systemRecordParam;

    @Override
    public void checkParam() {

    }
    /**
     * 包含赠票信息(普通票、赠票、工作票、折扣票)
     * @return
     */
    public boolean hasGift() {
        return this.orderGiftParam != null
                && (SystemGiftTypeEnum.GENERAL_TICKET.getCode() == this.orderGiftParam.getGiftType().getCode() || StringUtils.isNotBlank(orderGiftParam.getRemark()));
    }

    /**
     * 含优惠的赠票出票方式(赠票、工作票)
     * @return
     */
    public boolean hasGiftWithDiscount() {
        return this.orderGiftParam != null
                && StringUtils.isNotBlank(orderGiftParam.getRemark())
                && SystemGiftTypeEnum.GENERAL_TICKET.getCode() != this.orderGiftParam.getGiftType().getCode();
    }

    /**
     * 优惠信息
     * @return
     */
    public boolean hasDiscount () {
        return this.orderDiscountParam != null
                && this.orderDiscountParam.getDiscountAmount() != null
                && this.orderDiscountParam.getDiscountAmount().compareTo(BigDecimal.ZERO) == 1;
    }
}
