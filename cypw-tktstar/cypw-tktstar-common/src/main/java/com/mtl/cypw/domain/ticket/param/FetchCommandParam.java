package com.mtl.cypw.domain.ticket.param;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author Johnathon.Yuan
 * @date 2020-03-18 19:08
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class FetchCommandParam extends FetchCommand {

    /**
     * 锁票凭证号
     */
    private String voucherNo;

    /**
     * 取票明细
     */
    private List<Integer> ticketIds;


    @Override
    public void checkParam() {

    }
}
