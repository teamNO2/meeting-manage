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
    //已报名
    List<Meeting> selectisapply(String userId);
    //改变会议状态
   int updatemeetingStatus(int meetingId);

    List<List<Meeting>> selectmeetings(String userId);

    //根据会议查询详细
    Meeting selectByIddesc(String meetingId);

    //管理界面
    List<Meeting> selectbyuserId(String userId);


}
