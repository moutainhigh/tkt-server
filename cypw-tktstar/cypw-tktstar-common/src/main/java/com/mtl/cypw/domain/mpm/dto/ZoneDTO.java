package com.mtl.cypw.domain.mpm.dto;

import lombok.Data;

/**
 * @author tang.
 * @date 2019年12月12日 下午04:53:33
 */
@Data
public class ZoneDTO {
    private Integer zoneId;

    private Integer templateId;

    private String svgId;

    private String zoneName;

    private String zoneEntrance;

    private String zoneRemark;

    private Integer beginX;

    private Integer beginY;

    private Integer seatHeight;

    private Integer seatWidth;

    private Integer seatMargin;
}