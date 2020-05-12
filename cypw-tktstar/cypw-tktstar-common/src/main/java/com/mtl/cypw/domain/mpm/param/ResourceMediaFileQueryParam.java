package com.mtl.cypw.domain.mpm.param;

import com.juqitech.request.BaseParam;
import lombok.Data;

import java.util.List;

/**
 * @author Johnathon.Yuan
 * @date 2020-03-05 17:06
 */
@Data
public class ResourceMediaFileQueryParam extends BaseParam {

    /**
     * 资源类型（1：图片，2：视频，3：文件）
     */
    private List<Integer> fileTypes;

    /**
     * 关联业务类型（0：通用，1：衍生品规格图）
     */
    private List<Integer> businessTypes;

    /**
     * 关联业务ID
     */
    private Integer businessId;

    /**
     * 状态，0：失效，1：正常
     */
    private Integer status;

    /**
     * 企业ID
     */
    private Integer enterpriseId;

    @Override
    public void checkParam() {

    }
}
