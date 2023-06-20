package com.example.math_app;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.math_app.Adapter.CustomListAdapter;
import com.example.math_app.model.ListItem;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class list_video extends AppCompatActivity {
    private ListView listView;

    private VideoView videoView;
    private EditText edt_timkiem;
    private Button bt_timkiem;
    private List<ListItem> itemList = new ArrayList<>();
    List<ListItem> list = new ArrayList<>();
    private CustomListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_video);
        listView = findViewById(R.id.list_video);
        list.add(new ListItem(R.drawable.tapdem, "Dạy bé đếm số từ 1 đến 100", R.raw.tapdem1den100));
        list.add(new ListItem(R.drawable.hinhhoc, "Hình học cơ bản", R.raw.hinhhoccoban));
        list.add(new ListItem(R.drawable.tinhnham, "Học làm phép trừ", R.raw.toantrulop1));
        list.add(new ListItem(R.drawable.phepcongtoanlop1, "Học làm phép cộng", R.raw.phepconglop1));
        list.add(new ListItem(R.drawable.sotronchuc, "Số tròn chục", R.raw.tronchuc));
        list.add(new ListItem(R.drawable.sosanh, "Dấu nhỏ hơn, dấu lớn hơn, dấu bằng", R.raw.sosanh));
        list.add(new ListItem(R.drawable.demso, "Ta cùng tập đếm số", R.raw.tacungdemso));
        list.add(new ListItem(R.drawable.toancong20, "Phép toán cộng trừ trong phạm vi 20", R.raw.congpv20));
        list.add(new ListItem(R.drawable.cong100, "Phép toán cộng trong phạm vi 100", R.raw.cong100));



        itemList.addAll(list);
         adapter = new CustomListAdapter(this, R.layout.item_list_video, itemList);
        ListView listView = findViewById(R.id.list_video);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int videoResId = itemList.get(position).getVideoResId();
                String videoPath = "android.resource://" + getPackageName() + "/" + videoResId;
                Intent intent = new Intent(list_video.this, video.class);
                intent.putExtra("videoPath", videoPath);
                startActivity(intent);

            }
        });
        edt_timkiem = findViewById(R.id.edt_timkiem);
        bt_timkiem = findViewById(R.id.bt_timkiem);
        bt_timkiem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String searchText = edt_timkiem.getText().toString().toLowerCase(Locale.getDefault());
                filter(searchText);
            }
        });
    }
    public void filter(String query) {
        query = query.toLowerCase(Locale.getDefault());
        if (query.isEmpty()) {
            itemList.addAll(list);

        } else {
            itemList.clear();
            for (ListItem item : list) {
                if (item.getItemName().toLowerCase(Locale.getDefault()).contains(query)) {
                    itemList.add(item);
                }
            }
        }

        if(itemList.size() >0){
            adapter.notifyDataSetChanged();
        }
    }
}