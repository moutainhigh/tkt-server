package com.mtl.cypw.provider.show.endpoint;

import com.mtl.cypw.api.show.client.ProgramDistributionApiClient;
import com.mtl.cypw.domain.show.dto.ProgramDistributionDTO;
import com.mtl.cypw.provider.show.converter.ProgramDistributionConverter;
import com.mtl.cypw.show.service.ProgramDistributionService;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author tang.
 * @date 2019/11/21.
 */
@RestController
public class ProgramDistributionEndpoint implements ProgramDistributionApiClient {

    @Resource
    private ProgramDistributionService programDistributionService;

    @Resource
    private ProgramDistributionConverter programDistributionConverter;

    @Override
    public List<ProgramDistributionDTO> searchProgramDistributionList(Integer programId) {
        return programDistributionConverter.toDto(programDistributionService.searchProgramDistributionList(programId));
    }
}
