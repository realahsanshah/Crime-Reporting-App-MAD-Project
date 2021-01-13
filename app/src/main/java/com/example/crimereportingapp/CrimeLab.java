package com.example.crimereportingapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.crimereportingapp.database.CrimeBaseHelper;
import com.example.crimereportingapp.database.CrimeCursorWrapper;
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
                CrimeDBSchema.CrimeTable.Columns.UUID + "= ?",
                new String[]{uuid});
    }

    public CrimeCursorWrapper queryCrimes(String whereClause, String[] whereArgs) {
        Cursor cursor = mDatabase.query(CrimeDBSchema.CrimeTable.NAME,
                null,
                whereClause, whereArgs,
                null, null, null);

        return new CrimeCursorWrapper(cursor);

    }

    public List<Crime> getCrimes() {
//        return crimes;
        List<Crime> crimes = new ArrayList<>();

        CrimeCursorWrapper cursor = queryCrimes(null, null);

        try {
            cursor.moveToFirst();

            while (!cursor.isAfterLast()) {
                crimes.add(cursor.getCrime());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cursor.close();
        }
        return crimes;
    }

    public Crime getCrime(UUID id) {

        String whereClause = CrimeDBSchema.CrimeTable.Columns.UUID + "= ?";
        String[] whereArgs = new String[]{id.toString()};

        CrimeCursorWrapper cursor = queryCrimes(whereClause, whereArgs);

        try {
            if (cursor.getCount() == 0)
                return null;

            cursor.moveToFirst();
            return cursor.getCrime();

        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            cursor.close();
        }

        return null;
    }


}
