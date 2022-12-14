package com.example.mrcook.retrofot;

import com.example.mrcook.models.MealsList;

import java.util.Calendar;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {


    @GET("random.php")
    Call<MealsList> getRandomMeal();
}
