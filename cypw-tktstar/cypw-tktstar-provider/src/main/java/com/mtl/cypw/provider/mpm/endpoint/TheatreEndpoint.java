package com.mtl.cypw.provider.mpm.endpoint;

import com.juqitech.request.IdRequest;
import com.juqitech.request.QueryRequest;
import com.juqitech.response.ResultBuilder;
import com.juqitech.response.TMultiResult;
import com.juqitech.response.TSingleResult;
import com.mtl.cypw.api.mpm.endpoint.TheatreApi;
import com.mtl.cypw.common.enums.ErrorCode;
import com.mtl.cypw.domain.mpm.dto.TheatreDTO;
import com.mtl.cypw.domain.mpm.param.TheatreQueryParam;
import com.mtl.cypw.mpm.model.Theatre;
import com.mtl.cypw.mpm.service.TheatreService;
import com.mtl.cypw.provider.mpm.converter.TheatreConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author tang.
 * @date 2020/3/3.
 */
@Slf4j
@RestController
public class TheatreEndpoint implements TheatreApi {

    @Autowired
    TheatreService theatreServiceImpl;

    @Autowired
    TheatreConverter converter;

    @Override
    public TSingleResult<TheatreDTO> getTheatreById(IdRequest request) {
        Theatre theatre = theatreServiceImpl.getTheatreById(Integer.parseInt(request.getId()));
        return ResultBuilder.succTSingle(converter.toDto(theatre));
    }

    @Override
    public TMultiResult<TheatreDTO> getTheatreList(QueryRequest<TheatreQueryParam> request) {
        TheatreQueryParam param = request.getParam();
        if (param == null) {
            return ResultBuilder.failTMulti(ErrorCode.ERROR_COMMON_PARAMETER.getCode(), ErrorCode.ERROR_COMMON_PARAMETER.getMsg());
        }
        List<Theatre> theatres = theatreServiceImpl.getTheatreList(param.getTheatreIds());
        return ResultBuilder.succTMulti(converter.toDto(theatres));
    }
}
