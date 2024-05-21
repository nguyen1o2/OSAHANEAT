package com.nguyen1o2.service.imp;

import com.nguyen1o2.payload.request.OrderRequest;

public interface OrderServiceImp {
    boolean insertOrder(OrderRequest orderRequest);
}
