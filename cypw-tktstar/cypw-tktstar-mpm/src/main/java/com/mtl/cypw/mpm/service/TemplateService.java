package com.mtl.cypw.mpm.service;

import com.mtl.cypw.domain.mpm.param.TemplateQueryParam;
import com.mtl.cypw.mpm.model.Template;
import com.mtl.cypw.mpm.model.Zone;

import java.util.List;


/**
 * @author Johnathon.Yuan
 * @date 2020-01-10 17:35
 */
public interface TemplateService {

    /**
     * 新增
     *
     * @param template
     * @return
     */
    Template create(Template template);

    /**
     * 更新
     *
     * @param template
     * @return
     */
    Template update(Template template);

    /**
     * 详情
     *
     * @param templateId
     * @return
     */
    Template getTemplate(Integer templateId);

    /**
     * 批量查询模板
     *
     * @param param
     * @return
     */
    List<Template> getTemplateList(TemplateQueryParam param);

    /**
     * 模板区域列表
     *
     * @param templateId
     * @return
     */
    List<Zone> findZonesByTemplateId(Integer templateId);

}
