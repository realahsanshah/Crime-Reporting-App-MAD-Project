package com.example.crimereportingapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.crimereportingapp.database.CrimeBaseHelper;
import com.example.crimereportingapp.database.CrimeDBSchema;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CrimeLab {
    private static CrimeLab crimeLab;

    private final SQLiteDatabase mDatabase;

//    List<Crime> crimes;

    private CrimeLab(Context context) {
        mDatabase = new CrimeBaseHelper(context.getApplicationContext()).getWritableDatabase();
//        crimes = new ArrayList<>();


//        for (int i = 0; i < 100; i++) {
//            Crime c = new Crime();
//            c.setTitle("Crime #" + (i + 1));
//            c.setSolved(i % 3 == 0);
//            c.setRequirePolice(i % 5 == 0);
//            crimes.add(c);
//        }

    }

    public static CrimeLab get(Context context) {
        if (crimeLab == null)
            crimeLab = new CrimeLab(context);
        return crimeLab;
    }

    private static ContentValues getContentValues(Crime crime) {
        ContentValues contentValues = new ContentValues();

        contentValues.put(CrimeDBSchema.CrimeTable.Columns.UUID, crime.getId().toString());
        contentValues.put(CrimeDBSchema.CrimeTable.Columns.TITLE, crime.getTitle());
        contentValues.put(CrimeDBSchema.CrimeTable.Columns.DATE, crime.getDate().getTime());
        contentValues.put(CrimeDBSchema.CrimeTable.Columns.SOLVED, crime.isSolved() ? 1 : 0);
        contentValues.put(CrimeDBSchema.CrimeTable.Columns.POLICEREQUIRED, crime.isRequirePolice() ? 1 : 0);

        return contentValues;
    }

    public void addCrime(Crime c) {
        mDatabase.insert(CrimeDBSchema.CrimeTable.NAME, null, getContentValues(c));
//        crimes.add(c);
    }

    public void updateCrime(Crime crime) {
        String uuid = crime.getId().toString();
        mDatabase.update(CrimeDBSchema.CrimeTable.NAME,
                getContentValues(crime),
                CrimeDBSchema.CrimeTable.Columns.UUID + "=?",
                new String[]{uuid});
    }

    public List<Crime> getCrimes() {
//        return crimes;
        return new ArrayList<>();
    }

    public Crime getCrime(UUID id) {
//        Log.i("id", id.toString());
//        for (Crime crime : crimes) {
//            if (crime.getId().equals(id)) {
//                return crime;
//            }
//        }
        return null;
    }


}
