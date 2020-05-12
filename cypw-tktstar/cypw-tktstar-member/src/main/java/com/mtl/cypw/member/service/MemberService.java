package com.mtl.cypw.member.service;

import com.alibaba.fastjson.JSONObject;
import com.mtl.cypw.domain.member.param.MemberQueryParam;
import com.mtl.cypw.member.mapper.MemberMapper;
import com.mtl.cypw.member.pojo.Member;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author tang.
 * @date 2019/11/18.
 */
@Service
@Slf4j
public class MemberService {

    @Resource
    private MemberMapper memberMapper;

    public void addMember(Member member) {
        log.debug("新增会员，member:{}", JSONObject.toJSONString(member));
        memberMapper.insert(member);
    }

    public void updateMember(Member member) {
        log.debug("修改会员信息，member:{}", JSONObject.toJSONString(member));
        memberMapper.updateByPrimaryKeySelective(member);
    }

    public void updateDefaultAddressId(Integer memberId, Integer addressId) {
        log.debug("修改会员默认地址id，memberId:{},addressId:{}", memberId, addressId);
        Member member = new Member();
        member.setMemberId(memberId);
        member.setDefaultAddressId(addressId);
        memberMapper.updateByPrimaryKeySelective(member);
    }

    public Member getMemberById(MemberQueryParam query) {
        log.debug("根据会员id查询会员信息，request:{}", JSONObject.toJSONString(query));
        if (query != null && query.getMemberId() != null) {
            Member member = memberMapper.selectByPrimaryKey(query.getMemberId());
            log.debug("会员信息查询结果，result:{}", JSONObject.toJSONString(member));
            return member;
        } else {
            log.error("参数错误，会员ID为空");
            return null;
        }
    }

    public Member getMemberById(Integer memberId) {
        log.debug("根据会员id查询会员信息，memberId:{}", memberId);
        if (memberId != null) {
            Member member = memberMapper.selectByPrimaryKey(memberId);
            log.debug("会员信息查询结果，result:{}", JSONObject.toJSONString(member));
            return member;
        } else {
            log.error("参数错误，会员ID为空");
            return null;
        }
    }

    public Member getMember(MemberQueryParam query) {
        log.debug("查询会员信息，request:{}", JSONObject.toJSONString(query));
        if (query != null && StringUtils.isNotEmpty(query.getMemberMobile())) {
            List<Member> memberList = searchMemberList(query);
            log.debug("会员信息查询结果，result:{}", JSONObject.toJSONString(memberList));
            if (memberList != null && memberList.size() > 0) {
                return memberList.get(0);
            }
        }
        log.debug("该手机号未注册");
        return null;
    }

    public Member getMemberByPhone(String memberMobile, Integer enterpriseId) {
        log.debug("查询会员信息，memberMobile:{},enterpriseId:{}", memberMobile, enterpriseId);
        if (enterpriseId != null && StringUtils.isNotEmpty(memberMobile)) {
            MemberQueryParam query = new MemberQueryParam();
            query.setEnterpriseId(enterpriseId);
            query.setMemberMobile(memberMobile);
            List<Member> memberList = searchMemberList(query);
            log.debug("会员信息查询结果，result:{}", JSONObject.toJSONString(memberList));
            if (memberList != null && memberList.size() > 0) {
                return memberList.get(0);
            }
        }
        log.debug("该手机号未注册");
        return null;
    }

    public Member getMemberByThirdPartyUserId(String thirdPartyUserId, Integer enterpriseId) {
        log.debug("查询会员信息，thirdPartyUserId:{},enterpriseId:{}", thirdPartyUserId, enterpriseId);
        if (enterpriseId != null && StringUtils.isNotEmpty(thirdPartyUserId)) {
            MemberQueryParam query = new MemberQueryParam();
            query.setEnterpriseId(enterpriseId);
            query.setThirdPartyUserId(thirdPartyUserId);
            List<Member> memberList = searchMemberList(query);
            log.debug("会员信息查询结果，result:{}", JSONObject.toJSONString(memberList));
            if (memberList != null && memberList.size() > 0) {
                return memberList.get(0);
            }
        }
        log.debug("错误的第三方用户id");
        return null;
    }

    public List<Member> searchMemberList(MemberQueryParam query) {
        log.debug("查询会员列表，request:{}", JSONObject.toJSONString(query));
        Example example = new Example(Member.class);
        Example.Criteria cri = example.createCriteria();
        if (query != null) {
            if (query.getMemberId() != null) {
                cri.andEqualTo("memberId", query.getMemberId());
            }
            if (StringUtils.isNotEmpty(query.getMemberMobile())) {
                cri.andEqualTo("memberMobile", query.getMemberMobile());
            }
            if (query.getEnterpriseId() != null) {
                cri.andEqualTo("enterpriseId", query.getEnterpriseId());
            }
            if (query.getThirdPartyUserId() != null) {
                cri.andEqualTo("thirdPartyUserId", query.getThirdPartyUserId());
            }
        }
        List<Member> result = memberMapper.selectByExample(example);
        log.debug("会员列表查询结果，result:{}", JSONObject.toJSONString(result));
        return result;
    }

}
