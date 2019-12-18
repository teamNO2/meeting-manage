package com.suixingpay.service;

import com.suixingpay.entity.Meeting;

public interface HelloService {
    //按meeting-id查询
    public Meeting selectById(String meetingId);
}
