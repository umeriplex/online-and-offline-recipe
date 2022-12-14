package com.example.mrcook.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mrcook.R;
import com.example.mrcook.activities.DetailsActivity;
import com.example.mrcook.databinding.FavItemBinding;
import com.example.mrcook.room.FavList;

import java.io.Serializable;
import java.util.List;

public class FavAdapter extends RecyclerView.Adapter<FavAdapter.FavViewHolder> {

    Context context;

    List<FavList> list;

    public FavAdapter(Context context, List<FavList> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public FavViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new FavViewHolder(LayoutInflater.from(context).inflate(R.layout.fav_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull FavViewHolder holder, int position) {

        Glide.with(context).load(list.get(position).getImage()).into(holder.binding.favMealImg);
        holder.binding.favName.setText(list.get(position).getName());

        holder.binding.favCard.setOnClickListener(v->{
            Intent intent = new Intent(context, DetailsActivity.class);
            intent.putExtra("name",list.get(position).getName());
            intent.putExtra("cat",list.get(position).getCat());
            intent.putExtra("loc",list.get(position).getLoc());
            intent.putExtra("link",list.get(position).getLink());
            intent.putExtra("image",list.get(position).getImage());
            intent.putExtra("inst",list.get(position).getIns());
            intent.putExtra("pID",list.get(position).getId());
            intent.putExtra("flag","database");
            intent.putExtra("ingList", (Serializable) list.get(position).getIngList());
            intent.putExtra("mesList", (Serializable) list.get(position).getMesList());
            context.startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        if (list.isEmpty())
            return 0;
        else
            return list.size();
    }

    class FavViewHolder extends RecyclerView.ViewHolder {

        FavItemBinding binding;

        public FavViewHolder(@NonNull View itemView) {
            super(itemView);

            binding = FavItemBinding.bind(itemView);
        }
    }
}
