package com.example.crimereportingapp;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

public class CrimeLab {
    private static CrimeLab crimeLab;

    List<Crime> crimes;

    private CrimeLab(Context context){
        crimes=new ArrayList<Crime>();
    }

    public static CrimeLab get(Context context) {
        if(crimeLab==null)
            crimeLab=new CrimeLab(context);
        return crimeLab;
    }

    public List<Crime> getCrimes() {
        return crimes;
    }
}
