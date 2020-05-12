package com.mtl.cypw.provider.show.endpoint;

import com.juqitech.request.IdRequest;
import com.juqitech.response.ResultBuilder;
import com.juqitech.response.TMultiResult;
import com.mtl.cypw.api.show.endpoint.ActorApi;
import com.mtl.cypw.domain.show.dto.ActorDTO;
import com.mtl.cypw.provider.show.converter.ActorConverter;
import com.mtl.cypw.show.pojo.Actor;
import com.mtl.cypw.show.service.ActorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author tang.
 * @date 2019/11/27.
 */
@RestController
@Slf4j
public class ActorEndpoint implements ActorApi {

    @Resource
    ActorService actorService;

    @Resource
    ActorConverter actorConverter;

    @Override
    public TMultiResult<ActorDTO> searchActorList(IdRequest request) {
        List<Actor> actors = actorService.searchActorList(Integer.parseInt(request.getId()));
        TMultiResult<ActorDTO> result = ResultBuilder.succTMulti(actorConverter.toDto(actors));
        return result;
    }
}
