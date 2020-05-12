package com.mtl.cypw.coupon.service;

import com.mtl.cypw.coupon.mapper.PromotionProgramMapper;
import com.mtl.cypw.coupon.pojo.PromotionProgram;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author tang.
 * @date 2019/12/4.
 */
@Service
@Slf4j
public class PromotionProgramService {

    @Resource
    PromotionProgramMapper mapper;

    public List<PromotionProgram> searchPromotionProgramList(Integer programId) {
        Example example = new Example(PromotionProgram.class);
        Example.Criteria cri = example.createCriteria();
        cri.andEqualTo("programId", programId);
        return mapper.selectByExample(example);
    }
}
