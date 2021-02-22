package com.example.newpage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    private TextView benimtext;
    private Button benimbutton;
    private Button GotoB; // sadece B'ye giden ilk button

    private EditText editTextInput;
    private Button buttonSend;

    private Button buttonFeedback;

    private Button buttonExit;

    private TextView textViewUserPassDisplay;

    private SharedPreferences sp;
    private SharedPreferences.Editor editor;
    private String sUsernameDisplay;
    private String passwordDisplay;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        buttonFeedback = findViewById(R.id.buttonFeedback);

        buttonFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Snackbar snackbar = Snackbar.make(view,"No Internet Connection",Snackbar.LENGTH_LONG).setAction("Ok", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Snackbar.make(view,"Try Again",Snackbar.LENGTH_SHORT).show();
                    }
                });
               snackbar.setActionTextColor(Color.RED);

               snackbar.setBackgroundTint(Color.WHITE);

               snackbar.setTextColor(Color.BLUE);

               snackbar.show();
            }
        });


        textViewUserPassDisplay = (TextView) findViewById(R.id.textViewUserPass);
        //Shared Preference Başlangıç
        sp = getSharedPreferences("LoginInfo",MODE_PRIVATE);
        editor = sp.edit();

        sUsernameDisplay = sp.getString("username","No Username");
        passwordDisplay = sp.getString("password","No password");

        textViewUserPassDisplay.setText("Username :  "+sUsernameDisplay+"/ Password :"+passwordDisplay);
        //Shared Preference Bitiş


        benimtext = findViewById(R.id.benimText);
        benimbutton = findViewById(R.id.benimButton);
        GotoB = findViewById(R.id.buttonGotoB);


        editTextInput = findViewById(R.id.editTextInput);
        buttonSend = findViewById(R.id.buttonSend);

        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String data = editTextInput.getText().toString();

                Intent newIntent = new Intent(MainActivity.this, ActivityB.class);

                newIntent.putExtra("UpcomingMessageData",data);

                startActivity(newIntent);


            }
        });

        benimbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String data2 = editTextInput.getText().toString();
                benimtext.setText(data2);
                editTextInput.getText().clear();
                Log.e("Message", "Alov!");
            }
        });



        //keyboard hide denemeleriydi
      /*  editTextInput.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int keyCode, KeyEvent event) {

                if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                // hide virtual keyboard
                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(editTextInput.getWindowToken(), 0);
                return true;
            }
                return false;
            }
        });*/

        GotoB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Kisiler kisi = new Kisiler(999999, "Erkin", 1.80);

                Intent yeniIntent = new Intent(MainActivity.this, ActivityB.class);

                yeniIntent.putExtra("message", "Selamın Aleyküm.");
                yeniIntent.putExtra("yas", 15);
                yeniIntent.putExtra("boy", 1.80);
                yeniIntent.putExtra("nesne", kisi);
                startActivity(yeniIntent);*/

                //      Intent yeniIntent2 = new Intent(MainActivity.this,ActivityB.class);

                startActivity(new Intent(MainActivity.this, ActivityB.class));

            }
        });

        buttonExit = (Button) findViewById(R.id.buttonExit);


        buttonExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editor.remove("username");
                editor.remove("password");
                editor.putBoolean("isLogged", false);
                editor.commit();
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
                finish();



            }
        });

        Log.e("LifeCycle", "OnCreate worked!");

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e("LifeCycle", "OnStart worked!");


    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e("LifeCycle", "OnResume worked!");

    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e("LifeCycle", "OnPause worked!");

    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e("LifeCycle", "OnStop worked!");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("LifeCycle", "OnDestroy worked!");

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.e("LifeCycle", "OnRestart worked!");
    }


}