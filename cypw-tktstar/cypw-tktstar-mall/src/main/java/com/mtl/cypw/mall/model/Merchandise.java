package com.mtl.cypw.mall.model;

import com.juqitech.service.utils.DateUtils;
import com.mtl.cypw.common.enums.CommonStateEnum;
import com.mtl.cypw.common.enums.DeleteEnum;
import com.mtl.cypw.common.util.DateTimeUtils;
import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;


/**
 * @author Johnathon.Yuan
 * @date 2020-03-03 11:31
 */
@Data
@Table(name = "tblmerchandise")
public class Merchandise {

    @Id
    @KeySql(useGeneratedKeys = true)
    @Column(name = "merchandise_id")
    private Integer merchandiseId;

    /**
     * 商品编码
     */
    @Column(name = "merchandise_code")
    private String merchandiseCode;

    /**
     * 商品名称
     */
    @Column(name = "merchandise_name")
    private String merchandiseName;

    /**
     * 商品简述
     */
    @Column(name = "merchandise_brief")
    private String merchandiseBrief;

    /**
     * 商品图片
     */
    @Column(name = "merchandise_image")
    private String merchandiseImage;

    /**
     * 商品指导售价
     */
    @Column(name = "merchandise_price")
    private BigDecimal merchandisePrice;

    /**
     * 开始时间
     */
    @Column(name = "begin_date")
    private Date beginDate;

    /**
     * 结束时间
     */
    @Column(name = "end_date")
    private Date endDate;

    /**
     * 每单限制数量
     */
    @Column(name = "merchandise_limit_cnt")
    private Integer merchandiseLimitCnt;

    @Column(name = "add_date")
    private Date addDate;

    @Column(name = "update_date")
    private Date updateDate;

    @Column(name = "add_user")
    private Integer addUser;

    @Column(name = "update_user")
    private Integer updateUser;

    @Column(name = "enterprise_id")
    private Integer enterpriseId;

    /**
     * 是否上架
     */
    @Column(name = "is_enable")
    private Integer isEnable;

    /**
     * 是否删除
     */
    @Column(name = "is_delete")
    private Integer isDelete;

    /**
     * 排序
     */
    @Column(name = "sort_order")
    private Integer sortOrder;

    /**
     * 图文内容
     */
    @Column(name = "merchandise_content")
    private String merchandiseContent;

    /**
     * 购买须知
     */
    @Column(name = "purchase_notice")
    private String purchaseNotice;

    public boolean isSaleable() {
        if (DeleteEnum.DELETED.getCode() == this.getIsDelete()) {
            return false;
        }
        if (CommonStateEnum.INVALID.getCode() == this.getIsEnable()) {
            return false;
        }
        if (this.getBeginDate() != null
                && this.getEndDate() != null
                && !DateTimeUtils.withinValidityPeriod(DateUtils.now(), this.getBeginDate(), this.getEndDate())) {
            return false;
        }
        return true;
    }
}