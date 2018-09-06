package com.herman.di.dao;

import com.herman.di.entity.UserInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

/**
 * 用户信息dao
 * Created by hsh on 18/08/30.
 */
public interface UserInfoRepository extends JpaRepository<UserInfo, Integer>, JpaSpecificationExecutor<UserInfo> {


    //    @Query("select  u  from  UserInfo u where u.id=?1 ")
    //@Query("select  * from user_info  where id=:id",nativeQuery = true)//本地模式查询即原生sql  /*@Param("id")*/
    UserInfo findById(Integer id);


    /**
     * 原生分页 查询条件不能为空
     */
    Page<UserInfo> findByUNameContainingAndUNumberEqualsAndIdEquals(String uName, String uNumber, Integer id, Pageable pageable);
}
