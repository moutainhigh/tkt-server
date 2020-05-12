package com.mtl.cypw.api.admin.endpoint;

import com.juqitech.request.GenericRequest;
import com.juqitech.response.TSingleResult;
import com.mtl.cypw.domain.admin.dto.UserDTO;
import com.mtl.cypw.domain.admin.param.UserLoginParam;
import com.mtl.cypw.domain.admin.param.UserParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author tang.
 * @date 2020/3/18.
 */
public interface UserApi {

    /**
     * 后台用户登录
     *
     * @param request
     * @return
     */
    @PostMapping("/endpoint/admin/user/printer_login")
    TSingleResult<UserDTO> printerLogin(@RequestBody GenericRequest<UserLoginParam> request);

    /**
     * 修改用户信息
     *
     * @param request
     * @return
     */
    @PostMapping("/endpoint/admin/user/update")
    TSingleResult<Boolean> update(@RequestBody GenericRequest<UserParam> request);
}
