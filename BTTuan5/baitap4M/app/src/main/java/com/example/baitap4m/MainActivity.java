package com.example.baitap4m;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    private ListView listView;
    ArrayList<SocialNetwork> data = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setControl();
        setEvent();
    }

    private void setEvent(){
        KhoiTao();
        SocialNetworkAdapter adapter = new SocialNetworkAdapter( this,
                R.layout.listview_item_row, data);
        listView.setAdapter(adapter);
    }

    private void setControl(){
        listView = (ListView) findViewById(R.id.ListView);
    }

    void KhoiTao() {

        data.add(new SocialNetwork(R.drawable.facebook,  "Facebook"));
        data.add(new SocialNetwork(R.drawable.images,    "Linkedin"));
        data.add(new SocialNetwork(R.drawable.msn,   "MSN"));
        data.add(new SocialNetwork(R.drawable.skype,     "Skype"));
        data.add(new SocialNetwork(R.drawable.yahoo,     "Yahoo"));
    }
}