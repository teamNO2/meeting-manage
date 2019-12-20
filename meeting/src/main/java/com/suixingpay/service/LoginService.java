package com.suixingpay.service;

import com.suixingpay.entity.Users;

public interface LoginService {
    //根据用户名登录
    Users loginByUserName(String userName);
}
