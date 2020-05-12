package com.mtl.cypw.mpm.service.impl;

import com.mtl.cypw.domain.mpm.param.TemplateQueryParam;
import com.mtl.cypw.mpm.mapper.TemplateMapper;
import com.mtl.cypw.mpm.mapper.ZoneMapper;
import com.mtl.cypw.mpm.model.Template;
import com.mtl.cypw.mpm.model.Zone;
import com.mtl.cypw.mpm.service.TemplateService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author Johnathon.Yuan
 * @date 2020-01-10 17:44
 */
@Slf4j
@Component
public class TemplateServiceImpl implements TemplateService {

    @Autowired
    private ZoneMapper zoneMapper;

    @Autowired
    private TemplateMapper templateMapper;

    private Integer save(Template model) {
        if (model.getTemplateId() == null) {
            templateMapper.insert(model);
        } else {
            templateMapper.updateByPrimaryKeySelective(model);
        }
        return model.getTemplateId();
    }

    @Override
    public Template create(Template template) {
        this.save(template);
        return template;
    }

    @Override
    public Template update(Template template) {
        this.save(template);
        return template;
    }

    @Override
    public Template getTemplate(Integer templateId) {
        return templateMapper.selectByPrimaryKey(templateId);
    }

    @Override
    public List<Template> getTemplateList(TemplateQueryParam param) {
        Example example = new Example(Template.class);
        Example.Criteria criteria = example.createCriteria();
        if (param != null) {
            if (param.getTemplateId() != null) {
                criteria.andEqualTo("templateId", param.getTemplateId());
            }
            if (param.getVenueId() != null) {
                criteria.andEqualTo("venueId", param.getVenueId());
            }
            if (CollectionUtils.isNotEmpty(param.getTemplateIds())) {
                criteria.andIn("templateId", param.getTemplateIds());
            }
            if (CollectionUtils.isNotEmpty(param.getVenueIds())) {
                criteria.andIn("venueId", param.getVenueIds());
            }
        }
        criteria.andEqualTo("isEnable", 1);
        criteria.andEqualTo("isDelete", 0);
        return templateMapper.selectByExample(example);
    }

    @Override
    public List<Zone> findZonesByTemplateId(Integer templateId) {
        Example example = new Example(Zone.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("templateId", templateId);
        return zoneMapper.selectByExample(example);
    }

}
