package com.mtl.cypw.api.show.endpoint;

import com.mtl.cypw.domain.show.dto.ProgramDistributionDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author tang.
 * @date 2019/11/21.
 */
public interface ProgramDistributionApi {

    @GetMapping("/endpoint/program/distribution/search/{programId}")
    List<ProgramDistributionDTO> searchProgramDistributionList(@RequestParam("programId") Integer programId);
}
