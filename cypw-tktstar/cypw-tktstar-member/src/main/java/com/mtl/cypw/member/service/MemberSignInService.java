package com.mtl.cypw.member.service;

import com.juqitech.service.utils.DateUtils;
import com.mtl.cypw.domain.member.param.MemberSignInQueryParam;
import com.mtl.cypw.member.mapper.MemberSignInMapper;
import com.mtl.cypw.member.pojo.MemberSignIn;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author tang.
 * @date 2020/3/3.
 */
@Slf4j
@Service
public class MemberSignInService {

    @Resource
    private MemberSignInMapper mapper;

    public void addMemberSignIn(MemberSignIn memberSignIn) {
        if (memberSignIn != null) {
            memberSignIn.setCreateTime(DateUtils.now());
            mapper.insert(memberSignIn);
        }
    }

    public List<MemberSignIn> searchMemberSignInList(MemberSignInQueryParam param) {
        Example example = new Example(MemberSignIn.class);
        Example.Criteria criteria = example.createCriteria();
        if (param != null) {
            if (param.getMemberId() != null) {
                criteria.andEqualTo("memberId", param.getMemberId());
            }
            if (param.getOrderId() != null) {
                criteria.andEqualTo("orderId", param.getOrderId());
            }
            if (param.getProgramId() != null) {
                criteria.andEqualTo("programId", param.getProgramId());
            }
            if (param.getEventId() != null) {
                criteria.andEqualTo("eventId", param.getEventId());
            }
            if (param.getTheatreId() != null) {
                criteria.andEqualTo("theatreId", param.getTheatreId());
            }
            if (param.getType() != null) {
                criteria.andEqualTo("type", param.getType());
            }
            if (param.getEnterpriseId() != null) {
                criteria.andEqualTo("enterpriseId", param.getEnterpriseId());
            }
            if (param.getSignInTimeBegin() != null) {
                criteria.andGreaterThanOrEqualTo("createTime", param.getSignInTimeBegin());
            }
            if (param.getSignInTimeEnd() != null) {
                criteria.andLessThanOrEqualTo("createTime", param.getSignInTimeEnd());
            }
            criteria.andEqualTo("deleted", 0);
        }
        return mapper.selectByExample(example);
    }
}
