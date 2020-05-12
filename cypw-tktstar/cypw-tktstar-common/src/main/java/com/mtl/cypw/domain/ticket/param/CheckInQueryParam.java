package com.mtl.cypw.domain.ticket.param;

import com.juqitech.request.BaseParam;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * @author Johnathon.Yuan
 * @date 2020-02-14 19:08
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class CheckInQueryParam extends BaseParam {

    /**
     * 电子票ID
     */
    private Integer ticketId;

    /**
     * 电子检票码【核销查询】
     */
    private String checkCode;

    /**
     * 查询手机号【核销查询】
     */
    private String mobileNo;

    /**
     * 检票账号ID
     */
    private Integer checkUserId;

    /**
     * 检票日期（yyyy-MM-dd）
     */
    private Date checkDate;

    /**
     * 检票入口ID
     */
    private Integer checkEntryId;

    /**
     * 企业标识
     */
    private Integer enterpriseId;


    @Override
    public void checkParam() {

    }
}
