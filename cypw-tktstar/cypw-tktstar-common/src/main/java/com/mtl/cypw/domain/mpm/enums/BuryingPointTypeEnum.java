package com.mtl.cypw.domain.mpm.enums;

/**
 * 埋点类型枚举类
 *
 * @author tang.
 * @date 2020/1/19.
 */
public enum BuryingPointTypeEnum {
    /**
     * 埋点类型
     */
    MEMBER_SEARCH_LOG(1, "MEMBER_SEARCH_LOG", "用户搜索埋点"),
    COMPREHENSIVE(0, "COMPREHENSIVE", "综合");

    private int code;
    private String name;
    private String des;

    BuryingPointTypeEnum(int code, String name, String des) {
        this.code = code;
        this.name = name;
        this.des = des;
    }

    public static BuryingPointTypeEnum getObject(int code) {
        for (BuryingPointTypeEnum typeEnum : values()) {
            if (typeEnum.getCode() == code) {
                return typeEnum;
            }
        }
        return null;
    }

    public static BuryingPointTypeEnum getObject(String name) {
        for (BuryingPointTypeEnum typeEnum : values()) {
            if (typeEnum.getName().equalsIgnoreCase(name)) {
                return typeEnum;
            }
        }
        return null;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
