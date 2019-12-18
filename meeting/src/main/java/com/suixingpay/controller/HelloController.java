package com.suixingpay.controller;

import com.suixingpay.entity.Meeting;
import com.suixingpay.service.HelloService;
import com.suixingpay.utils.GenericResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.concurrent.Callable;

/*
 *@Author sunkeqiang
 */
@Controller
@RequestMapping("/hello")
public class HelloController {

    @Autowired
    private HelloService helloService;

    @GetMapping("/slectById/{id}")
    public Callable<GenericResponse> selectById(@PathVariable("id") String meetingId) {
        Meeting meeting = helloService.selectById(meetingId);
        if (meeting != null) {
            return () -> GenericResponse.success("666", "查询成功", meeting);
        } else {
            return () -> GenericResponse.failed("666", "查询成功");
        }
    }
}
