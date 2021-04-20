package com.zf1976.service.aspect.annotation;

import java.lang.annotation.*;

/**
 * @author ant
 * Create by Ant on 2020/4/6 上午1:23
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface Log {

    /**
     * 日志描述
     */
    String description() default "";
}
