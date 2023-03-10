package com.yancy.usodesensores;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    Button proxi, lumi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        proxi = findViewById(R.id.btnProximidad);
        proxi.setOnClickListener(this);
        lumi = findViewById(R.id.btnLuminosidad);
        lumi.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id==R.id.btnProximidad) {
            Intent intent = new Intent(MainActivity.this, SensorProximidad.class);
            startActivity(intent);
        }else if(id==R.id.btnLuminosidad){
            Intent intent = new Intent(MainActivity.this, SensorLuminosidad.class);
            startActivity(intent);
        }
    }
}