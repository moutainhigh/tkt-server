package com.mtl.cypw.domain.mpm.param;

import lombok.Data;

import java.util.List;

/**
 * @author tang.
 * @date 2020/3/4.
 */
@Data
public class VenueQueryParam {

    /**
     * 场馆id
     */
    private Integer venueId;
    /**
     * 场馆id集
     */
    private List<Integer> venueIds;
    /**
     * 剧院id
     */
    private Integer theatreId;
    /**
     * 剧院id集
     */
    private List<Integer> theatreIds;
}
