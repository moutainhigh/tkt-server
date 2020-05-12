package com.mtl.cypw.domain.auth.dto;

import com.mtl.cypw.domain.auth.dto.data.CmbAuthData;
import lombok.Data;

/**
 * @author tang.
 * @date 2020/1/15.
 */
@Data
public class CmbAuthDTO {
    private String type;
    private String userAttitude;
    private CmbAuthData data;
    private String verify;
}
