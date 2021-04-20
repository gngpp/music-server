package com.zf1976.server.config;

import com.zf1976.pojo.anno.AdminRestController;
import com.zf1976.pojo.anno.AppRestController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author ant
 * Create by Ant on 2020/5/18 下午7:49
 */
@Configuration
public class SwaggerConfig {
    @Bean
    public Docket appApiDoc() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("APP端接口")
                .apiInfo(appApiInfo())
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(AppRestController.class))
                .paths(PathSelectors.any())
                .build();
    }

    @Bean
    public Docket adminApiDoc() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("管理后台接口")
                .apiInfo(adminApiInfo())
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(AdminRestController.class))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo appApiInfo() {
        return new ApiInfoBuilder()
                .title("APP端接口文档")
                .description("提供给APP端的接口。\n"
                                     + "本文档由 [swagger2](http://springfox.github.io/springfox/) 自动生成。")
                .version("1.0")
                .build();
    }

    private ApiInfo adminApiInfo() {
        return new ApiInfoBuilder()
                .title("后台管理端接口文档")
                .description("提供给后台管理端的接口。\n"
                                     + "本文档由 [swagger2](http://springfox.github.io/springfox/) 自动生成。")
                .version("1.0")
                .build();
    }
}

