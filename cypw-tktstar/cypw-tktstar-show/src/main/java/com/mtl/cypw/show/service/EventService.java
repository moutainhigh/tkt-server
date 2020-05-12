package com.mtl.cypw.show.service;

import com.alibaba.fastjson.JSONObject;
import com.mtl.cypw.domain.show.query.EventQuery;
import com.mtl.cypw.mpm.model.Zone;
import com.mtl.cypw.mpm.service.TemplateService;
import com.mtl.cypw.show.mapper.EventMapper;
import com.mtl.cypw.show.pojo.Event;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.BooleanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

/**
 * @author tang.
 * @date 2019/11/22.
 */
@Service
@Slf4j
public class EventService {

    @Resource
    EventMapper eventMapper;

    @Autowired
    private TemplateService templateServiceImpl;

    public List<Event> searchEventList(EventQuery query) {
        log.debug("查询场次列表，request:{}", JSONObject.toJSONString(query));
        Example example = new Example(Event.class);
        Example.Criteria cri = example.createCriteria();
        if (query != null) {
            if (query.getProgramId() != null) {
                cri.andEqualTo("programId", query.getProgramId());
            }
            if (query.getIsEnable() != null) {
                cri.andEqualTo("isEnable", query.getIsEnable());
            }
            if (query.getLessSaleDateBegin() != null) {
                cri.andLessThanOrEqualTo("saleDateBegin", query.getLessSaleDateBegin());
            }
            if (query.getGreaterSaleDateBegin() != null) {
                cri.andGreaterThanOrEqualTo("saleDateBegin", query.getGreaterSaleDateBegin());
            }
            if (query.getLessSaleDateEnd() != null) {
                cri.andLessThanOrEqualTo("saleDateEnd", query.getLessSaleDateEnd());
            }
            if (query.getGreaterSaleDateEnd() != null) {
                cri.andGreaterThanOrEqualTo("saleDateEnd", query.getGreaterSaleDateEnd());
            }
            if (query.getEnterpriseId() != null) {
                cri.andEqualTo("enterpriseId", query.getEnterpriseId());
            }
            if(query.getLessEventDate()!=null){
                cri.andLessThanOrEqualTo("eventDate", query.getLessEventDate());
            }
            if(query.getGreaterEventDate()!=null){
                cri.andGreaterThanOrEqualTo("eventDate", query.getGreaterEventDate());
            }
        }
        example.orderBy("eventDate").asc();
        List<Event> result = eventMapper.selectByExample(example);
        log.debug("场次列表查询结果，result:{}", JSONObject.toJSONString(result));
        return result;
    }

    public Event findOneById(Integer eventId) {
        return eventMapper.selectByPrimaryKey(eventId);
    }

    public List<Event> findByIds(List<Integer> eventIds) {
        return eventMapper.selectByIdList(eventIds);
    }

    public List<Zone> findTemplateZonesByEventId(Integer eventId) {
        Event event = this.findOneById(eventId);
        if (event == null || event.getTemplateId() == null || !BooleanUtils.toBoolean(event.getIsSeat())) {
            return Collections.emptyList();
        }
        return templateServiceImpl.findZonesByTemplateId(event.getTemplateId());
    }

}
