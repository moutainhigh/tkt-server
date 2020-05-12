package com.mtl.cypw.test;

import com.alibaba.fastjson.JSONObject;
import com.mtl.cypw.domain.show.query.ActivityQuery;
import com.mtl.cypw.show.pojo.Activity;
import com.mtl.cypw.show.service.ActivityService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author tang.
 * @date 2020/1/6.
 */
@Slf4j
public class ActivityTest extends BaseTest {

    @Resource
    ActivityService activityService;

    @Test
    public void searchActivity() {
        ActivityQuery query = new ActivityQuery();
        List<Activity> list = activityService.searchActivity(query);
        log.info("activityList:{}", JSONObject.toJSONString(list));
    }
}
