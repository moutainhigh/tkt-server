package com.mtl.cypw.domain.ticket.dto;

import com.juqitech.response.BaseEntityInfo;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.List;

/**
 * @author Johnathon.Yuan
 * @date 2020-02-14 22:14
 */
@Setter
@Getter
@ToString(callSuper = true)
public class FetchRecordQueryDTO extends BaseEntityInfo {

    /**
     * 取票（货）日期
     */
    private Date fetchDate;

    /**
     * 取票（货）总数
     */
    private Integer fetchCount;

    /**
     * 取票（货）明细
     */
    private List<VoucherPaperDTO> voucherPapers;
}
