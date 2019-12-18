package com.suixingpay.service.serviceImpl;

import com.suixingpay.entity.Meeting;
import com.suixingpay.repository.MeetingRepository;
import com.suixingpay.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/*
 *@Author sunkeqiang
 */
@Service
public class HelloServiceImpl implements HelloService {
    @Autowired
    private MeetingRepository meetingRepository;


    @Override
    public Meeting selectById(String meetingId) {
        return meetingRepository.selectById(meetingId);
    }
}
