package com.zf1976.pojo.common.convert;

import com.zf1976.pojo.dto.app.CollectDTO;
import com.zf1976.pojo.po.Collect;
import com.zf1976.pojo.vo.CollectVO;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Repository;

import java.text.CollationElementIterator;
import java.util.List;

/**
 * @author ant
 * Create by Ant on 2020/5/22 下午11:14
 */
@Mapper(componentModel = "spring")
public interface CollectConvert {

    /**
     * 转vo
     *
     * @param collect po
     * @return vo
     */
    CollectVO toVo(Collect collect);

    /**
     * 转po
     *
     * @param dto dto
     * @return po
     */
    Collect toPo(CollectDTO dto);

    /**
     * 转vo list
     *
     * @param collectList po list
     * @return vo list
     */
    List<CollectVO> toVoList(List<Collect> collectList);

    /**
     * 转vo list
     *
     * @param vos vo list
     * @return po list
     */
    List<Collect> toPoList(List<CollectVO> vos);

}
