/*
 * Vo (View Object)
 * 用户封装给前端的数据, 必须包含 swagger的 @ApiModule 和 @ApiModuleProperty 注解
 * 并且 @ApiModuleProperty 注解中的 required 属性不要填写（默认为 false）
 *
 * controller 代码:
 *
 * public DataResult<AdminVo> getAdminInfo();
 *
 * 从数据库中查出的数据为 pojo 则需要转换成为 com.zf1976.pojo.vo 才能返回给前端
 */
package com.zf1976.pojo;
