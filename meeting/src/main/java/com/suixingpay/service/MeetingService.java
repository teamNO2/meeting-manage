package com.suixingpay.service;

import com.suixingpay.entity.Meeting;

public interface MeetingService {
    /**
     * @Author:柴宇航
     * @param meetingId
     * @return
     */

    Meeting selectstartTimeandtimeLong(int meetingId);

    /**
     * @Author:柴宇航
     * @param meetingId
     * @return
     */
    Meeting selectuserId(int meetingId);
}
