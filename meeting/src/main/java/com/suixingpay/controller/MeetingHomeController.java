package com.suixingpay.controller;

import com.suixingpay.entity.Meeting;
import com.suixingpay.service.MeetingHomeService;
import com.suixingpay.utils.GenericResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.Callable;

/**
 * @ClassName MeetingHomeController
 * @Description TODO
 * @Author ShiMengyao
 * @Date 2019 年 12 月 19 日 0019 11:24:15
 * @Version 1.0
 */
@RestController
@RequestMapping("/home")
public class MeetingHomeController {
    @Autowired
    MeetingHomeService meetingHomeService;

    //查询已经报名的
    @GetMapping("/selectisapply/{userId}")
    public Callable<GenericResponse> selectisapply(@PathVariable("userId") String userId) {
        List<Meeting>  meetings = meetingHomeService.selectisapply(userId);
        if (meetings != null) {
            return () -> GenericResponse.success("666", "查询成功", meetings);
        } else {
            return () -> GenericResponse.failed("999", "查询失败");
        }
    }
}
