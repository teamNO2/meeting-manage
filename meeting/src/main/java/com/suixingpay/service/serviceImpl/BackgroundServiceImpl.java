package com.suixingpay.service.serviceImpl;

import com.suixingpay.entity.Meeting;
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
}
