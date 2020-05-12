package com.mtl.cypw.provider.member.endpoint;

import com.juqitech.request.GenericRequest;
import com.juqitech.request.QueryRequest;
import com.juqitech.response.ResultBuilder;
import com.juqitech.response.TMultiResult;
import com.juqitech.response.TSingleResult;
import com.mtl.cypw.api.member.endpoint.MemberSignInApi;
import com.mtl.cypw.common.enums.ErrorCode;
import com.mtl.cypw.domain.member.dto.MemberSignInDTO;
import com.mtl.cypw.domain.member.param.MemberQueryParam;
import com.mtl.cypw.domain.member.param.MemberSignInParam;
import com.mtl.cypw.domain.member.param.MemberSignInQueryParam;
import com.mtl.cypw.member.pojo.MemberSignIn;
import com.mtl.cypw.member.service.MemberSignInService;
import com.mtl.cypw.provider.member.converter.MemberSignInConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author tang.
 * @date 2020/3/3.
 */
@Slf4j
@RestController
public class MemberSignInEndpoint implements MemberSignInApi {

    @Resource
    MemberSignInService memberSignInService;

    @Resource
    MemberSignInConverter memberSignInConverter;

    @Override
    public TSingleResult<Boolean> addMemberSignIn(GenericRequest<MemberSignInParam> request) {
        MemberSignInParam param = request.getParam();
        if (param == null || !param.checkParam()) {
            memberSignInService.addMemberSignIn(memberSignInConverter.toEntity(param));
            return ResultBuilder.succTSingle(true);
        } else {
            return ResultBuilder.failTSingle(ErrorCode.ERROR_COMMON_PARAMETER.getCode(), ErrorCode.ERROR_COMMON_PARAMETER.getMsg());
        }
    }

    @Override
    public TMultiResult<MemberSignInDTO> searchMemberSignInList(QueryRequest<MemberSignInQueryParam> request) {
        MemberSignInQueryParam param = request.getParam();
        return ResultBuilder.succTMulti(memberSignInConverter.toDto(memberSignInService.searchMemberSignInList(param)));
    }
}
