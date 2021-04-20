package com.zf1976.pojo.common.business.exception;

import com.zf1976.pojo.common.business.enums.BusinessMsgEnum;
import lombok.Data;

/**
 * @author mac
 * Create by Ant on 2020/5/30 10:42 下午
 */
@Data
public class ExistSingerException extends RuntimeException{

    /**
     * 异常码
     */
    private Integer code;

    /**
     * 异常信息
     */
    private String msg;

    public ExistSingerException(BusinessMsgEnum businessMsgEnum){
        this.code=businessMsgEnum.getCode();
        this.msg=businessMsgEnum.getMsg();
    }
}
