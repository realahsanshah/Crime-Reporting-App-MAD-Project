package com.example.crimereportingapp;

import androidx.fragment.app.Fragment;


public class CrimeListActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new CrimeListFragment();
    }
}