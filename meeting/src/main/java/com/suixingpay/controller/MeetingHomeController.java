package com.suixingpay.controller;

import com.suixingpay.entity.Meeting;
import com.suixingpay.service.MeetingHomeService;
import com.suixingpay.utils.GenericResponse;
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

//    //无邀请码会议
//    @GetMapping("/selectnocode")
//    public Callable<GenericResponse> selectnocode(String userId) {
//        List<Meeting>  meetings = meetingHomeService.selectnocode();
//
//        List<Meeting> meetingList = meetingHomeService.selectbyuserId(userId);
//        Date date = new Date();
//        for(Meeting meeting : meetingList) {
//
////            if(date.before(meeting.getMeetingEndtime() && date.after(meeting.getMeetingStarttime()))){
////                //当前时间在报名 截止之前  报名中
////            }
////            if(new Date().after(meeting.getMeetingEndtime())){
////
////                //当前时间在报名截止之后   报名截止
////            }else {
////
////            }
//
//        }
//
//        if (meetings != null) {
//            return () -> GenericResponse.success("666", "查询成功", meetings);
//        } else {
//            return () -> GenericResponse.failed("999", "查询失败");
//        }
//    }

    //点击进入会议详情 根据用户id查询会议详情
    @GetMapping("/selectByIddesc/{meetingId}")
    public Callable<GenericResponse> selectByIddesc(@PathVariable("meetingId") String meetingId) {
        List<Meeting>  meetings = meetingHomeService.selectByIddesc(meetingId);


        if (meetings != null) {
            return () -> GenericResponse.success("666", "查询成功", meetings);
        } else {
            return () -> GenericResponse.failed("999", "查询失败");
        }
    }
}
