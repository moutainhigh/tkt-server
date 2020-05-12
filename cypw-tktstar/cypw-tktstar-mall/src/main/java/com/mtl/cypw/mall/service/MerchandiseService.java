package com.mtl.cypw.mall.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.juqitech.response.paging.Pagination;
import com.mtl.cypw.common.enums.DeleteEnum;
import com.mtl.cypw.domain.mall.param.MerchandiseQueryParam;
import com.mtl.cypw.mall.mapper.MerchandiseMapper;
import com.mtl.cypw.mall.model.Merchandise;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author Johnathon.Yuan
 * @date 2020-03-04 18:01
 */
@Slf4j
@Component
public class MerchandiseService {

    @Autowired
    private MerchandiseMapper merchandiseMapper;

    /**
     * 对内服务详情
     * @param merchandiseId
     * @return
     */
    public Merchandise get(Integer merchandiseId) {
        return merchandiseMapper.selectByPrimaryKey(merchandiseId);
    }

    /**
     * 对外服务详情
     * @param merchandiseId
     * @param enterpriseId
     * @return
     */
    public Merchandise getDetail(Integer merchandiseId, Integer enterpriseId) {
        Merchandise merchandise = new Merchandise();
        merchandise.setMerchandiseId(merchandiseId);
        merchandise.setEnterpriseId(enterpriseId);
        merchandise.setIsDelete(DeleteEnum.EXIST.getCode());
        return merchandiseMapper.selectOne(merchandise);
    }

    public List<Merchandise> pageQuery(MerchandiseQueryParam param, Pagination pagination) {
        Example example = new Example(Merchandise.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("enterpriseId", param.getEnterpriseId());
        criteria.andEqualTo("isDelete", DeleteEnum.EXIST.getCode());
        Page page = PageHelper.startPage(pagination.getOffset(), pagination.getLength(), true);
        List<Merchandise> merchandises = merchandiseMapper.selectByExample(example);
        pagination.setCount(page.getTotal());
        return merchandises;
    }


}
