package com.example.math_app.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.math_app.R;
import com.example.math_app.model.LessonModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ImageAdapter extends PagerAdapter {

        private ArrayList<LessonModel> lessonList;
        private LayoutInflater layoutInflater;
        private Context context;



    public ImageAdapter(Context context) {
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
        lessonList = new ArrayList<>();
    }


    public void setLessonList(ArrayList<LessonModel> lessonList) {
        this.lessonList = lessonList;
    }

    @Override
    public int getCount() {
        return lessonList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = layoutInflater.inflate(R.layout.item_image, container, false);
        ImageView imageView = view.findViewById(R.id.img_hinhanh);
        String imgUrl = lessonList.get(position).getImgUrl();
        Log.e("imgurl",imgUrl);
        Picasso.get().load(lessonList.get(position).getImgUrl()).into(imageView);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
