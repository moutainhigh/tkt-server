package com.mtl.cypw.show.mapper;

import com.mtl.cypw.common.core.tkmybatis.BaseMapper;
import com.mtl.cypw.domain.show.dto.EventPriceDTO;
import com.mtl.cypw.show.pojo.EventPrice;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Mybatis Generator on 2019年11月22日 下午02:20:36
 */
public interface EventPriceMapper extends BaseMapper<EventPrice> {

    /**
     * 根据场次演出id分组统计最低票价
     *
     * @param programIds
     * @return
     */
    List<EventPriceDTO> searchMinPriceByProgramIds(@Param("programIds") List<Integer> programIds);
}