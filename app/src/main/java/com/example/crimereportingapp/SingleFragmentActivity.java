package com.example.crimereportingapp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public abstract class SingleFragmentActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        FragmentManager fragmentManager=getSupportFragmentManager();

        Fragment fragment=fragmentManager.findFragmentById(R.id.fragment_container);

        if(fragment==null){
            fragment=createFragment();
            fragmentManager.beginTransaction().replace(R.id.fragment_container,fragment).commit();
        }
    }

    protected abstract Fragment createFragment();
}
