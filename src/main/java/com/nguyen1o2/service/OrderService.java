package com.nguyen1o2.service;

import com.nguyen1o2.entity.*;
import com.nguyen1o2.entity.Key.KeyOrderDetail;
import com.nguyen1o2.payload.request.OrderRequest;
import com.nguyen1o2.repository.OrderDetailRepository;
import com.nguyen1o2.repository.OrderRepository;
import com.nguyen1o2.service.imp.OrderServiceImp;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
public class OrderService implements OrderServiceImp {
    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderDetailRepository orderDetailRepository;

    @Override
    public boolean insertOrder(OrderRequest orderRequest) {
        try{
            Users users = new Users();
            users.setId(orderRequest.getUserId());

            Restaurant restaurant = new Restaurant();
            restaurant.setId(orderRequest.getResId());

            Orders orders = new Orders();
            orders.setUsers(users);
            orders.setRestaurant(restaurant);

            orderRepository.save(orders);

            List<OrderDetail> items = new ArrayList<>();
            for (int idFood: orderRequest.getFoodIds()) {
                Food food = new Food();
                food.setId(idFood);

                OrderDetail orderDetail = new OrderDetail();
                KeyOrderDetail keyOrderDetail = new KeyOrderDetail(orders.getId(),idFood);
                orderDetail.setKeyOrderDetail(keyOrderDetail);
                orderDetail.setOrders(orders);
                orderDetail.setFood(food);

                items.add(orderDetail);
            }
            orderDetailRepository.saveAll(items);
            return true;
        }catch (Exception e){
            System.out.println("Error insert order: "+e.getMessage());
            return false;
        }
    }
}
