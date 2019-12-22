package com.suixingpay.controller;

import com.suixingpay.entity.Apply;
import com.suixingpay.entity.Meeting;
import com.suixingpay.entity.Sign;
import com.suixingpay.service.ApplyService;
import com.suixingpay.service.MeetingService;
import com.suixingpay.service.SignService;
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

@RestController
@RequestMapping("/sign")
@Api
@Slf4j
/***
 * @Author:柴宇航
 */
public class SignController {
    @Autowired
    private SignService signService;
    @Autowired
    private ApplyService applyService;
    @Autowired
    private MeetingService meetingService;
    @PostMapping("/signuser")
    @ApiOperation(value = "签到接口",notes = "通过传过来鑫管家的Id和会议的Id实现签到功能")
    public Callable<GenericResponse> insertSignuser(@RequestParam("userId") String userId,
                                                    @RequestParam("meetingId") int meettingId) {
        Date date = new Date();

        log.info("通过meetingId获取报名表里的userId(目的在于后期判断此用户是否报名过)");
        List<Apply> isapplyuser = applyService.isapplyuser(meettingId,userId);

        log.info("通过meetingId获取签到表里的userId(目的在于后期判断此用户是否重复签到)");
        List<Sign> issignuser = signService.issignuser(meettingId);

        for (Apply a : isapplyuser) {

            log.info("获取到报名表里的userId");
            String userId1 = a.getUserId();

            //判断获取到的userId和传过来的userId是否相等，如果相等则报名过，不相等没有报名
            log.info("判断当前传过来的userId和报名表里的userId是否一致，如果相等报名过，反之");
            System.out.println(userId1);
            System.out.println(userId);
            if (userId1.equals(userId)) {
                //获取会议开始时间和会议时长
                Meeting meeting = meetingService.selectstartTimeandtimeLong(meettingId);
                Date meetingStarttime=meeting.getMeetingStarttime();
                Double meetingTimelong = meeting.getMeetingTimelong();

                //将时间转换成毫秒数
                long time = date.getTime();
                long time1 = meetingStarttime.getTime();

                //判断当前时间 >= 会议开始时间+会议时长+24小时
                log.info("判断当前时间如果大于等于会议开始时间+会议时长+24小时则提示");
                if(time>=time1+((meetingTimelong+24)*60*60*1000)){
                    return () -> GenericResponse.failed("insertSignuser999", "会议已完成，不能再签到");
                }

                //判断若鑫管家重复扫码签到，则扫码后提示"您已经签到过，无需重新签到";
                log.info("判断鑫管家重复扫码签到");
                for(Sign s:issignuser) {
                    String signId=s.getUserId();
                    if (signId.equals(userId)) {
                        System.out.println(signId);
                        return () -> GenericResponse.failed("insertSignuser999", "您已经签到过，无需重新签到");
                    }
                }

                //签到
                log.info("都满足以后的实现签到的接口");
                int i = signService.insertSignuser(userId, meettingId, 1, date);
                if (i != 0) {
                    applyService.updateissign(1,meettingId);
                    return () -> GenericResponse.success("insertSignuser666", "签到成功");
                } else {
                    return () -> GenericResponse.success("insertSignuser999", "签到失败");
                }
            } else {
                int i1 = signService.insertSignuser(userId, meettingId, 0, date);
                if (i1 != 0) {
                    applyService.updateissign(1,meettingId);
                    return () -> GenericResponse.success("insertSignuser666", "签到成功");
                } else {
                    return () -> GenericResponse.failed("insertSignuser999", "签到失败");
                }
            }
        }
        return () -> GenericResponse.failed("insertSignuser999", "对不起,您没有报名此次会议");
    }
}
