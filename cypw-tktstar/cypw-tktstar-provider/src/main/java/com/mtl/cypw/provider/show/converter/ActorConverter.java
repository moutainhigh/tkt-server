package com.mtl.cypw.provider.show.converter;

import com.mtl.cypw.domain.show.dto.ActorDTO;
import com.mtl.cypw.show.pojo.Actor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tang.
 * @date 2019/11/27.
 */
@Component
public class ActorConverter {

    public ActorDTO toDto(Actor actor) {
        if (actor == null) {
            return null;
        }
        ActorDTO dto = new ActorDTO();
        dto.setId(actor.getId());
        dto.setProgramId(actor.getProgramId());
        dto.setActorName(actor.getActorName());
        dto.setActorImage(actor.getActorImage());
        return dto;
    }

    public List<ActorDTO> toDto(List<Actor> list) {
        if (list == null) {
            return null;
        }
        List<ActorDTO> dtoList = new ArrayList<>();
        list.forEach(n -> dtoList.add(toDto(n)));
        return dtoList;
    }

}
