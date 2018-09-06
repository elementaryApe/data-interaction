package com.herman.di.entity.vo;

import com.herman.di.entity.UserInfo;
import lombok.Data;

/**
 * 用户查询条件实体
 *
 * @author hsh
 * @create 2018-09-04 14:43
 **/
@Data
public class UserInfoVo extends UserInfo {

    private static final long serialVersionUID = -4056657303649751166L;

    private String address;

    private String ids;

}
