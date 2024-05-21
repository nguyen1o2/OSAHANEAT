package com.nguyen1o2.service;

import com.nguyen1o2.entity.Category;
import com.nguyen1o2.entity.Food;
import com.nguyen1o2.entity.Restaurant;
import com.nguyen1o2.repository.FoodRepository;
import com.nguyen1o2.service.imp.FileServiceImp;
import com.nguyen1o2.service.imp.FoodServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.Date;
@Service
public class FoodService implements FoodServiceImp {
    @Autowired
    FileServiceImp fileServiceImp;

    @Autowired
    FoodRepository foodRepository;

    @Override
    public boolean createFood(MultipartFile file, String title, boolean isFreeShip, String timeShip, double price, int cate_id) {
        boolean isInsertSuccess = false;
        try{
            boolean isSaveFileSuccess = fileServiceImp.saveFile(file);
            if(isSaveFileSuccess){
                Food food = new Food();
                food.setTitle(title);
                food.setImage(file.getOriginalFilename());
                food.setTimeShip(timeShip);
                food.setPrice(price);
                food.setIsFreeShip(isFreeShip);
                Category category = new Category();
                category.setId(cate_id);
                food.setCategory(category);
                foodRepository.save(food);
                isInsertSuccess = true;
            }
        }catch (Exception e){
            System.out.println("Error create food: "+e.getMessage());
        }
        return isInsertSuccess;
    }
}
