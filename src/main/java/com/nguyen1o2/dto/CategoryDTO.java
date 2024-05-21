package com.nguyen1o2.dto;

import java.util.List;

public class CategoryDTO {
    private String name;
    private List<FoodDTO> foods;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<FoodDTO> getFoods() {
        return foods;
    }

    public void setFoods(List<FoodDTO> foods) {
        this.foods = foods;
    }
}
