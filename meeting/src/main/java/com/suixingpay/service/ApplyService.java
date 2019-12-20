package com.suixingpay.service;

import com.suixingpay.entity.Apply;

import java.util.Date;
import java.util.List;

/**
 * @Author：柴宇航
 */
public interface ApplyService {
    /**
     * @Author 柴宇航
     * @param userId
     * @param meetingId
     * @param applyTime
     * @return
     */
    int insertApply(String userId, int meetingId, Date applyTime);

    /**
     * @Author 柴宇航
     * @param meetingId
     * @return
     */
    List<Apply> isapplyuser(int meetingId);

    /**
     * @Author 柴宇航
     * @param applyIssign
     * @param meetingId
     * @return
     */
    int updateissign(int applyIssign,int meetingId);
}
