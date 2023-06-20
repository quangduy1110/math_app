package com.example.math_app.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.math_app.R;
import com.example.math_app.exam;
import com.example.math_app.kiemtra2;
import com.example.math_app.kiemtra3;
import com.example.math_app.kiemtra4;
import com.example.math_app.model.ListKiemTra;
import com.example.math_app.ontap;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ListKiemTraAdapter extends RecyclerView.Adapter<ListKiemTraAdapter.KiemtraViewHolder>{
    private List<ListKiemTra> kiemTraList;
    private Context context;

    public ListKiemTraAdapter(List<ListKiemTra> kiemTraList) {
        this.kiemTraList = kiemTraList;
        this.context = context;
    }

    @NonNull
    @Override
    public KiemtraViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_topic_exam, parent, false);
        return new KiemtraViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull KiemtraViewHolder holder, int position) {
        ListKiemTra kiemtra = kiemTraList.get(position);
        Picasso.get().load(kiemtra.getHinhanh()).into(holder.img_hinhanh);
        holder.tv_tenchude.setText(kiemtra.getTenchude());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int index =  holder.getAdapterPosition();
                switch (index){
                    case 0:
                        Intent intent1 = new Intent(view.getContext(), exam.class);
                        view.getContext().startActivity(intent1);
                        break;
                    case 1:
                        Intent intent2 = new Intent(view.getContext(), kiemtra2.class);
                        view.getContext().startActivity(intent2);
                        break;
                    case 2:
                        Intent intent3 = new Intent(view.getContext(), kiemtra3.class);
                        view.getContext().startActivity(intent3);
                    case 3:
                        Intent intent4 = new Intent(view.getContext(), kiemtra4.class);
                        view.getContext().startActivity(intent4);
                    case 4:
                        Intent intent5 = new Intent(view.getContext(), ontap.class);
                        view.getContext().startActivity(intent5);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return kiemTraList.size();
    }

    public class KiemtraViewHolder extends RecyclerView.ViewHolder{
        private ImageView img_hinhanh;
        private TextView tv_tenchude;

        public KiemtraViewHolder(@NonNull View itemView) {
            super(itemView);
            img_hinhanh = itemView.findViewById(R.id.img_hinhanh_kt);
            tv_tenchude = itemView.findViewById(R.id.tv_noidung_kt);

        }
    }
}
