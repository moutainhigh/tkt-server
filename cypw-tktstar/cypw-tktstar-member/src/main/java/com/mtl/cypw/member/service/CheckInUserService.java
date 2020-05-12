package com.mtl.cypw.member.service;

import com.juqitech.service.utils.DateUtils;
import com.mtl.cypw.domain.member.param.CheckInUserQueryParam;
import com.mtl.cypw.member.mapper.CheckInUserMapper;
import com.mtl.cypw.member.pojo.CheckInUser;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author tang.
 * @date 2020/2/12.
 */
@Service
@Slf4j
public class CheckInUserService {

    @Resource
    CheckInUserMapper mapper;

    public void add(CheckInUser user) {
        if (user == null) {
            return;
        }
        user.setAddDate(DateUtils.now());
        mapper.insert(user);
    }

    public CheckInUser getCheckInUser(Integer userId) {
        if (userId == null) {
            return null;
        }
        return mapper.selectByPrimaryKey(userId);
    }

    public CheckInUser getCheckInUser(Integer enterpriseId, String userName) {
        if (enterpriseId == null || StringUtils.isEmpty(userName) ) {
            return null;
        }
        Example example = new Example(CheckInUser.class);
        Example.Criteria cri = example.createCriteria();
        cri.andEqualTo("enterpriseId", enterpriseId);
        cri.andEqualTo("userName", userName);
        return mapper.selectOneByExample(example);
    }

    public List<CheckInUser> searchCheckInUser(CheckInUserQueryParam query) {
        Example example = new Example(CheckInUser.class);
        Example.Criteria cri = example.createCriteria();
        if (query != null) {
            if (query.getEnterpriseId() != null) {
                cri.andEqualTo("enterpriseId", query.getEnterpriseId());
            }
            if (query.getIsEnable() != null) {
                cri.andEqualTo("isEnable", query.getIsEnable());
            }
            if (StringUtils.isNotEmpty(query.getUserName())) {
                cri.andEqualTo("userName", query.getUserName());
            }
            if (StringUtils.isNotEmpty(query.getUserPass())) {
                cri.andEqualTo("userPass", query.getUserPass());
            }
            if (query.getGreaterBeginDate() != null) {
                cri.andGreaterThanOrEqualTo("beginDate", query.getGreaterBeginDate());
            }
            if (query.getLessBeginDate() != null) {
                cri.andLessThanOrEqualTo("beginDate", query.getLessBeginDate());
            }
            if (query.getGreaterEndDate() != null) {
                cri.andGreaterThanOrEqualTo("endDate", query.getGreaterEndDate());
            }
            if (query.getLessEndDate() != null) {
                cri.andLessThanOrEqualTo("endDate", query.getLessEndDate());
            }
        }
        List list = mapper.selectByExample(example);
        return list;
    }
}
