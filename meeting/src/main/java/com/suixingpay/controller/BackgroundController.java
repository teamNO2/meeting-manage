package com.suixingpay.controller;

import com.suixingpay.entity.Meeting;
import com.suixingpay.entity.Users;
import com.suixingpay.service.BackgroundService;
import com.suixingpay.utils.GenericResponse;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.Callable;

@Controller
@RequestMapping("/background")
public class BackgroundController {
    @Autowired
    private BackgroundService backgroundService;

    @PostMapping("/createMeeting")
    @ResponseBody
    public Callable<GenericResponse> CreateMeeting(@RequestBody Map map)throws Exception{
        String name = (String)map.get("userName");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //判断前端是否传递空值
        if (name==null||"".equals(name)){
            return()->GenericResponse.failed("666", "创建失败");
        }

        Users users = backgroundService.selectByName(name);

        //判断数据库中是否存在该鑫管家
        if (users==null){
            return()->GenericResponse.failed("666", "鑫管家不存在，创建失败");
        }

        Integer id  = users.getId();

        int meetingId = (int)map.get("meetingId");
        //判空
        if (meetingId==0){
            return()->GenericResponse.failed("666", "meetingId，为空创建失败");
        }

        int meetingType = (int)map.get("meetingType");
        //判空
        if (meetingType==0){
            return()->GenericResponse.failed("666", "meetingType，为空创建失败");
        }

        String meetingName = (String)map.get("meetingName");
        //判空
        if (meetingName==null||"".equals(meetingName)){
            return()->GenericResponse.failed("666", "meetingName，为空创建失败");
        }

        String meetingCreate = (String)map.get("meetingCreate");
        //判空
        if (meetingCreate==null||"".equals(meetingCreate)){
            return()->GenericResponse.failed("666", "meetingCreate，为空创建失败");
        }

        int meetingSalary = (int)map.get("meetingSalary");
        //判空
        if (meetingSalary==0){
            return()->GenericResponse.failed("666", "meetingSalary，为空创建失败");
        }

        String meetingDate = (String) map.get("meetingDate");
        //判空
        if (meetingDate==null||"".equals(meetingDate)){
            return()->GenericResponse.failed("666", "meetingDate，为空创建失败");
        }

        String meetingStarttime = (String)map.get("meetingStarttime");
        //判空
        if (meetingStarttime==null||"".equals(meetingStarttime)){
            return()->GenericResponse.failed("666", "meetingStarttime，为空创建失败");
        }

        String meetingAddress = (String)map.get("meetingAddress");
        //判空
        if (meetingAddress==null||"".equals(meetingAddress)){
            return()->GenericResponse.failed("666", "meetingAddress，为空创建失败");
        }

        String meetingEndtime = (String)map.get("meetingEndtime");
        //判空
        if (meetingEndtime==null||"".equals(meetingEndtime)){
            return()->GenericResponse.failed("666", "meetingEndtime，为空创建失败");
        }

        String meetingDescribe = (String)map.get("meetingDescribe");
        //判空
        if (meetingDescribe==null||"".equals(meetingDescribe)){
            return()->GenericResponse.failed("666", "meetingDescribe，为空创建失败");
        }
        Meeting meeting = new Meeting();
        meeting.setMeetingAddress(meetingAddress);
        Date DmeetingDate = format.parse(meetingDate);
        meeting.setMeetingDate(DmeetingDate);
        meeting.setMeetingName(meetingName);
        meeting.setMeetingDescribe(meetingDescribe);
        Date DmeetingEndtime = format.parse(meetingEndtime);
        meeting.setMeetingEndtime(DmeetingEndtime);
        meeting.setMeetingId(meetingId);
        meeting.setMeetingSalary(meetingSalary);
        meeting.setUserId(id);
        Date DmeetingStarttime = format.parse(meetingStarttime);
        meeting.setMeetingStarttime(DmeetingStarttime);
        meeting.setMeetingAddress(meetingAddress);
        meeting.setMeetingCreate(meetingCreate);
        int index = backgroundService.createMeeting(meeting);
        //判断是否插入成功
        if (index ==0){
            return()->GenericResponse.failed("666","插入失败");
        }else {
            return()->GenericResponse.success("666","插入成功");
        }

    }
}
