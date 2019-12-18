package com.suixingpay.service;

import com.suixingpay.entity.Apply;
import com.suixingpay.entity.Meeting;
import com.suixingpay.entity.Sign;

import java.util.List;

public interface BackgroundService {

    /*
     * 张佳鑫
     * 后台管理查询所有会议
     */
    List<Meeting> backgroundSelectAll();

    /*
     * 张佳鑫
     * 后台管理查询会议详细
     */
    List<Meeting> backgroundSelectById1(Integer meetingId);

    List<Apply> backgroundSelectById2(Integer meetingId);

    List<Sign> backgroundSelectById3(Integer meetingId);

    /*
     * 张佳鑫
     * 后台管理审核会议
     */

    Integer backgroundUpdateStatus(Integer meetingId, Integer check);
}
