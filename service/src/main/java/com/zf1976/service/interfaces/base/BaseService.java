package com.zf1976.service.interfaces.base;

import cn.hutool.core.io.FileUtil;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zf1976.service.common.ResourceUtils;
import org.slf4j.Logger;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 基础service
 *
 * @param <D> Dao层类
 * @param <E> 实体类
 *
 * @author lyqingye
 * @since 2020/3/27
 */
public abstract class BaseService<D extends BaseMapper<E>, E> extends ServiceImpl<D, E> {
    /**
     * 根据id集合取回对象列表
     *
     * @param idList id列表
     * @return 对象列表
     * @since 2020/04/26
     */
    @Override
    public List<E> listByIds(Collection<? extends Serializable> idList) {
        if (CollectionUtils.isEmpty(idList)) {
            return Collections.emptyList();
        }
        return baseMapper.selectBatchIds(idList);
    }

    /**
     * 对象拷贝
     *
     * @param sourcePage 原对象
     * @param translator func
     * @param <S>        目标对象
     * @return 转换结果
     */
    protected <S> IPage<S> mapPageToTarget(IPage<E> sourcePage, Function<E, S> translator) {
        final Page<S> targetPage = new Page<>(sourcePage.getCurrent(),
                                              sourcePage.getSize(),
                                              sourcePage.getTotal(),
                                              sourcePage.isSearchCount());
        final List<S> targetList = sourcePage.getRecords()
                                             .stream()
                                             .map(translator)
                                             .collect(Collectors.toList());
        targetPage.setRecords(targetList);
        return targetPage;
    }

    /**
     * 列表对象拷贝
     *
     * @param sourceList 原列表
     * @param translator func
     * @param <S>        目标对象
     * @return 转换结果
     */
    protected <S> List<S> mapListToTarget(List<E> sourceList, Function<E, S> translator) {
        return sourceList.stream()
                         .map(translator)
                         .collect(Collectors.toList());
    }

    protected void uploadFile(MultipartFile uploadFile, String newName, String folderPath, String url, Logger log) throws IOException {
        FileUtil.mkdir(folderPath);
        final String dataRealResourcesPath = ResourceUtils.getDataRealResourcesPath();
        if (!Objects.equals(url,null)){
            final File file = new File(dataRealResourcesPath, url);
            if (file.isFile()) {
                final boolean delete = file.delete();
                if (delete) {
                    if (log.isInfoEnabled()) {
                        log.info("Source file :{} has been deleted", file);
                    }
                }
            }
        }
        uploadFile.transferTo(Paths.get(folderPath, newName));
        if (log.isInfoEnabled()) {
            log.info("File exists under :{} directory", folderPath);
        }
    }
}
