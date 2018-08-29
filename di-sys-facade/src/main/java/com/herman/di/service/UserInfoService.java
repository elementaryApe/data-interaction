package com.herman.di.service;

import com.herman.di.entity.UserInfo;

/**
 * 用户相关接口
 * Created by hsh on 18/08/29.
 */
public interface UserInfoService {

    /**
     * 根据用户Id获取用户信息
     * @param userId
     * @return
     * @throws Exception
     */
    UserInfo getUserInfoById(String userId) throws Exception;
}
