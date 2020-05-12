package com.mtl.cypw.ticket.model;

import com.mtl.cypw.common.core.tkmybatis.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author Johnathon.Yuan
 * @date 2019-03-18 16:00
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "cy_fetch_voucher")
public class FetchVoucher extends BaseModel {
    /**
     * 自增ID
     */
    @Id
    @KeySql(useGeneratedKeys = true)
    @Column(name = "id")
    private Integer id;

    /**
     * 订单ID
     */
    @Column(name = "order_id")
    private Integer orderId;

    /**
     * 取货/取票流水号
     */
    @Column(name = "voucher_no")
    private String voucherNo;

    /**
     * 凭证类型（1：票品, 2：衍生品）
     */
    @Column(name = "voucher_type")
    private Integer voucherType;

    /**
     * 凭证描述
     */
    @Column(name = "voucher_desc")
    private String voucherDesc;

    /**
     * 操作类型（1：正常取票/货），2：重新取票/货）
     */
    @Column(name = "handle_type")
    private Integer handleType;

    /**
     * 方式（1：自助取票机，2：线下打印机，3：小程序提货）
     */
    @Column(name = "fetch_method")
    private Integer fetchMethod;

    /**
     * 设备ID
     */
    @Column(name = "machine_id")
    private Integer machineId;

    /**
     * 设备 mac 地址
     */
    @Column(name = "mac_id")
    private String macId;

    /**
     * 身份证号
     */
    @Column(name = "id_card")
    private String idCard;

    /**
     * 身份证名称
     */
    @Column(name = "id_card_name")
    private String idCardName;

    /**
     * 取票（货）锁定时间
     */
    @Column(name = "fetch_lock_time")
    private Date fetchLockTime;

    /**
     * 取票（货）完成时间
     */
    @Column(name = "fetch_complete_time")
    private Date fetchCompleteTime;

    /**
     * 取票（货）码
     */
    @Column(name = "fetch_code")
    private String fetchCode;

    /**
     * 操作人ID
     */
    @Column(name = "fetch_user_id")
    private Integer fetchUserId;

    /**
     * 操作人姓名
     */
    @Column(name = "fetch_user_name")
    private String fetchUserName;

    /**
     * 商品总件数
     */
    @Column(name = "quantity")
    private Integer quantity;

    /**
     * 凭证状态（1：取货锁定中，2：取货完成）
     */
    @Column(name = "status")
    private Integer status;

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