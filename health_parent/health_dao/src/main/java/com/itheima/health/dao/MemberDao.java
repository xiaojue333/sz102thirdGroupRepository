package com.itheima.health.dao;

import com.github.pagehelper.Page;
import com.itheima.health.pojo.Member;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *
 * </p>
 *
 * @author: Eric
 * @since: 2020/10/29
 */
public interface MemberDao {
    /**
     * 查询所有
     * @return
     */
    List<Member> findAll();
    Page<Member> selectByCondition(String queryString);
    void add(Member member);
    void deleteById(Integer id);
    Member findById(Integer id);

    /**
     * 通过手机号码查询
     * @param telephone
     * @return
     */
    Member findByTelephone(String telephone);
    void edit(Member member);

    Integer findMemberCountBeforeDate(String date);
    Integer findMemberCountByDate(String date);
    Integer findMemberCountAfterDate(String date);
    Integer findMemberTotalCount();

    /**
     * 分性别查询会员占比
     * @return
     */
    List<Map<String, Object>> getSexBingTu();

    /**
     * 分年龄段查找会员数量 方案2
     * @return
     */
    List<Map<String, Object>> getAgeBingTuFa2();
}
