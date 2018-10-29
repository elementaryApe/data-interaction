package com.herman.di.dao;

import com.herman.di.entity.HousingInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * 房屋信息实体
 * Created by hsh on 18/10/06.
 */
public interface HousingInfoRepository extends JpaRepository<HousingInfo, Integer>, JpaSpecificationExecutor<HousingInfo> {
}
