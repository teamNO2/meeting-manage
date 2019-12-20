package com.suixingpay.controller;

import com.suixingpay.entity.Users;
import com.suixingpay.service.LoginService;
import com.suixingpay.utils.GenericResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.Callable;

/*
 *@Author 孙克强
 */
@RestController
@RequestMapping("/login")
@Slf4j
@Api
public class LoginController {

    @Autowired
    private LoginService loginService;

    //登录
    @ApiOperation(value = "登录接口", notes = "根据用户名和密码进行登录")
    @PostMapping("/login")
    public Callable<GenericResponse> login(Users users,
                                           HttpServletRequest request) {
        log.info("进入登陆方法");
        log.info("登录参数= " + "鑫管家名：" + users.getUserName() + " 鑫管家密码：" + users.getPassword());
        log.info("进行登录查询");
        Users user = loginService.loginByUserName(users.getUserName());
        //登录成功
        if (user != null && users.getPassword().equals("123456")) {
            log.info("登录用户查询到，登录成功");
            request.getSession().setAttribute(String.valueOf(user.getId()), user);
            return () -> GenericResponse.success("login666", "登录成功", user);
        } else {
            log.info("登录用户未查询到，登录失败");
            return () -> GenericResponse.failed("login999", "登录失败");
        }
    }

}
