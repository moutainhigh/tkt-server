package com.mtl.cypw.domain.show.dto;

import lombok.Data;

/**
 * @author sq
 * @date 2020/3/17  18:37
 */
@Data
public class ProgramCheckInDTO {
    private Integer id;

    private Integer programId;

    private String entranceName;

    private String checkinCount;

    private String checkinAccountIds;
}
