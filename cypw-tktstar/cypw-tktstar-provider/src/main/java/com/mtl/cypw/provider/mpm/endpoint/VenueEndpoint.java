package com.mtl.cypw.provider.mpm.endpoint;

import com.juqitech.request.IdRequest;
import com.juqitech.request.QueryRequest;
import com.juqitech.response.ResultBuilder;
import com.juqitech.response.TMultiResult;
import com.juqitech.response.TSingleResult;
import com.mtl.cypw.api.mpm.endpoint.VenueApi;
import com.mtl.cypw.domain.mpm.dto.VenueDTO;
import com.mtl.cypw.domain.mpm.param.VenueQueryParam;
import com.mtl.cypw.mpm.model.Venue;
import com.mtl.cypw.mpm.service.VenueService;
import com.mtl.cypw.provider.mpm.converter.VenueConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author tang.
 * @date 2019/12/12.
 */
@Slf4j
@RestController
public class VenueEndpoint implements VenueApi {

    @Autowired
    VenueService venueServiceImpl;

    @Autowired
    VenueConverter converter;

    @Override
    public TSingleResult<VenueDTO> getVenueById(IdRequest request) {
        Venue venue = venueServiceImpl.getVenueById(Integer.parseInt(request.getId()));
        return ResultBuilder.succTSingle(converter.toDto(venue));
    }

    @Override
    public TMultiResult<VenueDTO> getVenueList(QueryRequest<VenueQueryParam> request) {
        List<Venue> venues = venueServiceImpl.getVenueList(request.getParam());
        return ResultBuilder.succTMulti(converter.toDto(venues));
    }
}
