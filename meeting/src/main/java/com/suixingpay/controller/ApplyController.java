package com.suixingpay.controller;

import com.suixingpay.entity.Apply;
import com.suixingpay.entity.Meeting;
import com.suixingpay.service.ApplyService;
import com.suixingpay.service.MeetingService;
import com.suixingpay.utils.GenericResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * @Author:柴宇航
 */
@RestController
@RequestMapping("/apply")
@Api
@Slf4j
public class ApplyController {
    @Autowired
    private ApplyService applyService;
    @Autowired
    private MeetingService meetingService;
    @PostMapping("/insertApply")
    @ApiOperation(value = "报名接口",notes = "通过传过来的鑫管家Id和会议的Id实现报名参加活动功能")
    public Callable<GenericResponse> insertApply(@RequestParam("userId") String userId,
                                                 @RequestParam("meetingId") Integer meetingId) {
        Date date = new Date();
        log.info("通过meetingId查询出鑫管家是否报名过");
        List<Apply> isapplyuser = applyService.isapplyuser(meetingId,userId);

        log.info("通过meetingId查询出次会议是否是当前鑫管家所建立");
        Meeting meeting = meetingService.selectuserId(meetingId);
        String userId2=meeting.getUserId().toString();
        if(userId2.equals(userId)){
            return () -> GenericResponse.failed("insertApply999", "您是会议发起者，无需报名");
        }


        for(Apply a:isapplyuser){
            if(a.getUserId().equals(userId)){
                log.info("重复报名");
                return () -> GenericResponse.failed("insertApply999", "您已经报名过该会议");
            }
        }
        log.info("进入报名的接口");
        int i = applyService.insertApply(userId, meetingId, date);
        if (i != 0) {
            log.info("报名成功");
            return () -> GenericResponse.success("insertApply666", "报名成功");
        } else {
            log.info("报名失败");
            return () -> GenericResponse.failed("insertApply999", "报名失败");
        }
    }
}
