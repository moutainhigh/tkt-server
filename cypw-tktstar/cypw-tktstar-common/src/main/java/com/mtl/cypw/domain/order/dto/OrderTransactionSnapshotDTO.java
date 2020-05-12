package com.mtl.cypw.domain.order.dto;

import com.juqitech.response.BaseEntityInfo;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Johnathon.Yuan
 * @date 2019-12-10 15:52
 */
@Setter
@Getter
@ToString(callSuper = true)
public class OrderTransactionSnapshotDTO extends BaseEntityInfo {

    private Integer orderSnapshotId;

    /**
     * 订单ID
     */
    private Integer orderId;

    /**
     * 剧院名称
     */
    private String theatreName;

    /**
     * 剧院地址
     */
    private String theatreAddress;

    /**
     * 场馆ID
     */
    private Integer venueId;

    /**
     * 场馆名称
     */
    private String venueName;

    /**
     * 项目ID
     */
    private Integer projectId;

    /**
     * 项目名称
     */
    private String projectName;

    /**
     * 详情页海报
     */
    private String detailPosterUrl;

    /**
     * 列表页海报
     */
    private String listPosterUrl;

    /**
     * 场次ID
     */
    private Integer eventId;

    /**
     * 场次名称
     */
    private String eventName;

    /**
     * 演出时间[格式化]
     */
    private String showName;

    /**
     * 企业ID
     */
    private Integer enterpriseId;

    /**
     * 购票须知
     */
    private String programNotice;
}
