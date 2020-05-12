package com.mtl.cypw.provider.show.converter;

import com.mtl.cypw.domain.show.dto.FetchTicketWayDTO;
import com.mtl.cypw.show.pojo.FetchTicketWay;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tang.
 * @date 2019/12/3.
 */
@Component
public class FetchTicketWayConverter {
    public FetchTicketWayDTO toDto(FetchTicketWay pojo) {
        if (pojo == null) {
            return null;
        }
        FetchTicketWayDTO dto = new FetchTicketWayDTO();
        dto.setId(pojo.getId());
        dto.setProgramId(pojo.getProgramId());
        dto.setDeliverType(pojo.getDeliverType());
        dto.setNeedIdcard(pojo.getNeedIdcard());
        dto.setTips(pojo.getTips());
        dto.setExpressFee(pojo.getExpressFee());
        dto.setFetchAddress(pojo.getFetchAddress());
        dto.setContactMobile(pojo.getContactMobile());
        dto.setFetchTimeDesc(pojo.getFetchTimeDesc());
        dto.setEnterpriseId(pojo.getEnterpriseId());
        dto.setDeleted(pojo.getDeleted());
        return dto;
    }

    public List<FetchTicketWayDTO> toDto(List<FetchTicketWay> list) {
        if (list == null) {
            return null;
        }
        List<FetchTicketWayDTO> dtoList = new ArrayList<>();
        list.forEach(n -> dtoList.add(toDto(n)));
        return dtoList;
    }
}
