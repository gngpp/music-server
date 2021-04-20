package com.zf1976.pojo.common.convert;

import com.zf1976.pojo.dto.admin.SongDTO;
import com.zf1976.pojo.dto.app.StatisticalDTO;
import com.zf1976.pojo.po.Song;
import com.zf1976.pojo.vo.SongVO;
import com.zf1976.pojo.vo.app.StatisticalVO;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author ant
 * Create by Ant on 2020/5/23 上午11:49
 */
@Mapper(componentModel = "spring")
public interface SongConvert {

    /**
     * 转po
     *
     * @param vo vo
     * @return po
     */
    Song toPo(SongVO vo);

    /**
     * 转vo
     *
     * @param song po
     * @return vo
     */
    StatisticalVO toStatisticalVo(Song song);

    /**
     * 转po
     *
     * @param dto dto
     * @return po
     */
    Song toPo(StatisticalDTO dto);
    /**
     * 转vo
     *
     * @param song po
     * @return vo
     */
    SongVO toVo(Song song);

    /**
     * 转po
     *
     * @param dto dto
     * @return po
     */
    Song toVo(SongDTO dto);

    /**
     * 转vo list
     * @param list po list
     * @return vo list
     */
    List<SongVO> toVoList(List<Song> list);
}
