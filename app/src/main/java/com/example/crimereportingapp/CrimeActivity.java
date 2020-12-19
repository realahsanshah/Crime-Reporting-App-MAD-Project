package com.example.crimereportingapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

public class CrimeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crime);

        FragmentManager fragmentManager=getSupportFragmentManager();

        Fragment fragment=fragmentManager.findFragmentById(R.id.fragment_container);

        if(fragment==null){
            fragment=new CrimeFragement();
            fragmentManager.beginTransaction().replace(R.id.fragment_container,fragment).commit();
        }
    }
}