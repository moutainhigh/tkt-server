package com.mtl.cypw.provider.mpm.endpoint;

import com.juqitech.request.IdRequest;
import com.juqitech.response.ResultBuilder;
import com.juqitech.response.TMultiResult;
import com.mtl.cypw.api.member.endpoint.MachineApi;
import com.mtl.cypw.api.mpm.endpoint.MachinePosterApi;
import com.mtl.cypw.domain.mpm.dto.MachinePosterDTO;
import com.mtl.cypw.mpm.model.MachinePoster;
import com.mtl.cypw.mpm.service.MachinePosterService;
import com.mtl.cypw.provider.mpm.converter.MachinePosterConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author tang.
 * @date 2020/3/16.
 */
@RestController
@Slf4j
public class MachinePosterEndpoint implements MachinePosterApi {

    @Resource
    private MachinePosterService machinePosterServiceImpl;

    @Resource
    private MachinePosterConverter machinePosterConverter;

    @Override
    public TMultiResult<MachinePosterDTO> searchMachinePoster(IdRequest request) {
        List<MachinePoster> machinePosters = machinePosterServiceImpl.searchMachinePoster(Integer.parseInt(request.getId()));
        return ResultBuilder.succTMulti(machinePosterConverter.toDto(machinePosters));
    }
}
