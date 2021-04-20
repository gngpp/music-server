package com.zf1976.pojo.common.business.enums;

import lombok.Getter;
import lombok.ToString;

/**
 * @author ant
 * Create by Ant on 2020/5/22 下午1:56
 */
@Getter
@ToString
public enum BusinessMsgEnum {

    /**
     * 验证码错误
     */

    CODE_ERROR(500,"验证码错误"),

    /**
     * 空指针异常
     */
    NPE_EXCEPTION(500,"空指针异常!"),

    /**
     * 文件超过大小
     */
    FILE_OVER_MAX(500,"文件超过最大限度!"),

    /**
     * 文件上传失败
     */
    FILE_ERROR(500,"文件上传失败!"),

    /**
     * 查找失败异常
     */
    DATA_FAIL(500, "数据不存在!"),

    /**
     *
     */
    DATA_SUCCESS(500,"数据已存在!"),

    /**
     * 非会员
     */
    NOT_MEMBER(500,"非会员"),

    /**
     * 参数异常
     */
    PARAMETER_EXCEPTION(102,"参数异常!"),

    /**
     * 手机号已存在
     */
    EXIST_PHONE(403,"手机号已存在!"),

    /**
     * 邮箱已存在
     */
    EXIST_EMAIL(402,"邮箱已存在!"),

    /**
     * 用户已存在
     */
    EXIST_USER(401,"用户已存在!"),

    /**
     * 歌手已存在
     */
    EXIST_SINGER(401,"歌手已存在!"),

    /**
     * 用户不存在
     */
    NOT_EXIST_USER(400,"用户不存在!"),

    /**
     * 歌手不存在
     */
    NOT_EXIST_SINGER(400,"歌手不存在!");
    /**
     * 消息码
     */
    private final Integer code;

    /**
     * 消息
     */
    private final String msg;

    BusinessMsgEnum(Integer code,String msg) {
        this.code=code;
        this.msg=msg;
    }

}
