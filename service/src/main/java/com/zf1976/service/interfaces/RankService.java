package com.zf1976.service.interfaces;

import com.zf1976.dao.RankDao;
import com.zf1976.pojo.common.business.exception.DataException;
import com.zf1976.pojo.common.business.enums.BusinessMsgEnum;
import com.zf1976.pojo.common.convert.RankConvert;
import com.zf1976.pojo.dto.app.RankDTO;
import com.zf1976.pojo.po.Rank;
import com.zf1976.service.interfaces.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * (Rank)表Service接口
 *
 * @author makejava
 * @since 2020-05-20 00:00:49
 */
@Service
public class RankService extends BaseService<RankDao, Rank> {

    @Autowired
    private RankDao rankDao;

    @Autowired
    private RankConvert rankConvert;

    /**
     * 前台用户(客户)添加评分
     *
     * @param dto dto
     * @return null
     */
    public Void addRank(RankDTO dto){
        final Rank rank = rankConvert.toPo(dto);
        if (isSameConsumer(rank.getConsumerId(),rank.getSongListId())){
            super.lambdaUpdate()
                 .eq(Rank::getConsumerId,rank.getConsumerId())
                 .eq(Rank::getSongListId,rank.getSongListId())
                 .update(rank);
            return null;
        }
        super.save(rank);
        return null;
    }

    /**
     * 获取歌单评分
     *
     * @param songListId 歌单id
     * @return Integer
     */
    public Integer getSongListBankById(int songListId){
        final Integer avgScore = rankDao.getAvgScore(songListId);
        if (Objects.equals(avgScore,null)){
            throw new DataException(BusinessMsgEnum.DATA_FAIL);
        }
        return avgScore;
    }



    /**
     * 是否同一个客户评分
     *
     * @param userId 客户id
     * @param songListId 歌单id
     * @return Boolean
     */
    private Boolean isSameConsumer(int userId,int songListId){

        try {
            super.lambdaQuery()
                 .eq(Rank::getConsumerId, userId)
                 .eq(Rank::getSongListId, songListId)
                 .oneOpt().orElseThrow(() -> new DataException(BusinessMsgEnum.DATA_FAIL));
        } catch (DataException e) {
            return false;
        }
        return true;
    }

}
