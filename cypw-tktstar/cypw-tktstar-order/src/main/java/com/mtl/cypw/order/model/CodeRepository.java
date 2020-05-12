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
@Table(name = "cy_order_code_repository")
public class CodeRepository extends BaseModel {
    /**
     * 自增主键
     */
    @Id
    @KeySql(useGeneratedKeys = true)
    @Column(name = "id")
    private Integer id;

    /**
     * 码
     */
    @Column(name = "code")
    private String code;

    /**
     * 码的状态(0-未使用, 1-已使用)
     */
    @Column(name = "status")
    private Integer status;

    /**
     * 码的位数
     */
    @Column(name = "bit_length")
    private Integer bitLength;

    /**
     * 流水号
     */
    @Column(name = "serial_no")
    private String serialNo;

    /**
     * 检票码来源（0-自有, 1-第三方导入）
     */
    @Column(name = "code_source")
    private Integer codeSource;

    /**
     * 场次ID（第三方导入使用）
     */
    @Column(name = "event_id")
    private Integer eventId;

    /**
     * 票档ID（第三方导入使用）
     */
    @Column(name = "price_id")
    private Integer priceId;

    @Override
    public Integer getIdentify() {
        return id;
    }

}