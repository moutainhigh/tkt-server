package com.mtl.cypw.mpm.service;

import com.juqitech.response.paging.Pagination;
import com.mtl.cypw.domain.mpm.param.ResourceMediaFileQueryParam;
import com.mtl.cypw.mpm.model.ResourceMediaFile;

import java.util.List;

/**
 * @author Johnathon.Yuan
 * @date 2020-03-05 17:40
 */
public interface ResourceMediaFileService {

    List<ResourceMediaFile> findFiles(ResourceMediaFileQueryParam param, Pagination pagination);

    List<ResourceMediaFile> findFilesByPriceIdWithMall(Integer priceId);

}
