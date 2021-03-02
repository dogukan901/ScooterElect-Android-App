package com.example.newpage.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.newpage.objects.escooters;
import com.example.newpage.objects.users;

import java.util.ArrayList;

public class usersdao {

    public void addUser(DatabaseHelper db, String user_name, String user_password /* ,escooters used_escooter*/) {

        SQLiteDatabase dbx = db.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("user_name", user_name);
        values.put("user_password", user_password);
        values.put("used_escooter_id",3);// String.valueOf(used_escooter.getEscooter_id())
//        new escootersdao().addEscooter(db,"xiaomi","martı","181180761","2020");
        dbx.insertOrThrow("users", null, values);
        dbx.close();

    }

    public ArrayList<users> getallUsers(DatabaseHelper db) {
        ArrayList<users> usersArrayList = new ArrayList<>();
        SQLiteDatabase dbx = db.getWritableDatabase();

        Cursor c = dbx.rawQuery("SELECT * FROM users,escooters " +
                "WHERE users.used_escooter_id = escooters.escooter_id", null);

        while (c.moveToNext()) {

                    escooters escooter = new escooters(c.getInt(c.getColumnIndex("escooter_id")),
                    c.getString(c.getColumnIndex("escooter_model")),
                    c.getString(c.getColumnIndex("escooter_name")),
                    c.getString(c.getColumnIndex("escooter_number")),
                    c.getString(c.getColumnIndex("escooter_model_year"))
            );

            users user = new users(c.getInt(c.getColumnIndex("user_id")),
                    c.getString(c.getColumnIndex("user_name")),
                    c.getString(c.getColumnIndex("user_password")),
                    escooter);

            usersArrayList.add(user);
        }

        return usersArrayList;
    }


    public void deleteUser(DatabaseHelper db, int user_id) {

        SQLiteDatabase dbx = db.getWritableDatabase();
        dbx.delete("users", "user_id=?", new String[]{String.valueOf(user_id)});
        dbx.close();
    }


    public void updateUser(DatabaseHelper db, int user_id, String user_name, String user_password, escooters used_escooter) {

        SQLiteDatabase dbx = db.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("user_name", user_name);
        values.put("user_password", user_password);
        values.put("used_escooter_id", String.valueOf(used_escooter.getEscooter_id()));

        dbx.update("users", values, "user_id=?", new String[]{String.valueOf(user_id)});
        dbx.close();

    }

    public int userRecordControl(DatabaseHelper db) {
        int result = 0;
        SQLiteDatabase dbx = db.getWritableDatabase();

        Cursor c = dbx.rawQuery("SELECT count(*) as result FROM users", null);

        while (c.moveToNext()) {
            result = c.getInt(c.getColumnIndex("result"));

        }

        return  result;
    }

    public ArrayList<users> searchUser(DatabaseHelper db,String keyword) {
        ArrayList<users> usersArrayList = new ArrayList<>();
        SQLiteDatabase dbx = db.getWritableDatabase();

        Cursor c = dbx.rawQuery("SELECT * FROM users,escooters " +
                "WHERE user_name like '%"+keyword+"%'", null);

        while (c.moveToNext()) {
            escooters escooter = new escooters(c.getInt(c.getColumnIndex("escooter_id")),
                    c.getString(c.getColumnIndex("escooter_model")),
                    c.getString(c.getColumnIndex("escooter_name")),
                    c.getString(c.getColumnIndex("escooter_number")),
                    c.getString(c.getColumnIndex("escooter_model_year"))
            );

            users user = new users(c.getInt(c.getColumnIndex("user_id")),
                    c.getString(c.getColumnIndex("user_name")),
                    c.getString(c.getColumnIndex("user_password")),
                    escooter
            );
            usersArrayList.add(user);
        }

        return  usersArrayList;
    }
}
