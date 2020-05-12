package com.mtl.cypw.domain.show.param;

import lombok.Data;

/**
 * @author tang.
 * @date 2019/12/2.
 */
@Data
public class ShortageRegistrationParam {

    private Integer memberId;

    private String registerPhone;

    private Integer eventPriceId;
}
