package com.mtl.cypw.show.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.juqitech.response.paging.Pagination;
import com.mtl.cypw.domain.show.query.ActivityQuery;
import com.mtl.cypw.show.mapper.ActivityMapper;
import com.mtl.cypw.show.pojo.Activity;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author tang.
 * @date 2020/1/3.
 */
@Service
public class ActivityService {

    @Resource
    ActivityMapper mapper;

    public List<Activity> searchActivity(ActivityQuery query) {
        Example example = new Example(Activity.class);
        Example.Criteria cri = example.createCriteria();
        if (query != null) {
            if (CollectionUtils.isNotEmpty(query.getActivityIdList())) {
                cri.andIn("activityId", query.getActivityIdList());
            }
            if (query.getEnterpriseId() != null) {
                cri.andEqualTo("enterpriseId", query.getEnterpriseId());
            }
            if (query.getIsEnable() != null) {
                cri.andEqualTo("isEnable", query.getIsEnable());
            }
            if (query.getTypeId() != null) {
                cri.andEqualTo("typeId", query.getTypeId());
            }
            if (query.getLessBeginDate() != null) {
                cri.andLessThan("beginDate", query.getLessBeginDate());
            }
            if (query.getGreaterBeginDate() != null) {
                cri.andGreaterThan("beginDate", query.getGreaterBeginDate());
            }
            if (query.getLessEndDate() != null) {
                cri.andLessThan("endDate", query.getLessEndDate());
            }
            if (query.getGreaterEndDate() != null) {
                cri.andGreaterThan("endDate", query.getGreaterEndDate());
            }
        }
        cri.andEqualTo("isDelete", 0);
        example.orderBy("sortOrder").asc();
        example.orderBy("addDate").desc();
        return mapper.selectByExample(example);
    }

    public List<Activity> searchActivity(ActivityQuery query, Pagination pagination) {
        Page page = PageHelper.startPage(pagination.getOffset(), pagination.getLength());
        List<Activity> list  = searchActivity(query);
        pagination.setCount(page.getTotal());
        return list;
    }

    public Activity getActivity(Integer activityId) {
        return mapper.selectByPrimaryKey(activityId);
    }
}
