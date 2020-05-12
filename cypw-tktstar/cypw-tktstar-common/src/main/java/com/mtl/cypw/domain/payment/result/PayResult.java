package com.mtl.cypw.domain.payment.result;

import com.mtl.cypw.domain.payment.enums.PayTypeEnum;
import lombok.Data;

/**
 * @author tang.
 * @date 2019/12/9.
 */
@Data
public class PayResult {

    private PayTypeEnum payType;
}
