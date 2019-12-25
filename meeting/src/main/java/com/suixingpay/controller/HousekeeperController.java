package com.suixingpay.controller;

import com.suixingpay.entity.Apply;
import com.suixingpay.entity.Meeting;
import com.suixingpay.entity.Sign;
import com.suixingpay.service.HelloService;
import com.suixingpay.service.HousekeeperService;
import com.suixingpay.utils.GenericResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
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
        //设置会议日期
        meeting.setMeetingDate(meetingStarttime);
        //获取当前日期
        Date curDate = new Date();
        //如果会议时间不大于当前时间，失败
        if (meetingStarttime.before(curDate)) {
            log.info("新建失败,会议开始时间不能小于当前时间");
            return () -> GenericResponse.failed("999", "新建失败,会议时间不能小于当前时间");
        }
        //获取会议报名截止时间
        Date meetingEndtime = meeting.getMeetingEndtime();
        //如果报名截止时间不大于当前时间，失败
        if (meetingEndtime.before(curDate)) {
            log.info("新建失败,报名截止时间不能小于当前时间");
            return () -> GenericResponse.failed("999", "新建失败,报名截止时间不能小于当前时间");
        }
        //如果报名截止时间不大于会议时间，失败
        if (meetingEndtime.after(meetingStarttime)) {
            log.info("新建失败,报名截止时间不能大于会议开始时间");
            return () -> GenericResponse.failed("999", "新建失败,报名截止时间不能大于会议开始时间");
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
    @ApiOperation("根据id查询会议")
    public Callable<GenericResponse> findMeetingById(@PathVariable("meetingId") Integer meetingId) {
        log.info("进入按meetingId查询会议接口，参数" + meetingId);
        Meeting meeting = helloService.selectById(String.valueOf(meetingId));
        if (meeting != null) {
            return () -> GenericResponse.success("666", "查询成功", meeting);
        } else {
            return () -> GenericResponse.failed("999", "查询失败");
        }
    }


    //修改---孙克强
    @PutMapping("/updateMeetingById")
    @ApiOperation("修改会议")
    public Callable<GenericResponse> updateMeetingById(Meeting meeting, String[] address) {
        meeting.setMeetingAddress(address[0] + "," + address[1] + "," + address[2]);
        log.info("进入鑫管家修改会议接口, 参数为" + meeting);
        //判断审核状态 0 1 2(0,1可全部修改，2只能修改报名截止时间)
        //获取当前日期
        Date curDate = new Date();
        //获取会议开始时间
        Date meetingStarttime = meeting.getMeetingStarttime();
        //获取会议报名截止时间
        Date meetingEndtime = meeting.getMeetingEndtime();
        //如果报名截止时间不大于当前时间，失败
        if (meetingEndtime.before(curDate)) {
            log.info("修改失败,报名截止时间不能小于当前时间");
            return () -> GenericResponse.failed("999", "修改失败,报名截止时间不能小于当前时间");
        }
        //如果报名截止时间不大于会议时间，失败
        if (meetingEndtime.after(meetingStarttime)) {
            log.info("新建失败,报名截止时间不能大于会议开始时间");
            return () -> GenericResponse.failed("999", "新建失败,报名截止时间不能大于会议开始时间");
        }

        int i = housekeeperService.myUpdateMeetingById(meeting);
        if (i != 0) {
            return () -> GenericResponse.success("666", "修改成功");
        } else {
            return () -> GenericResponse.failed("999", "修改失败");
        }
    }


    //查看会议报名人数---孙克强
    @ApiOperation("查看会议报名人数")
    @GetMapping("/getApplyNumberByMeetingId/{meetingId}")
    public Callable<GenericResponse> getApplyNumberByMeetingId(@PathVariable("meetingId") Integer meetingId) {
        int i = housekeeperService.selectApplyNumberByMeetingId(meetingId);
        return () -> GenericResponse.success("666", "查询成功", i);
    }

    //查看会议报名信息---孙克强
    @ApiOperation("查看会议报名信息")
    @GetMapping("/getApplyByMeetingId/{meetingId}")
    public Callable<GenericResponse> getApplyByMeetingId(@PathVariable("meetingId") Integer meetingId) {
        List<Apply> applies = housekeeperService.selectApplyByMeetingId(meetingId);
        if (applies != null) {
            return () -> GenericResponse.success("666", "查询成功", applies);
        } else {
            return () -> GenericResponse.failed("999", "查询失败");
        }
    }

    //查看会议签到信息---孙克强
    @ApiOperation("查看会议签到信息")
    @GetMapping("/getSignByMeetingId/{meetingId}")
    public Callable<GenericResponse> getSignByMeetingId(@PathVariable("meetingId") Integer meetingId) {
        List<Sign> signs = housekeeperService.selectSignByMeetingId(meetingId);
        if (signs != null) {
            return () -> GenericResponse.success("666", "查询成功", signs);
        } else {
            return () -> GenericResponse.failed("999", "查询失败");
        }
    }

}
