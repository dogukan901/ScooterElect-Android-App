package com.example.newpage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ProgressBar;
import android.widget.Toast;

public class ActivityC extends AppCompatActivity {

    private Button ButtonGotoD;
    private CheckBox checkBoxMartı;
    private CheckBox checkBoxVolta;
    private CheckBox checkBoxXiaomi;
    private ProgressBar progressBarStrow;
    private int checkboxcounter = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_c);


        ButtonGotoD = findViewById(R.id.buttonGotoC1);
        checkBoxMartı = findViewById(R.id.checkBoxMartı);
        checkBoxVolta = findViewById(R.id.checkBoxVolta);
        checkBoxXiaomi = findViewById(R.id.checkBoxXiaomi);
        progressBarStrow = findViewById(R.id.progressBarStrow);

        ButtonGotoD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBoxMartı.isChecked() || checkBoxVolta.isChecked() || checkBoxXiaomi.isChecked()) {

                    startActivity(new Intent(ActivityC.this, ActivityC1.class));
                } else {

                    Toast.makeText(ActivityC.this, "Please Select At Least One Model.", Toast.LENGTH_SHORT).show();
                    return;
                }
                // finish(); // alternatifi flag activity clear top  bu activityi stackden çıkarır
            }
        });


        checkBoxMartı.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                    if (checkBoxMartı.isChecked()){
                        checkboxcounter++;
                        if (checkboxcounter==3){
                            progressBarStrow.setProgress(progressBarStrow.getProgress()+33);
                            Toast.makeText(ActivityC.this, "All models are selected.", Toast.LENGTH_SHORT).show();
                        }
                        else{

                            progressBarStrow.setProgress(progressBarStrow.getProgress()+33);
                            Toast.makeText(ActivityC.this, "Martı is selected.", Toast.LENGTH_SHORT).show();

                        }
                    }
                    else{
                        checkboxcounter--;
                        Toast.makeText(ActivityC.this, "Martı is deselected.", Toast.LENGTH_SHORT).show();
                        progressBarStrow.setProgress(progressBarStrow.getProgress()-33);
                    }


            }
        });

        checkBoxVolta.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                    if (checkBoxVolta.isChecked()) {
                        checkboxcounter++;
                        if (checkboxcounter==3){
                            progressBarStrow.setProgress(progressBarStrow.getProgress()+33);
                            Toast.makeText(ActivityC.this, "All models are selected.", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            progressBarStrow.setProgress(progressBarStrow.getProgress() + 33);
                            Toast.makeText(ActivityC.this, "Volta is selected.", Toast.LENGTH_SHORT).show();

                        }

                    } else {
                        checkboxcounter--;
                        Toast.makeText(ActivityC.this, "Volta is deselected.", Toast.LENGTH_SHORT).show();
                        progressBarStrow.setProgress(progressBarStrow.getProgress() - 33);

                    }



            }
        });

        checkBoxXiaomi.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                    if (checkBoxXiaomi.isChecked()) {
                        checkboxcounter++;
                        if (checkboxcounter==3){
                            progressBarStrow.setProgress(progressBarStrow.getProgress()+33);
                            Toast.makeText(ActivityC.this, "All models are selected.", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            progressBarStrow.setProgress(progressBarStrow.getProgress() + 33);
                            Toast.makeText(ActivityC.this, "Xiaomi is selected.", Toast.LENGTH_SHORT).show();
                        }

                    } else {
                        checkboxcounter--;
                        Toast.makeText(ActivityC.this, "Xiaomi is deselected.", Toast.LENGTH_SHORT).show();
                        progressBarStrow.setProgress(progressBarStrow.getProgress() - 33);

                    }

            }
        });
    }
}