package com.mtl.cypw.domain.stock.param;

import com.juqitech.request.BaseParam;
import com.juqitech.service.utils.ParamChecker;
import com.mtl.cypw.common.enums.CommonStateEnum;
import lombok.Data;

import java.util.Date;

/**
 * @author Johnathon.Yuan
 * @date 2020-01-20 11:32
 */
@Data
public class ReserveFormParam extends BaseParam {

    /**
     * 预留ID
     */
    private Integer reservedId;

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
     * 预留时间
     */
    private Date reservedTime;

    /**
     * 预留单状态（0-有效,1-无效）
     */
    private Integer reservedStatus;

    /**
     * 开启自动释放预留，0：否，1：是
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

    @Override
    public void checkParam() {
        ParamChecker.notNull(programId, "项目ID不能为空");
        ParamChecker.notNull(eventId, "场次ID不能为空");
        ParamChecker.notNull(reservedNo, "预留编号不能为空");
        ParamChecker.notNull(enterpriseId, "商户ID不能为空");
        if (this.autoRelease != null && CommonStateEnum.VALID.getCode() == this.autoRelease) {
            ParamChecker.notNull(releaseTime, "释放预留时间不能为空");
        }

    }
}
