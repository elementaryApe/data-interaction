package com.herman.di.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 房屋信息
 *
 * @author hsh
 * @create 2018-10-06 1:10
 **/
@Entity
@Data
@Table(name = "housing_info")
public class HousingInfo implements Serializable {
    private static final long serialVersionUID = 4569150058049221735L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "h_id")
    private long hId;

    @Column(name = "id")
    private long id;

    @Column(name = "area")
    private String area;

    @Column(name = "hxt")
    private int hxt;

    @Column(name = "hxw")
    private int hxw;

    @Column(name = "lceng")
    private int lceng;

    @Column(name = "mji")
    private int mji;

    @Column(name = "zceng")
    private int zceng;

    @Column(name = "djia")
    private int djia;

    @Column(name = "zjia")
    private int zjia;

    @Column(name = "quyu")
    private String quyu;

    @Column(name = "hxf")
    private int hxf;

    @Column(name = "ztai")
    private String ztai;

    @Column(name = "dhao")
    private String dhao;

    @Column(name = "fhao")
    private String fhao;
}
