package com.mtl.cypw.service.config;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author Johnathon.Yuan
 * @date 2019-11-17 15:42
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Value("${global.swagger.enable:false}")
    private boolean swaggerEnable;

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                //.enable(swaggerEnable)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.mtl.cypw.api"))
                .build()
                .directModelSubstitute(LocalDate.class, java.sql.Date.class)
                .directModelSubstitute(DateTime.class, java.util.Date.class)
                .apiInfo(apiInfo());
    }

    ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("彩熠-票务平台接口文档")
                .description("彩熠票务平台API接口")
                .license("Apache 2.0")
                .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html")
                .version("1.0.0")
                .contact(new Contact("彩熠票务","www.tktstar.com", "tech@tktstar.com"))
                .build();
    }

}
