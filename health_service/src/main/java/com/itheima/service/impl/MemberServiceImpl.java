package com.itheima.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.itheima.dao.MemberDao;
import com.itheima.pojo.Member;
import com.itheima.pojo.Time;
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
     *description:根据区间时间获取会员折线图数据
     * @param start, end]
     * @return java.util.Map
     */
    @Override
    public Map getMemberReportDefined(String start, String end) {

        try {
            Map<String,Object> map = new HashMap<>();
            //根据开始时间和结束时间 获取区间月份集合
            List<String> monthBetween = DateUtils.getMonthBetween(start, end, "yyyy-MM");
            map.put("months",monthBetween);
            //会员数量集合
            List<Integer> memberCounts = new ArrayList<>();
            if (monthBetween!=null){
                for (String month : monthBetween) {
                    //根据每一个月份查会员数量
                    month+="-31";
                    Integer memberCount = memberDao.findMemberCountBeforeDate(month);
                    memberCounts.add(memberCount);
                }
            }
            map.put("memberCount",memberCounts);
            return map;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
