package com.example.bai2.Acvitity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.bai2.Adapter.RauAdapter;
import com.example.bai2.Model.RauTraiCay;
import com.example.bai2.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        List<RauTraiCay> rauTraiCayList = new ArrayList<>();
        rauTraiCayList.add(new RauTraiCay(R.drawable.strawberry,"Trái Dâu","Dâu đấy"));
        rauTraiCayList.add(new RauTraiCay(R.drawable.banana,"Trái Chuối","Chuối đấy"));
        rauTraiCayList.add(new RauTraiCay(R.drawable.orange,"Trái Cam","Trái Cam"));
        rauTraiCayList.add(new RauTraiCay(R.drawable.diet,"Rau Củ Quả","Tùm lum trái"));
        listView = findViewById(R.id.listRau);
        RauAdapter countryAdapter = new RauAdapter(getApplicationContext(),R.layout.itemsrau,rauTraiCayList);
        listView.setAdapter(countryAdapter);
    }
}
