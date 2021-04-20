package com.zf1976.pojo.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * 请求响应对象
 *
 * @author ant
 * @since 2020/5/19
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@SuppressWarnings("rawtypes")
public class DataResult<T> {

    /**
     * 响应是否成功
     */
    private Boolean success;

    /**
     * 响应码
     */
    private Integer status;

    /**
     * 错误代码
     */
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private int errCode;

    /**
     * 错误消息
     */
    private String errMsg;

    /**
     * 错误详情
     */
    private String errDetail;

    /**
     * 响应数据
     */
    private T data;

    /**
     * 响应带数据的成功消息
     *
     * @param data 数据
     * @param <E>  E
     * @return 响应对象
     */
    public static <E> DataResult<E> success(E data) {
        DataResult<E> vo = new DataResult<>();
        vo.setSuccess(true);
        vo.setStatus(200);
        vo.setData(data);
        return vo;
    }


    /**
     * 响应成功消息
     *
     * @param <E> E
     * @param sign param
     * @return 响应对象
     */
    public static <E> DataResult<E> success(Void sign) {
        DataResult<E> vo = new DataResult<>();
        vo.setSuccess(true);
        vo.setStatus(200);
        vo.setData(null);
        return vo;
    }

    /**
     * 返回失败消息
     * @param errMsg 失败消息
     * @return 响应对象
     */
    public static DataResult fail(String errMsg) {
        return fail( errMsg,500);
    }

    /**
     * 返回失败消息
     * @param errMsg 错误消息
     * @param errCode 错误码
     * @return 响应对象
     */
    public static DataResult fail(String errMsg, int errCode) {
        DataResult vo = new DataResult();
        vo.setSuccess(false);
        vo.setErrCode(errCode);
        vo.setErrMsg(errMsg);
        return vo;
    }
}
