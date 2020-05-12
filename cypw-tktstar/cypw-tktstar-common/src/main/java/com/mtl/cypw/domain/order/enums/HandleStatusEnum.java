package com.mtl.cypw.domain.order.enums;

import com.juqitech.entity.EntityEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Johnathon.Yuan
 * @date 2019-11-26 12:11
 */
@Getter
@AllArgsConstructor
public enum HandleStatusEnum implements EntityEnum {

    UNKNOWN(0, "未知"),
    WAIT_HANDLE(1, "库存更新未处理"),
    HANDLED(2, "库存更新已处理");

    private int code;
    private String name;

    public static HandleStatusEnum getObject(int code) {
        for (HandleStatusEnum handleStatusEnum : values()) {
            if (handleStatusEnum.getCode() == code) {
                return handleStatusEnum;
            }
        }
        return null;
    }

    @Override
    public int getCode() {
        return this.code;
    }

    @Override
    public String getName() {
        return this.name;
    }

}
