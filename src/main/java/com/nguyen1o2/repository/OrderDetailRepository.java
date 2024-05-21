package com.nguyen1o2.repository;

import com.nguyen1o2.entity.Key.KeyOrderDetail;
import com.nguyen1o2.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, KeyOrderDetail> {
}
