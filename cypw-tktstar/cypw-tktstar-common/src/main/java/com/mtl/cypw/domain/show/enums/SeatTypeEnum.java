package com.mtl.cypw.domain.show.enums;

import com.google.common.collect.Lists;
import com.juqitech.entity.EntityEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

/**
 * @author Johnathon.Yuan
 * @date 2020-01-03 15:07
 */
@Getter
@AllArgsConstructor
public enum SeatTypeEnum implements EntityEnum {

    UNKNOWN(0, "未知"),
    GENERAL(1, "普通"),
    WHEELCHAIR(2, "轮椅"),
    DANCING_FLOOR(3, "乐池"),
    VIP(4, "VIP"),
    RESERVED(5, "保留");

    private int code;
    private String name;

    public static SeatTypeEnum getObject(int code) {
        for (SeatTypeEnum status : values()) {
            if (status.getCode() == code) {
                return status;
            }
        }
        return UNKNOWN;
    }

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getName() {
        return name;
    }

    public static List<Integer> supportedOffline() {
        return Lists.newArrayList(WHEELCHAIR.code, DANCING_FLOOR.code, DANCING_FLOOR.code, VIP.code, RESERVED.code);
    }

    public static List<Integer> supportedOnline() {
        return Lists.newArrayList(GENERAL.code);
    }
}
