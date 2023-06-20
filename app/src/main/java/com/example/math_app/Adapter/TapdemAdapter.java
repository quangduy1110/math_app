package com.example.math_app.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.math_app.R;
import com.example.math_app.model.Tapdem;

import java.util.List;

public class TapdemAdapter extends RecyclerView.Adapter<TapdemAdapter.TapdemViewHolder> {
    private List<Tapdem> tapdemList;

    public TapdemAdapter(List<Tapdem> tapdemList) {
        this.tapdemList = tapdemList;
    }

    @NonNull
    @Override
    public TapdemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.question,parent,false);
        return new TapdemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TapdemViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return tapdemList.size();
    }

    public class TapdemViewHolder extends RecyclerView.ViewHolder{
        public TapdemViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
