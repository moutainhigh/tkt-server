package com.mtl.cypw.admin.service;

import com.mtl.cypw.admin.mapper.EnterprisePermissionMapper;
import com.mtl.cypw.admin.pojo.EnterprisePermission;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author tang.
 * @date 2020/3/19.
 */
@Service
public class EnterprisePermissionService {

    @Resource
    EnterprisePermissionMapper mapper;

    public List<EnterprisePermission> getEnterprisePermission(Integer enterpriseId) {
        if (enterpriseId == null) {
            return null;
        }
        Example example = new Example(EnterprisePermission.class);
        Example.Criteria cri = example.createCriteria();
        cri.andEqualTo("enterpriseId", enterpriseId);
        return mapper.selectByExample(example);
    }

    public List<Integer> getPermissionIdList(Integer enterpriseId) {
        List<EnterprisePermission> list = getEnterprisePermission(enterpriseId);
        List<Integer> idList = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(list)) {
            list.forEach(n -> idList.add(n.getPermissionId()));
        }
        return idList;
    }
}
