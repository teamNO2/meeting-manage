package com.suixingpay.controller;

import com.suixingpay.entity.Apply;
import com.suixingpay.entity.Meeting;
import com.suixingpay.entity.Sign;
import com.suixingpay.service.BackgroundService;
import com.suixingpay.utils.GenericResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
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

}
