package com.zf1976.service.interfaces;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zf1976.dao.ConsumerDao;
import com.zf1976.pojo.common.RequestPage;
import com.zf1976.pojo.common.business.enums.BusinessMsgEnum;
import com.zf1976.pojo.common.business.exception.*;
import com.zf1976.pojo.common.convert.ConsumerConvert;
import com.zf1976.pojo.dto.admin.ConsumerDTO;
import com.zf1976.pojo.dto.app.ChangePassDTO;
import com.zf1976.pojo.dto.app.UserInfoDTO;
import com.zf1976.pojo.dto.app.UserSignUpDTO;
import com.zf1976.service.secutity.entity.UserLoginDTO;
import com.zf1976.pojo.po.Consumer;
import com.zf1976.pojo.vo.ConsumerVO;
import com.zf1976.pojo.vo.app.UserInfoVO;
import com.zf1976.pojo.vo.app.UserMsgVO;
import com.zf1976.service.interfaces.base.BaseService;
import com.zf1976.service.common.ResourceUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.DigestUtils;
import org.springframework.web.multipart.MultipartFile;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * (Consumer)表Service接口
 *
 * @author makejava
 * @since 2020-05-20 00:00:49
 */
@Service
@Slf4j
@SuppressWarnings("rawtypes")
public class ConsumerService extends BaseService<ConsumerDao, Consumer> {

    @Autowired
    private ConsumerDao consumerDao;

    @Autowired
    private ConsumerConvert consumerConvert;

    private Consumer consumer = null;

    /**
     * 获取所有客户
     *
     * @return vo list
     */
    public List<ConsumerVO> getAllUser(){
        final List<Consumer> consumers = super.lambdaQuery()
                                         .list();
        return super.mapListToTarget(consumers,consumer -> {
            return consumerConvert.toVo(consumer);
        });
    }

    public Consumer findByUsername(String username){
        return super.lambdaQuery()
                    .eq(Consumer::getUsername, username)
                    .oneOpt().orElseThrow(() -> new ExistUserException(BusinessMsgEnum.NOT_EXIST_USER));
    }

    /**
     * 分页查询客户
     *
     * @param requestPage page
     * @return page vo
     */
    public IPage<ConsumerVO> getUserPage(RequestPage<ConsumerDTO> requestPage){
        final Page<Consumer> page = super.lambdaQuery()
                                       .page(new Page<Consumer>(requestPage.getPageNo(),
                                                        requestPage.getPageSize()));
        return super.mapPageToTarget(page, c -> {
            return consumerConvert.toVo(c);
        });

    }


    /**
     * 根据id查询客户
     *
     * @param id 客户id
     * @return vo
     */
    public ConsumerVO getConsumerById(int id) {
        final Consumer consumer = super.lambdaQuery()
                                       .eq(Consumer::getId, id)
                                       .oneOpt().orElseThrow(() -> new ExistUserException(BusinessMsgEnum.NOT_EXIST_USER));
        return consumerConvert.toVo(consumer);
    }

    /**
     * 新增用户
     *
     * @param dto dto
     * @return null
     */
    public Void addUser(ConsumerDTO dto){
        isExistUsername(dto.getUsername());
        isExistEmail(dto.getEmail());
        isExistPhone(dto.getPhoneNum());
        final Consumer consumer = consumerConvert.toPo(dto);
        super.save(consumer);
        return null;
    }

    /**
     * 头像更新
     *
     * @param uploadFile 上传文件
     * @param id id
     * @return null
     */
    public Void updateAvatar(MultipartFile uploadFile,int id) {

        ResourceUtils.uploadCheckEmpty(uploadFile);

        final Consumer consumer = super.lambdaQuery()
                                       .eq(Consumer::getId, id)
                                       .oneOpt().orElseThrow(() -> new ExistUserException(BusinessMsgEnum.NOT_EXIST_USER));

        final String uploadName = uploadFile.getOriginalFilename();
        final String newName = ResourceUtils.rename(uploadName);
        final String folderPath = ResourceUtils.getUploadAvatarFolderPath();
        final String uploadAvatarPath = ResourceUtils.getUploadAvatarPath(newName);
        try {
            super.uploadFile(uploadFile,newName,folderPath,consumer.getAvatar(),log);
            consumer.setAvatar(uploadAvatarPath);
            super.updateById(consumer);
        }catch (Exception e){
            if (log.isInfoEnabled()) {
                log.info("Exception message:{}",e.getMessage());
            }
            throw new FileUploadException(BusinessMsgEnum.FILE_ERROR);
        }
        return null;
    }


    /**
     * 邮箱/用户名/手机号-索引
     * 更新客户信息
     *
     * @param dto dto
     * @return null
     */
    public Void updateUser(ConsumerDTO dto){

        final Consumer consumer = consumerConvert.toPo(dto);
        //手机号或邮箱有更新
        isUpdate(dto.getEmail(),
                 dto.getPhoneNum(),
                 dto.getId());
        super.updateById(consumer);
        return null;
    }

    /**
     * 判断手机 邮箱是否更改
     *
     * @param email 邮箱
     * @param phone 手机
     * @param id 客户id
     */
    private void isUpdate(String email, String phone, int id){

        final Consumer beforeConsumer = super.lambdaQuery()
                                       .eq(Consumer::getId, id)
                                       .oneOpt().orElseThrow(() -> new ExistUserException(BusinessMsgEnum.NOT_EXIST_USER));

        final boolean flag1 = Objects.equals(email,beforeConsumer.getEmail());

        final boolean flag2 = Objects.equals(phone,beforeConsumer.getPhoneNum());

        if (!flag1){
            isExistEmail(email);
        }else if (!flag2){
            isExistPhone(phone);
        }
    }

    /**
     * 查询客户是否存在
     *
     * @param username 客户名
     */
    private void isExistUsername(String username){
        try {
           this.consumer = super.lambdaQuery()
                                .eq(Consumer::getUsername, username)
                                .oneOpt().orElseThrow(() -> new ExistUserException(BusinessMsgEnum.NOT_EXIST_USER));
        } catch (ExistUserException e) {
            return;
        }
        if (this.consumer.getUsername().equals(username)){
            throw new ExistUserException(BusinessMsgEnum.EXIST_USER);
        }
    }

    /**
     * 查询手机号是否存在
     *
     * @param phone 手机号
     */
    private void isExistPhone(String phone) {
        try {
            this.consumer = super.lambdaQuery()
                                 .eq(Consumer::getPhoneNum, phone)
                                 .oneOpt().orElseThrow(() -> new ExistUserException(BusinessMsgEnum.NOT_EXIST_USER));
        } catch (ExistUserException e) {
            return;
        }
        if (this.consumer.getPhoneNum().equals(phone)){
            throw new ExistPhoneException(BusinessMsgEnum.EXIST_PHONE);
        }
    }

    /**
     * 查询邮箱是否存在
     *
     * @param email 邮箱
     */
    private void isExistEmail(String email) {
        try {
            this.consumer = super.lambdaQuery()
                                 .eq(Consumer::getEmail, email)
                                 .oneOpt().orElseThrow(() -> new ExistUserException(BusinessMsgEnum.NOT_EXIST_USER));
        } catch (ExistUserException e) {
            return;
        }
        if (this.consumer.getEmail().equals(email)){
            throw new ExistEmailException(BusinessMsgEnum.EXIST_EMAIL);
        }
    }

    /**
     * 前台用户注册
     *
     * @param dto dto
     * @return null
     */
    public Void signUp(UserSignUpDTO dto){
        isExistUsername(dto.getUsername());
        isExistEmail(dto.getEmail());
        isExistPhone(dto.getPhoneNum());
        Consumer consumer = consumerConvert.toPo(dto);
        super.save(consumer);
        return null;
    }

    /**
     * 前台用户获取登陆信息
     *
     * @param dto dto
     * @return null
     */
    public UserMsgVO getInfo(UserLoginDTO dto){

        final Consumer consumer = super.lambdaQuery()
                                       .eq(Consumer::getUsername, dto.getUsername())
                                       .eq(Consumer::getPassword, DigestUtils.md5DigestAsHex(dto.getPassword().getBytes()))
                                       .oneOpt().orElseThrow(() -> new ExistUserException(BusinessMsgEnum.NOT_EXIST_USER));
        return consumerConvert.toUserMasVo(consumer);
    }

    /**
     * 前台用户修改信息
     *
     * @param dto dto
     * @return null
     */
    public Void updateUserMsg(UserInfoDTO dto){
        Consumer consumer = consumerConvert.toPo(dto);
        //手机号或邮箱有更新
        isUpdate(dto.getEmail(),
                 dto.getPhoneNum(),
                 dto.getId());
        super.updateById(consumer);
        return null;
    }

    /**
     * 前台查询用户信息
     *
     * @param id 用户id
     * @return vo
     */
    public UserInfoVO getUserById(int id){
        Consumer consumer = super.lambdaQuery()
                .eq(Consumer::getId, id)
                .oneOpt().orElseThrow(() -> new ExistUserException(BusinessMsgEnum.NOT_EXIST_USER));
        return consumerConvert.toUserInfoVo(consumer);
    }

    /**
     * 用户id集合(由于前端统计单条记录，所以逐一查询)
     *
     * @param ids id集合
     * @return 用户信息集合
     */
    public List<UserInfoVO> getUserByIds(List<Integer> ids){
        List<Consumer> consumers = new ArrayList<>();
        ids.forEach((id)->{
            consumers.add(super.getById(id));
        });
        return super.mapListToTarget(consumers,consumer -> {
            return consumerConvert.toUserInfoVo(consumer);
        });
    }

    /**
     * 获取用户头像
     *
     * @param id id
     * @return 头像url
     */
    public String getUserAvatar(int id){
        final Consumer consumer = super.lambdaQuery()
                                       .eq(Consumer::getId, id)
                                       .oneOpt().orElseThrow(() -> new ExistUserException(BusinessMsgEnum.NOT_EXIST_USER));
        return consumer.getAvatar();
    }

    /**
     * 判断是否客户是否为会员
     *
     * @param username 用户名
     * @return null
     */
    public Void isMember(String username){
        final Consumer consumer = super.lambdaQuery()
                                       .eq(Consumer::getUsername,username)
                                       .oneOpt().orElseThrow(() -> new ExistUserException(BusinessMsgEnum.NOT_EXIST_USER));
        if (!consumer.getIsMember()){
            throw new BusinessException(BusinessMsgEnum.NOT_MEMBER);
        }
        return null;
    }



    /**
     * 确认密码
     *
     * @param dto dto
     * @return 加密旧密码
     */
    public String checkOldPass(ChangePassDTO dto){
        final String encrypt = DigestUtils.md5DigestAsHex(dto.getOldPass()
                                                             .getBytes());
            super.lambdaQuery()
                 .eq(Consumer::getUsername,dto.getUsername())
                 .eq(Consumer::getPassword,encrypt)
                 .oneOpt().orElseThrow(() -> new ExistUserException(BusinessMsgEnum.NOT_EXIST_USER));
            if (Objects.equals(dto.getCheckPass(),dto.getPass())) {
                return DigestUtils.md5DigestAsHex(dto.getPass()
                                                     .getBytes());
            }
        return null;
    }

    /**
     * 修改密码
     *
     * @param dto dto
     * @return null
     */
    public Void changePass(ChangePassDTO dto){
        final String newPass = checkOldPass(dto);
        final String oldPass = DigestUtils.md5DigestAsHex(dto.getOldPass().getBytes());
        super.lambdaUpdate()
             .eq(Consumer::getUsername,dto.getUsername())
             .eq(Consumer::getPassword,oldPass)
             .update(Consumer.builder()
                             .password(newPass)
                             .build());
        return null;
    }

}
