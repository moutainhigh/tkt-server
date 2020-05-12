package com.mtl.cypw.domain.ticket.dto;

import com.juqitech.response.BaseEntityInfo;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * @author Johnathon.Yuan
 * @date 2020-02-14 22:14
 */
@Setter
@Getter
@ToString(callSuper = true)
public class TicketPaperResultDTO extends BaseEntityInfo {

    /**
     * 电子检票码
     */
    private String checkCode;

    /**
     * 查询手机号【查询】
     */
    private String mobileNo;

    /**
     * 结果
     */
    private Boolean success;

    /**
     * 错误码
     */
    private Integer errorCode;

    /**
     * 错误描述
     */
    private String errorMessage;

    /**
     * 电子票列表
     */
    private List<TicketPaperDTO> ticketPapers;
}
