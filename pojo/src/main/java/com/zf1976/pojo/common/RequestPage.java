package com.zf1976.pojo.common;

import lombok.Data;

/**
 * 分页查询对象
 *
 * @author ant
 * @since 2020/5/19
 */
@Data
public class RequestPage<T> {

    /**
     * 分页大小
     */
    private int limit;

    /**
     * 排序字段
     */
    private String sort;

    /**
     * 排序方式 AES，DESC
     */
    private String dir;

    /**
     * 起始索引
     */
    private int start;

    /**
     * 当前页
     */
    private int page;

    /**
     * 请求数据
     */
    private T data;



    /**
     * 获取页大小
     *
     * @return int
     */
    public int getPageSize() {
        return limit == 0 ? 500 : limit;
    }

    /**
     * 获取页数
     *
     * @return 页数
     */
    public int getPageNo() {
        return Math.max(page, 1);
    }
}
