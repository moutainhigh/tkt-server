package com.mtl.cypw.order.model;

import com.mtl.cypw.common.core.tkmybatis.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Johnathon.Yuan
 * @date 2019-11-24 19:41
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "cy_order_snapshot")
public class OrderSnapshot extends BaseModel {
    /**
     * 自增ID
     */
    @Id
    @KeySql(useGeneratedKeys = true)
    @Column(name = "id")
    private Integer id;

    /**
     * 订单编号
     */
    @Column(name = "order_id")
    private Integer orderId;

    /**
     * 项目ID(兼容衍生品)
     */
    @Column(name = "product_id")
    private Integer productId;

    /**
     * 项目名称(兼容衍生品)
     */
    @Column(name = "product_name")
    private String productName;

    /**
     * 详情海报地址
     */
    @Column(name = "detail_poster_url")
    private String detailPosterUrl;

    /**
     * 列表海报地址
     */
    @Column(name = "list_poster_url")
    private String listPosterUrl;

    /**
     * 场次ID
     */
    @Column(name = "event_id")
    private Integer eventId;

    /**
     * 场次名称
     */
    @Column(name = "event_name")
    private String eventName;

    /**
     * 演出名称（日期）
     */
    @Column(name = "show_name")
    private String showName;

    /**
     * 场馆（厅）ID
     */
    @Column(name = "venue_id")
    private Integer venueId;

    /**
     * 场馆（厅）名称
     */
    @Column(name = "venue_name")
    private String venueName;

    /**
     * 剧院名称
     */
    @Column(name = "theatre_name")
    private String theatreName;

    /**
     * 剧院地址
     */
    @Column(name = "theatre_address")
    private String theatreAddress;

    /**
     * 备注
     */
    @Column(name = "remark")
    private String remark;

    /**
     * 企业ID
     */
    @Column(name = "enterprise_id")
    private Integer enterpriseId;

    /**
     * 购票须知
     */
    @Column(name = "program_notice")
    private String programNotice;
}