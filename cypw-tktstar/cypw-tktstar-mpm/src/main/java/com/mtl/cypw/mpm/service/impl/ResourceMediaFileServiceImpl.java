package com.mtl.cypw.mpm.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.google.common.collect.Lists;
import com.juqitech.response.paging.Pagination;
import com.mtl.cypw.common.enums.CommonStateEnum;
import com.mtl.cypw.domain.mpm.enums.BusinessTypeEnum;
import com.mtl.cypw.domain.mpm.param.ResourceMediaFileQueryParam;
import com.mtl.cypw.mpm.mapper.ResourceMediaFileMapper;
import com.mtl.cypw.mpm.model.ResourceMediaFile;
import com.mtl.cypw.mpm.service.ResourceMediaFileService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Collections;
import java.util.List;

/**
 * @author Johnathon.Yuan
 * @date 2020-03-05 17:44
 */
@Slf4j
@Service
public class ResourceMediaFileServiceImpl implements ResourceMediaFileService {

    @Autowired
    private ResourceMediaFileMapper resourceMediaFileMapper;

    @Override
    public List<ResourceMediaFile> findFiles(ResourceMediaFileQueryParam param,  Pagination pagination) {
        if (param == null) {
            return Collections.emptyList();
        }
        Example example = new Example(ResourceMediaFile.class);
        Example.Criteria criteria = example.createCriteria();
        if (param.getEnterpriseId() != null) {
            criteria.andEqualTo("enterpriseId", param.getEnterpriseId());
        }
        if (param.getBusinessId() != null) {
            criteria.andEqualTo("businessId", param.getBusinessId());
        }
        if (CollectionUtils.isNotEmpty(param.getFileTypes())) {
            criteria.andIn("fileType", param.getFileTypes());
        }
        if (CollectionUtils.isNotEmpty(param.getBusinessTypes())) {
            criteria.andIn("businessType", param.getBusinessTypes());
        }
        if (param.getStatus() != null) {
            criteria.andEqualTo("status", param.getStatus());
        } else {
            criteria.andEqualTo("status", CommonStateEnum.VALID.getCode());
        }
        Page page = null;
        if (pagination != null) {
            page = PageHelper.startPage(pagination.getOffset(), pagination.getLength(), true);
        }
        List<ResourceMediaFile> files = resourceMediaFileMapper.selectByExample(example);
        if (pagination != null) {
            pagination.setCount(page.getTotal());
        }
        return files;
    }

    @Override
    public List<ResourceMediaFile> findFilesByPriceIdWithMall(Integer priceId) {
        ResourceMediaFileQueryParam param = new ResourceMediaFileQueryParam();
        param.setBusinessId(priceId);
        param.setBusinessTypes(Lists.newArrayList(BusinessTypeEnum.PIC_GOODS_SPEC.getCode()));
        return this.findFiles(param, null);
    }
}
