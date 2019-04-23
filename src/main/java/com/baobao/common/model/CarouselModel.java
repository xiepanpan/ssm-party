package com.baobao.common.model;

public class CarouselModel {
    private Integer carouselId;

    private String carouselImgurl;

    private Integer imgId;

    public Integer getCarouselId() {
        return carouselId;
    }

    public void setCarouselId(Integer carouselId) {
        this.carouselId = carouselId;
    }

    public String getCarouselImgurl() {
        return carouselImgurl;
    }

    public void setCarouselImgurl(String carouselImgurl) {
        this.carouselImgurl = carouselImgurl == null ? null : carouselImgurl.trim();
    }

    public Integer getImgId() {
        return imgId;
    }

    public void setImgId(Integer imgId) {
        this.imgId = imgId;
    }
}