package com.mtl.cypw.api.member.endpoint;

import com.juqitech.request.QueryRequest;
import com.juqitech.response.TSingleResult;
import com.mtl.cypw.domain.member.dto.CheckInUserDTO;
import com.mtl.cypw.domain.member.param.CheckInUserQueryParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author tang.
 * @date 2020/2/12.
 */
public interface CheckInUserApi {

    /**
     * 核销用户登录
     *
     * @param request
     * @return
     */
    @PostMapping("/endpoint/check/user/getCheckInUser")
    TSingleResult<CheckInUserDTO> checkInUserLogin(@RequestBody QueryRequest<CheckInUserQueryParam> request);
}
