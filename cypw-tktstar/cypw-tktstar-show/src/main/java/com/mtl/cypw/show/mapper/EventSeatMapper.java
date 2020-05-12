package com.mtl.cypw.show.mapper;

import com.mtl.cypw.domain.show.dto.CountDTO;
import com.mtl.cypw.show.pojo.EventSeat;
import com.mtl.cypw.common.core.tkmybatis.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Mybatis Generator on 2019年11月22日 下午02:20:36
 */
public interface EventSeatMapper extends BaseMapper<EventSeat> {

    /**
     * 查询票价库存
     *
     * @param priceId
     * @param priceIds
     * @param seatStatus
     * @param seatStatusList
     * @return
     */
    List<CountDTO> searchSeatCount(@Param("priceId") Integer priceId, @Param("priceIds") List<Integer> priceIds,
                                   @Param("seatStatus") Integer seatStatus, @Param("seatStatusList") List<Integer> seatStatusList);
}