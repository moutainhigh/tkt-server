package com.mtl.cypw.show.service;

import com.alibaba.fastjson.JSONObject;
import com.mtl.cypw.show.mapper.ShortageRegistrationMapper;
import com.mtl.cypw.show.pojo.ShortageRegistration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author tang.
 * @date 2019/12/2.
 */
@Slf4j
@Service
public class ShortageRegistrationService {

    @Resource
    ShortageRegistrationMapper mapper;

    public void create(ShortageRegistration shortageRegistration) {
        log.debug("新增缺货登记，shortageRegistration：{}", JSONObject.toJSONString(shortageRegistration));
        if (shortageRegistration != null) {
            shortageRegistration.setCreateTime(new Date());
            mapper.insert(shortageRegistration);
        }
    }

    public ShortageRegistration getShortageRegistration(Integer memberId, Integer eventPriceId) {
        if (memberId == null || eventPriceId == null) {
            return null;
        }
        Example example = new Example(ShortageRegistration.class);
        Example.Criteria cri = example.createCriteria();
        cri.andEqualTo("memberId", memberId);
        cri.andEqualTo("eventPriceId", eventPriceId);
        List<ShortageRegistration> list = mapper.selectByExample(example);
        if (list != null && list.size() > 0) {
            return list.get(0);
        }
        return null;
    }
}
