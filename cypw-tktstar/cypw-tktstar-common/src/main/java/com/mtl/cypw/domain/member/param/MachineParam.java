package com.mtl.cypw.domain.member.param;

import lombok.Data;

import java.util.Date;

/**
 * @author tang.
 * @date 2020/3/12.
 */
@Data
public class MachineParam {

    private Integer enterpriseId;

    /**
     * 密钥
     */
    private String machineKey;

    /**
     * MAC地址，第一次登陆时绑定
     */
    private String macAddress;
}
