package com.zf1976.pojo.common.convert;

import com.zf1976.pojo.dto.admin.SingerDTO;
import com.zf1976.pojo.po.Singer;
import com.zf1976.pojo.vo.SingerVO;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author ant
 * Create by Ant on 2020/5/23 上午11:46
 */
@Mapper(componentModel = "spring")
public interface SingerConvert {

    /**
     * 转po
     *
     * @param dto dto
     * @return po
     */
    Singer toPo(SingerDTO dto);

    /**
     * 转vo
     *
     * @param singer po
     * @return vo
     */
    SingerVO toVo(Singer singer);

    /**
     * 转vo list
     *
     * @param singers po list
     * @return vo list
     */
    List<SingerVO> toVoList(List<Singer> singers);
}
