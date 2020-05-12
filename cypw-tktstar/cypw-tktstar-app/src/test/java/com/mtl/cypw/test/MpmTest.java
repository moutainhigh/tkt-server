package com.mtl.cypw.test;

import com.mtl.cypw.common.utils.JsonUtils;
import com.mtl.cypw.mpm.model.Template;
import com.mtl.cypw.mpm.service.TemplateService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Johnathon.Yuan
 * @date 2020-01-14 15:17
 */
@Slf4j
public class MpmTest extends BaseTest {

    @Autowired
    private TemplateService templateServiceImpl;

    @Test
    public void testTemplate() {
        //Template template = templateMapper.selectByPrimaryKey(4);

        Template template = templateServiceImpl.getTemplate(4);
        log.info(JsonUtils.toJson(template));
    }
}
