package com.mtl.cypw.service;

import com.mtl.cypw.common.utils.PropertyRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author Johnathon.Yuan
 * @date 2019-11-17 15:37
 */

@ComponentScan(basePackages = {
        "com.mtl",
        "com.juqitech"
})

@MapperScan(value = {
        "com.mtl.cypw.api",
        "com.mtl.cypw.coupon",
        "com.mtl.cypw.member",
        "com.mtl.cypw.mpm",
        "com.mtl.cypw.order",
        "com.mtl.cypw.payment",
        "com.mtl.cypw.show",
        "com.mtl.cypw.stock",
        "com.mtl.cypw.ticket",
        "com.mtl.cypw.mall",
        "com.mtl.cypw.msg",
        "com.mtl.cypw.admin"
})
@EnableSwagger2
@Slf4j
@EnableEurekaClient
@EnableDiscoveryClient
@EnableFeignClients(basePackages={"com.mtl","com.juqitech"})
@SpringBootApplication
public class TktStarServiceAppBoot {

    public static void main(String[] args) {
        try {
            PropertyRepository.init("cypw-global.properties");
            PropertyRepository.init("cypw-tktstar.properties");
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            System.exit(0);
        }
        SpringApplication.run(TktStarServiceAppBoot.class, args);
    }


}
