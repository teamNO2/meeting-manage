package com.suixingpay.service;

import com.suixingpay.entity.Meeting;

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
    List<Meeting> backgroundSelectById(Integer meetingId);
}
