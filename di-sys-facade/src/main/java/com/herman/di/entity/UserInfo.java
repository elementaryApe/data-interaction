package com.herman.di.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * 用户实体
 *
 * @author hsh
 * @create 2018-08-30 11:49
 **/
@Data
@Entity
@Table(name = "user_info")
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler", "fieldHandler"})
public class UserInfo implements Serializable {
    private static final long serialVersionUID = -12285021873923699L;
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "uname")
    private String uName;

    @Column(name = "unumber")
    private String uNumber;

    @Column(name = "password")
    private String password;

    @JsonIgnoreProperties(value = "userInfo")//避免递归死循环
    @OneToOne(mappedBy = "userInfo", cascade = CascadeType.ALL)
    private UserDetails userDetails;

    @JsonIgnoreProperties(value = "userInfo")//避免递归死循环
    @OneToMany(mappedBy = "userInfo", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ArticleInfo> articleInfoList;

    @JsonIgnoreProperties(value = "userInfoList")//避免递归死循环
    @ManyToMany
    @JoinTable(name = "user_info_role",
            joinColumns = {@JoinColumn(name = "user_info_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")})
    private List<Role> roleList;


    public UserInfo() {
    }

    public UserInfo(Integer id) {
        this.id = id;
    }

}
