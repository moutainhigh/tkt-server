package com.mtl.cypw.mpm.service.impl;

import com.mtl.cypw.mpm.mapper.EnterpriseDialogMapper;
import com.mtl.cypw.mpm.model.EnterpriseDialog;
import com.mtl.cypw.mpm.service.EnterpriseDialogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author tang.
 * @date 2020/02/18.
 */
@Service
@Slf4j
public class EnterpriseDialogServiceImpl implements EnterpriseDialogService {

    @Autowired
    EnterpriseDialogMapper mapper;

    @Override
    public EnterpriseDialog getEnterpriseDialog(Integer enterpriseId, Integer isShow) {
        if (enterpriseId == null) {
            return null;
        }
        Example example = new Example(EnterpriseDialog.class);
        Example.Criteria cri = example.createCriteria();
        cri.andEqualTo("enterpriseId", enterpriseId);
        if (isShow != null) {
            cri.andEqualTo("isShow", isShow);
        }
        List<EnterpriseDialog> list = mapper.selectByExample(example);
        if (list != null && list.size() > 0) {
            return list.get(0);
        }
        return null;
    }
}
