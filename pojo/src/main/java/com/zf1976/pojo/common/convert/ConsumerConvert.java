package com.zf1976.pojo.common.convert;


import com.zf1976.pojo.dto.admin.ConsumerDTO;
import com.zf1976.pojo.dto.app.UserInfoDTO;
import com.zf1976.pojo.dto.app.UserSignUpDTO;
import com.zf1976.pojo.po.Consumer;
import com.zf1976.pojo.vo.ConsumerVO;
import com.zf1976.pojo.vo.app.UserInfoVO;
import com.zf1976.pojo.vo.app.UserMsgVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

/**
 * @author ant
 * Create by Ant on 2020/5/21 下午3:42
 */
@Mapper(componentModel = "spring")
public interface ConsumerConvert {
    /**
     * 转po
     *
     * @param dto dto
     * @return po
     */
    @Mapping(target = "isMember", ignore = true)
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "updateTime", ignore = true)
    @Mapping(target = "createTime", ignore = true)
    Consumer toPo(ConsumerDTO dto);

    /**
     * 转po
     *
     * @param dto dto
     * @return po
     */
    Consumer toPo(UserSignUpDTO dto);

    /**
     * 转vo
     *
     * @param consumer po
     * @return vo
     */
    ConsumerVO toVo(Consumer consumer);

    /**
     * 转vo
     *
     * @param consumer po
     * @return vo
     */
    UserMsgVO toUserMasVo(Consumer consumer);


    /**
     * 转vo
     *
     * @param consumer po
     * @return vo
     */
    List<UserMsgVO> toUserMasVoList(List<Consumer> consumer);

    /**
     * 转po
     *
     * @param vo vo
     * @return po
     */
    @Mapping(target = "isMember", ignore = true)
    @Mapping(target = "updateTime", ignore = true)
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "createTime", ignore = true)
    Consumer toPo(ConsumerVO vo);

    /**
     * 转po
     *
     * @param userInfoDTO dto
     * @return po
     */
    Consumer toPo(UserInfoDTO userInfoDTO);

    /**
     * 转vo
     *
     * @param consumer po
     * @return vo
     */
    UserInfoVO toUserInfoVo(Consumer consumer);

    /**
     * 转vo list
     *
     * @param consumers po list
     * @return vo list
     */
    List<ConsumerVO> toVoList(List<Consumer> consumers);

    /**
     * 转vo list
     *
     * @param consumers po list
     * @return vo list
     */
    List<UserInfoVO> toUserInfoVoList(List<Consumer> consumers);
}
