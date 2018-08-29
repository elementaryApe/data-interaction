package com.herman.di.service.impl;

import com.herman.di.entity.UserInfo;
import com.herman.di.service.UserInfoService;
import org.springframework.stereotype.Service;

/**
 * 用户接口实现
 *
 * @author hsh
 * @create 2018-08-29 15:59
 **/
@Service
public class UserInfoServiceImpl implements UserInfoService {

    public UserInfo getUserInfoById(String userId) throws Exception {
        UserInfo userInfo = new UserInfo();
        userInfo.setUserName("HHH");
        userInfo.setPassword("kjl");
        userInfo.setUserId(userId);
        return userInfo;
    }
}
