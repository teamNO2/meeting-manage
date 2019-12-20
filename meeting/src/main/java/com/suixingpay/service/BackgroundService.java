package com.suixingpay.service;

import com.suixingpay.entity.Apply;
import com.suixingpay.entity.Meeting;
import com.suixingpay.entity.Sign;

import java.util.Date;
import java.util.List;

import com.suixingpay.entity.Meeting;
import com.suixingpay.entity.Users;

import java.util.Map;

public interface BackgroundService {
    int createMeeting(Meeting meeting);
    Users selectByName(String userName);
    Users selectByTwo(String userName,String referralCode);
    Meeting selectById(String meetingId);
    List<Meeting> likeMeeting(List<Meeting>meetings,List<Users>users);
    List<Users> findPageWithResultLike(Users users);
    List<Users> selectAll();
    List<Meeting> findPageWithResultLike(Meeting meeting);
    List<Meeting> limitDate(Date beginDate,Date endDate);

    /*
     * 张佳鑫
     * 后台管理查询所有会议
     */
    List<Meeting> backgroundSelectAll();

    /*
     * 张佳鑫
     * 后台管理查询会议详细
     */
    Meeting backgroundSelectById1(Integer meetingId);

    List<Apply> backgroundSelectById2(Integer meetingId);

    List<Sign> backgroundSelectById3(Integer meetingId);

    /*
     * 张佳鑫
     * 后台管理审核会议
     */

    Integer backgroundUpdateStatus(Integer meetingId, Integer check);
}
