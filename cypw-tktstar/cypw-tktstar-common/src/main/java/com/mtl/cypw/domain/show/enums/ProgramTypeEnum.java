package com.mtl.cypw.domain.show.enums;

/**
 * @author tang.
 * @date 2019/12/4.
 */
public enum ProgramTypeEnum {
    /**
     * 演出类型
     */
    STAGE_PLAY(80, "话剧舞台剧"),
    MUSICAL_PLAY(81, "音乐剧"),
    PARENT_CHILD_TIME(82, "亲子时光"),
    EXHIBITION(83, "展览"),
    DANCE(84, "舞蹈"),
    VOCAL_CONCERT(85, "演唱会"),
    SPORTS_EVENTS(86, "体育赛事");

    ProgramTypeEnum(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public static ProgramTypeEnum getObject(int code) {
        for (ProgramTypeEnum programTypeEnum : values()) {
            if (programTypeEnum.getCode() == code) {
                return programTypeEnum;
            }
        }
        return null;
    }

    private int code;

    private String name;

    public int getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
