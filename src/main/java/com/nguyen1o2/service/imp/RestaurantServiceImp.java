package com.nguyen1o2.service.imp;

import com.nguyen1o2.dto.RestaurantDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


public interface RestaurantServiceImp {
    boolean insertRestaurant(MultipartFile file,
                             String title,
                             String  subtitle,
                             String description,
                             boolean  isFreeShip,
                             String address,
                             String openDate);
    List<RestaurantDTO> getHomePageRestaurant();
    RestaurantDTO getDetailRestaurant(int id);
}
