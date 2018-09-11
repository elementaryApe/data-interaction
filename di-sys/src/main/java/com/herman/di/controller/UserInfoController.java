package com.herman.di.controller;


import com.herman.di.entity.*;
import com.herman.di.entity.vo.UserInfoVo;
import com.herman.di.service.UserInfoService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    @RequestMapping(value = "{userId}", method = RequestMethod.GET)
    @ResponseBody
    public Result getUserInfo(@PathVariable("userId") String userId) {
        try {
            Result<UserInfo> userInfoResult = new Result<>();
            UserInfo userInfo = userInfoService.getUserInfoById(userId);
            userInfoResult.setResult(userInfo);
            return userInfoResult;
        } catch (Exception e) {
            e.printStackTrace();
            return Result.returnError("出错了 请查看日志");
        }
    }

//    @RequestMapping(method = RequestMethod.POST)
//    @ResponseBody
//    public Result addUserInfo(@RequestParam(value = "name") String name,
//                              @RequestParam(value = "number") String number,
//                              @RequestParam(value = "password") String password) {
//        try {
//            UserInfo userInfo = new UserInfo();
//            userInfo.setUName(name);
//            userInfo.setPassword(password);
//            userInfo.setUNumber(number);
//            if (!userInfoService.addUserInfo(userInfo))
//                return Result.returnOperateFail();
//            return new Result();
//        } catch (Exception e) {
//            e.printStackTrace();
//            return Result.returnError("出错了 请查看日志");
//        }
//    }
//
//    /**
//     * 获取列表
//     */
//    @RequestMapping(value = "list", method = RequestMethod.GET)
//    @ResponseBody
//    public Result getUserList(@RequestParam(value = "userId", required = false) String userId,
//                              @RequestParam(value = "userName", required = false) String userName,
//                              @RequestParam(value = "number", required = false) String number) {
//        try {
//            UserInfoVo query = new UserInfoVo();
//            if (StringUtils.isNotEmpty(userId))
//                query.setId(Integer.valueOf(userId));
//            query.setUName(userName);
//            query.setUNumber(number);
//            List<UserInfo> userInfoList = userInfoService.getUserInfoList(query);
//            return new Result<>(Result.SUCESS, Result.Message.SUCESS.getDescription(), userInfoList);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return Result.returnError("出错了 请查看日志");
//        }
//    }
//
//    /**
//     * 分页查询
//     */
//    @RequestMapping(value = "page/{pageIndex}", method = RequestMethod.GET)
//    @ResponseBody
//    public Result findUserInfoWithPages(@PathVariable(value = "pageIndex") Integer pageIndex,
//                                        @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
//                                        @RequestParam(value = "userId", required = false) String userId,
//                                        @RequestParam(value = "userName", required = false) String userName,
//                                        @RequestParam(value = "number", required = false) String number) {
//        try {
//            UserInfoVo query = new UserInfoVo();
//            if (StringUtils.isNotEmpty(userId))
//                query.setIds(userId);
//            query.setUName(userName);
//            query.setUNumber(number);
//            Page<UserInfo> data = userInfoService.findUserInfo(query, new PageRequest(pageIndex >= 0 ? pageIndex : 0, pageSize));
//            return new Result<>(Result.SUCESS, Result.Message.SUCESS.getDescription(), data);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return Result.returnError("出错了 请查看日志");
//        }
//    }








}
