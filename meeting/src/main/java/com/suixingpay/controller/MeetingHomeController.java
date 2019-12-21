package com.suixingpay.controller;

import com.suixingpay.entity.Meeting;
import com.suixingpay.service.MeetingHomeService;
import com.suixingpay.utils.GenericResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
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
@Slf4j
public class MeetingHomeController {
    @Autowired
    MeetingHomeService meetingHomeService;

    //查询已经报名的
    @GetMapping("/selectisapply/{userId}")
    public Callable<GenericResponse> selectisapply(@PathVariable("userId") String userId) {
        log.info("进入selectisapply接口，参数:userId = " + userId);
        List<Meeting>  meetings = meetingHomeService.selectisapply(userId);
        if (meetings != null) {
            log.info("查询成功，结果 = " + meetings);
            return () -> GenericResponse.success("666", "查询成功", meetings);
        } else {
            return () -> GenericResponse.failed("999", "查询失败");
        }
    }

    //查询未报名的
    @GetMapping("/selectmeetings/{userId}")
    public Callable<GenericResponse> selectmeetings(@PathVariable("userId") String userId) {
        List<List<Meeting >> meetingList = meetingHomeService.selectmeetings(userId);
        if (meetingList != null) {
            return () -> GenericResponse.success("666", "查询成功", meetingList);
        } else {
            return () -> GenericResponse.failed("999", "查询失败");
        }
    }

    //点击进入会议详情 根据用户id查询会议详情
    @GetMapping("/selectByIddesc/{meetingId}")
    public Callable<GenericResponse> selectByIddesc(@PathVariable("meetingId") String meetingId) {
        Meeting meetings = meetingHomeService.selectByIddesc(meetingId);
        Date date = new Date();

        if(date.before(meetingHomeService.selectByIddesc(meetingId).getMeetingEndtime()) && meetings != null){
            return () -> GenericResponse.success("666", "查询成功", meetings);
        }else{
            meetingHomeService.updatemeetingStatus(Integer.valueOf(meetingId));
            Meeting meetings1 = meetingHomeService.selectByIddesc(meetingId);
            log.info(meetings1.toString());
            return () -> GenericResponse.success("999", "查询成功",meetings1);
        }
    }
    @GetMapping("/selectbyuserId/{userId}")
    public Callable<GenericResponse> selectbyuserId(@PathVariable("userId") String userId) {
        List<Meeting > meetingList = meetingHomeService.selectbyuserId(userId);
        if (meetingList != null) {
            return () -> GenericResponse.success("666", "查询成功", meetingList);
        } else {
            return () -> GenericResponse.failed("999", "查询失败");
        }
    }
}
