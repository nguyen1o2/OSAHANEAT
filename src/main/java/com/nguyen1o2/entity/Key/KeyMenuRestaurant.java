package com.nguyen1o2.entity.Key;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class KeyMenuRestaurant implements Serializable {
    @Column(name = "cate_id", insertable = false, updatable = false)
    private int cateId;

    @Column(name = "res_id", insertable = false, updatable = false)
    private int resId;

    public KeyMenuRestaurant() {
    }

    public KeyMenuRestaurant(int cateId, int resId) {
        this.cateId = cateId;
        this.resId = resId;
    }

    public int getCateId() {
        return cateId;
    }

    public void setCateId(int cateId) {
        this.cateId = cateId;
    }

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }
}
