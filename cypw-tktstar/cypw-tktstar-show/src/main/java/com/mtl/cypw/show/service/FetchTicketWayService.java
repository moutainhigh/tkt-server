package com.mtl.cypw.show.service;

import com.mtl.cypw.domain.order.enums.DeliverTypeEnum;
import com.mtl.cypw.domain.show.constant.ShowConstant;
import com.mtl.cypw.show.mapper.FetchTicketWayMapper;
import com.mtl.cypw.show.pojo.FetchTicketWay;
import com.mtl.cypw.show.pojo.Program;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author Johnathon.Yuan
 * @date 2019-12-02 18:29
 */

@Slf4j
@Service
public class FetchTicketWayService {

    @Autowired
    private FetchTicketWayMapper fetchTicketWayMapper;

    public List<FetchTicketWay> findListByProgramId(Integer programId) {
        if (programId == null) {
            return null;
        }
        Example example = new Example(FetchTicketWay.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("deleted", 0);
        criteria.andEqualTo("programId", programId);
        return fetchTicketWayMapper.selectByExample(example);
    }

    public List<FetchTicketWay> findListByProgramIds(List<Integer> programIds) {
        if (programIds == null || programIds.size() == 0) {
            return null;
        }
        Example example = new Example(FetchTicketWay.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("deleted", 0);
        criteria.andIn("programId", programIds);
        return fetchTicketWayMapper.selectByExample(example);
    }

    public FetchTicketWay findOneByProgramIdAndType(Integer programId, DeliverTypeEnum deliverType) {
        Assert.isTrue(programId != null && deliverType != null, "查询取票方式参数错误");
        Example example = new Example(FetchTicketWay.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("deleted", 0);
        criteria.andEqualTo("programId", programId);
        criteria.andEqualTo("deliverType", deliverType.getCode());
        List<FetchTicketWay> list = fetchTicketWayMapper.selectByExample(example);
        if (CollectionUtils.isNotEmpty(list)) {
            return list.get(0);
        }
        return null;
    }

    /**
     * 初始化取票方式.
     * @param program 实体类
     * @return FetchTicketWay列表.
     */
    public List<FetchTicketWay> initTickeWayList(Program program) {
        List<FetchTicketWay> list = Lists.newArrayList();
        for (DeliverTypeEnum deliverTypeEnum : DeliverTypeEnum.values()) {
            FetchTicketWay fetchTicketWay = new FetchTicketWay();
            fetchTicketWay.setEnterpriseId(program.getEnterpriseId());
            fetchTicketWay.setProgramId(program.getProgramId());
            fetchTicketWay.setDeliverType(deliverTypeEnum.getCode());
            fetchTicketWay.setDeleted(1);
            switch (deliverTypeEnum) {
                case OFFLINE:
                case SPOT_PICKING:
                    fetchTicketWay.setExpressFee(ShowConstant.OFFLINE_EXPRESS_FREE_INIT);
                    break;
                case EXPRESS:
                    fetchTicketWay.setExpressFee(ShowConstant.EXPRESS_EXPRESS_FREE_INIT);
                    break;
                case E_TICKET:
                    //默认电子票选中
                    fetchTicketWay.setDeleted(0);
                    fetchTicketWay.setExpressFee(ShowConstant.OFFLINE_EXPRESS_FREE_INIT);
                    break;
                default:
            }
            list.add(fetchTicketWay);
        }
        return list;
    }
}
