package com.example.newpage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;

public class NewActivity extends AppCompatActivity {

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);

        toolbar = findViewById(R.id.toolbarNew);

        toolbar.setTitle("Özel Toolbar Başlık");
        toolbar.setSubtitle("Özel Alt Başlık");
        toolbar.setLogo(R.drawable.location_target_escooter);
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));

        setSupportActionBar(toolbar);

    }
}