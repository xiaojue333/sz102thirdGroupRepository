package com.itheima.health.service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *
 * </p>
 *
 * @author: Eric
 * @since: 2020/11/1
 */
public interface ReportService {

    /**
     * 获取运营数据统计
     * @return
     */
    Map<String, Object> getBusinessReportData();

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
