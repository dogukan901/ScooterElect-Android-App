package com.example.newpage.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.newpage.objects.escooters;
import com.example.newpage.objects.users;

import java.util.ArrayList;

public class escootersdao {

    public ArrayList<escooters> getallEscooters(DatabaseHelper db) {
        ArrayList<escooters> escootersArrayList = new ArrayList<>();
        SQLiteDatabase dbx = db.getWritableDatabase();

        Cursor c = dbx.rawQuery("SELECT * FROM escooters", null);

        while (c.moveToNext()) {
            escooters escooter = new escooters(c.getInt(c.getColumnIndex("escooter_id")),
                    c.getString(c.getColumnIndex("escooter_model")),
                    c.getString(c.getColumnIndex("escooters_name")),
                    c.getString(c.getColumnIndex("escooter_number")),
                    c.getString(c.getColumnIndex("escooter_model_year"))
            );

            escootersArrayList.add(escooter);
        }

        return escootersArrayList;
    }
}
