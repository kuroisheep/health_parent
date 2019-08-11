package com.itheima.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.itheima.dao.MemberDao;
import com.itheima.pojo.Member;
import com.itheima.service.MemberService;
import com.itheima.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 会员业务层实现类
 */
@Service(interfaceClass = MemberService.class)
@Transactional
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberDao memberDao;

    @Override
    public Member findByTelephone(String telephone) {
        return memberDao.findByTelephone(telephone);
    }

    @Override
    public void add(Member member) {
        memberDao.add(member);
    }
    /**
     * 获取会员折线图数据
     * @return
     */
    @Override
    public Map getMemberReport() {
        Map<String,Object> map = new HashMap<>();
        //1.获取年月数据
        //获取当前时间
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH,-12);//减少12个月
        List<String> stringList = new ArrayList<>();
        for(int i = 1;i<=12;i++){
            stringList.add(new SimpleDateFormat("yyyy-MM").format(calendar.getTime()));
            calendar.add(Calendar.MONTH,+1);
        }
        map.put("months",stringList);

        //2.获取年月对应数量  select count(*) from t_member where regTime(2018-07-03) <=  2018-07-31
        List<Integer> listCount = new ArrayList<>();
        for (String ym : stringList) {
            String newYM = ym + "-31";//2018-07-31
            Integer memberCount = memberDao.findMemberCountBeforeDate(newYM);
            listCount.add(memberCount);
        }
        map.put("memberCount",listCount);
        return map;
    }

    /**
     * 查询会员性别饼形图
     * @return
     */
    @Override
    public List<Map> findSex() {
        return memberDao.findSex();
    }

    /**
     * 查询各年龄阶段的会员数量
     * @return
     */
    @Override
    public Map getMemberAgeReport() {
        Map rsMap=new HashMap();
        //设置日期集合，用于封装日期集合
        Map dateMap=new HashMap();
        //各年龄阶段表；分别为[0-18,18-30,30-45,45以上]
        List<String>ageNames=new ArrayList<>();
        ageNames.add("0-18");
        ageNames.add("18-30");
        ageNames.add("30-45");
        ageNames.add("45以上");
        rsMap.put("ageNames",ageNames);
        //各年龄阶段数量；
        List<Map>ageCount=new ArrayList<>();
        //获取0-18
        Map map1=new HashMap();
        dateMap.put("yearDate0",DateUtils.getYearDay(0));
        dateMap.put("yearDate1",DateUtils.getYearDay(18));
        int ageCount1=memberDao.findAgeCount(dateMap);
        map1.put("value",ageCount1);
        map1.put("name","0-18");
        ageCount.add(map1);
        //获取18-30
        Map map2=new HashMap();
        dateMap.put("yearDate0",DateUtils.getYearDay(18));
        dateMap.put("yearDate1",DateUtils.getYearDay(30));
        int ageCount2=memberDao.findAgeCount(dateMap);
        map2.put("value",ageCount2);
        map2.put("name","18-30");
        ageCount.add(map2);
        //获取30-45
        Map map3=new HashMap();
        dateMap.put("yearDate0",DateUtils.getYearDay(30));
        dateMap.put("yearDate1",DateUtils.getYearDay(45));
        int ageCount3=memberDao.findAgeCount(dateMap);
        map3.put("value",ageCount3);
        map3.put("name","30-45");
        ageCount.add(map3);
        //获取45以上
        Map map4=new HashMap();
        dateMap.put("yearDate0",DateUtils.getYearDay(45));
        dateMap.put("yearDate1",DateUtils.getYearDay(1000));
        int ageCount4=memberDao.findAgeCount(dateMap);
        map4.put("value",ageCount4);
        map4.put("name","45以上");
        ageCount.add(map4);
        //封装各年龄阶段数量
        rsMap.put("ageCount",ageCount);
        return rsMap;
    }



}
