package com.example.math_app.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.math_app.R;
import com.example.math_app.model.Tinhnham;

import java.util.List;

public class TinhnhamAdapter extends RecyclerView.Adapter<TinhnhamAdapter.TinhnhamViewHolder>{
    private List<Tinhnham> tinhnhamList;

    public TinhnhamAdapter(List<Tinhnham> tinhnhamList) {
        this.tinhnhamList = tinhnhamList;
    }

    @NonNull
    @Override
    public TinhnhamViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.question, parent, false);
        return new TinhnhamViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TinhnhamViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return tinhnhamList.size();
    }

    public class TinhnhamViewHolder extends RecyclerView.ViewHolder {

        public TinhnhamViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

}
