package com.suixingpay.controller;

import com.suixingpay.entity.Meeting;
import com.suixingpay.service.HelloService;
import com.suixingpay.service.HousekeeperService;
import com.suixingpay.utils.GenericResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.concurrent.Callable;

/*
 *@Author 孙克强  鑫管家模块
 */
@RestController
@RequestMapping("/housekeeper")
@Slf4j
@Api
public class HousekeeperController {
    @Autowired
    private HousekeeperService housekeeperService;
    @Autowired
    private HelloService helloService;

    //新增---孙克强
    @PostMapping("/insertMeeting")
    @ApiOperation(value = "鑫管家添加会议接口", notes = "鑫管家添加会议接口")
    public Callable<GenericResponse> insertMeeting(Meeting meeting, String[] address) {
        meeting.setMeetingAddress(address[0] + "," + address[1] + "," + address[2]);
        log.info("进入鑫管家添加会议接口, 参数为" + meeting);
        //获取会议开始时间
        Date meetingStarttime = meeting.getMeetingStarttime();
        //获取当前日期
        Date curDate = new Date();
        //如果会议时间不大于当前时间，失败
        if (meetingStarttime.before(curDate)) {
            log.info("新建失败,会议开始时间不能小于当前时间");
            return () -> GenericResponse.failed("999", "新建失败,会议时间不能大于当前时间");
        }
        //获取会议报名截止时间
        Date meetingEndtime = meeting.getMeetingEndtime();
        //如果报名截止时间不大于当前时间，失败
        if (meetingEndtime.before(curDate)) {
            log.info("新建失败,报名截止时间不能小于当前时间");
            return () -> GenericResponse.failed("999", "新建失败,报名截止时间不能小于当前时间");
        }
        //如果报名截止时间不大于会议时间，失败
        if (meetingEndtime.before(meetingStarttime)) {
            log.info("新建失败,报名截止时间不能小于会议开始时间");
            return () -> GenericResponse.failed("999", "新建失败,报名截止时间不能小于会议开始时间");
        }


        int i = housekeeperService.insert(meeting);
        if (i != 0) {
            log.info("新建成功，退出鑫管家添加会议接口");
            return () -> GenericResponse.success("666", "新建成功");
        } else {
            log.info("新建失败，退出鑫管家添加会议接口");
            return () -> GenericResponse.failed("999", "新建失败");
        }
    }


    //根据id查询会议---孙克强
    @GetMapping("/findMeetingById/{meetingId}")
    public Callable<GenericResponse> findMeetingById(@PathVariable("meetingId") Integer meetingId) {
        log.info("进入按meetingId查询会议接口，参数" + meetingId);
        Meeting meeting = helloService.selectById(String.valueOf(meetingId));
        if (meeting != null) {
            return () -> GenericResponse.success("666", "查询成功", meeting);
        } else {
            return () -> GenericResponse.failed("999", "查询失败");
        }
    }


    //修改---文状
    @PutMapping("/updateMeetingById")
    public Callable<GenericResponse> updateMeetingById(Meeting meeting, String[] address) {
        meeting.setMeetingAddress(address[0] + "," + address[1] + "," + address[2]);
        log.info("进入鑫管家修改会议接口, 参数为" + meeting);
        //旧会议
        Meeting oldMeeting = helloService.selectById(String.valueOf(meeting.getMeetingId()));
        //如果未修改
        if (oldMeeting.toString().equals(meeting.toString())) {
            return () -> GenericResponse.failed("999", "未做修改");
        }
        //修改了
        //判断审核状态 0 1 2(0,1可全部修改，2只能修改报名截止时间)
        if (meeting.getMeetingCheckstatus() != 2) {
            //获取当前日期
            Date curDate = new Date();
            //获取会议开始时间
            Date meetingStarttime = meeting.getMeetingStarttime();
            //如果会议时间不大于当前时间，失败
            if (meetingStarttime.before(curDate)) {
                log.info("修改失败,会议开始时间不能小于当前时间");
                return () -> GenericResponse.failed("999", "修改失败,会议时间不能大于当前时间");
            }
            //获取会议报名截止时间
            Date meetingEndtime = meeting.getMeetingEndtime();
            //如果报名截止时间不大于当前时间，失败
            if (meetingEndtime.before(curDate)) {
                log.info("修改失败,报名截止时间不能小于当前时间");
                return () -> GenericResponse.failed("999", "修改失败,报名截止时间不能小于当前时间");
            }
            //如果报名截止时间不大于会议时间，失败
            if (meetingEndtime.before(meetingStarttime)) {
                log.info("修改失败,报名截止时间不能小于会议开始时间");
                return () -> GenericResponse.failed("999", "修改失败,报名截止时间不能小于会议开始时间");
            }

            int i = housekeeperService.updateByPrimaryKey(meeting);
            if (i != 0) {
                return () -> GenericResponse.success("666", "修改成功");
            } else {
                return () -> GenericResponse.failed("999", "修改失败");
            }
        } else {//状态为通过(2)的时候
            if (oldMeeting.getMeetingType() != meeting.getMeetingType()) {
                return () -> GenericResponse.failed("999", "修改失败,不能修改除截止时间之外的信息");
            }
            if (!oldMeeting.getMeetingName().equals(meeting.getMeetingName())) {
                return () -> GenericResponse.failed("999", "修改失败,不能修改除截止时间之外的信息");
            }
            if (!oldMeeting.getMeetingCreate().equals(meeting.getMeetingCreate())) {
                return () -> GenericResponse.failed("999", "修改失败,不能修改除截止时间之外的信息");
            }
            if (oldMeeting.getMeetingSalary() != meeting.getMeetingSalary()) {
                return () -> GenericResponse.failed("999", "修改失败,不能修改除截止时间之外的信息");
            }
            if (oldMeeting.getMeetingDate().compareTo(meeting.getMeetingDate()) != 0) {
                return () -> GenericResponse.failed("999", "修改失败,不能修改除截止时间之外的信息");
            }
            if (oldMeeting.getMeetingStarttime().compareTo(meeting.getMeetingStarttime()) != 0) {
                return () -> GenericResponse.failed("999", "修改失败,不能修改除截止时间之外的信息");
            }
            if (!oldMeeting.getMeetingTimelong().toString().equals(meeting.getMeetingTimelong().toString())) {
                return () -> GenericResponse.failed("999", "修改失败,不能修改除截止时间之外的信息");
            }
            if (!oldMeeting.getMeetingAddress().equals(meeting.getMeetingAddress())) {
                return () -> GenericResponse.failed("999", "修改失败,不能修改除截止时间之外的信息");
            }
            if (!oldMeeting.getMeetingAlladdress().equals(meeting.getMeetingAlladdress())) {
                return () -> GenericResponse.failed("999", "修改失败,不能修改除截止时间之外的信息");
            }
            if (!oldMeeting.getMeetingDescribe().equals(meeting.getMeetingDescribe())) {
                return () -> GenericResponse.failed("999", "修改失败,不能修改除截止时间之外的信息");
            }
            if (oldMeeting.getMeetingCheckstatus() != meeting.getMeetingCheckstatus()) {
                return () -> GenericResponse.failed("999", "修改失败,不能修改除截止时间之外的信息");
            }
            if (oldMeeting.getMeetingStatus() != meeting.getMeetingStatus()) {
                return () -> GenericResponse.failed("999", "修改失败,不能修改除截止时间之外的信息");
            }
            if (oldMeeting.getUserId() != meeting.getUserId()) {
                return () -> GenericResponse.failed("999", "修改失败,不能修改除截止时间之外的信息");
            }
            if (oldMeeting.getMeetingApplypersonnumber() != meeting.getMeetingApplypersonnumber()) {
                return () -> GenericResponse.failed("999", "修改失败,不能修改除截止时间之外的信息");
            }
            if (oldMeeting.getMeetingSignpersonnumber() != meeting.getMeetingSignpersonnumber()) {
                return () -> GenericResponse.failed("999", "修改失败,不能修改除截止时间之外的信息");
            }
            if (oldMeeting.getStartType() != meeting.getStartType()) {
                return () -> GenericResponse.failed("999", "修改失败,不能修改除截止时间之外的信息");
            }
            int i = housekeeperService.updateByPrimaryKey(meeting);
            if (i != 0) {
                return () -> GenericResponse.success("666", "修改成功");
            } else {
                return () -> GenericResponse.failed("999", "修改失败");
            }
        }
    }

}
