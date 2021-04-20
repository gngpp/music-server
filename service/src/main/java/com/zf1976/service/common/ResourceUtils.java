package com.zf1976.service.common;

import cn.hutool.core.lang.UUID;
import com.zf1976.pojo.common.business.exception.DataException;
import com.zf1976.pojo.common.business.enums.BusinessMsgEnum;
import com.zf1976.pojo.common.business.enums.PathNameEnum;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.text.SimpleDateFormat;

/**
 * 这里dataParentResourcePath会多一级路径，实际上传是真实路径
 * @author ant
 * Create by Ant on 2020/5/20 下午1:36
 */
public final class ResourceUtils {

    public final static PathMatchingResourcePatternResolver PATH_UTIL = new PathMatchingResourcePatternResolver();

    public static SimpleDateFormat SIMPLE_DATE_FORMAT = null;

    public static SimpleDateFormat getSimpleDateFormat(){
        if (SIMPLE_DATE_FORMAT == null) {
            SIMPLE_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        }
        return SIMPLE_DATE_FORMAT;
    }

    public static String getFileDataResourcesPath() {
        return PathNameEnum.FILE.value + getDataParentResourcesPath() + PathNameEnum.SYSTEM_DATA.value;
    }

    public static String getDataRealResourcesPath() {
        return getDataParentResourcesPath() + PathNameEnum.FILE_DATA.value;
    }

    public static String getDataParentResourcesPath() {

        try {
            File baseFile = new File("");
            return baseFile.getCanonicalFile()
                           .getParent();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getUuid(){
        return UUID.randomUUID().toString();
    }

    public static Long getCurrentTimeMillis(){
        return System.currentTimeMillis();
    }

    public static String rename(String oldName){
        final String substring = oldName.substring(oldName.lastIndexOf("."));
        return getUuid()+substring;
    }

    public static String getUploadAvatarFolderPath(){
        return getDataParentResourcesPath() + PathNameEnum.FILE_DATA.value + PathNameEnum.AVATAR_IMAGES.value;
    }

    public static String getUploadAvatarPath(String fileName){
        return PathNameEnum.AVATAR_IMAGES.value +fileName;
    }

    public static String getUploadSingerPicFolderPath(){
        return getDataParentResourcesPath() + PathNameEnum.FILE_DATA.value + PathNameEnum.IMG_SINGER_PIC.value;
    }

    public static String getUploadSingerPicPath(String fileName){
        return PathNameEnum.IMG_SINGER_PIC.value + fileName;
    }

    public static String getUploadSongPicFolderPath(){
        return getDataParentResourcesPath() + PathNameEnum.FILE_DATA.value + PathNameEnum.IMG_SONG_PIC.value;
    }

    public static String getUploadSongPicPath(String fileName){
        return PathNameEnum.IMG_SONG_PIC.value + fileName;
    }

    public static String getUploadSongListPicFolderPath(){
        return getDataParentResourcesPath() + PathNameEnum.FILE_DATA.value + PathNameEnum.IMG_SONG_LIST_PIC.value;
    }

    public static String getUploadSongListPicPath(String fileName){
        return PathNameEnum.IMG_SONG_LIST_PIC.value +fileName;
    }

    public static String getUploadSongFolderPath(){
        return getDataParentResourcesPath() + PathNameEnum.FILE_DATA.value + PathNameEnum.SONG.value;
    }

    public static String getUploadSongPath(String fileName){
        return PathNameEnum.SONG.value +fileName;
    }

    public static  Void uploadCheckEmpty(MultipartFile uploadFile){
        if (uploadFile.isEmpty()) {
            throw new DataException(BusinessMsgEnum.FILE_ERROR);
        }
        return null;
    }
}
