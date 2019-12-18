package com.suixingpay.service;

import com.suixingpay.entity.Meeting;

import java.util.Date;
import java.util.List;

import com.suixingpay.entity.Meeting;
import com.suixingpay.entity.Users;

import java.util.Map;

public interface BackgroundService {
    int createMeeting(Meeting meeting);
    Users selectByName(String userName);
    Meeting selectById(String meetingId);
    List<Meeting> likeMeeting(List<Meeting>meetings,List<Users>users);
    List<Users> findPageWithResultLike(Users users);
    List<Meeting> findPageWithResultLike(Meeting meeting);
    List<Meeting> limitDate(Date beginDate,Date endDate);

    /*
     * 张佳鑫
     * 后台管理查询所有会议
     */
    List<Meeting> backgroundSelectAll();
}
