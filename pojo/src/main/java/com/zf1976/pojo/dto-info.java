/*
 * Dto (Data Transfer Object)
 * 数据传输对象, 用户接收前端传入的参数，必须包含 swagger的 @ApiModule 和 @ApiModuleProperty 注解
 * 如果需要参数校验则在Dto字段上添加校验注解
 *
 * controller 代码:
 *
 * public DataResult<AdminVo> getAdminInfo(@Validated @RequestBody AdminAddDto com.zf1976.pojo.com.zf1976.pojo.anno.com.zf1976.pojo.com.zf1976.pojo.dto);
 *
 */
package com.zf1976.pojo;
