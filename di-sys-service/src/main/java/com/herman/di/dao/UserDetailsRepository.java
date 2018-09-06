package com.herman.di.dao;

import com.herman.di.entity.UserDetails;
import com.herman.di.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * 用户明细信息实体
 * Created by hsh on 18/09/03.
 */
public interface UserDetailsRepository extends JpaRepository<UserDetails, Integer>, JpaSpecificationExecutor<UserDetails> {

    UserDetails findByUserInfo(UserInfo userInfo);
}
