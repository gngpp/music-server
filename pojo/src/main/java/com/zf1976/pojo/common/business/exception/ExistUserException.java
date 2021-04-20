package com.zf1976.pojo.common.business.exception;

import com.zf1976.pojo.common.business.enums.BusinessMsgEnum;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author ant
 * Create by Ant on 2020/5/22 下午2:32
 */
@Data
public class ExistUserException extends RuntimeException{

    /**
     * 异常码
     */
    private Integer code;

    /**
     * 异常信息
     */
    private String msg;

    public ExistUserException(BusinessMsgEnum businessMsgEnum){
        this.code=businessMsgEnum.getCode();
        this.msg=businessMsgEnum.getMsg();
    }


}
