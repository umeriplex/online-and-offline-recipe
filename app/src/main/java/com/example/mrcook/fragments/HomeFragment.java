package com.example.mrcook.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.room.Room;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.mrcook.R;
import com.example.mrcook.activities.DetailsActivity;
import com.example.mrcook.databinding.FragmentHomeBinding;
import com.example.mrcook.models.MealsList;
import com.example.mrcook.retrofot.ApiClint;
import com.example.mrcook.retrofot.ApiInterface;
import com.example.mrcook.room.FavDB;
import com.example.mrcook.room.ListDao;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    FragmentHomeBinding binding;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);


        getRandomMeal();

        binding.sLauOut.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getRandomMeal();
                binding.sLauOut.setRefreshing(false);
            }
        });

        return binding.getRoot();
    }

    private void getRandomMeal() {
        binding.gifAnim.setVisibility(View.VISIBLE);
        ApiClint apiClint = new ApiClint();
        ApiInterface apiInterface = apiClint.getRetrofit().create(ApiInterface.class);

        apiInterface.getRandomMeal().enqueue(new Callback<MealsList>() {
            @Override
            public void onResponse(Call<MealsList> call, Response<MealsList> response) {
                if (response.body() != null) {
                    binding.gifAnim.setVisibility(View.GONE);
                    binding.ranCard.setVisibility(View.VISIBLE);
                    binding.swipeDown.setVisibility(View.VISIBLE);
                    List<MealsList.Meals> ran = new ArrayList<>();
                    ran = response.body().getMeals();
                    if (getActivity() != null) {
                        Glide.with(getActivity()).load(ran.get(0).getStrMealThumb()).into(binding.randomMealImg);
                    }
                    binding.ranName.setText(ran.get(0).getStrMeal());
                    binding.ranCountry.setText(ran.get(0).getStrArea());
                    binding.ranRecipe.setText(ran.get(0).getStrInstructions());

                    List<MealsList.Meals> finalRan = ran;
                    binding.ranCard.setOnClickListener(view -> {
                        Intent intent = new Intent(getActivity(), DetailsActivity.class);
                        intent.putExtra("name", finalRan.get(0).getStrMeal());
                        intent.putExtra("image", finalRan.get(0).getStrMealThumb());
                        intent.putExtra("cat", finalRan.get(0).getStrCategory());
                        intent.putExtra("location", finalRan.get(0).getStrArea());
                        intent.putExtra("inst", finalRan.get(0).getStrInstructions());
                        intent.putExtra("link", finalRan.get(0).getStrYoutube());
                        intent.putExtra("pid", finalRan.get(0).getIdMeal());
                        intent.putExtra("flag", "server");

                        List<String> ingList = new ArrayList<>();
                        ingList.add(finalRan.get(0).getStrIngredient1());
                        ingList.add(finalRan.get(0).getStrIngredient2());
                        ingList.add(finalRan.get(0).getStrIngredient3());
                        ingList.add(finalRan.get(0).getStrIngredient4());
                        ingList.add(finalRan.get(0).getStrIngredient5());
                        ingList.add(finalRan.get(0).getStrIngredient6());
                        ingList.add(finalRan.get(0).getStrIngredient7());
                        ingList.add(finalRan.get(0).getStrIngredient8());
                        ingList.add(finalRan.get(0).getStrIngredient9());
                        ingList.add(finalRan.get(0).getStrIngredient10());
                        ingList.add(finalRan.get(0).getStrIngredient11());
                        ingList.add(finalRan.get(0).getStrIngredient12());
                        ingList.add(finalRan.get(0).getStrIngredient13());
                        ingList.add(finalRan.get(0).getStrIngredient14());
                        ingList.add(finalRan.get(0).getStrIngredient15());
                        ingList.add(finalRan.get(0).getStrIngredient16());
                        ingList.add(finalRan.get(0).getStrIngredient17());
                        ingList.add(finalRan.get(0).getStrIngredient18());
                        ingList.add(finalRan.get(0).getStrIngredient19());
                        ingList.add(finalRan.get(0).getStrIngredient20());


                        List<String> measList = new ArrayList<>();
                        measList.add(finalRan.get(0).getStrMeasure1());
                        measList.add(finalRan.get(0).getStrMeasure2());
                        measList.add(finalRan.get(0).getStrMeasure3());
                        measList.add(finalRan.get(0).getStrMeasure4());
                        measList.add(finalRan.get(0).getStrMeasure5());
                        measList.add(finalRan.get(0).getStrMeasure6());
                        measList.add(finalRan.get(0).getStrMeasure7());
                        measList.add(finalRan.get(0).getStrMeasure8());
                        measList.add(finalRan.get(0).getStrMeasure9());
                        measList.add(finalRan.get(0).getStrMeasure10());
                        measList.add(finalRan.get(0).getStrMeasure11());
                        measList.add(finalRan.get(0).getStrMeasure12());
                        measList.add(finalRan.get(0).getStrMeasure13());
                        measList.add(finalRan.get(0).getStrMeasure14());
                        measList.add(finalRan.get(0).getStrMeasure15());
                        measList.add(finalRan.get(0).getStrMeasure16());
                        measList.add(finalRan.get(0).getStrMeasure17());
                        measList.add(finalRan.get(0).getStrMeasure18());
                        measList.add(finalRan.get(0).getStrMeasure19());
                        measList.add(finalRan.get(0).getStrMeasure20());

                        intent.putExtra("ingList", (Serializable) ingList);
                        intent.putExtra("measList", (Serializable) measList);

                        startActivity(intent);

                    });



                } else{
                    binding.gifAnim.setVisibility(View.VISIBLE);
                    binding.ranCard.setVisibility(View.GONE);
                    binding.swipeDown.setVisibility(View.GONE);
                    return;
                }
            }

            @Override
            public void onFailure(Call<MealsList> call, Throwable t) {
                try{
                    Toast.makeText(getActivity(), "Slow Internet", Toast.LENGTH_LONG).show();
                    binding.gifAnim.setVisibility(View.VISIBLE);
                    binding.ranCard.setVisibility(View.GONE);
                    binding.swipeDown.setVisibility(View.GONE);
                }catch (Exception e){
                    e.printStackTrace();
                    Log.e("slow Internet : ",e.getLocalizedMessage());
                }
            }
        });
    }


}