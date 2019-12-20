package com.suixingpay.service;

import com.suixingpay.entity.Sign;

import java.util.Date;
import java.util.List;

public interface SignService {

    /**
     * @Author:柴宇航
     * @param userId
     * @param meetingId
     * @param signIsapply
     * @param signTime
     * @return
     */
    int insertSignuser(String userId, int meetingId, int signIsapply, Date signTime);

    /**
     * @Author:柴宇航
     * @param meetingId
     * @return
     */
    List<Sign> issignuser(int meetingId);

}
