package com.suixingpay.service.serviceImpl;

import com.suixingpay.entity.Users;
import com.suixingpay.repository.UsersRepository;
import com.suixingpay.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/*
 *@Author sunkeqiang
 */
@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private UsersRepository usersRepository;

    @Override
    public Users loginByUserName(String userName) {
        return usersRepository.loginByUserName(userName);
    }
}
