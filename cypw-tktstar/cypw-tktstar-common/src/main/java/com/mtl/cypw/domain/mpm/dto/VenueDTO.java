package com.mtl.cypw.domain.mpm.dto;

import lombok.Data;

import java.util.Date;

/**
 * @author tang.
 * @date 2019年12月12日 下午04:53:33
 */
@Data
public class VenueDTO {
    private Integer venueId;

    private Integer theatreId;

    private String venueName;

    private String venueDesc;

    private Date addDate;

    private Date updateDate;

    private Integer addUser;

    private Integer updateUser;

    private Integer isEnable;
}