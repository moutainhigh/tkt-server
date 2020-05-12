package com.mtl.cypw.domain.mpm.param;

import lombok.Data;

import java.util.List;

/**
 * @author tang.
 * @date 2020/3/3.
 */
@Data
public class TheatreQueryParam {
    /**
     * 剧院id
     */
    private Integer theatreId;
    /**
     * 剧院id集
     */
    private List<Integer> theatreIds;
}
