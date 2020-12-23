package com.example.crimereportingapp;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.UUID;

public class CrimeFragment extends Fragment {

    private Button dateButton;
    private CheckBox solvedCheckBox;
    private CheckBox policeRequiredCheckBox;
    private EditText crimeTitleText;
    private Crime crime;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        UUID crimeId = (UUID) getActivity().getIntent().getSerializableExtra(CrimeActivity.EXTRA_CRIME_ID);

        crime = CrimeLab.get(getActivity()).getCrime(crimeId);
        Log.i("crime", String.valueOf(crime == null));
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.fragment_crime, container, false);

        dateButton = view.findViewById(R.id.crime_button);
        dateButton.setText(crime.getDate().toString());
        dateButton.setEnabled(false);
        solvedCheckBox = view.findViewById(R.id.crime_solved);
        solvedCheckBox.setChecked(crime.isSolved());
        policeRequiredCheckBox = view.findViewById(R.id.police_required);
        policeRequiredCheckBox.setChecked(crime.isRequirePolice());
        solvedCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                crime.setSolved(isChecked);
            }
        });
        if (crime.isSolved())
            policeRequiredCheckBox.setVisibility(View.GONE);
        policeRequiredCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                crime.setRequirePolice(isChecked);
            }
        });
        crimeTitleText = view.findViewById(R.id.crime_title);
        crimeTitleText.setText(crime.getTitle());

        crimeTitleText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                crime.setTitle(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        return view;

    }
}
