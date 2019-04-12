package com.example.bai3.Acvitity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import com.example.bai3.Adapter.CauThuAdapter;
import com.example.bai3.Model.CauThu;
import com.example.bai3.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        List<CauThu> CauThuList = new ArrayList<>();
        CauThuList.add(new CauThu(R.drawable.nguoi1,"PeLe","Tuoi: 72",R.drawable.brazil));
        CauThuList.add(new CauThu(R.drawable.nguoi2,"Diego Maradona","Tuoi: 52",R.drawable.argentina));
        CauThuList.add(new CauThu(R.drawable.nguoi3,"Johan Cruyff","Tuoi: 65",R.drawable.netherlands));
        CauThuList.add(new CauThu(R.drawable.nguoi4,"Franz Beckenbauer","Tuoi: 67",R.drawable.germany));
        CauThuList.add(new CauThu(R.drawable.nguoi5,"Michal Platini","Tuoi: 57",R.drawable.france));
        CauThuList.add(new CauThu(R.drawable.nguoi6,"Ronaldo De Lima","Tuoi: 35",R.drawable.brazil));
        listView = findViewById(R.id.listCauThu);
        CauThuAdapter cauThuAdapter = new CauThuAdapter(getApplicationContext(),R.layout.itemcauthu,CauThuList);
        listView.setAdapter(cauThuAdapter);
    }
}
