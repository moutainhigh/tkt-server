package com.mtl.cypw.stock.model;

import com.mtl.cypw.common.core.tkmybatis.BaseModel;
import com.mtl.cypw.common.enums.CommonStateEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.joda.time.DateTime;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author Johnathon.Yuan
 * @date 2019-01-19 12:00
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "cy_reserve_form")
public class ReserveForm extends BaseModel {
    /**
     * 自增ID
     */
    @Id
    @KeySql(useGeneratedKeys = true)
    @Column(name = "id")
    private Integer id;

    /**
     * 项目ID
     */
    @Column(name = "program_id")
    private Integer programId;

    /**
     * 场次ID
     */
    @Column(name = "event_id")
    private Integer eventId;

    /**
     * 预留流水号
     */
    @Column(name = "reserved_no")
    private String reservedNo;

    /**
     * 预留标题
     */
    @Column(name = "reserved_name")
    private String reservedName;

    /**
     * 预留会员ID
     */
    @Column(name = "member_id")
    private Integer memberId;

    /**
     * 预留对象手机号
     */
    @Column(name = "target_mobile")
    private String targetMobile;

    /**
     * 预留对象名称
     */
    @Column(name = "target_name")
    private String targetName;

    /**
     * 预留总金额（分）
     */
    @Column(name = "total_amount")
    private Long totalAmount;

    /**
     * 预留座位总数
     */
    @Column(name = "seat_count")
    private Integer seatCount;

    /**
     * 预留时间
     */
    @Column(name = "reserved_time")
    private Date reservedTime;

    /**
     * 预留单状态（0-无效,1-有效）
     */
    @Column(name = "reserved_status")
    private Integer reservedStatus;

    /**
     * 开启自动释放预留（0-否,1-是）
     */
    @Column(name = "auto_release")
    private Integer autoRelease;

    /**
     * 自动释放预留时间
     */
    @Column(name = "release_time")
    private Date releaseTime;

    /**
     * 预留说明
     */
    @Column(name = "remark")
    private String remark;

    /**
     * 企业ID
     */
    @Column(name = "enterprise_id")
    private Integer enterpriseId;

    @Override
    public Integer getIdentify() {
        return id;
    }

    public boolean isValid() {
        return CommonStateEnum.VALID.getCode() == this.reservedStatus;
    }

    public boolean isReserveDeadline() {
        return CommonStateEnum.VALID.getCode() == this.autoRelease
                && this.releaseTime != null
                && new DateTime(this.releaseTime).plusMinutes(1).isBefore(DateTime.now());
    }

}