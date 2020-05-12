package com.mtl.cypw.provider.admin.endpoint;

import com.juqitech.request.GenericRequest;
import com.juqitech.response.ResultBuilder;
import com.juqitech.response.TSingleResult;
import com.mtl.cypw.admin.pojo.User;
import com.mtl.cypw.admin.service.UserService;
import com.mtl.cypw.api.admin.endpoint.UserApi;
import com.mtl.cypw.common.enums.ErrorCode;
import com.mtl.cypw.domain.admin.dto.UserDTO;
import com.mtl.cypw.domain.admin.param.UserLoginParam;
import com.mtl.cypw.domain.admin.param.UserParam;
import com.mtl.cypw.provider.admin.converter.UserConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author tang.
 * @date 2020/3/18.
 */
@Slf4j
@RestController
public class UserEndpoint implements UserApi {

    @Resource
    UserService userService;

    @Resource
    UserConverter userConverter;

    @Override
    public TSingleResult<UserDTO>  printerLogin(GenericRequest<UserLoginParam> request) {
        UserLoginParam param = request.getParam();
        if (param.checkParam()) {
            User user = userService.gerUser(param.getLoginName(), param.getEnterpriseId());
            if (user != null) {
                if (param.getLoginPass().equals(user.getLoginPass())) {
                    if (1 == user.getIsPrint()) {
                        return ResultBuilder.succTSingle(userConverter.toDto(user));
                    } else {
                        return ResultBuilder.failTSingle(ErrorCode.ERROR_COMMON_AUTHORITY.getCode(), ErrorCode.ERROR_COMMON_AUTHORITY.getMsg());
                    }
                }
                return ResultBuilder.failTSingle(ErrorCode.ERROR_COMMON_USER_PASS.getCode(), ErrorCode.ERROR_COMMON_USER_PASS.getMsg());
            }
            return ResultBuilder.failTSingle(ErrorCode.ERROR_COMMON_USER_NAME.getCode(), ErrorCode.ERROR_COMMON_USER_NAME.getMsg());
        }
        return ResultBuilder.failTSingle(ErrorCode.ERROR_COMMON_PARAMETER.getCode(), ErrorCode.ERROR_COMMON_PARAMETER.getMsg());
    }

    @Override
    public TSingleResult<Boolean> update(GenericRequest<UserParam> request){
        UserParam param = request.getParam();
        userService.update(userConverter.toEntity(param));
        return ResultBuilder.succTSingle(true);
    }
}
