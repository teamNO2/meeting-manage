package com.suixingpay.service.serviceImpl;

import com.suixingpay.entity.Meeting;
import com.suixingpay.repository.MeetingRepository;
import com.suixingpay.service.HousekeeperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/*
 *@Author 孙克强
 */
@Service
public class HousekeeperServiceImpl implements HousekeeperService {
    @Autowired
    private MeetingRepository meetingRepository;

    @Override
    public int insert(Meeting meeting) {
        return meetingRepository.insert(meeting);
    }

    @Override
    public int updateByPrimaryKey(Meeting meeting) {
        return meetingRepository.updateByPrimaryKey(meeting);
    }
}
