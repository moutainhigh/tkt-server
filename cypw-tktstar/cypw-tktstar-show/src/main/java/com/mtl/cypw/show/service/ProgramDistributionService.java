package com.mtl.cypw.show.service;

import com.alibaba.fastjson.JSONObject;
import com.mtl.cypw.show.mapper.ProgramDistributionMapper;
import com.mtl.cypw.show.pojo.ProgramDistribution;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author tang.
 * @date 2019/11/21.
 */
@Service
@Slf4j
public class ProgramDistributionService {

    @Resource
    ProgramDistributionMapper programDistributionMapper;

    public List<ProgramDistribution> searchProgramDistributionList(Integer programId) {
        log.debug("查询演出分销列表，programId:{}", programId);
        Example example = new Example(ProgramDistribution.class);
        Example.Criteria cri = example.createCriteria();
        cri.andEqualTo("programId", programId);
        List<ProgramDistribution> result =  programDistributionMapper.selectByExample(example);
        log.debug("演出分销列表查询结果，result:{}", JSONObject.toJSONString(result));
        return result;
    }
}
