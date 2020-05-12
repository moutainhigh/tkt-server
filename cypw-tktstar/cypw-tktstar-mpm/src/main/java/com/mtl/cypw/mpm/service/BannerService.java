package com.mtl.cypw.mpm.service;

import com.juqitech.response.paging.Pagination;
import com.mtl.cypw.domain.mpm.param.BannerQueryParam;
import com.mtl.cypw.mpm.model.Banner;

import java.util.List;

/**
 * @author zhengyou.yuan
 * @date 2019-11-22 14:53
 */
public interface BannerService {

    /**
     * 新增
     * @param banner
     * @return
     */
    Banner create(Banner banner);

    /**
     * 更新
     * @param banner
     * @return
     */
    Banner update(Banner banner);

    /**
     * 删除
     * @param id
     * @return
     */
    Boolean delete(int id);

    /**
     * 详情
     * @param id
     * @return
     */
    Banner findOne(int id);

    /**
     * 获取列表
     * @param param
     * @return
     */
    List<Banner> findListAll(BannerQueryParam param);

    /**
     * 分页列表
     * @param param
     * @param pagination
     * @return
     */
    List<Banner> findPageAll(BannerQueryParam param, Pagination pagination);

}
