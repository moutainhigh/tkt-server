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
@Table(name = "cy_order_inventory_record")
public class OrderInventoryConsumeRecord extends BaseModel {
    /**
     * 自增主键
     */
    @Id
    @KeySql(useGeneratedKeys = true)
    @Column(name = "id")
    private Integer id;

    /**
     * 记录流水号
     */
    @Column(name = "serial_no")
    private String serialNo;

    /**
     * 订单ID
     */
    @Column(name = "order_id")
    private Integer orderId;

    /**
     * 消耗状态（1-已锁库存, 2-已下单, 3-已释放）
     */
    @Column(name = "consume_status")
    private Integer consumeStatus;

    /**
     * 消耗类型（1-非选座票品, 2-选座票品, 3-衍生品）
     */
    @Column(name = "consume_type")
    private Integer consumeType;

    /**
     * 0-无效, 1-有效
     */
    @Column(name = "status")
    private Integer status;

    /**
     * 0-库存更新未处理, 1-已处理
     */
    @Column(name = "handle_status")
    private Integer handleStatus;

    /**
     * 区域ID
     */
    @Column(name = "area_id")
    private Integer areaId;

    /**
     * 场次ID
     */
    @Column(name = "event_id")
    private Integer eventId;

    /**
     * 库存消耗时间
     */
    @Column(name = "consume_time")
    private Long consumeTime;

    /**
     * 用户ID
     */
    @Column(name = "member_id")
    private Integer memberId;

    /**
     * 数据更新的版本号
     */
    @Column(name = "version")
    private Integer version;

    /**
     * 企业ID
     */
    @Column(name = "enterprise_id")
    private Integer enterpriseId;

    @Override
    public Integer getIdentify() {
        return id;
    }
}