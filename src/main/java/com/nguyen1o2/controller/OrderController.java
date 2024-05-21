package com.nguyen1o2.controller;

import com.nguyen1o2.payload.ResponseData;
import com.nguyen1o2.payload.request.OrderRequest;
import com.nguyen1o2.service.imp.OrderServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    OrderServiceImp orderServiceImp;

    @PostMapping()
    public ResponseEntity<?> getOrder(@RequestBody OrderRequest orderRequest){
        ResponseData responseData = new ResponseData();
        responseData.setData(orderServiceImp.insertOrder(orderRequest));
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }
}
