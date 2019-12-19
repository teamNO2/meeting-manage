package com.suixingpay.controller;

import com.suixingpay.entity.Apply;
import com.suixingpay.entity.Meeting;
import com.suixingpay.entity.Users;
import com.suixingpay.service.BackgroundService;
import com.suixingpay.utils.GenericResponse;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.suixingpay.entity.Meeting;
import com.suixingpay.entity.Sign;
import com.suixingpay.service.BackgroundService;
import com.suixingpay.utils.GenericResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.Callable;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Max;
import java.util.*;
import java.util.concurrent.Callable;

/*
 * 后台管理
 * 张佳鑫和李常昊
 */
@RestController
@RequestMapping("/background")
public class BackgroundController {
    @Autowired
    private BackgroundService backgroundService;





    /**
     * @Description: 管理员创建会议
     * @Param: [map]
     * @return: java.util.concurrent.Callable<com.suixingpay.utils.GenericResponse>
     * @Author: lichanghao
     * @Date: 2019/12/18
     */
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

        String userId  = users.getUserId();
        System.out.println(userId);
        int meetingId = (int)map.get("meetingId");
        //判空
        if (meetingId==0){
            return()->GenericResponse.failed("666", "meetingId，为空创建失败");
        }
        Meeting meeting1 = backgroundService.selectById(String.valueOf(meetingId));
        if(meeting1!=null){
            return()->GenericResponse.failed("666", "会议已存在");
        }

        int meetingType = (int)map.get("meetingType");
        //判空
        if (meetingType==-1){
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
        meeting.setUserId(Integer.parseInt(userId));
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


    /*
     * 张佳鑫
     * 后台管理查询所有会议
     */
    @GetMapping("/backgroundSelectAll")
    public Callable<GenericResponse> backgroundSelectAll(){
        List<Meeting> meetingList = backgroundService.backgroundSelectAll();
        if (meetingList != null) {
            return () -> GenericResponse.success("666", "backgroundSelectAll查询成功", meetingList);
        } else {
            return () -> GenericResponse.failed("666", "backgroundSelectAll查询失败");
        }
    }

    /*
     * 张佳鑫
     * 后台管理查询会议详细
     */
    @GetMapping("/backgroundSelectById/{meetingId}")
    public Callable<GenericResponse> backgroundSelectById1(@PathVariable("meetingId") String meetingId){
        Map<String, Object> map = new HashMap<>();
        Meeting meeting = backgroundService.backgroundSelectById1(Integer.valueOf(meetingId));
        List<Apply> applyList = backgroundService.backgroundSelectById2(Integer.valueOf(meetingId));
        List<Sign> signList = backgroundService.backgroundSelectById3(Integer.valueOf(meetingId));
        map.put("a", meeting);
        map.put("m", signList);
        map.put("s", applyList);
        if (map != null) {
            return () -> GenericResponse.success("666", "backgroundSelectById查询成功", map);
        } else {
            return () -> GenericResponse.failed("666", "backgroundSelectById查询失败");
        }
    }

    /*
     * 张佳鑫
     * 后台管理审核会议
     */
    @GetMapping("/backgroundUpdateStatus/{meetingId}/{check}")
    public Callable<GenericResponse> backgroundUpdateStatus(@PathVariable("meetingId") String meetingId, @PathVariable("check") String check){
        Integer num = backgroundService.backgroundUpdateStatus(Integer.valueOf(meetingId),Integer.valueOf(check));
        if (num!=null) {
            return () -> GenericResponse.success("666", "backgroundUpdateStatus修改成功", num);
        } else {
            return () -> GenericResponse.failed("666", "backgroundUpdateStatus修改失败");
        }
    }

    /**
     * @Description: 模糊查询会议
     * @Param: [map]
     * @return: java.util.List<com.suixingpay.entity.Meeting>
     * @Author: lichanghao
     * @Date: 2019/12/18
     */
    @PostMapping("/selectMeetingWithLike")
    @ResponseBody
    public List<Meeting> selectMeetingWithLike(@RequestBody Map map)throws Exception{
        Users users = new Users();
        String referralCode =(String) map.get("referralCode");
        users.setReferralCode(referralCode);
        List<Users>usersList = backgroundService.findPageWithResultLike(users);

        Meeting meeting = new Meeting();
        List<Meeting> meetingList = new ArrayList<>();
        List<Meeting> meetingList1 = null;
        List<Meeting> meetingList2 = new ArrayList<>();
        List<Meeting> meetingListAll = new ArrayList<>();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String beginDate = (String)map.get("startDate");
        String endDate = (String)map.get("endDate");
        //当日期范围存在时
        if (beginDate!=null&&endDate!=null) {
            System.out.println("xingeshishabi");
            Date date1 = format.parse(beginDate);
            Date date2 = format.parse(endDate);
            meetingList1 = backgroundService.limitDate(date1, date2);
        }

        String meetingSalary = (String)map.get("meetingSalary");
        meeting.setMeetingSalary(Integer.valueOf(meetingSalary));
        String meetingType = (String) map.get("meetingType");
        //判空
        if (meetingType!=null) {
            meeting.setMeetingType(Integer.valueOf(meetingType));
        }
        String startType = (String) map.get("startType");
        //判空
        if (startType!=null) {
            meeting.setStartType(Integer.valueOf(startType));
        }
        String meetingCheckstatus = (String) map.get("meetingCheckstatus");
        //判空
        if (meetingCheckstatus!=null) {
            meeting.setMeetingCheckstatus(Integer.valueOf(meetingCheckstatus));
        }
        String meetingStatus = (String) map.get("meetingStatus");
        //判空
        if(meetingStatus!=null) {
            meeting.setMeetingStatus(Integer.valueOf(meetingStatus));
        }
        meetingList2 = backgroundService.findPageWithResultLike(meeting);

        if (meetingList1!=null){
            System.out.println("caonima");
            for (Meeting meeting1:meetingList1){
                for (Meeting meeting2:meetingList2){
                    if(meeting1.getMeetingId() == meeting2.getMeetingId()){
                        meetingList.add(meeting1);
                        break;
                    }
                }
            }
            meetingListAll = backgroundService.likeMeeting(meetingList, usersList);
        }else {
            meetingListAll= backgroundService.likeMeeting(meetingList2, usersList);

        }
        return meetingListAll;
    }

}
