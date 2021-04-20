package com.zf1976.pojo.dto.admin;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

/**
 * @author ant
 * Create by Ant on 2020/5/23 下午11:00
 */
@Data
@ApiModel(value = "歌手DTO")
public class SongDTO {

    @ApiModelProperty(value = "id")
    private Integer id;

    @ApiModelProperty(value = "歌手id")
    private Integer singerId;

    @ApiModelProperty(value = "歌曲名")
    private String name;

    @ApiModelProperty(value = "歌曲说明")
    private String introduction;

    @ApiModelProperty(value = "抒情的")
    private String lyric;


}
