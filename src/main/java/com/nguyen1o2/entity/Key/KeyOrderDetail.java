package com.nguyen1o2.entity.Key;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class KeyOrderDetail implements Serializable {
    @Column(name = "order_id", insertable = false, updatable = false)
    private int orderId;

    @Column(name = "food_id", insertable = false, updatable = false)
    private int foodId;

    public KeyOrderDetail() {
    }

    public KeyOrderDetail(int orderId, int foodId) {
        this.orderId = orderId;
        this.foodId = foodId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getFoodId() {
        return foodId;
    }

    public void setFoodId(int foodId) {
        this.foodId = foodId;
    }
}
