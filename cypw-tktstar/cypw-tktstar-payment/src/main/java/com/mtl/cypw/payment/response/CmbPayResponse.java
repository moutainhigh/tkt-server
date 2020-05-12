package com.mtl.cypw.payment.response;

import com.mtl.cypw.payment.pojo.CmbPayBase;
import com.mtl.cypw.payment.response.data.CmbPayNoticeData;
import lombok.Data;

/**
 * @author tang.
 * @date 2019/10/18.
 */
@Data
public class CmbPayResponse extends CmbPayBase {

    private CmbPayNoticeData noticeData;
}
