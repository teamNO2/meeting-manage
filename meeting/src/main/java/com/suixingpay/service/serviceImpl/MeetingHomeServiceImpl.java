package com.suixingpay.service.serviceImpl;

import com.suixingpay.entity.Meeting;
import com.suixingpay.repository.MeetingRepository;
import com.suixingpay.repository.SignRepository;
import com.suixingpay.repository.UsersRepository;
import com.suixingpay.service.MeetingHomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName MeetingHomeServiceImpl
 * @Description TODO
 * @Author ShiMengyao
 * @Date 2019 年 12 月 19 日 0019 11:19:45
 * @Version 1.0
 */
@Service
public class MeetingHomeServiceImpl implements MeetingHomeService {
    @Autowired
    MeetingRepository meetingRepository;

    @Autowired
    UsersRepository usersRepository;

    @Autowired
    SignRepository signRepository;

    @Override
    public List<Meeting> selectisapply(String userId) {
        return meetingRepository.selectisapply(userId);
    }

    @Override
    public int updatemeetingStatus(int meetingId) {
        return meetingRepository.updatemeetingStatus(meetingId);
    }

    @Override
    public List<List<Meeting>> selectmeetings(String userId) {
        String a = userId;
        List<String > list = new ArrayList<>();
        List<List<Meeting>> meetingList = new ArrayList<>();
        list.add(meetingRepository.selectRootId(userId));
        for (int i = 0 ;i < usersRepository.countUser()+signRepository.countSign(); i++ ){
            userId = meetingRepository.selectRootId(userId);
            list.add(meetingRepository.selectRootId(userId));
            meetingList.add(meetingRepository.selectMeetings(list.get(i),a));
        }
        return meetingList;
    }
}
