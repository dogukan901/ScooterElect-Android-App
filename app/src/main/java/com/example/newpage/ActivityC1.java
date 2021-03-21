package com.example.newpage;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.newpage.dao.DatabaseHelper;
import com.example.newpage.dao.usersdao;
import com.example.newpage.objects.escooters;
import com.example.newpage.objects.users;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class ActivityC1 extends AppCompatActivity implements SearchView.OnQueryTextListener {

    private Toolbar toolbar;
    private RecyclerView rv;
    private FloatingActionButton fab;
    private ArrayList<users> usersArrayList;
    private usersAdapter usersAdapter;
    private DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_c1);

        toolbar = findViewById(R.id.toolbarNew);
        rv = findViewById(R.id.rv);
        fab = findViewById(R.id.fab);



        toolbar.setTitle("C1 Sayfası");
        toolbar.setSubtitle("Arama yapılabilir");
        toolbar.setLogo(R.drawable.location_target_escooter);
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));

        setSupportActionBar(toolbar);

        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(this));

        /*usersArrayList = new ArrayList<>();
        escooters esc1 = new escooters(7,"Daelim","binbin","181180763","2018");
        users u1 = new users(80,"Ahmet","cakar123",esc1);
        users u2 = new users(80,"Zeynep","ayoz123",esc1);

        usersArrayList.add(u1);
        usersArrayList.add(u2);*/


        db = new DatabaseHelper(this);

        usersArrayList = new usersdao().getallUsers(db);


        usersAdapter = new usersAdapter(this,usersArrayList);

        rv.setAdapter(usersAdapter);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertgoster();
                //startActivity(new Intent(ActivityC1.this, ActivityD.class));

            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbarmenu, menu);

        MenuItem menuItem = menu.findItem(R.id.action_ara);

        SearchView searchView = (SearchView) menuItem.getActionView();

        searchView.setOnQueryTextListener(this);


        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        Log.e("onQueryTextSubmit", query);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        Log.e("onQueryTextChange", newText);
        return false;
    }

    public void alertgoster() {
        LayoutInflater layout = LayoutInflater.from(this);
        View tasarim = layout.inflate(R.layout.alert_tasarim, null);

        EditText editTextAd = tasarim.findViewById(R.id.editTextAd);
        EditText editTextTel = tasarim.findViewById(R.id.editTextTel);

        AlertDialog.Builder alertdialog = new AlertDialog.Builder(this);

        alertdialog.setTitle("Kişi Ekle");
        alertdialog.setView(tasarim);

        alertdialog.setPositiveButton("Ekle", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                String kisi_ad = editTextAd.getText().toString().trim();
                String kisi_tel = editTextTel.getText().toString().trim();

                new usersdao().addUserWTel(db,kisi_ad,kisi_tel);

                usersArrayList = new usersdao().getallUsers(db);

                usersAdapter = new usersAdapter(ActivityC1.this,usersArrayList);
                rv.setAdapter(usersAdapter);

                Toast.makeText(getApplicationContext(),kisi_ad+"--"+kisi_tel,Toast.LENGTH_SHORT).show();
            }
        });
        alertdialog.setNegativeButton("İptal", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        alertdialog.create().show();
    }
}