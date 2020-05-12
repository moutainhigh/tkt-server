package com.mtl.cypw.stock.repository;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.juqitech.response.paging.Pagination;
import com.mtl.cypw.common.component.GenericRepository;
import com.mtl.cypw.common.util.CollatorUtils;
import com.mtl.cypw.domain.stock.param.ReserveQuerySpec;
import com.mtl.cypw.domain.stock.param.ReserveSeatQuerySpec;
import com.mtl.cypw.stock.mapper.ReserveFormMapper;
import com.mtl.cypw.stock.mapper.ReserveSeatDetailMapper;
import com.mtl.cypw.stock.model.ReserveForm;
import com.mtl.cypw.stock.model.ReserveSeatDetail;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author Johnathon.Yuan
 * @date 2020-01-19 12:22
 */
@Slf4j
@Component
public class ReserveFormRepository implements GenericRepository<Integer, ReserveForm> {

    @Autowired
    private ReserveFormMapper reserveFormMapper;

    @Autowired
    private ReserveSeatDetailMapper reserveSeatDetailMapper;

    @Override
    public Integer save(ReserveForm model) {
        Assert.notNull(model, "ReserveForm is null");
        if (model.brandNew()) {
            reserveFormMapper.insertSelective(model);
        } else {
            reserveFormMapper.updateByPrimaryKeySelective(model);
        }
        return model.getId();
    }

    public int insertSeatDetails(List<ReserveSeatDetail> seatDetails) {
        if (CollectionUtils.isEmpty(seatDetails)) {
            return 0;
        }
        return reserveSeatDetailMapper.insertList(seatDetails);
    }

    public int updateSeatDetails(List<ReserveSeatDetail> seatDetails) {
        if (CollectionUtils.isEmpty(seatDetails)) {
            return 0;
        }
        reserveSeatDetailMapper.bulkUpdateByExampleSelective(seatDetails);
        return seatDetails.size();
    }

    public ReserveForm findOne(Integer reserveId) {
        return reserveFormMapper.selectByPrimaryKey(reserveId);
    }

    public ReserveForm findOneByReservedNo (String reservedNo) {
        Example example = new Example(ReserveForm.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("reservedNo", reservedNo);
        return reserveFormMapper.selectOneByExample(example);
    }

    public List<ReserveForm> findBySpec(ReserveQuerySpec spec, Pagination pagination) {
        Page page = null;
        Example example = new Example(ReserveForm.class);
        Example.Criteria criteria = example.createCriteria();
        if (spec.getProgramId() != null) {
            criteria.andEqualTo("programId", spec.getProgramId());
        }
        if (spec.getEventId() != null) {
            criteria.andEqualTo("eventId", spec.getEventId());
        }
        if (spec.getEnterpriseId() != null) {
            criteria.andEqualTo("enterpriseId", spec.getEnterpriseId());
        }
        if (StringUtils.isNotBlank(spec.getTargetMobile())) {
            criteria.andEqualTo("targetMobile", spec.getTargetMobile());
        }
        if (StringUtils.isNotBlank(spec.getTargetName())) {
            criteria.andLike("targetName", spec.getTargetName() + "%");
        }
        if (spec.getStartTime() != null) {
            criteria.andGreaterThanOrEqualTo("reservedTime", spec.getStartTime());
        }
        if (spec.getEndTime() != null) {
            criteria.andLessThanOrEqualTo("reservedTime", spec.getEndTime());
        }
        if (spec.getReservedStatus() != null) {
            criteria.andEqualTo("reservedStatus", spec.getReservedStatus().getCode());
        }
        if (BooleanUtils.toBoolean(spec.getAutoRelease())) {
            criteria.andEqualTo("autoRelease", BooleanUtils.toInteger(spec.getAutoRelease()));
        }
        boolean paging = false;
        if (pagination != null) {
            paging = true;
            CollatorUtils.parseSortByExample(pagination, example);
            page = PageHelper.startPage(pagination.getOffset(), pagination.getLength(), true);
        }
        List<ReserveForm> reserveForms = reserveFormMapper.selectByExample(example);
        if (paging) {
            pagination.setCount(page.getTotal());
        }
        return reserveForms;
    }

    public List<ReserveSeatDetail> findSeatDetailsBySpec(ReserveSeatQuerySpec spec, Pagination pagination) {
        Page page = null;
        Example example = new Example(ReserveSeatDetail.class);
        Example.Criteria criteria = example.createCriteria();
        if (spec.getReserveId() != null) {
            criteria.andEqualTo("reserveId", spec.getReserveId());
        }
        if (spec.getEventId() != null) {
            criteria.andEqualTo("eventId", spec.getEventId());
        }
        if (spec.getPriceId() != null) {
            criteria.andEqualTo("priceId", spec.getPriceId());
        }
        if (spec.getEnterpriseId() != null) {
            criteria.andEqualTo("enterpriseId", spec.getEnterpriseId());
        }
        if (CollectionUtils.isNotEmpty(spec.getSeatIds())) {
            criteria.andIn("seatId", spec.getSeatIds());
        }
        if (spec.getReserveSeatStatus() != null) {
            criteria.andEqualTo("status", spec.getReserveSeatStatus().getCode());
        }
        boolean paging = false;
        if (pagination != null) {
            paging = true;
            CollatorUtils.parseSortByExample(pagination, example);
            page = PageHelper.startPage(pagination.getOffset(), pagination.getLength(), true);
        }
        List<ReserveSeatDetail> reserveSeatDetails = reserveSeatDetailMapper.selectByExample(example);
        if (paging) {
            pagination.setCount(page.getTotal());
        }
        return reserveSeatDetails;
    }

}
