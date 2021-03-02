package com.example.newpage.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {


    public DatabaseHelper(@Nullable Context context) {
        super(context, "KullaniciRehberi.sqlite", null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE IF NOT EXISTS escooters ("+"escooter_id INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE,"+
                "escooter_model TEXT,"+
                "escooter_name TEXT,"+
                "escooter_number TEXT,"+
                "escooter_model_year TEXT"+
                ");"

        );

        db.execSQL("CREATE TABLE IF NOT EXISTS users" + "(user_id INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE," +
                "user_name	TEXT NOT NULL," +
                "user_password	TEXT NOT NULL," +
                "used_escooter_id INTEGER NOT NULL,"+
                "FOREIGN KEY (used_escooter_id) REFERENCES escooters(escooter_id)" + ");"

        );

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS esooters");
        db.execSQL("DROP TABLE IF EXISTS users");
        onCreate(db);
    }
}
