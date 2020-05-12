package com.mtl.cypw.domain.mpm.param;

import lombok.Data;

import java.util.List;

/**
 * @author tang.
 * @date 2020/3/4.
 */
@Data
public class TemplateQueryParam {
    /**
     * 模板id
     */
    private Integer templateId;
    /**
     * 模板id集
     */
    private List<Integer> templateIds;
    /**
     * 场馆id
     */
    private Integer venueId;
    /**
     * 场馆id集
     */
    private List<Integer> venueIds;
}
