package com.zf1976.server;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.zf1976.dao.*;
import com.zf1976.pojo.common.convert.ConsumerConvert;
import com.zf1976.pojo.po.ClubCard;
import com.zf1976.pojo.po.Membership;
import com.zf1976.service.aspect.impl.LogAspectHandlerImpl;
import com.zf1976.service.common.ThreadPool;
import com.zf1976.service.interfaces.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.RedisServer;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ServerApplicationTests {

    @Autowired
    private AdminService adminService;

    @Autowired
    private CollectService collectService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private ConsumerService consumerService;

    @Autowired
    private ListSongService listSongService;

    @Autowired
    private RankService rankService;

    @Autowired
    private SingerService singerService;

    @Autowired
    private SongListService songListService;

    @Autowired
    private SongService songService;

    @Resource
    private ConsumerConvert consumerConvert;

    @Autowired
    private RankDao rankDao;

    @Autowired
    private CommentDao dao;

    @Autowired
    PaginationInterceptor paginationInterceptor;

    @Autowired
    private LogAspectHandlerImpl logAspectHandler;

    @Autowired
    private ClubCardService clubCardService;

    @Autowired
    private ClubCardDao clubCardDao;
    @Autowired
    private ConsumerDao consumerDao;

    @Autowired
    private MembershipDao membershipDao;

    @Autowired
    private ThreadPool threadPool;


    @org.junit.Test
    public void  cardTest() throws IOException {

        final LambdaQueryWrapper<Membership> wrapper = new LambdaQueryWrapper<Membership>().eq(Membership::getConsumerId, 1);
        final Membership membership = membershipDao.selectOne(wrapper);
//        System.out.println(membership);
        final long currentTimeMillis = System.currentTimeMillis();
        System.out.println(DateUtil.betweenDay(new Date(currentTimeMillis), new Date(membership.getExpireTime()), false));
    }

    @org.junit.Test
    public void findCardTest(){
        final String s = "1r38V78GsiHfO2y";
        System.out.println("card:"+DigestUtils.md5DigestAsHex("1976001".getBytes()));
        System.out.println("pwd:"+DigestUtils.md5DigestAsHex(s.getBytes()));

        clubCardService.list().forEach(System.out::println);

        //clubCardService.findClubCard("1976001"  ,"1r38V78GsiHfO2y");
    }

    @org.junit.Test
    public void contextLoads() throws IOException {
        final InputStream resourceAsStream = com.zf1976.server.Test.class.getResourceAsStream("/card_pwd.txt");
        byte[] b = new byte[100000];
        final int read = resourceAsStream.read(b);
        final String s = new String(b, 0, read);
        final String[] split = s.split("\n");
        for (String value : split) {

            // 截取卡号
            final String[] split1 = value.split("\t");
            final String card = split1[0];
            final String pwd = split1[1].split("\r")[0];

            clubCardDao.insert(ClubCard.builder()
                                       .cardNumber(DigestUtils.md5DigestAsHex(card.getBytes()))
                                       .cardPwd(DigestUtils.md5DigestAsHex(pwd.getBytes()))
                                       .build());
        }
//        for (String s1 : split) {
//            final String[] split1 = s1.split("\t");
//          //  System.out.println("card:"+split1[0] + "加密后："+ DigestUtils.md5DigestAsHex(split1[0].getBytes()));
//            System.out.println("pwd:"+split1[1]);
//            String str =(DigestUtils.md5DigestAsHex(split1[1].getBytes()));
//            System.out.println("加密后："+str);
//            System.out.println();
////            clubCardDao.insert(ClubCard.builder()
////                                       .cardNumber(DigestUtils.md5DigestAsHex(split1[0].getBytes()))
////                                       .cardPwd(DigestUtils.md5DigestAsHex(split1[1].getBytes()))
////                                       .build());
//        }
    }
    @org.junit.Test
    public void test(){
        final LambdaQueryWrapper<ClubCard> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ClubCard::getType,1);
        clubCardDao.update(ClubCard.builder()
                                   .price(16)
                                   .build(),wrapper);
    }
}
