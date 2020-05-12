package com.mtl.cypw.domain.mpm.enums;

import com.juqitech.entity.EntityEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Johnathon.Yuan
 * @date 2020-03-05 16:47
 */
@Getter
@AllArgsConstructor
public enum MediaTypeEnum implements EntityEnum {
    IMAGE(1, "IMAGE", "图片"),
    VIDEO(2, "VIDEO", "视频"),
    FILE(3, "FILE", "文件");

    private int code;
    private String name;
    private String desc;

    public static MediaTypeEnum getObject(String name) {
        for (MediaTypeEnum typeEnum : values()) {
            if (typeEnum.getName().equalsIgnoreCase(name)) {
                return typeEnum;
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
