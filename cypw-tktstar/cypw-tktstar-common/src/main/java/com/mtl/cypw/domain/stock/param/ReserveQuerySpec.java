package com.mtl.cypw.domain.stock.param;

import com.juqitech.request.BaseParam;
import com.mtl.cypw.common.enums.CommonStateEnum;
import lombok.Data;

import java.util.Date;

/**
 * @author Johnathon.Yuan
 * @date 2020-01-19 13:49
 */
@Data
public class ReserveQuerySpec extends BaseParam {

    /**
     * 项目ID
     */
    private Integer programId;

    /**
     * 场次ID
     */
    private Integer eventId;

    /**
     * 预留对象手机号
     */
    private String targetMobile;

    /**
     * 预留对象名称
     */
    private String targetName;

    /**
     * 开始时间
     */
    private Date startTime;

    /**
     * 结束时间
     */
    private Date endTime;

    /**
     * 预留单状态（0-无效,1-有效）
     */
    private CommonStateEnum reservedStatus;

    /**
     * 开启自动释放预留（0-否,1-是）
     */
    private Boolean autoRelease;

    /**
     * 企业ID
     */
    private Integer enterpriseId;

    @Override
    public void checkParam() {

    }
}
