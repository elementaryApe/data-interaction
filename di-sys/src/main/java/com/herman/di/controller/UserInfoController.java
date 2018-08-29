package com.herman.di.controller;


import com.herman.di.entity.Result;
import com.herman.di.entity.UserInfo;
import com.herman.di.service.UserInfoService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * 用户请求
 *
 * @author hsh
 * @create 2018-08-29 16:32
 **/
@Controller
@RequestMapping("user")
public class UserInfoController {
    @Resource
    private UserInfoService userInfoService;

    @RequestMapping(value="{userId}",method= RequestMethod.GET)
    @ResponseBody
    public Result getUserInfo(@PathVariable("userId") String userId) throws Exception {
        Result<UserInfo> userInfoResult = new Result<UserInfo>();
        UserInfo userInfo =userInfoService.getUserInfoById(userId);
        userInfoResult.setResult(userInfo);
        return userInfoResult;
    }

}
