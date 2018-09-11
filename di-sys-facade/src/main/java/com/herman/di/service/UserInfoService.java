package com.herman.di.service;

import com.alibaba.dubbo.rpc.protocol.rest.support.ContentType;
import com.herman.di.entity.UserInfo;
import com.herman.di.entity.vo.UserInfoVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;


import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * 用户相关接口
 * Created by hsh on 18/08/29.
 */
@Path("/userService")
@Consumes({MediaType.APPLICATION_JSON, MediaType.TEXT_XML})
@Produces({ContentType.APPLICATION_JSON_UTF_8, ContentType.TEXT_XML_UTF_8})
public interface UserInfoService {

    /**
     * 根据用户Id获取用户信息
     *
     * @param userId
     * @return
     * @throws Exception
     */
    @GET
    @Path("/get/{userId}")
    UserInfo getUserInfoById(@PathParam(value = "userId")String userId) throws Exception;

//    /**
//     * 添加用户信息
//     */
//    boolean addUserInfo(UserInfo userInfo) throws Exception;
//
//    /**
//     * 获取用户信息列表
//     */
//    List<UserInfo> getUserInfoList(UserInfoVo query) throws Exception;
//
//    /**
//     * 获取用户分页信息
//     */
//    Page findUserInfo(UserInfoVo userInfo, PageRequest request) throws Exception;

}
