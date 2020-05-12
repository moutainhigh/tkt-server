package com.mtl.cypw.admin.service;

import com.mtl.cypw.admin.mapper.UserPermissionMapper;
import com.mtl.cypw.admin.pojo.UserPermission;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author tang.
 * @date 2020/3/17.
 */
@Service
public class UserPermissionService {

    @Resource
    UserPermissionMapper userPermissionMapper;

    public List<UserPermission> gerPermissionByRoleId(Integer roleId) {
        if (roleId == null) {
            return null;
        }
        Example example = new Example(UserPermission.class);
        Example.Criteria cri = example.createCriteria();
        cri.andEqualTo("roleId", roleId);
        return userPermissionMapper.selectByExample(example);
    }

    public List<UserPermission> gerPermissionByRoled(List<Integer> roleIdList) {
        if (CollectionUtils.isEmpty(roleIdList)) {
            return null;
        }
        Example example = new Example(UserPermission.class);
        Example.Criteria cri = example.createCriteria();
        cri.andIn("roleId", roleIdList);
        return userPermissionMapper.selectByExample(example);
    }

}
