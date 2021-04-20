package com.zf1976.service.task;

import cn.hutool.core.date.DateUtil;
import com.zf1976.pojo.po.Membership;
import com.zf1976.service.interfaces.ClubCardService;
import com.zf1976.service.interfaces.MembershipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;


/**
 * @author mac
 * Create by Ant on 2020/6/12 8:28 下午
 */

@Component
public class MembershipTask {

    @Autowired
    private ClubCardService clubCardService;

    @Autowired
    private MembershipService membershipService;

    @Scheduled(cron = "0 0 0 * * ?")
    public void membershipTask(){
        final List<Membership> memberships = membershipService.list();

        final long currentTimeMillis = System.currentTimeMillis();

        // 每天计算一次，到期取消客户会员身份，并删除记录
        memberships.forEach(membership -> {
            System.out.println(getBetweenDay(currentTimeMillis,membership.getExpireTime()));
            if (getBetweenDay(currentTimeMillis,membership.getExpireTime()) < 0){
                membershipService.membershipCancel(membership.getConsumerId());
                membershipService.removeById(membership.getId());
            }
        });
    }

    private Long getBetweenDay(long renewalTime,long expireTime){
        return DateUtil.betweenDay(new Date(renewalTime),new Date(expireTime),false);
    }
}
