package com.mtl.cypw.common.util;

import com.juqitech.response.paging.Pagination;
import org.apache.commons.collections.CollectionUtils;
import tk.mybatis.mapper.entity.Example;

/**
 * @author Johnathon.Yuan
 * @date 2019-11-22 18:01
 */
public class CollatorUtils {

    /**
     * 排序组装
     * @param pagination
     * @param example
     */
    public static void parseSortByExample(Pagination pagination, Example example) {

        if (pagination != null && CollectionUtils.isNotEmpty(pagination.getSortingConditions())) {
            StringBuilder builder = new StringBuilder(32);
            pagination.getSortingConditions().stream().forEach(sorting ->
                    builder.append(sorting.getFieldName())
                            .append(" ")
                            .append(sorting.getSortingMethod())
                            .append(","));
            example.setOrderByClause(builder.deleteCharAt(builder.length()-1).toString());
        } else {
            example.setOrderByClause("create_time DESC");
        }
    }
}
