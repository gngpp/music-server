package com.zf1976.server.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.pagination.optimize.JsqlParserCountOptimize;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author mac
 * Create by Ant on 2020/6/4 12:23 下午
 */
@Configuration
public class MybatisPlusConfig {

    @Bean
    PaginationInterceptor paginationInterceptor(){
        final PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        return paginationInterceptor.setOverflow(false)
                                    .setCountSqlParser(new JsqlParserCountOptimize(true))
                                    .setLimit(-1);
    }
}
