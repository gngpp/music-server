package com.zf1976.service.interfaces;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zf1976.dao.SingerDao;
import com.zf1976.dao.SongDao;
import com.zf1976.pojo.common.RequestPage;
import com.zf1976.pojo.common.business.exception.ExistSingerException;
import com.zf1976.pojo.common.business.exception.FileUploadException;
import com.zf1976.pojo.common.business.exception.DataException;
import com.zf1976.pojo.common.business.enums.BusinessMsgEnum;
import com.zf1976.pojo.common.convert.SingerConvert;
import com.zf1976.pojo.dto.admin.SingerDTO;
import com.zf1976.pojo.po.Singer;
import com.zf1976.pojo.po.Song;
import com.zf1976.pojo.vo.SingerVO;
import com.zf1976.service.interfaces.base.BaseService;
import com.zf1976.service.common.ResourceUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

/**
 * (Singer)表Service接口
 *
 * @author makejava
 * @since 2020-05-20 00:00:49
 */
@Service
@Slf4j
@SuppressWarnings("rawtypes")
public class SingerService extends BaseService<SingerDao, Singer> {

    @Autowired
    private SingerDao singerDao;

    @Autowired
    private SongDao songDao;

    @Autowired
    private SingerConvert singerConvert;

    /**
     * 返回所有歌手
     *
     * @return 所有歌手
     */
    public List<SingerVO> getAllSinger(){
        final List<Singer> singers = super.lambdaQuery()
                                       .list();
        return super.mapListToTarget(singers,singer -> {
            return singerConvert.toVo(singer);
        });
    }

    /**
     * 分页查询歌手
     *
     * @param requestPage page
     * @return IPage<SingerVo>
     */
    public IPage<SingerVO> getSingerPage(RequestPage requestPage){

        final Page<Singer> page = super.lambdaQuery()
                                       .page(new Page<Singer>(requestPage.getPageNo(),
                                                              requestPage.getPageSize()));
        return super.mapPageToTarget(page,singer -> {
            return singerConvert.toVo(singer);
        });
    }

    /**
     * 通过性别对歌手分类
     *
     * @param sex 性别
     * @return List<SingerVO>
     */
    public List<SingerVO> getSingerBySex(int sex){
        final List<Singer> singers = super.lambdaQuery()
                                       .eq(Singer::getSex, sex)
                                       .list();
        return super.mapListToTarget(singers,singer -> {
            return singerConvert.toVo(singer);
        });
    }

    /**
     * 通过性别对歌手分类分页查询
     *
     * @param requestPage page
     * @return IPage<SingerVO>
     */
    public IPage<SingerVO> getSingerPageBySex(RequestPage<SingerDTO> requestPage){
        final Integer sex = requestPage.getData()
                                       .getSex();
        final LambdaQueryWrapper<Singer> wrapper = new LambdaQueryWrapper<Singer>().eq(Singer::getSex, sex);
        final Page<Singer> page = super.page(new Page<>(requestPage.getPageNo(),
                                                        requestPage.getPageSize()),wrapper);
        return super.mapPageToTarget(page,singer -> {
            return singerConvert.toVo(singer);
        });
    }

    /**
     * 添加歌手
     *
     * @param dto dto
     * @return null
     */
    public Void addSinger(SingerDTO dto){
        isExistSinger(dto.getName());
        final Singer singer = singerConvert.toPo(dto);
        super.save(singer);
        return null;
    }

    /**
     * 是否存在歌手
     *
     * @param name 歌手名
     */
    private void isExistSinger(String name){
        Singer singer=null;
        try {
            singer = super.lambdaQuery()
                        .eq(Singer::getName, name)
                        .oneOpt().orElseThrow(() -> new ExistSingerException(BusinessMsgEnum.NOT_EXIST_SINGER));
        }catch (ExistSingerException e){
            return;
        }
        if (Objects.equals(singer.getName(),name)) {
            throw new ExistSingerException(BusinessMsgEnum.EXIST_SINGER);
        }
    }

    /**
     * 更新歌手图片
     *
     * @param uploadFile 上传图片
     * @param id 歌手id
     * @return null
     */
    public Void updateSingerPic(MultipartFile uploadFile, int id){

        ResourceUtils.uploadCheckEmpty(uploadFile);

        final Singer singer = super.lambdaQuery()
                                   .eq(Singer::getId, id)
                                   .oneOpt().orElseThrow(() -> new DataException(BusinessMsgEnum.DATA_FAIL));

        final String oldName = uploadFile.getOriginalFilename();
        final String newName = ResourceUtils.rename(oldName);
        final String folderPath = ResourceUtils.getUploadSingerPicFolderPath();
        final String uploadSingerPicPath = ResourceUtils.getUploadSingerPicPath(newName);
        try {
            super.uploadFile(uploadFile, newName, folderPath, singer.getPic(), log);
            singer.setPic(uploadSingerPicPath);
            super.updateById(singer);
        } catch (IOException e) {
            if (log.isInfoEnabled()) {
                log.info("Exception message:{}",e.getMessage());
            }
            throw new FileUploadException(BusinessMsgEnum.FILE_ERROR);
        }

        return null;
    }

    /**
     * 根据歌手id更新信息
     *
     * @param dto dto
     * @return null
     */
    public Void updateSingerMsg(SingerDTO dto){
        isUpdate(dto.getId(), dto.getName());
        final Singer singer = singerConvert.toPo(dto);
        super.updateById(singer);
        return null;
    }

    /**
     * 是否更新
     *
     * @param id id
     * @param name name
     */
    private void isUpdate(int id, String name){
        final Singer singer = super.lambdaQuery()
                                   .eq(Singer::getId, id)
                                   .oneOpt().orElseThrow(() -> new DataException(BusinessMsgEnum.DATA_FAIL));
        if (!Objects.equals(singer.getName(), name)) {
            isExistSinger(name);
        }
    }

    /**
     * 根据歌手id删除歌手 两张表需要事务
     *
     * @param id id
     * @return null
     */
    @Transactional(rollbackFor = Exception.class)
    public Void deleteSinger(int id){
        final LambdaQueryWrapper<Song> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Song::getSingerId, id);
        songDao.delete(wrapper);
        super.removeById(id);
        return null;
    }
}
