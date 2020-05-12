package com.mtl.cypw.admin.service;

import com.mtl.cypw.admin.mapper.PermissionMapper;
import com.mtl.cypw.admin.pojo.Permission;
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
public class PermissionService {

    @Resource
    PermissionMapper permissionMapper;

    public List<Permission> gerPermissionByIds(List<Integer> permissionIdList) {
        if (CollectionUtils.isEmpty(permissionIdList)) {
            return null;
        }
        Example example = new Example(Permission.class);
        Example.Criteria cri = example.createCriteria();
        cri.andIn("permissionId", permissionIdList);
        return permissionMapper.selectByExample(example);
    }

    public List<Permission> gerAllPermission(Integer enterpriseId) {
        Example example = new Example(Permission.class);
        Example.Criteria cri = example.createCriteria();
        cri.andIsNull("enterpriseIds").orLike("enterpriseIds", "{" + enterpriseId + "}");
        return permissionMapper.selectByExample(example);
    }
}
