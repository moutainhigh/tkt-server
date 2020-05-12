package com.mtl.cypw.mpm.service.impl;

import com.mtl.cypw.domain.mpm.param.VenueQueryParam;
import com.mtl.cypw.mpm.mapper.VenueMapper;
import com.mtl.cypw.mpm.model.Venue;
import com.mtl.cypw.mpm.service.VenueService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author tang.
 * @date 2019/12/12.
 */
@Slf4j
@Service
public class VenueServiceImpl implements VenueService {

    @Resource
    private VenueMapper mapper;

    @Override
    public Venue getVenueById(Integer id) {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Venue> getVenueList(VenueQueryParam param) {
        Example example = new Example(Venue.class);
        Example.Criteria criteria = example.createCriteria();
        if (param != null) {
            if (param.getVenueId() != null) {
                criteria.andEqualTo("venueId", param.getVenueId());
            }
            if (param.getTheatreId() != null) {
                criteria.andEqualTo("theatreId", param.getTheatreId());
            }
            if (CollectionUtils.isNotEmpty(param.getVenueIds())) {
                criteria.andIn("venueId", param.getVenueIds());
            }
            if (CollectionUtils.isNotEmpty(param.getTheatreIds())) {
                criteria.andIn("theatreId", param.getTheatreIds());
            }
        }
        criteria.andEqualTo("isEnable", 1);
        criteria.andEqualTo("isDelete", 0);
        return mapper.selectByExample(example);
    }
}
