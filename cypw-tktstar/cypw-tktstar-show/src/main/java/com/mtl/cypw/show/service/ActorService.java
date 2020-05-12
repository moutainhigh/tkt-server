package com.mtl.cypw.show.service;

import com.alibaba.fastjson.JSONObject;
import com.mtl.cypw.show.mapper.ActorMapper;
import com.mtl.cypw.show.pojo.Actor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author tang.
 * @date 2019/11/27.
 */
@Service
@Slf4j
public class ActorService {

    @Resource
    ActorMapper actorMapper;

    public List<Actor> searchActorList(Integer programId) {
        log.debug("根据演出ID查询演职人员列表，programId:{}", programId);
        Example example = new Example(Actor.class);
        Example.Criteria cri = example.createCriteria();
        if (programId != null) {
            cri.andEqualTo("programId", programId);
        }
        example.orderBy("sortOrder").asc();
        List<Actor> result = actorMapper.selectByExample(example);
        log.debug("演职人员列表查询结果，result:{}", JSONObject.toJSONString(result));
        return result;
    }
}
