package com.suixingpay.service;

import com.suixingpay.entity.Meeting;

/*
 *@Author 孙克强
 */
public interface HousekeeperService {
    //添加鑫管家会议
    int insert(Meeting meeting);

    //根据会议id修改会议
    int updateByPrimaryKey(Meeting meeting);
}
