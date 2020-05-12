package com.mtl.cypw.domain.mpm.param;

import com.juqitech.request.BaseParam;
import com.juqitech.service.utils.ParamChecker;
import com.mtl.cypw.domain.mpm.enums.LinkTypeEnum;
import com.mtl.cypw.domain.mpm.enums.ResourceTypeEnum;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * @author Johnathon.Yuan
 * @date 2019-11-23 12:23
 */
@Setter
@Getter
@ToString
public class BannerSaveParam extends BaseParam {

    /**
     * Banner ID
     */
    private Integer id;

    /**
     * Banner 名称
     */
    private String name;

    /**
     * 图片链接 src
     */
    private String imageSrc;

    /**
     * 链接地址
     */
    private String linkAddress;

    /**
     * 链接类型，外部/内部
     * @see LinkTypeEnum
     */
    private String linkType;

    /**
     * 资源类型，演出/衍生品
     * @see ResourceTypeEnum
     */
    private String resourceType;

    /**
     * 资源ID
     */
    private String resourceId;

    /**
     * 开始时间
     */
    private Date beginTime;

    /**
     * 结束时间
     */
    private Date endTime;

    /**
     * 是否启用，0：未启用，1：已启用
     * @see com.mtl.cypw.common.enums.CommonStateEnum
     */
    private Integer isEnable;

    /**
     * 排序
     */
    private Integer sortOrder;

    /**
     * 最后修改人
     */
    private Integer lastModifier;

    /**
     * 企业ID
     */
    private Integer enterpriseId;

    @Override
    public void checkParam() {
        ParamChecker.notNull(name, "bannerName 为必填");
        ParamChecker.notNull(enterpriseId, "enterpriseId 为必填");
    }
}
