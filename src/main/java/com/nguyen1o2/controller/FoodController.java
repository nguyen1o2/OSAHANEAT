package com.nguyen1o2.controller;

import com.nguyen1o2.payload.ResponseData;
import com.nguyen1o2.service.imp.FileServiceImp;
import com.nguyen1o2.service.imp.FoodServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin("*")
@RestController
@RequestMapping("/food")
public class FoodController {
    @Autowired
    FileServiceImp fileServiceImp;

    @Autowired
    FoodServiceImp foodServiceImp;

    @PostMapping()
    public ResponseEntity<?> createRestaurant(@RequestParam MultipartFile file,
                                              @RequestParam String title,
                                              @RequestParam boolean isFreeShip,
                                              @RequestParam String timeShip,
                                              @RequestParam double price,
                                              @RequestParam int cate_id){
        ResponseData responseData = new ResponseData();
        boolean isSuccess = foodServiceImp.createFood(file,title,isFreeShip,timeShip,price,cate_id);
        responseData.setData(isSuccess);
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }
}
