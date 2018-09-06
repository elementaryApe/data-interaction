package com.herman.di.service;

import com.herman.di.entity.ArticleInfo;
import com.herman.di.entity.Role;
import com.herman.di.entity.UserDetails;
import com.herman.di.entity.UserInfo;
import com.herman.di.entity.vo.UserInfoVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * 用户相关接口
 * Created by hsh on 18/08/29.
 */
public interface UserInfoService {

    /**
     * 根据用户Id获取用户信息
     *
     * @param userId
     * @return
     * @throws Exception
     */
    UserInfo getUserInfoById(String userId) throws Exception;

    /**
     * 添加用户信息
     */
    boolean addUserInfo(UserInfo userInfo) throws Exception;

    /**
     * 用户添加文章
     */
    boolean addArticleInfo(ArticleInfo articleInfo) throws Exception;

    /**
     * 新增权限
     */
    boolean addRole(Role role)throws Exception;

    /**
     * 获取用户信息列表
     */
    List<UserInfo> getUserInfoList(UserInfoVo query) throws Exception;

    /**
     * 获取用户分页信息
     */
    Page findUserInfo(UserInfoVo userInfo, PageRequest request) throws Exception;

    /**
     * 获取用户详情信息
     */
    UserDetails getUserDetails(Integer userId) throws Exception;

    /**
     * 获取文章信息
     */
    List<ArticleInfo> getArticleInfo(Integer userId) throws Exception;

    /**
     * 获取权限信息
     */
    List<Role> getRoleInfo(String[] userIds) throws Exception;

    /**
     * 根据id获取权限信息
     */
    Role getRole(Integer roleId) throws Exception;




}
