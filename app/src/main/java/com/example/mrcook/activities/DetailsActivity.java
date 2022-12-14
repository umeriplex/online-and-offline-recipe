package com.example.mrcook.activities;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.room.Room;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.mrcook.R;
import com.example.mrcook.adapters.FavAdapter;
import com.example.mrcook.adapters.IngListAdapter;
import com.example.mrcook.databinding.ActivityDetailsBinding;
import com.example.mrcook.fragments.FavFragment;
import com.example.mrcook.room.FavDB;
import com.example.mrcook.room.FavList;
import com.example.mrcook.room.ListDao;
import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ScheduledExecutorService;

public class DetailsActivity extends AppCompatActivity {

    ActivityDetailsBinding binding;
    String flag;
    IngListAdapter adapter;
    private InterstitialAd mInterstitialAd;

    private ScheduledExecutorService scheduler;
    private boolean isVisible;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolB);
        //AdRequest adRequest = new AdRequest.Builder().build();
        setAd();

        goBack();

        flag = getIntent().getStringExtra("flag");

        if (flag.equalsIgnoreCase("server")) {
            getDataFromIntent();
            saveToFav();
        }

        if (flag.equalsIgnoreCase("database")) {
            getDataFromDB();
            binding.detDelBtn.setOnClickListener(v -> {
                delByID();
                onBackPressed();
            });

        }
    }

    public void goBack() {
        binding.toolB.setNavigationIcon(getResources().getDrawable(R.drawable.ic_back));
        binding.toolB.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void getDataFromIntent() {
        String name = getIntent().getStringExtra("name");
        String image = getIntent().getStringExtra("image");
        String cat = getIntent().getStringExtra("cat");
        String location = getIntent().getStringExtra("location");
        String inst = getIntent().getStringExtra("inst");
        String link = getIntent().getStringExtra("link");

        binding.videoBtn.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(link));
            startActivity(intent);
        });

        ArrayList<String> list = (ArrayList<String>) getIntent().getSerializableExtra("ingList");
        ArrayList<String> list2 = (ArrayList<String>) getIntent().getSerializableExtra("measList");

        binding.detCat.setText(cat);
        binding.detLocation.setText(location);
        Glide.with(this).load(image).into(binding.detImg);
        binding.detHeader.setTitle(name);
        binding.detRecipe.setText(inst);
        Log.e("data", String.valueOf(list));


        binding.detIngRecview.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true));
        adapter = new IngListAdapter(this, list, list2);
        binding.detIngRecview.setAdapter(adapter);
    }

    private void getDataFromDB() {
        binding.detSaveBtn.setVisibility(View.GONE);
        binding.detCheckBtn.setVisibility(View.GONE);
        binding.detDelBtn.setVisibility(View.VISIBLE);
        String name = getIntent().getStringExtra("name");
        String image = getIntent().getStringExtra("image");
        String cat = getIntent().getStringExtra("cat");
        String location = getIntent().getStringExtra("loc");
        String inst = getIntent().getStringExtra("inst");
        String link = getIntent().getStringExtra("link");

        binding.videoBtn.setOnClickListener(v -> {
            try {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(link));
                startActivity(intent);
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(this, "video not available :(", Toast.LENGTH_SHORT).show();
            }
        });

        ArrayList<String> list = (ArrayList<String>) getIntent().getSerializableExtra("ingList");
        ArrayList<String> list2 = (ArrayList<String>) getIntent().getSerializableExtra("mesList");

        binding.detCat.setText(cat);
        binding.detLocation.setText(location);
        Glide.with(this).load(image).into(binding.detImg);
        binding.detHeader.setTitle(name);
        binding.detRecipe.setText(inst);
        Log.e("data", String.valueOf(list));


        binding.detIngRecview.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true));
        adapter = new IngListAdapter(this, list, list2);
        binding.detIngRecview.setAdapter(adapter);
    }

    private void saveToFav() {

        String name = getIntent().getStringExtra("name");
        String image = getIntent().getStringExtra("image");
        String cat = getIntent().getStringExtra("cat");
        String location = getIntent().getStringExtra("location");
        String inst = getIntent().getStringExtra("inst");
        String link = getIntent().getStringExtra("link");
        String pid = getIntent().getStringExtra("pid");
        int pidInt = Integer.parseInt(pid);
        ArrayList<String> list = (ArrayList<String>) getIntent().getSerializableExtra("ingList");
        ArrayList<String> list2 = (ArrayList<String>) getIntent().getSerializableExtra("measList");


        binding.detSaveBtn.setOnClickListener(v -> {
            FavDB db = Room.databaseBuilder(DetailsActivity.this, FavDB.class, "fav_db").fallbackToDestructiveMigration().allowMainThreadQueries().build();
            ListDao dao = db.listDao();

            Boolean check = dao.is_exists(pidInt);
            if (check == false) {
                dao.insertRecord(new FavList(0, image, name, cat, location, inst, list, list2, link, pidInt));
                Toast.makeText(this, "Save to favorites", Toast.LENGTH_LONG).show();
                binding.detSaveBtn.setVisibility(View.GONE);
                binding.detCheckBtn.setVisibility(View.VISIBLE);
            } else {
                Toast.makeText(this, "already in favorites", Toast.LENGTH_LONG).show();
                return;
            }


        });


    }

    private void delByID() {
        int id = getIntent().getIntExtra("pID",0);
        FavDB db = Room.databaseBuilder(DetailsActivity.this, FavDB.class, "fav_db").fallbackToDestructiveMigration().allowMainThreadQueries().build();
        ListDao dao = db.listDao();
        Toast.makeText(this, "ID : "+id, Toast.LENGTH_LONG).show();
        dao.deleteById(id);
    }

    private void setAd(){
        InterstitialAd.load(this,"ca-app-pub-3940256099942544/1033173712", MainActivity.adRequest,
                new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                        mInterstitialAd = interstitialAd;
                        mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                            @Override
                            public void onAdClicked() {
                                super.onAdClicked();
                            }

                            @Override
                            public void onAdDismissedFullScreenContent() {
                                super.onAdDismissedFullScreenContent();
                            }

                            @Override
                            public void onAdFailedToShowFullScreenContent(@NonNull AdError adError) {
                                super.onAdFailedToShowFullScreenContent(adError);
                            }

                            @Override
                            public void onAdImpression() {
                                super.onAdImpression();
                            }

                            @Override
                            public void onAdShowedFullScreenContent() {
                                super.onAdShowedFullScreenContent();
                                mInterstitialAd = null;
                            }
                        });
                    }
                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        Log.d("Error : ", loadAdError.toString());
                        mInterstitialAd = null;
                    }
                });

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (mInterstitialAd!=null)
                    mInterstitialAd.show(DetailsActivity.this);
                else
                    Log.e("Ad Pending : ","Ad is not ready yet!!");
            }
        },10000);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mInterstitialAd = null;
    }
}