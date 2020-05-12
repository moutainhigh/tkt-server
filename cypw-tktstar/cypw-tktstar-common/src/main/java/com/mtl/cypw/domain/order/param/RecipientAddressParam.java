package com.mtl.cypw.domain.order.param;

import com.juqitech.request.BaseParam;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Johnathon.Yuan
 * @date 2020-03-16 18:03
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class RecipientAddressParam extends BaseParam {

    /**
     * 省份名
     */
    private String provinceName;

    /**
     * 城市名
     */
    private String cityName;

    /**
     * 区县名
     */
    private String districtName;

    /**
     * 收件人具体地址
     */
    private String detailedAddress;

    /**
     * 收件人邮编
     */
    private String postCode;

    /**
     * 收件人名
     */
    private String addresseeName;

    /**
     * 收件人电话
     */
    private String addresseeMobile;

    @Override
    public void checkParam() {

    }
}
