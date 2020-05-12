package com.mtl.cypw.provider.mpm.endpoint;

import com.juqitech.request.GenericRequest;
import com.juqitech.request.IdRequest;
import com.juqitech.request.QueryRequest;
import com.juqitech.response.ResultBuilder;
import com.juqitech.response.TMultiResult;
import com.juqitech.response.TSingleResult;
import com.mtl.cypw.api.mpm.endpoint.BannerApi;
import com.mtl.cypw.domain.mpm.dto.BannerDTO;
import com.mtl.cypw.domain.mpm.param.BannerQueryParam;
import com.mtl.cypw.domain.mpm.param.BannerSaveParam;
import com.mtl.cypw.mpm.model.Banner;
import com.mtl.cypw.mpm.service.BannerService;
import com.mtl.cypw.provider.mpm.converter.BannerConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Johnathon.Yuan
 * @date 2019-11-23 12:45
 */
@Slf4j
@RestController
public class BannerEndpoint implements BannerApi {

    @Autowired
    private BannerService bannerServiceImpl;

    @Autowired
    private BannerConverter bannerConverter;

    @Override
    public TSingleResult<BannerDTO> create(GenericRequest<BannerSaveParam> request) {
        return null;
    }

    @Override
    public TSingleResult<BannerDTO> update(GenericRequest<BannerSaveParam> request) {
        return null;
    }

    @Override
    public TSingleResult<Boolean> deleteById(IdRequest request) {
        return null;
    }

    @Override
    public TSingleResult<BannerDTO> findById(IdRequest request) {
        return null;
    }

    @Override
    public TMultiResult<BannerDTO> pageQuery(QueryRequest<BannerQueryParam> request) {
        List<Banner> banners = bannerServiceImpl.findListAll(request.getParam());
        return ResultBuilder.succTMulti(bannerConverter.toDto(banners));
    }
}
