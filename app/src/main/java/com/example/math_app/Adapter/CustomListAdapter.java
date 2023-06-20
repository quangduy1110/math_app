package com.example.math_app.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.math_app.R;
import com.example.math_app.model.ListItem;

import java.util.List;

public class CustomListAdapter extends ArrayAdapter<ListItem> {
    private Context context;
    private int layoutResourceId;
    private List<ListItem> data;

    public CustomListAdapter(Context context, int layoutResourceId, List<ListItem> data) {
        super(context, layoutResourceId, data);
        this.context = context;
        this.layoutResourceId = layoutResourceId;
        this.data = data;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            convertView = inflater.inflate(layoutResourceId, parent, false);
        }

        ImageView imageView = convertView.findViewById(R.id.img_item_video);
        TextView textView = convertView.findViewById(R.id.tv_item_video);

        ListItem item = data.get(position);
        imageView.setImageResource(item.getImageResId());
        textView.setText(item.getItemName());

        return convertView;
    }
}
