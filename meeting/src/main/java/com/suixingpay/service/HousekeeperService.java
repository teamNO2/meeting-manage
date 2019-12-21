package com.suixingpay.service;

import com.suixingpay.entity.Apply;
import com.suixingpay.entity.Meeting;
import com.suixingpay.entity.Sign;

import java.util.List;

/*
 *@Author 孙克强
 */
public interface HousekeeperService {
    //添加鑫管家会议
    int insert(Meeting meeting);

    //根据会议id修改会议
    int updateByPrimaryKey(Meeting meeting);

    //查看报名人数
    int selectApplyNumberByMeetingId(int meetingId);

    //查看会议报名信息
    List<Apply> selectApplyByMeetingId(int meetingId);

    //myUpdateMeetingById
    int myUpdateMeetingById(Meeting meeting);

    //查看会议签到信息
    List<Sign> selectSignByMeetingId(int meetingId);
}
