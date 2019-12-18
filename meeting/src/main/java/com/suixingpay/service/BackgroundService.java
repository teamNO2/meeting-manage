package com.suixingpay.service;

import com.suixingpay.entity.Meeting;
import com.suixingpay.entity.Users;

import java.util.Map;

public interface BackgroundService {
    int createMeeting(Meeting meeting);
    Users selectByName(String userName);
}
