package com.nguyen1o2.service;

import com.nguyen1o2.dto.CategoryDTO;
import com.nguyen1o2.dto.FoodDTO;
import com.nguyen1o2.dto.RestaurantDTO;
import com.nguyen1o2.entity.Food;
import com.nguyen1o2.entity.MenuRestaurant;
import com.nguyen1o2.entity.RatingRestaurant;
import com.nguyen1o2.entity.Restaurant;
import com.nguyen1o2.repository.RestaurantRepository;
import com.nguyen1o2.service.imp.FileServiceImp;
import com.nguyen1o2.service.imp.RestaurantServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class RestaurantService implements RestaurantServiceImp {
    @Autowired
    RestaurantRepository restaurantRepository;

    @Autowired
    FileServiceImp fileServiceImp;

    @Override
    public boolean insertRestaurant(MultipartFile file, String title, String subtitle, String description, boolean isFreeShip, String address, String openDate) {
        boolean isInsertSuccess = false;
        try{
            boolean isSaveFileSuccess = fileServiceImp.saveFile(file);
            if(isSaveFileSuccess){
                Restaurant restaurant = new Restaurant();
                restaurant.setTitle(title);
                restaurant.setSubTitle(subtitle);
                restaurant.setDescription(description);
                restaurant.setImage(file.getOriginalFilename());
                restaurant.setFreeShip(isFreeShip);
                restaurant.setAddress(address);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");
                Date openDate2 = simpleDateFormat.parse(openDate);
                restaurant.setOpenDate(openDate2);
                restaurantRepository.save(restaurant);
                isInsertSuccess = true;
            }
        }catch (Exception e){
            System.out.println("Error insert restaurant: "+e.getMessage());
        }

        return isInsertSuccess;
    }

    @Override
    public List<RestaurantDTO> getHomePageRestaurant() {
        PageRequest pageRequest = PageRequest.of(0,6);
        Page<Restaurant> listData = restaurantRepository.findAll(pageRequest);
        List<RestaurantDTO> restaurantDTOList = new ArrayList<>();
        for (Restaurant data: listData) {
            RestaurantDTO restaurantDTO = new RestaurantDTO();
            restaurantDTO.setId(data.getId());
            restaurantDTO.setImage(data.getImage());
            restaurantDTO.setTitle(data.getTitle());
            restaurantDTO.setSubTitle(data.getSubTitle());
            restaurantDTO.setFreeShip(data.isFreeShip());
            restaurantDTO.setRaiting(calculatorRating(data.getListRaitingRestaurant()));
            restaurantDTOList.add(restaurantDTO);
        }
        return restaurantDTOList;
    }


    public double calculatorRating(Set<RatingRestaurant> ratingRestaurants){
        double totalPoint = 0;
        for (RatingRestaurant data : ratingRestaurants) {
            totalPoint+= data.getRatePoint();
        }
        return totalPoint/ ratingRestaurants.size();
    }

    @Override
    public RestaurantDTO getDetailRestaurant(int id) {
        Optional<Restaurant> restaurant = restaurantRepository.findById(id);
        RestaurantDTO restaurantDTO = new RestaurantDTO();
        if(restaurant.isPresent()){
            List<CategoryDTO> listCategoryDTO = new ArrayList<>();
            restaurantDTO.setDescription(restaurant.get().getDescription());
            restaurantDTO.setTitle(restaurant.get().getTitle());
            restaurantDTO.setSubTitle(restaurant.get().getSubTitle());
            restaurantDTO.setRaiting(calculatorRating(restaurant.get().getListRaitingRestaurant()));
            restaurantDTO.setImage(restaurant.get().getImage());
            restaurantDTO.setAddress(restaurant.get().getAddress());
            restaurantDTO.setOpenDate(restaurant.get().getOpenDate());
            restaurantDTO.setFreeShip(restaurant.get().isFreeShip());

            for (MenuRestaurant menuRestaurant : restaurant.get().getListMenuRestaurant()) {
                List<FoodDTO> foodDTO = new ArrayList<>();
                CategoryDTO categoryDTO = new CategoryDTO();
                categoryDTO.setName(menuRestaurant.getCategory().getNameCate());

                for (Food food : menuRestaurant.getCategory().getListFood()) {
                    FoodDTO foodDTO1 = new FoodDTO();
                    foodDTO1.setTitle(food.getTitle());
                    foodDTO1.setImage(food.getImage());
                    foodDTO1.setFreeShip(food.getIsFreeShip());
                    foodDTO.add(foodDTO1);
                }
                categoryDTO.setFoods(foodDTO);
                listCategoryDTO.add(categoryDTO);
            }
            restaurantDTO.setListCategory(listCategoryDTO);
        }
        return restaurantDTO;
    }
}
