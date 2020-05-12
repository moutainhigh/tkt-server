package com.mtl.cypw.test;

import com.alibaba.fastjson.JSONObject;
import com.mtl.cypw.domain.mpm.enums.BuryingPointTypeEnum;
import com.mtl.cypw.domain.mpm.param.BuryingPointParam;
import com.mtl.cypw.domain.mpm.param.BuryingPointQueryParam;
import com.mtl.cypw.mpm.model.BuryingPointInfo;
import com.mtl.cypw.mpm.service.BuryingPointService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author tang.
 * @date 2020/3/12.
 */
@Slf4j
public class BuryingPointTest extends BaseTest {

    @Resource
    private BuryingPointService buryingPointServiceImpl;

    @Test
    public void delete() {
        BuryingPointParam param = new BuryingPointParam();
        param.setMemberId(80001084);
        buryingPointServiceImpl.deleteBuryingPoint(param);
    }

    @Test
    public void getBuryingPoint() {
        BuryingPointQueryParam queryParam = new BuryingPointQueryParam();
        queryParam.setBuryingPointType(BuryingPointTypeEnum.MEMBER_SEARCH_LOG);
        List<BuryingPointInfo> list = buryingPointServiceImpl.findBuryingPointInfoList(queryParam);
        log.info("num:{},json:{}", list.size(), JSONObject.toJSONString(list));
    }
}
