package com.example.math_app.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.math_app.R;
import com.example.math_app.model.Dovui;

import java.util.List;

public class DovuiAdapter extends RecyclerView.Adapter<DovuiAdapter.DoVuiViewHolder>{
    private List<Dovui> dovuiList;

    public DovuiAdapter(List<Dovui> dovuiList) {
        this.dovuiList = dovuiList;
    }

    @NonNull
    @Override
    public DoVuiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.question, parent, false);
        return new DoVuiViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DoVuiViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return dovuiList.size();
    }

    public class DoVuiViewHolder extends RecyclerView.ViewHolder{

        public DoVuiViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
