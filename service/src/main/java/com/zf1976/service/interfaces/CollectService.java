package com.zf1976.service.interfaces;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zf1976.dao.CollectDao;
import com.zf1976.pojo.common.business.exception.DataException;
import com.zf1976.pojo.common.business.enums.BusinessMsgEnum;
import com.zf1976.pojo.common.convert.CollectConvert;
import com.zf1976.pojo.dto.app.CollectDTO;
import com.zf1976.pojo.po.Collect;
import com.zf1976.pojo.vo.CollectVO;
import com.zf1976.service.interfaces.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * (Collect)表Service接口
 *
 * @author makejava
 * @since 2020-05-20 00:00:49
 */
@Service
public class CollectService extends BaseService<CollectDao, Collect> {

    @Autowired
    private CollectDao collectDao;

    @Autowired
    private CollectConvert collectConvert;

    /**
     * 返回的指定用户ID的收藏列表
     *
     * @param userId 客户id
     * @return 收藏列表
     */
    public List<CollectVO> getCollectByUserId(int userId){
        final List<Collect> collects = super.lambdaQuery()
                                        .eq(Collect::getUserId, userId)
                                        .list();
        return super.mapListToTarget(collects,collect -> {
            return collectConvert.toVo(collect);
        });
    }

    /**
     * 添加收藏
     *
     * @param dto dto
     * @return null
     */
    public Void addCollection(CollectDTO dto){
        isExistCollection(dto.getUserId(), dto.getSongId());
        final Collect collect = collectConvert.toPo(dto);
        super.save(collect);
        return null;
    }

    /**
     * 取消收藏
     *
     * @param dto dto
     * @return null
     */
    public Void deleteCollection(CollectDTO dto){
        final LambdaQueryWrapper<Collect> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Collect::getSongId, dto.getSongId())
               .eq(Collect::getUserId, dto.getUserId());
        super.remove(wrapper);
        return null;
    }

    /**
     * 是否已收藏
     *
     * @param userId 用户(客户)id
     * @param songId 歌曲id
     */
    private void isExistCollection(int userId, int songId){
        Collect collect = null;
        try {
            collect = super.lambdaQuery()
                           .eq(Collect::getSongId, songId)
                           .eq(Collect::getUserId, userId)
                           .oneOpt().orElseThrow(() -> new DataException(BusinessMsgEnum.DATA_FAIL));
        } catch (DataException e) {
            return;
        }
        if (Objects.equals(collect.getUserId(),userId) && Objects.equals(collect.getSongId(),songId)){
            throw new DataException(BusinessMsgEnum.DATA_SUCCESS);
        }
    }

    /**
     * 根据客户删除收藏歌曲
     *
     * @param userId 客户id
     * @param songId 歌曲id
     * @return null
     */
    public Void deleteCollect(int userId,int songId){
        final Collect collect = super.lambdaQuery()
                                    .eq(Collect::getUserId, userId)
                                    .eq(Collect::getSongId, songId)
                                    .oneOpt().orElseThrow(() -> new DataException(BusinessMsgEnum.DATA_FAIL));
        super.removeById(collect.getId());
        return null;
    }
}
