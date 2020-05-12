package com.mtl.cypw.admin.service;

import com.mtl.cypw.admin.mapper.ModuleMapper;
import com.mtl.cypw.admin.pojo.Module;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author tang.
 * @date 2020/3/17.
 */
@Service
public class ModuleService {

    @Resource
    ModuleMapper moduleMapper;

    public List<Module> getModules() {
        return moduleMapper.selectAll();
    }
}
