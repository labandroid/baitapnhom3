package com.example.bai5.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.bai5.Adapter.CustomAdapter;
import com.example.bai5.Model.CustomItem;
import com.example.bai5.R;

import java.util.ArrayList;
import java.util.List;

public class ThucUong extends AppCompatActivity {
    CustomAdapter customAdapter;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thuc_uong);
        final List<CustomItem> itemList = new ArrayList<>();
        listView = findViewById(R.id.listThucUong);
        itemList.add(new CustomItem(R.drawable.coca,"Coca"));
        itemList.add(new CustomItem(R.drawable.pepsi,"Pepsi"));
        itemList.add(new CustomItem(R.drawable.trasua,"Trà Sửa"));
        customAdapter = new CustomAdapter(getApplicationContext(),R.layout.itemcustom,itemList);
        listView.setAdapter(customAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CustomItem customItem = itemList.get(position);
                MainActivity.kq=MainActivity.kq+" - ";
                MainActivity.kq += customItem.getName();
                finish();
            }
        });
    }
}
