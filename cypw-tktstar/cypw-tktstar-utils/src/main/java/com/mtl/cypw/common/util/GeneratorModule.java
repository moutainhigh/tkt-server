package com.mtl.cypw.common.util;

import com.juqitech.entity.EntityEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Johnathon.Yuan
 * @date 2020-03-18 17:58
 */
@Getter
@AllArgsConstructor
public enum GeneratorModule implements EntityEnum {

    BIZ_FETCH(1, "取票（货）凭证"),
    BIZ_OTHER(9, "其他");

    private int code;
    private String name;


}
