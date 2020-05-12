package com.mtl.cypw.mpm.service.impl;

import com.mtl.cypw.mpm.mapper.TheatreMapper;
import com.mtl.cypw.mpm.model.Theatre;
import com.mtl.cypw.mpm.service.TheatreService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author tang.
 * @date 2020/3/3.
 */
@Slf4j
@Service
public class TheatreServiceImpl implements TheatreService {

    @Resource
    TheatreMapper mapper;

    @Override
    public Theatre getTheatreById(Integer theatreId) {
        if (theatreId == null) {
            log.error("id不能为空");
            return null;
        }
        return mapper.selectByPrimaryKey(theatreId);
    }

    @Override
    public List<Theatre> getTheatreList(List<Integer> theatreIds) {
        if (CollectionUtils.isEmpty(theatreIds)) {
            log.error("id不能为空");
            return null;
        }
        Example example = new Example(Theatre.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andIn("theatre_id", theatreIds);
        criteria.andEqualTo("isEnable", 1);
        return mapper.selectByExample(example);
    }
}
