package com.suixingpay.service.serviceImpl;

import com.suixingpay.entity.Apply;
import com.suixingpay.entity.Meeting;
import com.suixingpay.entity.Sign;
import com.suixingpay.repository.MeetingRepository;
import com.suixingpay.service.HousekeeperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public int selectApplyNumberByMeetingId(int meetingId) {
        return meetingRepository.selectApplyNumberByMeetingId(meetingId);
    }

    @Override
    public List<Apply> selectApplyByMeetingId(int meetingId) {
        return meetingRepository.selectApplyByMeetingId(meetingId);
    }

    @Override
    public int myUpdateMeetingById(Meeting meeting) {
        return meetingRepository.myUpdateMeetingById(meeting);
    }

    @Override
    public List<Sign> selectSignByMeetingId(int meetingId) {
        return meetingRepository.selectSignByMeetingId(meetingId);
    }
}
