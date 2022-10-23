package com.eceozbay.s.global_jeoit_ondilasyonu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivityAna extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_ana);
    }


    public  void manuel(View view){

        Intent intent=new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent);

    }

    public  void haritalarla(View view){

        Intent intent=new Intent(getApplicationContext(),MapsActivity.class);
        startActivity(intent);

    }

    public  void bilgilendirme(View view){

        Intent intent=new Intent(getApplicationContext(),MainActivityBilgilendirme.class);
        startActivity(intent);

    }

}
