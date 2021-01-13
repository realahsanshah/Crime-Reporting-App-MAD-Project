package com.example.crimereportingapp.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class CrimeBaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "crimeBase.db";
    private static final int VERSION = 1;

    public CrimeBaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + CrimeDBSchema.CrimeTable.NAME + "(" +
                "_id integer primary key autoincrement," +
                CrimeDBSchema.CrimeTable.Columns.UUID + "," +
                CrimeDBSchema.CrimeTable.Columns.TITLE + "," +
                CrimeDBSchema.CrimeTable.Columns.DATE + "," +
                CrimeDBSchema.CrimeTable.Columns.SOLVED + "," +
                CrimeDBSchema.CrimeTable.Columns.POLICEREQUIRED +
                ");";

        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
