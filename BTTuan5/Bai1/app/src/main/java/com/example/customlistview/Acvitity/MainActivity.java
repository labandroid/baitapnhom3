package com.example.customlistview.Acvitity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.customlistview.Adapter.CountryAdapter;
import com.example.customlistview.Model.Country;
import com.example.customlistview.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        List<Country> countryList = new ArrayList<>();
        countryList.add(new Country(R.drawable.vietnam,"Việt Nam","Popular : 90Tr"));
        countryList.add(new Country(R.drawable.america,"America","Popular: 300Tr"));
        countryList.add(new Country(R.drawable.korea,"Korea","Popular: 70 Tr"));
        listView = findViewById(R.id.listViewCountry);
        CountryAdapter countryAdapter = new CountryAdapter(getApplicationContext(),R.layout.itemcountry,countryList);
        listView.setAdapter(countryAdapter);

    }
}
