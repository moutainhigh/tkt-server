package com.mtl.cypw.payment.request;

import com.mtl.cypw.payment.pojo.CmbPayBase;
import lombok.Data;

/**
 * @author tang.
 * @date 2019/10/11.
 */
@Data
public class CmbPayRequest extends CmbPayBase {

    private Object reqData;
}
