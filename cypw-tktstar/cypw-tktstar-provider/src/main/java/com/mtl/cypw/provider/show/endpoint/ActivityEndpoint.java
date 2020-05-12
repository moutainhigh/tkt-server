package com.mtl.cypw.provider.show.endpoint;

import com.juqitech.request.IdRequest;
import com.juqitech.request.QueryRequest;
import com.juqitech.response.ResultBuilder;
import com.juqitech.response.TMultiResult;
import com.juqitech.response.TPageResult;
import com.juqitech.response.TSingleResult;
import com.juqitech.response.paging.Pagination;
import com.mtl.cypw.api.show.endpoint.ActivityApi;
import com.mtl.cypw.domain.show.dto.ActivityDTO;
import com.mtl.cypw.domain.show.query.ActivityQuery;
import com.mtl.cypw.provider.show.converter.ActivityConverter;
import com.mtl.cypw.show.pojo.Activity;
import com.mtl.cypw.show.service.ActivityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author tang.
 * @date 2020/1/3.
 */
@RestController
@Slf4j
public class ActivityEndpoint implements ActivityApi {

    @Resource
    private ActivityService activityService;

    @Resource
    private ActivityConverter converter;

    @Override
    public TSingleResult<ActivityDTO> getActivity(IdRequest request) {
        Activity activity = activityService.getActivity(Integer.parseInt(request.getId()));
        return ResultBuilder.succTSingle(converter.toDto(activity));
    }

    @Override
    public TMultiResult<ActivityDTO> searchActivity(QueryRequest<ActivityQuery> request) {
        List<Activity> activityList = activityService.searchActivity(request.getParam());
        return ResultBuilder.succTMulti(converter.toDto(activityList));
    }

    @Override
    public TPageResult<ActivityDTO> searchActivityPage(QueryRequest<ActivityQuery> request) {
        Pagination pagination = request.buildPagination();
        List<Activity> activityList = activityService.searchActivity(request.getParam(),pagination);
        return ResultBuilder.succTPage(converter.toDto(activityList),pagination);
    }
}
