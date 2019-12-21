package com.suixingpay.service;

import com.suixingpay.entity.Meeting;

import java.util.List;

/**
 * @ClassName MeetingHomeService
 * @Description TODO
 * @Author ShiMengyao
 * @Date 2019 年 12 月 19 日 0019 11:18:19
 * @Version 1.0
 */
public interface MeetingHomeService {
    List<Meeting> selectisapply(String userId);
    //改变会议状态
   int updatemeetingStatus(int meetingId);

    List<List<Meeting>> selectmeetings(String userId);

    //查询所有上级及自己创建并且没有报名的活动
    List<Meeting> selectrootdesc(String userId);

    //根据会议查询详细
    List<Meeting> selectByIddesc(String meetingId);

    //查询没有邀请码的会议
    List<Meeting> selectnocode();

    //查询父类的用户id
    List<Meeting> selectrootId(String userId);

    //利用userID查询meeting
    List<Meeting> selectbyuserId(String userid);
}
