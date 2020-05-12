package com.mtl.cypw.msg.service;

import com.mtl.cypw.msg.mapper.MsgTemplateVariableMapper;
import com.mtl.cypw.msg.model.MsgTemplateVariable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author tang.
 * @date 2020/3/5.
 */
@Slf4j
@Service
public class MsgTemplateVariableService {

    @Resource
    private MsgTemplateVariableMapper mapper;

    public List<MsgTemplateVariable> getMsgTemplateVariable(Integer templateConfigId) {
        Example example = new Example(MsgTemplateVariable.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("templateConfigId", templateConfigId);
        criteria.andEqualTo("isEnable", 1);
        criteria.andEqualTo("deleted", 0);
        return mapper.selectByExample(example);
    }

    public List<MsgTemplateVariable> getMsgTemplateVariable(String templateCode, Integer enterpriseId) {
        Example example = new Example(MsgTemplateVariable.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("enterpriseId", enterpriseId);
        criteria.andEqualTo("templateCode", templateCode);
        criteria.andEqualTo("isEnable", 1);
        criteria.andEqualTo("deleted", 0);
        return mapper.selectByExample(example);
    }
}
