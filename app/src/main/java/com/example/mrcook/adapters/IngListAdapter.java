package com.example.mrcook.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mrcook.R;
import com.example.mrcook.databinding.IngListItemBinding;

import java.util.List;

public class IngListAdapter extends RecyclerView.Adapter<IngListAdapter.IngListViewHolder>{

    Context context;
    List<String> list;
    List<String> list2;

    public IngListAdapter(Context context, List<String> list, List<String> list2) {
        this.context = context;
        this.list = list;
        this.list2 = list2;
    }

    @NonNull
    @Override
    public IngListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new IngListViewHolder(LayoutInflater.from(context).inflate(R.layout.ing_list_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull IngListViewHolder holder, int position) {

        try{
            for(int i = 0; i <list.size(); i++){
                if (list.get(position).isEmpty()){
                    continue;
                }
                holder.binding.ingImg.setText(list.get(position));
            }

            for(int i = 0; i <list2.size(); i++){
                if (list2.get(position).isEmpty()){
                    continue;
                }
                holder.binding.ingMeas.setText(list2.get(position));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class IngListViewHolder extends RecyclerView.ViewHolder {
        IngListItemBinding binding;
        public IngListViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = IngListItemBinding.bind(itemView);
        }
    }
}
