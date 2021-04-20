package com.zf1976.server.config;


import com.zf1976.service.common.ResourceUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author ant
 * Create by Ant on 2020/5/20 上午1:44
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("*")
                .allowCredentials(true);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**","swagger-ui.html","/webjars/**")
                .addResourceLocations(ResourceUtils.getFileDataResourcesPath(),
                                      "classpath:/static/",
                                      "classpath:/META-INF/resources/",
                                      "classpath:/META-INF/resources/webjars/");
    }

}

