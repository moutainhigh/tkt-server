package com.mtl.cypw.mpm.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.juqitech.response.paging.Pagination;
import com.mtl.cypw.common.component.GenericRepository;
import com.mtl.cypw.domain.mpm.param.BannerQueryParam;
import com.mtl.cypw.mpm.mapper.BannerMapper;
import com.mtl.cypw.mpm.model.Banner;
import com.mtl.cypw.mpm.service.BannerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author Johnathon.Yuan
 * @date 2019-11-22 15:41
 */
@Slf4j
@Service
public class BannerServiceImpl implements BannerService, GenericRepository<Integer, Banner> {

    @Autowired
    private BannerMapper bannerMapper;

    @Override
    public Integer save(Banner model) {
        if (model.getBannerId() == null) {
            bannerMapper.insert(model);
        } else {
            bannerMapper.updateByPrimaryKeySelective(model);
        }
        return model.getBannerId();
    }

    @Override
    public Banner create(Banner banner) {
        this.save(banner);
        return banner;
    }

    @Override
    public Banner update(Banner banner) {
        this.save(banner);
        return banner;
    }

    @Override
    public Boolean delete(int id) {
        Banner banner = this.findOne(id);
        if (banner != null) {
            bannerMapper.delete(banner);
        }
        return true;
    }

    @Override
    public Banner findOne(int id) {
        return bannerMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Banner> findListAll(BannerQueryParam param) {
        Example example = new Example(Banner.class);
        this.setCriteria(param, example);
        example.orderBy("sortOrder").asc();
        return bannerMapper.selectByExample(example);
    }

    @Override
    public List<Banner> findPageAll(BannerQueryParam param, Pagination pagination) {
        Example example = new Example(Banner.class);
        this.setCriteria(param, example);
        example.orderBy("sortOrder").asc();
        // RowBounds rowBounds = new RowBounds(pagination.getOffset(), pagination.getLength());
        // List<Banner> banners = bannerMapper.selectByExampleAndRowBounds(example, rowBounds);
        Page page = PageHelper.offsetPage(pagination.getOffset(), pagination.getLength(), true);
        List<Banner> banners = bannerMapper.selectByExample(example);
        pagination.setCount(page.getTotal());
        return banners;
    }

    private void setCriteria(BannerQueryParam param, Example example) {
        Example.Criteria criteria = example.createCriteria();
        if (param.getCommonState() != null) {
            criteria.andEqualTo("isEnable", param.getCommonState().getCode());
        }
        if (param.getEnterpriseId() != null) {
            criteria.andEqualTo("enterpriseId", param.getEnterpriseId());
        }
        if(param.getTypeEnum() != null) {
            criteria.andEqualTo("bannerType", param.getTypeEnum().getCode());
        }
    }

}
