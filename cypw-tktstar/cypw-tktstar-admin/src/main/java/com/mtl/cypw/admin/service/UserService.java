package com.mtl.cypw.admin.service;

import com.mtl.cypw.admin.mapper.UserMapper;
import com.mtl.cypw.admin.pojo.User;
import com.mtl.cypw.domain.admin.param.UserQueryParam;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author tang.
 * @date 2020/3/17.
 */
@Service
public class UserService {

    @Resource
    UserMapper userMapper;

    public User gerUser(String loginName, Integer enterpriseId) {
        Example example = new Example(User.class);
        Example.Criteria cri = example.createCriteria();
        cri.andEqualTo("loginName", loginName);
        cri.andEqualTo("enterpriseId", enterpriseId);
        cri.andEqualTo("isEnable", 1);
        cri.andEqualTo("isDelete", 0);
        return userMapper.selectOneByExample(example);
    }

    public List<User> searchUser(UserQueryParam queryParam) {
        Example example = new Example(User.class);
        Example.Criteria cri = example.createCriteria();
        cri.andEqualTo("isEnable", 1);
        cri.andEqualTo("isDelete", 0);
        if (queryParam != null) {
            if (StringUtils.isNotEmpty(queryParam.getLoginName())) {
                cri.andEqualTo("loginName", queryParam.getLoginName());
            }
            if (queryParam.getEnterpriseId() != null) {
                cri.andEqualTo("enterpriseId", queryParam.getEnterpriseId());
            }
            if (queryParam.getUserId() != null) {
                cri.andEqualTo("userId", queryParam.getUserId());
            }
            if (CollectionUtils.isNotEmpty(queryParam.getUserIdList())) {
                cri.andIn("userId", queryParam.getUserIdList());
            }
            if (queryParam.getIsEnable() != null) {
                cri.andEqualTo("isEnable", queryParam.getIsEnable());
            }
            if (queryParam.getIsDelete() != null) {
                cri.andEqualTo("isDelete", queryParam.getIsDelete());
            }
        }
        return userMapper.selectByExample(example);
    }

    public void update(User user) {
        userMapper.updateByPrimaryKeySelective(user);
    }
}
