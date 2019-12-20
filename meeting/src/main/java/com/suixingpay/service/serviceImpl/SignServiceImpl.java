package com.suixingpay.service.serviceImpl;

import com.suixingpay.entity.Sign;
import com.suixingpay.repository.SignRepository;
import com.suixingpay.service.SignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class SignServiceImpl implements SignService {
    @Autowired
    private SignRepository signRepository;

    /**
     * @Author 柴宇航
     * @param userId
     * @param meetingId
     * @param signIsapply
     * @param signTime
     * @return
     */
    public int insertSignuser(String userId, int meetingId, int signIsapply, Date signTime){
        return signRepository.insertSignuser(userId,meetingId,signIsapply,signTime);
    }

    /**
     * @Author 柴宇航
     * @param meetingId
     * @return
     */
    public List<Sign> issignuser(int meetingId){
        return signRepository.issignuser(meetingId);
    }
}
