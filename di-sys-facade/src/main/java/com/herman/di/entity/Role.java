package com.herman.di.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 权限实体
 *
 * @author hsh
 * @create 2018-09-05 14:16
 **/
@Data
@Entity
@Table(name = "role")
public class Role implements Serializable {
    private static final long serialVersionUID = -5330977032681117847L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;

    @ManyToMany(mappedBy = "roleList")
    private List<UserInfo> userInfoList;

    public Role(Integer id) {
        this.id = id;
    }

    public Role() {
    }
}
