package com.suixingpay.service.serviceImpl;

import com.suixingpay.entity.Meeting;
import com.suixingpay.repository.MeetingRepository;
import com.suixingpay.service.MeetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MeetingServiceImpl implements MeetingService {
    @Autowired
    private MeetingRepository meetingRepository;

    /**
     * @Author 柴宇航
     * @param meetingId
     * @return
     */
    public Meeting selectstartTimeandtimeLong(int meetingId){
        return meetingRepository.selectstartTimeandtimeLong(meetingId);
    }

    /**
     * @Author 柴宇航
     * @param meetingId
     * @return
     */
    public Meeting selectuserId(int meetingId){
        return meetingRepository.selectuserId(meetingId);
    }
}
