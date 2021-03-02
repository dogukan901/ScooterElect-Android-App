package com.example.newpage.dao;

import android.content.ContentValues;
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


    public void addEscooter(DatabaseHelper db, String escooter_model, String escooter_name,String escooter_number,String escooter_model_year) {

        SQLiteDatabase dbx = db.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("escooter_model", escooter_model);
        values.put("escooter_name",escooter_name);
        values.put("escooter_number",escooter_number);// String.valueOf(used_escooter.getEscooter_id())
        values.put("escooter_model_year",escooter_model_year);
        dbx.insertOrThrow("escooters", null, values);
//        dbx.close();

    }
}
