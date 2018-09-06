package com.herman.di.dao;

import com.herman.di.entity.ArticleInfo;
import com.herman.di.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * 文章dao层
 * Created by hsh on 18/09/05.
 */
public interface ArticleInfoRepository extends JpaRepository<ArticleInfo, Integer>, JpaSpecificationExecutor<ArticleInfo> {

    List<ArticleInfo> findByUserInfo(UserInfo userInfo);

}
