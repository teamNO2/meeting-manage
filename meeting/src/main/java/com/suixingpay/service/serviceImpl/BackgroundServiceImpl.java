package com.suixingpay.service.serviceImpl;

import com.suixingpay.entity.Apply;
import com.suixingpay.entity.Meeting;
import com.suixingpay.entity.Sign;
import com.suixingpay.repository.BackgroundRepository;
import com.suixingpay.service.BackgroundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BackgroundServiceImpl implements BackgroundService {

    @Autowired
    private BackgroundRepository backgroundRepository;

    /*
     * 张佳鑫
     * 后台管理查询所有会议
     */
    @Override
    public List<Meeting> backgroundSelectAll() {
        return backgroundRepository.backgroundSelectAll();
    }

    /*
     * 张佳鑫
     * 后台管理查询会议详细
     */

    @Override
    public List<Meeting> backgroundSelectById1(Integer meetingId) {
        return backgroundRepository.backgroundSelectById1(meetingId);
    }

    @Override
    public List<Apply> backgroundSelectById2(Integer meetingId) {
        return backgroundRepository.backgroundSelectById2(meetingId);
    }

    @Override
    public List<Sign> backgroundSelectById3(Integer meetingId) {
        return backgroundRepository.backgroundSelectById3(meetingId);
    }

    /*
     * 张佳鑫
     * 后台管理审核会议
     */

    @Override
    public Integer backgroundUpdateStatus(Integer meetingId, Integer check) {
        if(check==0){
            return backgroundRepository.backgroundUpdateStatus0(meetingId);
        }else if(check==1){
            return backgroundRepository.backgroundUpdateStatus1(meetingId);
        }else{
            return backgroundRepository.backgroundUpdateStatus2(meetingId);
        }
    }
}
