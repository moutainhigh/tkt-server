package com.mtl.cypw.provider.member.endpoint;

import com.alibaba.fastjson.JSONObject;
import com.juqitech.request.GenericRequest;
import com.juqitech.request.QueryRequest;
import com.juqitech.response.ResultBuilder;
import com.juqitech.response.TSingleResult;
import com.mtl.cypw.api.member.endpoint.MemberApi;
import com.mtl.cypw.common.enums.ErrorCode;
import com.mtl.cypw.domain.member.dto.MemberDTO;
import com.mtl.cypw.domain.member.param.MemberLoginParam;
import com.mtl.cypw.domain.member.param.MemberParam;
import com.mtl.cypw.domain.member.param.MemberQueryParam;
import com.mtl.cypw.domain.mpm.enums.LoginTypeEnum;
import com.mtl.cypw.member.pojo.Member;
import com.mtl.cypw.member.service.MemberService;
import com.mtl.cypw.provider.member.converter.MemberConverter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author tang.
 * @date 2019/11/22.
 */
@RestController
@Slf4j
public class MemberEndpoint implements MemberApi {

    @Resource
    MemberService memberService;

    @Resource
    MemberConverter memberConverter;

    @Override
    public TSingleResult<MemberDTO> getMemberById(QueryRequest<MemberQueryParam> request) {
        Member member = memberService.getMemberById(request.getParam());
        return ResultBuilder.succTSingle(memberConverter.toDto(member));
    }

    @Override
    public TSingleResult<MemberDTO> registerAndLogin(QueryRequest<MemberLoginParam> request) {
        MemberLoginParam param = request.getParam();
        log.debug("用户登录或注册，request:{}", JSONObject.toJSONString(param));
        if (param == null || param.getEnterpriseId() == null) {
            log.error("参数错误，企业不能为空");
            return ResultBuilder.failTSingle(ErrorCode.ERROR_COMMON_PARAMETER.getCode(), ErrorCode.ERROR_COMMON_PARAMETER.getMsg());
        }
        if (StringUtils.isEmpty(param.getMemberMobile()) && StringUtils.isEmpty(param.getThirdPartyUserId())) {
            log.error("参数错误，会员手机号或第三方用户信息为空");
            return ResultBuilder.failTSingle(ErrorCode.ERROR_COMMON_PARAMETER.getCode(), ErrorCode.ERROR_COMMON_PARAMETER.getMsg());
        }

        if (StringUtils.isNotEmpty(param.getThirdPartyUserId())) {
            return thirdPartyUserLogin(param);
        }

        Member member = memberService.getMemberByPhone(param.getMemberMobile(), param.getEnterpriseId());
        if (member == null) {
            return ResultBuilder.succTSingle(memberConverter.toDto(addMember(param)));
        } else {
            return ResultBuilder.succTSingle(memberConverter.toDto(member));
        }
    }

    private Member addMember(MemberLoginParam param) {
        Member member = new Member();
        member.setMemberMobile(param.getMemberMobile());
        member.setEnterpriseId(param.getEnterpriseId());
        member.setOpenId(param.getOpenId());
        member.setNickName(param.getMemberMobile());
        member.setThirdPartyUserId(param.getThirdPartyUserId());
        member.setAddDate(new Date());
        memberService.addMember(member);
        return member;
    }

    /**
     * 第三方用户登录
     *
     * @param param
     * @return
     */
    private TSingleResult<MemberDTO> thirdPartyUserLogin(MemberLoginParam param) {
        Member member1 = memberService.getMemberByThirdPartyUserId(param.getThirdPartyUserId(), param.getEnterpriseId());
        if (member1 != null) {
            return ResultBuilder.succTSingle(memberConverter.toDto(member1));
        }
        //第三方用户信息为空，判断手机号是否为空
        if (StringUtils.isEmpty(param.getMemberMobile())) {
            //手机号为空
            return ResultBuilder.failTSingle(ErrorCode.ERROR_COMMON_DATA_NOT_FOUND.getCode(), ErrorCode.ERROR_COMMON_DATA_NOT_FOUND.getMsg());
        }
        Member member2 = memberService.getMemberByPhone(param.getMemberMobile(), param.getEnterpriseId());
        //手机号不为空，判断手机号是否注册
        if (member2 == null) {
            //手机号未注册，新增用户
            return ResultBuilder.succTSingle(memberConverter.toDto(addMember(param)));
        }
        //手机号已注册，判断是否绑定过其他第三方用户信息
        if (StringUtils.isEmpty(member2.getThirdPartyUserId())) {
            member2.setThirdPartyUserId(param.getThirdPartyUserId());
            memberService.updateMember(member2);
            return ResultBuilder.succTSingle(memberConverter.toDto(member2));
        }
        //1,手机号已注册，且绑定了其他第三方用户
        return ResultBuilder.failTSingle(ErrorCode.ERROR_THIRD_PARTY_USER_PHONE.getCode(), ErrorCode.ERROR_THIRD_PARTY_USER_PHONE.getMsg());
    }

    @Override
    public TSingleResult<Boolean> updateMember(GenericRequest<MemberParam> request) {
        log.debug("修改会员信息，request:{}", JSONObject.toJSONString(request.getParam()));
        TSingleResult<Boolean> result = ResultBuilder.succTSingle(true);
        MemberParam param = request.getParam();
        if (param != null && param.getMemberId() != null) {
            Member member = new Member();
            member.setMemberId(param.getMemberId());
            member.setMemberName(param.getMemberName());
            member.setNickName(param.getNickName());
            member.setMemberImage(param.getMemberImage());
            memberService.updateMember(member);
            log.debug("修改会员信息成功");
        } else {
            log.error("修改会员信息失败");
            return ResultBuilder.failTSingle(ErrorCode.ERROR_MEMBER_PARAMETER.getCode(), ErrorCode.ERROR_MEMBER_PARAMETER.getMsg());
        }
        return result;
    }
}
