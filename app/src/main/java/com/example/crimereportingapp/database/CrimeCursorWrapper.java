package com.example.crimereportingapp.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.example.crimereportingapp.Crime;

import java.util.Date;
import java.util.UUID;

public class CrimeCursorWrapper extends CursorWrapper {

    public CrimeCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Crime getCrime() {
        String uuidString = getString(getColumnIndex(CrimeDBSchema.CrimeTable.Columns.UUID));
        String title = getString(getColumnIndex(CrimeDBSchema.CrimeTable.Columns.TITLE));
        Long date = getLong(getColumnIndex(CrimeDBSchema.CrimeTable.Columns.DATE));
        boolean isSolved = getInt(getColumnIndex(CrimeDBSchema.CrimeTable.Columns.SOLVED)) != 0;
        boolean isPoliceRequired = getInt(getColumnIndex(CrimeDBSchema.CrimeTable.Columns.POLICEREQUIRED)) != 0;

        Crime crime = new Crime(UUID.fromString(uuidString));
        crime.setTitle(title);
        crime.setDate(new Date(date));
        crime.setRequirePolice(isPoliceRequired);
        crime.setSolved(isSolved);

        return crime;
    }
}
