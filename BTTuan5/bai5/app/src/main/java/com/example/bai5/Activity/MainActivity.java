package com.example.bai5.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.bai5.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnMonAn,btnThucUong;
    TextView tvResult;
    public static String kq = "";

    @Override
    protected void onResume() {
        super.onResume();
        if(!kq.isEmpty())
        {
            tvResult.setText(kq);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Anh Xa
        btnMonAn = findViewById(R.id.btnSelectThucAn);
        btnThucUong = findViewById(R.id.btnSelectDoUong);
        tvResult = findViewById(R.id.tvSelectedFood);

        btnThucUong.setOnClickListener(this);
        btnMonAn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id)
        {
            case R.id.btnSelectDoUong:
                Intent intent = new Intent(MainActivity.this,ThucUong.class);
                startActivity(intent);
                break;
            case R.id.btnSelectThucAn:
                Intent intent2 = new Intent(MainActivity.this,MonAn.class);
                startActivity(intent2);
                break;
        }
    }
}
