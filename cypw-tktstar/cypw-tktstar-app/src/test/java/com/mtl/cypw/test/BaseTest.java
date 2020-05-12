package com.mtl.cypw.test;

import com.mtl.cypw.service.TktStarServiceAppBoot;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author: tang
 * @Date: 2019-10-18 14:40
 */
@SpringBootTest(classes = {TktStarServiceAppBoot.class}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
@TestPropertySource(locations = {"file:/opt/cypw_configs/cypw-global.properties","file:/opt/cypw_configs/cypw-tktstar.properties"})
public class BaseTest {

}
