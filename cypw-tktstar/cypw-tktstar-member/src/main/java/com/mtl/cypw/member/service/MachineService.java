package com.mtl.cypw.member.service;

import com.juqitech.service.utils.DateUtils;
import com.mtl.cypw.member.mapper.MachineMapper;
import com.mtl.cypw.member.pojo.Machine;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;

/**
 * @author tang.
 * @date 2020/3/11.
 */
@Service
public class MachineService {
    
    @Resource
    MachineMapper mapper;

    public void add(Machine machine) {
        if (machine == null) {
            return;
        }
        machine.setCreateDate(DateUtils.now());
        mapper.insertSelective(machine);
    }

    public void update(Machine machine) {
        machine.setUpdateDate(DateUtils.now());
        mapper.updateByPrimaryKeySelective(machine);
    }

    public Machine getMachine(Integer machineId) {
        if (machineId == null) {
            return null;
        }
        return mapper.selectByPrimaryKey(machineId);
    }

    public Machine getMachine(Integer enterpriseId, String machineKey) {
        if (enterpriseId == null || StringUtils.isEmpty(machineKey) ) {
            return null;
        }
        Example example = new Example(Machine.class);
        Example.Criteria cri = example.createCriteria();
        cri.andEqualTo("enterpriseId", enterpriseId);
        cri.andEqualTo("machineKey", machineKey);
        cri.andEqualTo("isDelete", 0);
        return mapper.selectOneByExample(example);
    }
}
