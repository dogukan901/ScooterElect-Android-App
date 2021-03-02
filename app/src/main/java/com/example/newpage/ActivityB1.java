package com.example.newpage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ActivityB1 extends AppCompatActivity {

    private GridView gridView;
    private ArrayList<String> countries = new ArrayList<>();
    private ArrayAdapter<String> dataAdapter;


    private Button buttonGoToC;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b1);

        gridView = findViewById(R.id.gridView);


        countries.add("Turkey");
        countries.add("Russia");
        countries.add("Belgium");
        countries.add("USA");
        countries.add("Germany");
        countries.add("Italy");
        countries.add("France");

        dataAdapter = new ArrayAdapter<>(ActivityB1.this, android.R.layout.simple_list_item_1, android.R.id.text1, countries);

        gridView.setAdapter(dataAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast toast = Toast.makeText(getApplicationContext(), " Selected Country : " + countries.get(i), Toast.LENGTH_SHORT);

                toast.setGravity(Gravity.BOTTOM, 0, 200);
                toast.show();
            }
        });

        buttonGoToC = findViewById(R.id.buttonGoToC);

        buttonGoToC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ActivityB1.this, ActivityC.class));
            }
        });
    }
}