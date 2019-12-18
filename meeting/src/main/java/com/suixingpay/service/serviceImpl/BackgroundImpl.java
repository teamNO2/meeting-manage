package com.suixingpay.service.serviceImpl;

import com.suixingpay.entity.Meeting;
import com.suixingpay.entity.Users;
import com.suixingpay.repository.MeetingRepository;
import com.suixingpay.repository.UsersRepository;
import com.suixingpay.service.BackgroundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class BackgroundImpl implements BackgroundService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private MeetingRepository meetingRepository;


    @Override
    public int createMeeting(Meeting meeting) {

        return meetingRepository.insertSelective(meeting);
    }

    @Override
    public Users selectByName(String userName) {
        return usersRepository.selectByName(userName);
    }
}
