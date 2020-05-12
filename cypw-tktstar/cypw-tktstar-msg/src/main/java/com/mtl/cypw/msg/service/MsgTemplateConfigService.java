package com.mtl.cypw.msg.service;

import com.mtl.cypw.domain.msg.param.MsgSendParam;
import com.mtl.cypw.msg.mapper.MsgTemplateConfigMapper;
import com.mtl.cypw.msg.model.MsgTemplateConfig;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
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
public class MsgTemplateConfigService {

    @Resource
    MsgTemplateConfigMapper msgTemplateConfigMapper;

    public MsgTemplateConfig getMsgTemplateConfig(MsgSendParam param) {
        Example example = new Example(MsgTemplateConfig.class);
        Example.Criteria criteria = example.createCriteria();
        if (param != null) {
            if (param.getEnterpriseId() != null) {
                criteria.andEqualTo("enterpriseId", param.getEnterpriseId());
            }
            if (StringUtils.isNotEmpty(param.getMsgCode())) {
                criteria.andEqualTo("msgCode", param.getMsgCode());
            }
            criteria.andEqualTo("isEnable", 1);
            criteria.andEqualTo("deleted", 0);
        }
        List<MsgTemplateConfig> list = msgTemplateConfigMapper.selectByExample(example);
        if (CollectionUtils.isNotEmpty(list)) {
            return list.get(0);
        }
        return null;
    }

}
