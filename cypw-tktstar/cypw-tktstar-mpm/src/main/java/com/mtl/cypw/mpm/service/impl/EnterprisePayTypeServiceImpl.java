package com.mtl.cypw.mpm.service.impl;

import com.mtl.cypw.domain.payment.enums.PayTypeEnum;
import com.mtl.cypw.mpm.mapper.EnterprisePayTypeMapper;
import com.mtl.cypw.mpm.model.EnterprisePayType;
import com.mtl.cypw.mpm.service.EnterprisePayTypeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author tang.
 * @date 2019/12/3.
 */
@Service
@Slf4j
public class EnterprisePayTypeServiceImpl implements EnterprisePayTypeService {

    @Autowired
    EnterprisePayTypeMapper mapper;

    @Override
    public List<EnterprisePayType> getEnterprisePayType(Integer enterpriseId) {
        log.debug("查询企业支持的支付方式,enterpriseId:{}", enterpriseId);
        if (enterpriseId == null) {
            return null;
        }
        Example example = new Example(EnterprisePayType.class);
        Example.Criteria cri = example.createCriteria();
        cri.andEqualTo("isEnable", 1);
        cri.andEqualTo("isDelete", 0);
        cri.andEqualTo("enterpriseId", enterpriseId);
        return mapper.selectByExample(example);
    }

    @Override
    public EnterprisePayType getEnterprisePayType(Integer enterpriseId, PayTypeEnum payTypeEnum) {
        log.debug("查询企业支付配置,enterpriseId:{}，payTypeEnum:{}", enterpriseId, payTypeEnum);
        if (enterpriseId == null || payTypeEnum == null) {
            return null;
        }
        Example example = new Example(EnterprisePayType.class);
        Example.Criteria cri = example.createCriteria();
        cri.andEqualTo("isEnable", 1);
        cri.andEqualTo("isDelete", 0);
        cri.andEqualTo("enterpriseId", enterpriseId);
        cri.andEqualTo("payTypeId", payTypeEnum.getPayCode());
        return mapper.selectOneByExample(example);
    }
}
