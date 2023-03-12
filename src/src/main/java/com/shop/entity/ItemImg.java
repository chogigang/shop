package com.shop.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "item_img")
public class ItemImg extends  BaseEntity{
    @Id
    @Column(name = "item_img_id")
    @GeneratedValue(strategy =GenerationType.AUTO)
    private Long id;

    private String imgNme; //이미지 파일명

    private String oriImgName;//원본 이미지 파일명

    private String imgUrl;// 이미지 조회 경로

    private String repimgYn;//대표 이미지 여부

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    public void updateItemImg(String oriImgName, String imgNme, String imgUrl){
        this.oriImgName = oriImgName;
        this.imgNme = imgNme;
        this. imgUrl = imgUrl;

    }
}
