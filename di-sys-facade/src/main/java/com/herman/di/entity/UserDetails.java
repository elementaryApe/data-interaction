package com.herman.di.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 用户详情信息实体
 *
 * @author hsh
 * @create 2018-08-31 10:55
 **/
@Data
@Entity
@Table(name = "user_details")
public class UserDetails implements Serializable {
    private static final long serialVersionUID = -3678404406666619942L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "email")
    private String email;

    @Column(name = "address")
    private String address;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserInfo userInfo;

}

