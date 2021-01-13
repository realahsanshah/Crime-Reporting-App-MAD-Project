package com.example.crimereportingapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.crimereportingapp.database.CrimeBaseHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CrimeLab {
    private static CrimeLab crimeLab;

    private final SQLiteDatabase mDatabase;

    List<Crime> crimes;

    private CrimeLab(Context context) {
        mDatabase = new CrimeBaseHelper(context.getApplicationContext()).getWritableDatabase();
        crimes = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            Crime c = new Crime();
            c.setTitle("Crime #" + (i + 1));
            c.setSolved(i % 3 == 0);
            c.setRequirePolice(i % 5 == 0);
            crimes.add(c);
        }

    }

    public static CrimeLab get(Context context) {
        if (crimeLab == null)
            crimeLab = new CrimeLab(context);
        return crimeLab;
    }

    public void addCrime(Crime c) {
        crimes.add(c);
    }

    public List<Crime> getCrimes() {
        return crimes;
    }

    public Crime getCrime(UUID id) {
        Log.i("id", id.toString());
        for (Crime crime : crimes) {
            if (crime.getId().equals(id)) {
                return crime;
            }
        }
        return null;
    }

}
