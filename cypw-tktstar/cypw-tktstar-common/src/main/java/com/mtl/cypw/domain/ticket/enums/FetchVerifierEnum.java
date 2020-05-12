package com.mtl.cypw.domain.ticket.enums;

import com.juqitech.entity.EntityEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Johnathon.Yuan
 * @date 2020-02-21 11:31
 */
@Getter
@AllArgsConstructor
public enum FetchVerifierEnum implements EntityEnum {

    PASSED(1, "PASSED","验证通过"),
    SPENT(2, "SPENT","已取票（货）");

    private int code;
    private String name;
    private String desc;

    public static FetchVerifierEnum getObject(int code) {
        for (FetchVerifierEnum type : values()) {
            if (type.getCode() == code) {
                return type;
            }
        }
        return null;
    }

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getName() {
        return name;
    }
}

