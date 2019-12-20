package com.suixingpay.service.serviceImpl;

import com.suixingpay.entity.Apply;
import com.suixingpay.repository.ApplyRepository;
import com.suixingpay.service.ApplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ApplyServiceImpl implements ApplyService {
    @Autowired
    private ApplyRepository applyRepository;

    /**
     * @Author 柴宇航
     * @param userId
     * @param meetingId
     * @param applyTime
     * @return
     */
    public int insertApply(String userId, int meetingId, Date applyTime) {
        return applyRepository.insertApply(userId, meetingId, applyTime);
    }

    /**
     * @Author 柴宇航
     * @param meetingId
     * @return
     */
    public List<Apply> isapplyuser(int meetingId) {
        return applyRepository.isapplyuser(meetingId);
    }

    /**
     * @Author 柴宇航
     * @param applyIssign
     * @param meetingId
     * @return
     */
    public int updateissign(int applyIssign,int meetingId){
        return applyRepository.updateissign(applyIssign,meetingId);
    }
}
