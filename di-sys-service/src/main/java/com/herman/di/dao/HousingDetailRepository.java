package com.herman.di.dao;

import com.herman.di.entity.HousingDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * 房屋实体dao
 * Created by hsh on 18/10/06.
 */
public interface HousingDetailRepository extends JpaRepository<HousingDetail, Integer>, JpaSpecificationExecutor<HousingDetail> {
}
