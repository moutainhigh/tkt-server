package com.mtl.cypw.provider.member.endpoint;

import com.juqitech.request.QueryRequest;
import com.juqitech.response.ResultBuilder;
import com.juqitech.response.TSingleResult;
import com.mtl.cypw.api.member.endpoint.CheckInUserApi;
import com.mtl.cypw.common.enums.ErrorCode;
import com.mtl.cypw.domain.member.dto.CheckInUserDTO;
import com.mtl.cypw.domain.member.param.CheckInUserQueryParam;
import com.mtl.cypw.member.pojo.CheckInUser;
import com.mtl.cypw.member.service.CheckInUserService;
import com.mtl.cypw.provider.member.converter.CheckInUserConverter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author tang.
 * @date 2020/2/12.
 */
@RestController
@Slf4j
public class CheckInUserEndpoint implements CheckInUserApi {

    @Resource
    CheckInUserService checkInUserService;

    @Resource
    CheckInUserConverter converter;

    @Override
    public TSingleResult<CheckInUserDTO> checkInUserLogin(QueryRequest<CheckInUserQueryParam> request) {
        if (request == null || request.getParam() == null) {
            return ResultBuilder.failTSingle(ErrorCode.ERROR_COMMON_PARAMETER.getCode(), ErrorCode.ERROR_COMMON_PARAMETER.getMsg());
        }
        CheckInUserQueryParam param = request.getParam();
        if (param.getEnterpriseId() == null || StringUtils.isEmpty(param.getUserName())) {
            return ResultBuilder.failTSingle(ErrorCode.ERROR_COMMON_PARAMETER.getCode(), ErrorCode.ERROR_COMMON_PARAMETER.getMsg());
        }
        CheckInUser checkInUser = checkInUserService.getCheckInUser(param.getEnterpriseId(), param.getUserName());
        if (checkInUser == null) {
            log.error("没有找到该用户");
            return ResultBuilder.failTSingle(ErrorCode.ERROR_COMMON_USER_NAME.getCode(), ErrorCode.ERROR_COMMON_USER_NAME.getMsg());
        }
        if (StringUtils.isEmpty(checkInUser.getUserPass()) || !checkInUser.getUserPass().equals(param.getUserPass())) {
            log.error("密码错误");
            return ResultBuilder.failTSingle(ErrorCode.ERROR_COMMON_USER_PASS.getCode(), ErrorCode.ERROR_COMMON_USER_PASS.getMsg());
        }
        if (checkInUser.getIsEnable() == 0) {
            log.error("用户已停用");
            return ResultBuilder.failTSingle(ErrorCode.ERROR_COMMON_USER_NOT_ENABLE.getCode(), ErrorCode.ERROR_COMMON_USER_NOT_ENABLE.getMsg());
        }
        return ResultBuilder.succTSingle(converter.toDto(checkInUser));
    }
}
