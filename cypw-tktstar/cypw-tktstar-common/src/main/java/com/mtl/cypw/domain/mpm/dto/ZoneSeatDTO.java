package com.mtl.cypw.domain.mpm.dto;

import lombok.Data;

import java.util.Date;

/**
 * @author tang.
 * @date 2019年12月12日 下午04:53:33
 */
@Data
public class ZoneSeatDTO {
    private Integer zoneSeatId;

    private Integer zoneId;

    private String rowName;

    private String colName;

    private Integer rowInt;

    private Integer colInt;

    private Integer onlyPrefix;

    private String seatType;

    private Date addDate;

    private Integer addUser;
}