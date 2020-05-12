package com.mtl.cypw.domain.payment.param;

import lombok.Data;

/**
 * @author tang.
 * @date 2020/2/14.
 */
@Data
public class PayPalExecutePaymentParam {

    private String paymentId;

    private String payerId;
}
