package com.mtl.cypw.domain.show.dto;

import com.mtl.cypw.domain.mpm.dto.TemplateDTO;
import com.mtl.cypw.domain.mpm.dto.ZoneDTO;
import lombok.Data;

import java.util.List;

/**
 * @author Johnathon.Yuan
 * @date 2020-01-10 18:06
 */
@Data
public class SeatMapDTO {

    private EventDTO event;

    private TemplateDTO template;

    private List<ZoneDTO> zones;

    private List<EventPriceDTO> prices;

    private List<SeatDTO> seats;
}
