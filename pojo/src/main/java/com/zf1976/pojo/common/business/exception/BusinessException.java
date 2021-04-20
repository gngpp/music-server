package com.zf1976.pojo.common.business.exception;

import com.zf1976.pojo.common.business.enums.BusinessMsgEnum;
import lombok.Data;

/**
 * @author mac
 * Create by Ant on 2020/6/12 7:19 下午
 */
@Data
public class BusinessException extends RuntimeException{
    /**
     * 异常码
     */
    private Integer code;

    /**
     * 异常信息
     */
    private String msg;

    public BusinessException(BusinessMsgEnum businessMsgEnum){
        this.code=businessMsgEnum.getCode();
        this.msg=businessMsgEnum.getMsg();
    }
}
