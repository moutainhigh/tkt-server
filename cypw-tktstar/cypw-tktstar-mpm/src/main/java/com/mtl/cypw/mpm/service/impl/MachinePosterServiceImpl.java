package com.mtl.cypw.mpm.service.impl;

import com.mtl.cypw.mpm.mapper.MachinePosterMapper;
import com.mtl.cypw.mpm.model.MachinePoster;
import com.mtl.cypw.mpm.service.MachinePosterService;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author tang.
 * @date 2020/3/16.
 */
@Service
public class MachinePosterServiceImpl implements MachinePosterService {

    @Resource
    private MachinePosterMapper machinePosterMapper;

    @Override
    public List<MachinePoster> searchMachinePoster(Integer enterpriseId) {
        if (enterpriseId == null) {
            return null;
        }
        Example example = new Example(MachinePoster.class);
        Example.Criteria cri = example.createCriteria();
        if (enterpriseId != null) {
            cri.andEqualTo("enterpriseId", enterpriseId);
        }
        cri.andEqualTo("isEnable", 1);
        cri.andEqualTo("isDelete", 0);
        example.orderBy("sortOrder").asc();
        List<MachinePoster> list = machinePosterMapper.selectByExample(example);
        return list;
    }
}
