package com.mtl.cypw.domain.show.query;

import com.mtl.cypw.domain.show.enums.ProgramTypeEnum;
import lombok.Data;

import java.util.List;

/**
 * @author tang.
 * @date 2019/11/21.
 */
@Data
public class ProgramQuery {

    private Integer enterpriseId;

    private Integer programId;

    private Integer isRecommend;

    /**
     * 演出类型
     */
    private ProgramTypeEnum programType;

    /**
     * 演出类型集
     * @see ProgramTypeEnum
     */
    private List<Integer> programTypeList;

    /**
     * 模糊搜索，匹配演出名称or演出关键字
     */
    private String likeName;

    /**
     * 销售状态
     * 70 ：停售
     * 71 ：开售
     * 72 ：预售
     * 73 ：预约
     */
    private Integer saleStatus;

    /**
     * 销售状态集
     * 70 ：停售
     * 71 ：开售
     * 72 ：预售
     * 73 ：预约
     */
    private List<Integer> saleStatusList;
}
