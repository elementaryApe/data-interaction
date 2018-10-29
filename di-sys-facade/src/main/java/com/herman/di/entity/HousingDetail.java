package com.herman.di.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 房屋明细表
 *
 * @author hsh
 * @create 2018-10-06 1:22
 **/
@Data
@Entity
@Table(name = "housing_detail")
public class HousingDetail implements Serializable {
    private static final long serialVersionUID = 8294160256187873074L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "h_id")
    private long hId;

    @Column(name = "id")
    private long id;

    @Column(name = "cid")
    private int cid;

    @Column(name = "did")
    private int did;

    @Column(name = "uid")
    private int uid;

    @Column(name = "quyu")
    private String quyu;

    @Column(name = "area")
    private String area;

    @Column(name = "dhao")
    private String dhao;

    @Column(name = "fhao")
    private String fhao;

    @Column(name = "address")
    private String address;

    @Column(name = "lceng")
    private int lceng;

    @Column(name = "zceng")
    private int zceng;

    @Column(name = "lxing")
    private String lxing;

    @Column(name = "hxf")
    private int hxf;

    @Column(name = "hxt")
    private int hxt;

    @Column(name = "hxw")
    private int hxw;

    @Column(name = "zxiu")
    private String zxiu;

    @Column(name = "mji")
    private int mji;

    @Column(name = "zjia")
    private int zjia;

    @Column(name = "djia")
    private int djia;

    @Column(name = "lxr")
    private String lxr;

    @Column(name = "tel")
    private String tel;

    @Column(name = "forlxr")
    private String forlxr;

    @Column(name = "fortel")
    private String fortel;

    @Column(name = "beizhu")
    private String beizhu;

    @Column(name = "ztai")
    private String ztai;

    @Column(name = "dateyear")
    private int dateyear;

    @Column(name = "dateadd")
    private Date dateadd;

    @Column(name = "dategenjin")
    private String dategenjin;

    @Column(name = "dategjlock")
    private String dategjlock;

    @Column(name = "fav")
    private String fav;

    @Column(name = "sh")
    private int sh;

    @Column(name = "seeGX")
    private int seeGX;

    @Column(name = "seeHM")
    private int seeHM;

    @Column(name = "seeFH")
    private int seeFH;

    @Column(name = "isdel")
    private int isdel;

    @Column(name = "href")
    private String href;

    @Column(name = "site")
    private String site;

    @Column(name = "telImg")
    private String telImg;

    @Column(name = "updatetime")
    private Date updatetime;

    @Column(name = "latitude")
    private double latitude;

    @Column(name = "longitude")
    private double longitude;

    @Column(name = "isfav")
    private String isfav;

    @Column(name = "hxing")
    private String hxing;

    @Column(name = "year")
    private int year;

    @Column(name = "readCount")
    private int readCount;

    @Column(name = "imageCount")
    private int imageCount;

}
