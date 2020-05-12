package com.mtl.cypw.domain.mpm.dto;

import com.juqitech.response.BaseEntityInfo;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * @author Johnathon.Yuan
 * @date 2020-03-05 17:06
 */
@Setter
@Getter
@ToString(callSuper = true)
public class ResourceMediaFileDTO extends BaseEntityInfo {

    private Integer fileId;

    /**
     * 资源路径（ucloud.cn 路径）
     */
    private String fileSrc;

    /**
     * 资源类型（1：图片，2：视频，3：文件）
     */
    private Integer fileType;

    /**
     * 关联业务类型（0：通用，1：衍生品规格图）
     */
    private Integer businessType;

    /**
     * 关联业务ID
     */
    private Integer businessId;

    /**
     * 状态，0：失效，1：正常
     */
    private Boolean status;

    /**
     * 上传时间
     */
    private Date uploadTime;

    /**
     * 企业ID
     */
    private Integer enterpriseId;
}
