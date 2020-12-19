package com.example.crimereportingapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class CrimeFragement extends Fragment {

    private Button dateButton;
    private CheckBox solvedCheckBox;
    private EditText crimeTitleText;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        super.onCreateView(inflater, container, savedInstanceState);

        View view=inflater.inflate(R.layout.fragement_crime,container,false);

        dateButton=(Button)view.findViewById(R.id.crime_button);
        solvedCheckBox=(CheckBox)view.findViewById(R.id.crime_solved);
        crimeTitleText=(EditText)view.findViewById(R.id.crime_title);

        return view;

    }
}
