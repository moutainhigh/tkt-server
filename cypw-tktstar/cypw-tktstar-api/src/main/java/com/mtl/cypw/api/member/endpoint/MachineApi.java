package com.mtl.cypw.api.member.endpoint;

import com.juqitech.request.QueryRequest;
import com.juqitech.response.TSingleResult;
import com.mtl.cypw.domain.member.dto.MachineDTO;
import com.mtl.cypw.domain.member.param.MachineParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author tang.
 * @date 2020/3/12.
 */
public interface MachineApi {

    /**
     * 硬件设备登录
     *
     * @param request
     * @return
     */
    @PostMapping("/endpoint/machine/login")
    TSingleResult<MachineDTO> login(@RequestBody QueryRequest<MachineParam> request);
}
