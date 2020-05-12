package com.mtl.cypw.domain.admin.param;

import lombok.Data;

import java.util.List;

/**
 * @author tang.
 * @date 2020/3/17.
 */
@Data
public class UserQueryParam {

    private String loginName;

    private Integer enterpriseId;

    private Integer userId;

    private List<Integer> userIdList;

    private Integer isEnable;

    private Integer isDelete;
}
