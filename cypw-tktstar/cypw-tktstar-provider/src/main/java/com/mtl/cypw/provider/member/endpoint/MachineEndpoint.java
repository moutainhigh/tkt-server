package com.mtl.cypw.provider.member.endpoint;

import com.juqitech.request.QueryRequest;
import com.juqitech.response.ResultBuilder;
import com.juqitech.response.TSingleResult;
import com.mtl.cypw.api.member.endpoint.MachineApi;
import com.mtl.cypw.common.enums.ErrorCode;
import com.mtl.cypw.domain.member.dto.MachineDTO;
import com.mtl.cypw.domain.member.param.MachineParam;
import com.mtl.cypw.member.pojo.Machine;
import com.mtl.cypw.member.service.MachineService;
import com.mtl.cypw.provider.member.converter.MachineConverter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author tang.
 * @date 2020/3/12.
 */
@RestController
@Slf4j
public class MachineEndpoint implements MachineApi {

    @Resource
    MachineService machineService;

    @Resource
    MachineConverter converter;

    @Override
    public TSingleResult<MachineDTO> login(QueryRequest<MachineParam> request) {
        if (request == null || request.getParam() == null) {
            return ResultBuilder.failTSingle(ErrorCode.ERROR_COMMON_PARAMETER.getCode(), ErrorCode.ERROR_COMMON_PARAMETER.getMsg());
        }
        MachineParam param = request.getParam();
        if (param.getEnterpriseId() == null || StringUtils.isEmpty(param.getMachineKey()) || StringUtils.isEmpty(param.getMacAddress())) {
            return ResultBuilder.failTSingle(ErrorCode.ERROR_COMMON_PARAMETER.getCode(), ErrorCode.ERROR_COMMON_PARAMETER.getMsg());
        }
        Machine machine = machineService.getMachine(param.getEnterpriseId(), param.getMachineKey());
        if (machine == null) {
            log.error("没有找到该设备");
            return ResultBuilder.failTSingle(ErrorCode.ERROR_COMMON_USER_NAME.getCode(), ErrorCode.ERROR_COMMON_USER_NAME.getMsg());
        }
        if (machine.getIsEnable() == 0) {
            log.error("用户已停用");
            return ResultBuilder.failTSingle(ErrorCode.ERROR_COMMON_USER_NOT_ENABLE.getCode(), ErrorCode.ERROR_COMMON_USER_NOT_ENABLE.getMsg());
        }
        if (StringUtils.isNotEmpty(machine.getMacAddress()) && !machine.getMacAddress().equals(param.getMacAddress())) {
            return ResultBuilder.failTSingle(ErrorCode.ERROR_COMMON_MAC_ADDRESS.getCode(), ErrorCode.ERROR_COMMON_MAC_ADDRESS.getMsg());
        }
        if (StringUtils.isEmpty(machine.getMacAddress())) {
            machine.setMacAddress(param.getMacAddress());
            machineService.update(machine);
        }
        return ResultBuilder.succTSingle(converter.toDto(machine));
    }
}
