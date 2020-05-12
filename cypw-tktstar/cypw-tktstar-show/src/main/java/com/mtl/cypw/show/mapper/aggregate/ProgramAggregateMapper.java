package com.mtl.cypw.show.mapper.aggregate;

import com.mtl.cypw.show.pojo.Program;
import com.mtl.cypw.show.pojo.aggregate.ProgramAggregate;

import java.util.List;

/**
 * @author sq
 * @date 2020/3/17  10:38
 */
public interface ProgramAggregateMapper {
    /**
     * 后台查询演出列表.
     * @param enterpriseId 商户id
     * @return 列表
     */
    List<ProgramAggregate> getProgramList(Integer enterpriseId);
}
