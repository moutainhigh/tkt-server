package com.mtl.cypw.mpm.service;

import com.mtl.cypw.domain.mpm.param.VenueQueryParam;
import com.mtl.cypw.mpm.model.Venue;

import java.util.List;

/**
 * @author tang.
 * @date 2019/12/12.
 */
public interface VenueService {
    Venue getVenueById(Integer id);

    List<Venue> getVenueList(VenueQueryParam param);
}
