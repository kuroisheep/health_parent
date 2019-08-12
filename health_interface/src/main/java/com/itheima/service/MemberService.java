package com.itheima.service;

import com.itheima.pojo.Member;
import com.itheima.pojo.Time;

import java.util.List;
import java.util.Map;

/**
 * 会员服务接口
 */
public interface MemberService {
    /**
     * 根据手机号查询会员对象
     * @param telephone
     * @return
     */
    Member findByTelephone(String telephone);

    void add(Member member);

    /**
     * 获取会员折线图数据
     * @return
     */
    Map getMemberReport();


    //根据区间时间获取会员折线图数据
    Map getMemberReportDefined(String start,String end);

    Map getMemberAgeReport();

    List<Map> findSex();
}
