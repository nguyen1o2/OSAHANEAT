package com.nguyen1o2.entity;

import com.nguyen1o2.entity.Key.KeyOrderDetail;
import jakarta.persistence.*;

import java.util.Date;

@Entity(name = "orderdetail")
public class OrderDetail {
    @EmbeddedId
    private KeyOrderDetail keyOrderDetail;

    @ManyToOne
    @MapsId("orderId")
    @JoinColumn(name = "order_id")
    private Orders orders;

    @ManyToOne
    @MapsId("foodId")
    @JoinColumn(name = "food_id")
    private Food food;

    @Column(name = "create_date")
    private Date createDate;

    public KeyOrderDetail getKeyOrderDetail() {
        return keyOrderDetail;
    }

    public void setKeyOrderDetail(KeyOrderDetail keyOrderDetail) {
        this.keyOrderDetail = keyOrderDetail;
    }

    public Orders getOrders() {
        return orders;
    }

    public void setOrders(Orders orders) {
        this.orders = orders;
    }

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
