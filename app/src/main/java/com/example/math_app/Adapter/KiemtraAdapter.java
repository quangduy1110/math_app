package com.example.math_app.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.math_app.R;
import com.example.math_app.model.Kiemtra;

import java.util.List;

public class KiemtraAdapter extends RecyclerView.Adapter<KiemtraAdapter.KiemtraViewHolder>{
    private List<Kiemtra> kiemtraList;

    public KiemtraAdapter(List<Kiemtra> kiemtraList) {
        this.kiemtraList = kiemtraList;
    }

    @NonNull
    @Override
    public KiemtraViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.exam, parent, false);
        return new KiemtraViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull KiemtraViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return kiemtraList.size();
    }

    public class KiemtraViewHolder extends RecyclerView.ViewHolder{

        public KiemtraViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
