package com.mtl.cypw.domain.show.dto;

import lombok.Data;

/**
 * @author tang.
 * @date 2019/12/3.
 */
@Data
public class FetchTicketWayDTO {
    /**
     * 自增主键
     */
    private Integer id;

    /**
     * 项目ID
     */
    private Integer programId;

    /**
     * 取票方式
     */
    private Integer deliverType;

    /**
     * 是否需要证件取件(0-不需要, 1-需要)
     */
    private Integer needIdcard;

    /**
     * 温馨提示
     */
    private String tips;

    /**
     * 配送费
     */
    private Long expressFee;

    /**
     * 取票地址
     */
    private String fetchAddress;

    /**
     * 联系电话
     */
    private String contactMobile;

    /**
     * 上门取票的服务时间说明
     */
    private String fetchTimeDesc;

    /**
     * 企业ID
     */
    private Integer enterpriseId;

    /**
     * 是否可用
     */
    private Integer deleted;

}
