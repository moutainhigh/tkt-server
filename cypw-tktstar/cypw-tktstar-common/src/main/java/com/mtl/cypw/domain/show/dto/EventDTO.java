package com.mtl.cypw.domain.show.dto;

import lombok.Data;

import java.util.Date;

/**
 * @author tang.
 * @date 2019/11/22.
 */
@Data
public class EventDTO {
    private Integer eventId;

    private Integer programId;

    private String eventTitle;

    private Date eventDate;

    private Date addDate;

    private Date updateDate;

    private Integer isSeat;

    private Integer isEnable;

    private Integer isDelete;

    private Integer addUser;

    private Integer updateUser;

    private Date saleDateBegin;

    private Date saleDateEnd;

    private Integer templateId;

    private Integer enterpriseId;
}
