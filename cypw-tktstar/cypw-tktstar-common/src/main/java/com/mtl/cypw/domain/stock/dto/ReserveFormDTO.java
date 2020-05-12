package com.mtl.cypw.domain.stock.dto;

import com.juqitech.response.BaseEntityInfo;
import com.mtl.cypw.common.utils.Money;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * @author Johnathon.Yuan
 * @date 2020-01-20 17:33
 */
@Setter
@Getter
@ToString(callSuper = true)
public class ReserveFormDTO extends BaseEntityInfo {

    /**
     * 预留单ID
     */
    private Integer reserveId;

    /**
     * 项目ID
     */
    private Integer programId;

    /**
     * 场次ID
     */
    private Integer eventId;

    /**
     * 预留流水号
     */
    private String reservedNo;

    /**
     * 预留标题
     */
    private String reservedName;

    /**
     * 预留会员ID
     */
    private Integer memberId;

    /**
     * 预留对象手机号
     */
    private String targetMobile;

    /**
     * 预留对象名称
     */
    private String targetName;

    /**
     * 预留总金额
     */
    private Money totalAmount;

    /**
     * 预留座位总数
     */
    private Integer seatCount;

    /**
     * 预留时间
     */
    private Date reservedTime;

    /**
     * 预留单状态（0-无效,1-有效）
     */
    private Integer reservedStatus;

    /**
     * 开启自动释放预留（0-否,1-是）
     */
    private Integer autoRelease;

    /**
     * 自动释放预留时间
     */
    private Date releaseTime;

    /**
     * 预留说明
     */
    private String remark;

    /**
     * 企业ID
     */
    private Integer enterpriseId;
}
