package com.nguyen1o2.service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.nguyen1o2.dto.CategoryDTO;
import com.nguyen1o2.dto.FoodDTO;
import com.nguyen1o2.entity.Category;
import com.nguyen1o2.entity.Food;
import com.nguyen1o2.repository.CategoryRepository;
import com.nguyen1o2.service.imp.CategoryServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
@Service
public class CategoryService implements CategoryServiceImp {
    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    RedisTemplate redisTemplate;

    private Gson gson = new Gson();

//    @Cacheable("CategoryCache")
    @Override
    public List<CategoryDTO> getCategoryHomePage() {

        String dataRedis = (String) redisTemplate.opsForValue().get("category");
        List<CategoryDTO> categoryDTOS = new ArrayList<>();
        if(dataRedis == null){
            System.out.println("Chua co data");
            PageRequest pageRequest = PageRequest.of(0,2);
            Page<Category> categories = categoryRepository.findAll(pageRequest);
            for (Category category : categories) {
                CategoryDTO categoryDTO = new CategoryDTO();
                categoryDTO.setName(category.getNameCate());

                List<FoodDTO> foodDTOS = new ArrayList<>();
                for (Food food: category.getListFood()) {
                    FoodDTO foodDTO = new FoodDTO();
                    foodDTO.setTitle(food.getTitle());
                    foodDTO.setFreeShip(food.getIsFreeShip());
                    foodDTO.setImage(food.getImage());

                    foodDTOS.add(foodDTO);
                }
                categoryDTO.setFoods(foodDTOS);
                categoryDTOS.add(categoryDTO);
            }

            String dataJson = gson.toJson(categoryDTOS);
            redisTemplate.opsForValue().set("category",dataJson);
        }
        else {
            Type listType = new TypeToken<List<CategoryDTO>>(){}.getType();
            categoryDTOS = gson.fromJson(dataRedis,listType);
            System.out.println("Da co data");
        }

        return categoryDTOS;
    }
}
