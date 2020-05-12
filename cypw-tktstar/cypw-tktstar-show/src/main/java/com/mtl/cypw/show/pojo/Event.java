package com.mtl.cypw.show.pojo;

import com.mtl.cypw.common.enums.CommonStateEnum;
import com.mtl.cypw.common.util.DateTimeUtils;
import com.mtl.cypw.common.utils.DateUtil;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
* Created by Mybatis Generator on 2019年11月22日 下午02:20:36
*/
@Data
@Table(name = "tblevent")
public class Event {
    @Id
    @Column(name = "event_id")
    private Integer eventId;

    @Column(name = "program_id")
    private Integer programId;

    @Column(name = "event_title")
    private String eventTitle;

    @Column(name = "event_date")
    private Date eventDate;

    @Column(name = "add_date")
    private Date addDate;

    @Column(name = "update_date")
    private Date updateDate;

    @Column(name = "is_seat")
    private Integer isSeat;

    @Column(name = "is_enable")
    private Integer isEnable;

    @Column(name = "is_delete")
    private Integer isDelete;

    @Column(name = "add_user")
    private Integer addUser;

    @Column(name = "update_user")
    private Integer updateUser;

    @Column(name = "sale_date_begin")
    private Date saleDateBegin;

    @Column(name = "sale_date_end")
    private Date saleDateEnd;

    @Column(name = "template_id")
    private Integer templateId;

    @Column(name = "code_source")
    private Integer codeSource;

    @Column(name = "enterprise_id")
    private Integer enterpriseId;

    @Column(name = "is_tongpiao")
    private Integer isPass;

    @Column(name = "tongpiao_begin_date")
    private Date passBeginDate;

    @Column(name = "tongpiao_end_date")
    private Date passEndDate;

    @Column(name = "include_weekdays")
    private String includeWeekdays;

    /**
     * 普通票验证
     * @param date
     * @return
     */
    public boolean canCheckIn4Normal(Date date) {
        return !isPassSchedule() &&
                DateTimeUtils.withinValidityPeriod(date, DateUtil.getDayBeginTime(this.getEventDate()), DateUtil.getDayEndTime(this.getEventDate()));
    }

    /**
     * 通票场验证
     * @param date
     * @return
     */
    public boolean canCheckIn4Pass(Date date) {
        return isPassSchedule()
                && DateTimeUtils.withinValidityPeriod(date, DateUtil.getDayBeginTime(this.getPassBeginDate()), DateUtil.getDayEndTime(this.getPassEndDate()));
    }

    /**
     * 通票场次
     * @return
     */
    public boolean isPassSchedule() {
        return CommonStateEnum.VALID.getCode() == this.getIsPass();
    }

}