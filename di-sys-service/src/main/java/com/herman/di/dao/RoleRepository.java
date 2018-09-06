package com.herman.di.dao;

import com.herman.di.entity.Role;
import com.herman.di.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * 权限dao层
 * Created by hsh on 18/09/05.
 */
public interface RoleRepository extends JpaRepository<Role, Integer>, JpaSpecificationExecutor<Role> {

    List<Role> findByUserInfoList(List<UserInfo> userInfos);
}
