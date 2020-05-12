package com.mtl.cypw.show.service;

import com.alibaba.fastjson.JSONObject;
import com.mtl.cypw.common.enums.CommonStateEnum;
import com.mtl.cypw.common.enums.SkuTypeEnum;
import com.mtl.cypw.domain.show.dto.EventPriceDTO;
import com.mtl.cypw.domain.show.query.EventQuery;
import com.mtl.cypw.show.mapper.EventPriceMapper;
import com.mtl.cypw.show.pojo.EventPrice;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author tang.
 * @date 2019/11/22.
 */
@Service
@Slf4j
public class EventPriceService {

    @Resource
    EventPriceMapper eventPriceMapper;

    public List<EventPrice> searchEventPriceList(EventQuery query) {
        log.debug("查询场次票价列表，request:{}", JSONObject.toJSONString(query));
        Example example = new Example(EventPrice.class);
        Example.Criteria cri = example.createCriteria();
        if (query != null) {
            if (query.getEventId() != null) {
                cri.andEqualTo("eventId", query.getEventId());
            }
            if (query.getProgramId() != null) {
                cri.andEqualTo("programId", query.getProgramId());
            }
            if (query.getIsEnable() != null) {
                cri.andEqualTo("isEnable", query.getIsEnable());
            }
            if (query.getProductType() != null) {
                cri.andEqualTo("productType", query.getProductType());
            }
        }
        List<EventPrice> result = eventPriceMapper.selectByExample(example);
        log.debug("场次票价列表查询结果，result:{}", JSONObject.toJSONString(result));
        return result;
    }

    public EventPrice searchMinEventPriceByEventId(Integer eventId) {
        if (eventId == null) {
            log.error("error message eventId is null");
            return null;
        }
        List<Integer> eventIdList = new ArrayList<>();
        eventIdList.add(eventId);
        return searchMinEventPriceByEventId(eventIdList);
    }

    public EventPrice searchMinEventPriceByEventId(List<Integer> eventIdList) {
        if (eventIdList == null || eventIdList.size() == 0) {
            log.error("error message eventId is null");
            return null;
        }
        log.debug("根据场次id查询场次最低票价，eventIds:{}", eventIdList);
        Example example = new Example(EventPrice.class);
        Example.Criteria cri = example.createCriteria();
        cri.andIn("eventId", eventIdList);
        cri.andEqualTo("isEnable", 1);
        example.orderBy("priceValue").asc();
        List<EventPrice> result = eventPriceMapper.selectByExample(example);
        if (CollectionUtils.isNotEmpty(result)) {
            log.debug("场次最低票价查询结果，result:{}", JSONObject.toJSONString(result.get(0)));
            return result.get(0);
        }
        log.debug("未查询到票价");
        return null;
    }

    public EventPrice getEventPriceById(Integer eventPriceId) {
        log.debug("查询场次票价，eventPriceId:{}", eventPriceId);
        if (eventPriceId == null) {
            return null;
        }
        return eventPriceMapper.selectByPrimaryKey(eventPriceId);
    }

    public List<EventPriceDTO> searchMinEventPriceByProgramIds(List<Integer> programIds) {
        log.debug("根据演出id分组查询，演出最低票价");
        if (programIds == null || programIds.size() == 0) {
            log.error("error message eventId is null");
            return null;
        }
        return eventPriceMapper.searchMinPriceByProgramIds(programIds);
    }

    public List<EventPrice> findPricesByEventId(Integer eventId) {
        EventQuery query = new EventQuery();
        query.setEventId(eventId);
        query.setIsEnable(CommonStateEnum.VALID.getCode());
        return this.searchEventPriceList(query);
    }

    public List<EventPrice> findPricesByProductIdWithMall(Integer productId) {
        EventQuery query = new EventQuery();
        query.setEventId(productId);
        query.setIsEnable(CommonStateEnum.VALID.getCode());
        query.setProductType(SkuTypeEnum.GOODS.getCode());
        return this.searchEventPriceList(query);
    }
}
