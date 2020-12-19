package com.example.crimereportingapp;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CrimeLab {
    private static CrimeLab crimeLab;

    List<Crime> crimes;

    private CrimeLab(Context context){
        crimes=new ArrayList<Crime>();

        for(int i=0;i<30;i++){
            Crime c = new Crime();
            c.setTitle("Crime #" + i);
            c.setSolved(i % 3 == 0);
            c.setRequirePolice(i % 5 == 0);
            crimes.add(c);
        }

    }

    public static CrimeLab get(Context context) {
        if(crimeLab==null)
            crimeLab=new CrimeLab(context);
        return crimeLab;
    }

    public List<Crime> getCrimes() {
        return crimes;
    }

    public Crime getCrime(UUID id){
        for(Crime crime:crimes){
            if(crime.getId()==id){
                return crime;
            }
        }
        return null;
    }

}
