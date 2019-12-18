package com.suixingpay.controller;

import com.suixingpay.entity.Meeting;
import com.suixingpay.service.BackgroundService;
import com.suixingpay.utils.GenericResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Max;
import java.util.List;
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
            return () -> GenericResponse.success("666", "查询成功", meetingList);
        } else {
            return () -> GenericResponse.failed("666", "查询成功");
        }
    }

}
