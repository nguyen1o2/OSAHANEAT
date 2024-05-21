package com.nguyen1o2.service.imp;

import org.springframework.web.multipart.MultipartFile;

public interface FoodServiceImp {
    boolean createFood(MultipartFile file, String title, boolean isFreeShip, String timeShip, double price, int cate_id);
}
