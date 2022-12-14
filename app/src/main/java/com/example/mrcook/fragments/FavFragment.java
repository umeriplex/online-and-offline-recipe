package com.example.mrcook.fragments;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.room.Room;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mrcook.R;
import com.example.mrcook.activities.DetailsActivity;
import com.example.mrcook.adapters.FavAdapter;
import com.example.mrcook.databinding.FragmentFavBinding;
import com.example.mrcook.databinding.FragmentHomeBinding;
import com.example.mrcook.room.FavDB;
import com.example.mrcook.room.FavList;
import com.example.mrcook.room.ListDao;

import java.util.ArrayList;
import java.util.List;

public class FavFragment extends Fragment {

    FragmentFavBinding binding;
    FavAdapter adapter;
    List<FavList> list;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentFavBinding.inflate(inflater, container, false);

        binding.favSlide.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getData();
                binding.favSlide.setRefreshing(false);
            }
        });


        getData();

        return binding.getRoot();
    }

    private void getData() {
        FavDB db = Room.databaseBuilder(getActivity(), FavDB.class, "fav_db").fallbackToDestructiveMigration().allowMainThreadQueries().build();
        ListDao dao = db.listDao();
        list = new ArrayList<>();
        list = dao.getAll();
        if (list.isEmpty()) {
            binding.textView2.setVisibility(View.VISIBLE);
        } else {
            adapter = new FavAdapter(getActivity(), list);
            binding.favRecView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
            binding.favRecView.setAdapter(adapter);
        }

    }


}