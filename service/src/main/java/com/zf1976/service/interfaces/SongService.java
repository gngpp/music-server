package com.zf1976.service.interfaces;

import cn.hutool.core.io.FileUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zf1976.dao.SongDao;
import com.zf1976.pojo.common.RequestPage;
import com.zf1976.pojo.common.business.exception.FileUploadException;
import com.zf1976.pojo.common.business.exception.DataException;
import com.zf1976.pojo.common.business.enums.BusinessMsgEnum;
import com.zf1976.pojo.common.convert.SongConvert;
import com.zf1976.pojo.dto.admin.SongDTO;
import com.zf1976.pojo.dto.app.StatisticalDTO;
import com.zf1976.pojo.po.Song;
import com.zf1976.pojo.vo.SongVO;
import com.zf1976.service.interfaces.base.BaseService;
import com.zf1976.service.common.ResourceUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

/**
 * (Song)表Service接口
 *
 * @author makejava
 * @since 2020-05-20 00:00:49
 */
@Service
public class SongService extends BaseService<SongDao, Song> {

    public static final Logger log = LoggerFactory.getLogger(SongService.class);

    @Autowired
    private SongDao songDao;

    @Autowired
    private SongConvert songConvert;

    /**
     * 获取所有歌曲
     *
     * @return vo list
     */
    public List<SongVO> getAllSong(){
        final List<Song> songs = super.lambdaQuery()
                                     .list();
        return super.mapListToTarget(songs,song -> {
            return songConvert.toVo(song);
        });
    }

    /**
     * 分页查询歌曲
     *
     * @param requestPage page
     * @return IPage<SongVO>
     */
    public IPage<SongVO> getSongPage(RequestPage<SongDTO> requestPage){
        final Page<Song> page = super.page(new Page<>(requestPage.getPageNo(),
                                                      requestPage.getPageSize()));
        return super.mapPageToTarget(page,song -> {
            return songConvert.toVo(song);
        });
    }

    /**
     * 返回指定歌手ID的歌曲
     *
     * @param singerId singId
     * @return SongVo
     */
    public List<SongVO> getSongBySingerId(int singerId){
        final List<Song> songs = super.lambdaQuery()
                               .eq(Song::getSingerId, singerId)
                               .list();
        return super.mapListToTarget(songs,song -> {
            return songConvert.toVo(song);
        });
    }

    /**
     * 添加歌曲，附带音频
     *
     * @param uploadFile 上传mp3文件
     * @param dto dto
     * @return null
     */
    public Void addSong(MultipartFile uploadFile,SongDTO dto) {
        ResourceUtils.uploadCheckEmpty(uploadFile);
        isExistSong(dto);

        final Song song = songConvert.toVo(dto);
        final String oldName = uploadFile.getOriginalFilename();
        final String newName = ResourceUtils.rename(oldName);
        final String folderPath = ResourceUtils.getUploadSongFolderPath();
        final String uploadSongPath = ResourceUtils.getUploadSongPath(newName);
        try {
            super.uploadFile(uploadFile, newName, folderPath, song.getUrl(), log);
            song.setUrl(uploadSongPath);
            super.save(song);
        } catch (Exception e) {
            if (log.isInfoEnabled()) {
                log.info("Exception message:{}", e.getMessage());
            }
            throw new FileUploadException(BusinessMsgEnum.FILE_ERROR);
        }

        return null;
    }



    /**
     * 更新歌曲url
     *
     * @param uploadFile 上传mp3文件
     * @param id 歌曲id
     * @return null
     */
    public Void uploadSongUrl(MultipartFile uploadFile,int id){

        ResourceUtils.uploadCheckEmpty(uploadFile);

        final Song song = super.lambdaQuery()
                               .eq(Song::getId, id)
                               .oneOpt().orElseThrow(() -> new DataException(BusinessMsgEnum.DATA_FAIL));

        final String oldName = uploadFile.getOriginalFilename();
        final String newName = ResourceUtils.rename(oldName);
        final String folderPath = ResourceUtils.getUploadSongFolderPath();
        final String uploadSongPath = ResourceUtils.getUploadSongPath(newName);

        FileUtil.mkdir(folderPath);
        try {
            super.uploadFile(uploadFile, newName, folderPath, song.getUrl(), log);
            song.setUrl(uploadSongPath);
            super.updateById(song);
        } catch (IOException e) {
            if (log.isInfoEnabled()) {
                log.info("Exception message:{}",e.getMessage());
            }
            throw new FileUploadException(BusinessMsgEnum.FILE_ERROR);
        }

        return null;
    }

    /**
     * 更新歌曲封面
     *
     * @param uploadFile 上传封面
     * @param id id
     * @return null
     */
    public Void uploadSongPic(MultipartFile uploadFile,int id){

        ResourceUtils.uploadCheckEmpty(uploadFile);

        final Song song = super.lambdaQuery()
                               .eq(Song::getId, id)
                               .oneOpt().orElseThrow(() -> new DataException(BusinessMsgEnum.DATA_FAIL));

        final String oldName = uploadFile.getOriginalFilename();
        final String newName = ResourceUtils.rename(oldName);
        final String folderPath = ResourceUtils.getUploadSongPicFolderPath();
        final String uploadSongPicPath = ResourceUtils.getUploadSongPicPath(newName);

        FileUtil.mkdir(folderPath);

        try {
            super.uploadFile(uploadFile, newName, folderPath, song.getPic(), log);
            song.setPic(uploadSongPicPath);
            super.updateById(song);
        } catch (IOException e) {
            if (log.isInfoEnabled()) {
                log.info("Exception message:{}",e.getMessage());
            }
            throw new FileUploadException(BusinessMsgEnum.FILE_ERROR);
        }

        return null;
    }

    /**
     * 根据歌曲id返回歌曲
     *
     * @param id id
     * @return SongVo
     */
    public SongVO getSongById(int id){
        final Song song = super.lambdaQuery()
                               .eq(Song::getId, id)
                               .oneOpt().orElseThrow(() -> new DataException(BusinessMsgEnum.DATA_FAIL));
        return songConvert.toVo(song);
    }

    /**
     * 返回包含歌手名歌曲
     *
     * @param name name
     * @return vo
     */
    public List<SongVO> getSongByLikeSingerName(String name){
        final List<Song> songs = super.lambdaQuery()
                                     .like(Song::getName,name)
                                     .list();

        return super.mapListToTarget(songs,song -> {
            return songConvert.toVo(song);
        });
    }

    /**
     * 是否已存在歌曲
     *
     * @param dto dto
     */
   public void isExistSong(SongDTO dto) {
       Song song = null;
       try {
           song = super.lambdaQuery()
                       .eq(Song::getName, dto.getName())
                       .oneOpt().orElseThrow(() -> new DataException(BusinessMsgEnum.DATA_FAIL));
       } catch (DataException e) {
           return;
       }
       if (Objects.equals(song.getName(), dto.getName())) {
           throw new DataException(BusinessMsgEnum.DATA_SUCCESS);
       }
   }

    /**
     * 是否更新
     *
     * @param dto dto
     */
   public void isUpdate(SongDTO dto){
       final Song song = super.lambdaQuery()
                              .eq(Song::getId, dto.getId())
                              .oneOpt().orElseThrow(() -> new DataException(BusinessMsgEnum.DATA_FAIL));
       if (!Objects.equals(song.getName(), dto.getName())){
           isExistSong(dto);
       }
   }

    /**
     * 更新下载量播放量
     *
     * @param dto dto
     * @return null
     */
   public Void updateStatistical(StatisticalDTO dto){
       final Song song = songConvert.toPo(dto);
       super.updateById(song);
       return null;
   }

    /**
     * 根据歌曲id更新信息
     *
     * @param dto dto
     * @return null
     */
    public Void updateSongMsg(SongDTO dto){
        isUpdate(dto);
        final Song song = songConvert.toVo(dto);
        super.updateById(song);
        return null;
    }

    /**
     * 根据歌曲id删除歌曲
     *
     * @param id id
     * @return null
     */
    public Void deleteSong(int id){
        super.removeById(id);
        return null;
    }

}
