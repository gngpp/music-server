package com.zf1976.pojo.common.convert;

import com.zf1976.pojo.dto.app.RankDTO;
import com.zf1976.pojo.po.Rank;
import com.zf1976.pojo.vo.app.RankVO;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author ant
 * Create by Ant on 2020/5/23 上午11:44
 */
@Mapper(componentModel = "spring")
public interface RankConvert {

    /**
     * 转po
     *
     * @param vo vo
     * @return po
     */
    Rank toPo(RankVO vo);

    /**
     * 转po
     *
     * @param dto dto
     * @return po
     */
    Rank toPo(RankDTO dto);

    /**
     * 转vo
     *
     * @param rank po
     * @return vo
     */
    RankVO toVo(Rank rank);

}
