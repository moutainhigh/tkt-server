package com.mtl.cypw.domain.ticket.param;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * @author Johnathon.Yuan
 * @date 2020-03-18 19:08
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class FetchQueryParam extends FetchCommand {

    /**
     * 身份证号
     */
    private String idCard;

    /**
     * 凭证编号
     */
    private String voucherNo;

    /**
     * 取票（货）日期（yyyy-MM-dd）
     */
    private Date fetchDate;

    @Override
    public void checkParam() {

    }
}
