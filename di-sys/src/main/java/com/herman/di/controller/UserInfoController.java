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

    @RequestMapping(value = "details/{userId}", method = RequestMethod.GET)
    @ResponseBody
    public Result getUserDetails(@PathVariable("userId") String userId) {
        try {
            Result<UserDetails> userInfoResult = new Result<>();
            UserDetails userDetails = userInfoService.getUserDetails(Integer.valueOf(userId));
            userInfoResult.setResult(userDetails);
            return userInfoResult;
        } catch (Exception e) {
            e.printStackTrace();
            return Result.returnError("出错了 请查看日志");
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public Result addUserInfo(@RequestParam(value = "name") String name,
                              @RequestParam(value = "number") String number,
                              @RequestParam(value = "password") String password,
                              @RequestParam(value = "roleName") String roleId) {
        try {
            UserInfo userInfo = new UserInfo();
            userInfo.setUName(name);
            userInfo.setPassword(password);
            userInfo.setUNumber(number);
            if (StringUtils.isNotEmpty(roleId)) {
                List<Role> authorityList = new ArrayList<>();
                authorityList.add(new Role(Integer.valueOf(roleId)));
                userInfo.setRoleList(authorityList);
            }
            if (!userInfoService.addUserInfo(userInfo))
                return Result.returnOperateFail();
            return new Result();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.returnError("出错了 请查看日志");
        }
    }

    /**
     * 获取列表
     */
    @RequestMapping(value = "list", method = RequestMethod.GET)
    @ResponseBody
    public Result getUserList(@RequestParam(value = "userId", required = false) String userId,
                              @RequestParam(value = "userName", required = false) String userName,
                              @RequestParam(value = "number", required = false) String number) {
        try {
            UserInfoVo query = new UserInfoVo();
            if (StringUtils.isNotEmpty(userId))
                query.setId(Integer.valueOf(userId));
            query.setUName(userName);
            query.setUNumber(number);
            List<UserInfo> userInfoList = userInfoService.getUserInfoList(query);
            return new Result<>(Result.SUCESS, Result.Message.SUCESS.getDescription(), userInfoList);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.returnError("出错了 请查看日志");
        }
    }

    /**
     * 分页查询
     */
    @RequestMapping(value = "page/{pageIndex}", method = RequestMethod.GET)
    @ResponseBody
    public Result findUserInfoWithPages(@PathVariable(value = "pageIndex") Integer pageIndex,
                                        @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
                                        @RequestParam(value = "userId", required = false) String userId,
                                        @RequestParam(value = "userName", required = false) String userName,
                                        @RequestParam(value = "address", required = false) String address,
                                        @RequestParam(value = "number", required = false) String number) {
        try {
            UserInfoVo query = new UserInfoVo();
            if (StringUtils.isNotEmpty(userId))
                query.setIds(userId);
            query.setUName(userName);
            query.setUNumber(number);
            query.setAddress(address);
            Page<UserInfo> data = userInfoService.findUserInfo(query, new PageRequest(pageIndex >= 0 ? pageIndex : 0, pageSize));
            return new Result<>(Result.SUCESS, Result.Message.SUCESS.getDescription(), data);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.returnError("出错了 请查看日志");
        }
    }


    /**
     * 发布文章
     */
    @RequestMapping(value = "article/{userId}", method = RequestMethod.POST)
    @ResponseBody
    public Result addArticleInfo(@PathVariable("userId") String userId,
                                 @RequestParam("context") String context) {
        try {
            if (StringUtils.isBlank(userId))
                return Result.returnError("用户Id不能为空");
            ArticleInfo articleInfo = new ArticleInfo();
            articleInfo.setContext(context);
            articleInfo.setCreateTime(new Date());
            articleInfo.setUserInfo(new UserInfo(Integer.valueOf(userId)));
            if (!userInfoService.addArticleInfo(articleInfo))
                return Result.returnOperateFail();
            return Result.resultSucess();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.returnError("出错了 请查看日志");
        }
    }

    /**
     * 查询文章
     */
    @RequestMapping(value = "article/{userId}", method = RequestMethod.GET)
    @ResponseBody
    public Result getArticleInfo(@PathVariable("userId") String userId) {
        try {
            if (StringUtils.isBlank(userId))
                return Result.returnError("用户Id不能为空");
            List<ArticleInfo> articleInfo = userInfoService.getArticleInfo(Integer.valueOf(userId));
            return new Result<>(Result.SUCESS, Result.Message.SUCESS.getDescription(), articleInfo);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.returnError("出错了 请查看日志");
        }
    }

    /**
     * 新增权限
     */
    @RequestMapping(value = "role", method = RequestMethod.POST)
    @ResponseBody
    public Result updateRoleInfo(@RequestParam("roleName") String roleName,
                                 @RequestParam("userId")String userId) {
        try {
            Role role = new Role();
            role.setName(roleName);
            role.setCreateTime(new Date());
            if (!userInfoService.addRole(role))
                return Result.returnOperateFail();
            return Result.resultSucess();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.returnError("出错了 请查看日志");
        }
    }

    /**
     * 更新用户权限
     */
    @RequestMapping(value = "role/{userId}", method = RequestMethod.PUT)
    @ResponseBody
    public Result putUserRoleInfo(@PathVariable("userId") String userId,
                                  @RequestParam("roleId") String roleId) {
        try {
            if (StringUtils.isBlank(userId))
                return Result.returnError("参数不能为空");
            UserInfo userInfo = userInfoService.getUserInfoById(userId);
            List<Role> authorityList = new ArrayList<>();
            authorityList.add(new Role(Integer.valueOf(roleId)));
            userInfo.setRoleList(authorityList);
            if (!userInfoService.addUserInfo(userInfo))
                return Result.returnOperateFail();
            return Result.resultSucess();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.returnError("出错了 请查看日志");
        }
    }

    /**
     * 获取用户权限
     */
    @RequestMapping(value = "role", method = RequestMethod.GET)
    @ResponseBody
    public Result getRoleInfo(@RequestParam("roleId") String roleId,
                              @RequestParam("userIds") String userId) {
        try {
            if (StringUtils.isBlank(userId) && StringUtils.isBlank(roleId))
                return Result.returnError("参数不能为空");
            else if (StringUtils.isNotEmpty(roleId))
                return new Result<>(Result.SUCESS, Result.Message.SUCESS.getDescription(), userInfoService.getRole(Integer.valueOf(roleId)));
            else {
                List<Role> roleInfo = userInfoService.getRoleInfo(userId.split(","));
                return new Result<>(Result.SUCESS, Result.Message.SUCESS.getDescription(), roleInfo);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Result.returnError("出错了 请查看日志");
        }
    }

}
