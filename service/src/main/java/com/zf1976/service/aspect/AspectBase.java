package com.zf1976.service.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

/**
 * @author mac
 * Create by Ant on 2020/4/14 9:01 上午
 */
public interface AspectBase {

    /**
     * pointcut
     */
    public void pointCut();

    /**
     * 在切点之前织入
     * @param joinPoint 切点
     * @throws Throwable 向上抛异常
     */
    public void doBefore(JoinPoint joinPoint) throws Throwable;

    /**
     * 在切点之后织入
     * @throws Throwable 向上抛异常
     */
    public void doAfter() throws Throwable;

    /**
     * 环切
     * @param proceedingJoinPoint 程序切点
     * @return 程序信息
     * @throws Throwable 向上抛异常
     */
    public Object doAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable;

    /**
     * 获取切面注释
     * @param joinPoint 切点
     * @return 描述信息
     * @throws Exception 向上抛异常
     */
    public String getAspectLogDescription(JoinPoint joinPoint) throws Exception;

}
