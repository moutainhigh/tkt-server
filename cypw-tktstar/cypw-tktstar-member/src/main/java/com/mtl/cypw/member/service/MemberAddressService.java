package com.mtl.cypw.member.service;

import com.alibaba.fastjson.JSONObject;
import com.mtl.cypw.domain.member.param.MemberQueryParam;
import com.mtl.cypw.member.mapper.MemberAddressMapper;
import com.mtl.cypw.member.pojo.MemberAddress;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author tang.
 * @date 2019/11/22.
 */
@Service
@Slf4j
public class MemberAddressService {

    @Resource
    MemberAddressMapper memberAddressMapper;

    public void createMemberAddress(MemberAddress memberAddress) {
        memberAddress.setAddDate(new Date());
        memberAddressMapper.insert(memberAddress);
    }

    public void updateMemberAddress(MemberAddress memberAddress) {
        memberAddress.setUpdateDate(new Date());
        memberAddressMapper.updateByPrimaryKeySelective(memberAddress);
    }

    public void deleteMemberAddress(Integer addressId) {
        memberAddressMapper.deleteByPrimaryKey(addressId);
    }

    public MemberAddress getMemberAddress(Integer addressId) {
        log.debug("查询会员地址，addressId:{}", addressId);
        return memberAddressMapper.selectByPrimaryKey(addressId);
    }

    public List<MemberAddress> searchMemberAddressList(MemberQueryParam query) {
        log.debug("查询会员地址列表，request:{}", JSONObject.toJSONString(query));
        Example example = new Example(MemberAddress.class);
        Example.Criteria cri = example.createCriteria();
        if (query != null) {
            if (query.getMemberId() != null) {
                cri.andEqualTo("memberId", query.getMemberId());
            }
        }
        example.orderBy("isDefault").desc().orderBy("addDate").desc();
        List<MemberAddress> result = memberAddressMapper.selectByExample(example);
        log.debug("会员地址列表查询结果，result:{}", JSONObject.toJSONString(result));
        return result;
    }

    public void updateDefaultAddress(Integer memberId, Integer addressId) {
        log.debug("设置会员默认地址，memberId:{},addressId:{}", memberId, addressId);
        Example example = new Example(MemberAddress.class);
        Example.Criteria cri = example.createCriteria();
        cri.andEqualTo("memberId", memberId);
        MemberAddress memberAddress = new MemberAddress();
        memberAddress.setIsDefault((byte) 0);
        memberAddressMapper.updateByExampleSelective(memberAddress, example);
        memberAddress.setAddressId(addressId);
        memberAddress.setIsDefault((byte) 1);
        memberAddressMapper.updateByPrimaryKeySelective(memberAddress);
    }
}
