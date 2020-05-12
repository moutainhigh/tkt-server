package com.mtl.cypw.mpm.model;

import com.mtl.cypw.common.core.tkmybatis.BaseModel;
import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author Johnathon.Yuan
 * @date 2020-03-05 16:45
 */
@Data
@Table(name = "cy_resource_media_file")
public class ResourceMediaFile extends BaseModel {
    /**
     * 自增主键
     */
    @Id
    @KeySql(useGeneratedKeys = true)
    @Column(name = "id")
    private Integer id;

    /**
     * 资源路径（ucloud.cn 路径）
     */
    @Column(name = "file_src")
    private String fileSrc;

    /**
     * 资源类型（1：图片，2：视频，3：文件）
     */
    @Column(name = "file_type")
    private Integer fileType;

    /**
     * 关联业务类型（0：通用，1：衍生品规格图）
     */
    @Column(name = "business_type")
    private Integer businessType;

    /**
     * 关联业务ID
     */
    @Column(name = "business_id")
    private Integer businessId;

    /**
     * 状态，0：失效，1：正常
     */
    @Column(name = "status")
    private Integer status;

    /**
     * 上传时间
     */
    @Column(name = "upload_time")
    private Date uploadTime;

    /**
     * 企业ID
     */
    @Column(name = "enterprise_id")
    private Integer enterpriseId;
}