package com.nhandev.changeanimal;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {
    RadioButton rdBird,rdPig,rdCat,rdRabbit,rdDog;
    RadioGroup rGAnimal;
    ImageView imAnimal;
    private void changeColor(RadioButton rdButton,boolean action)
    {
        if(action)
        {
            rdButton.setBackgroundColor(Color.parseColor("#2196F3"));
            rdButton.setTextColor(Color.parseColor("#FFFFFF"));
        }
        else
        {
            rdButton.setBackgroundColor(Color.parseColor("#FFFFFF"));
            rdButton.setTextColor(Color.parseColor("#000000"));
        }

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Anh Xa
        rdBird = findViewById(R.id.rbBird);
        rdRabbit = findViewById(R.id.rbRabbit);
        rdCat = findViewById(R.id.rbCat);
        rdDog = findViewById(R.id.rbDog);
        rdPig = findViewById(R.id.rbPig);
        rGAnimal = findViewById(R.id.grAnimal);
        imAnimal = findViewById(R.id.imAnimal);
        // Event
        rGAnimal.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId)
                {
                    case R.id.rbBird:
                        imAnimal.setBackgroundResource(R.drawable.bird);
                        changeColor(rdBird,true);
                        changeColor(rdCat,false);
                        changeColor(rdDog,false);
                        changeColor(rdRabbit,false);
                        changeColor(rdPig,false);
                        break;
                    case R.id.rbCat:
                        imAnimal.setBackgroundResource(R.drawable.cat);
                        changeColor(rdCat,true);
                        changeColor(rdBird,false);
                        changeColor(rdDog,false);
                        changeColor(rdRabbit,false);
                        changeColor(rdPig,false);
                        break;
                    case R.id.rbDog:
                        imAnimal.setBackgroundResource(R.drawable.dog);
                        changeColor(rdDog,true);
                        changeColor(rdCat,false);
                        changeColor(rdBird,false);
                        changeColor(rdRabbit,false);
                        changeColor(rdPig,false);
                        break;
                    case R.id.rbPig:
                        imAnimal.setBackgroundResource(R.drawable.pig);
                        changeColor(rdPig,true);
                        changeColor(rdCat,false);
                        changeColor(rdDog,false);
                        changeColor(rdRabbit,false);
                        changeColor(rdBird,false);
                        break;
                    case R.id.rbRabbit:
                        imAnimal.setBackgroundResource(R.drawable.rabbit);
                        changeColor(rdRabbit,true);
                        changeColor(rdCat,false);
                        changeColor(rdDog,false);
                        changeColor(rdBird,false);
                        changeColor(rdPig,false);
                        break;
                }
            }
        });

    }
}
