package com.suixingpay.controller;

import com.suixingpay.entity.Meeting;
import com.suixingpay.service.HousekeeperService;
import com.suixingpay.utils.GenericResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.concurrent.Callable;

/*
 *@Author 孙克强  鑫管家模块
 */
@Controller
@RequestMapping("/housekeeper")
public class HousekeeperController {
    @Autowired
    private HousekeeperService housekeeperService;

    //新增
    @PostMapping("/insertMeeting")
    public Callable<GenericResponse> insertMeeting(Meeting meeting) {
        int i = housekeeperService.insert(meeting);
        if (i != 0) {
            return () -> GenericResponse.success("666", "新建成功");
        } else {
            return () -> GenericResponse.failed("999", "新建失败");
        }
    }
}
