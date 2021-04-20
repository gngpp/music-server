package com.zf1976.pojo.common.business.exception;

import com.zf1976.pojo.common.business.enums.BusinessMsgEnum;
import lombok.Data;

/**
 * @author ant
 * Create by Ant on 2020/5/22 下午2:31
 */
@Data
public class ExistEmailException extends RuntimeException{

    /**
     * 异常码
     */
    private Integer code;

    /**
     * 异常信息
     */
    private String msg;

    public ExistEmailException(BusinessMsgEnum businessMsgEnum){
        this.code=businessMsgEnum.getCode();
        this.msg=businessMsgEnum.getMsg();
    }
}
