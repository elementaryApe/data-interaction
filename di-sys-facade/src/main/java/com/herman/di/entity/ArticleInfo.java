package com.herman.di.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 文章表
 *
 * @author hsh
 * @create 2018-09-05 10:01
 **/
@Data
@Entity
@Table(name = "article")
public class ArticleInfo implements Serializable {
    private static final long serialVersionUID = 1179862849693748278L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Lob
    @Column(name = "context")
    private String context;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH}, optional = false,fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserInfo userInfo;

}
