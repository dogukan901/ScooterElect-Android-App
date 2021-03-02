package com.example.newpage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatAutoCompleteTextView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.LinkMovementMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.newpage.dao.DatabaseCopyHelper;
import com.example.newpage.dao.DatabaseHelper;
import com.example.newpage.dao.escootersdao;
import com.example.newpage.dao.usersdao;
import com.example.newpage.objects.escooters;
import com.example.newpage.objects.users;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity /*implements View.OnTouchListener*/ {
    private Button LoginButton;
    private Button SignInButton;
    private TextView needhelp;
    private AppCompatAutoCompleteTextView autoTextView;
    private AppCompatAutoCompleteTextView autoTextViewCustom;
    private android.widget.EditText edtPassword;
    private CheckBox checkbox;


    private EditText usernameEditText1;
    private EditText passwordEditText1;
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;
    private String sUsernameDisplay;
    private String passwordDisplay;

    private DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        SignInButton = findViewById(R.id.signInbutton);
        checkbox = findViewById(R.id.passwordcheckBox);
        usernameEditText1 = (EditText) findViewById(R.id.editTextUsername);
        edtPassword = findViewById(R.id.editTextPassword);


        SignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db = new DatabaseHelper(LoginActivity.this);
                // new usersdao().addUser(db, "Dogukan", "ankara0634", "xiaomi");//elle kullanıcı ekleme
                //new escooters(31,"model not selected yet","no escooter name yet","155","2019");


                if (usernameEditText1.getText().toString().equals("") || edtPassword.getText().toString().equals("")) {
                    Toast.makeText(LoginActivity.this, "Please enter your Username and Password", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    //escooters esc = new escooters(1, "xiaomi", "martı", "181180761", "2020");
                    new usersdao().addUser(db, String.valueOf(usernameEditText1.getText()).trim(), String.valueOf(edtPassword.getText()).trim());

                    ArrayList<users> addedUsersList = new usersdao().getallUsers(db);
                    for (users u : addedUsersList) {

                        Log.e(String.valueOf(u.getUser_id()), u.getUser_name() + "---" + u.getUser_password() + "---" + u.getUsed_escooter().getEscooter_model()+ "---" + u.getUsed_escooter().getEscooter_number());

                    }
                    Toast.makeText(LoginActivity.this, "User " + usernameEditText1.getText().toString() + " Created.", Toast.LENGTH_SHORT).show();
                    usernameEditText1.setText("");
                    edtPassword.setText("");

                }

            }
        });

        sp = getSharedPreferences("LoginInfo", MODE_PRIVATE);
        editor = sp.edit();
        boolean isLogged = sp.getBoolean("isLogged", false);

        sUsernameDisplay = sp.getString("username", "No Username");
        passwordDisplay = sp.getString("password", "No password");

        db = new DatabaseHelper(LoginActivity.this);

        copyDatabase();
        //veri silme ve güncelleme işlemleri deneme istenildiği yerde buraya bakarak kullanılabilir
         new usersdao().deleteUser(db, 10);//silme işlemi


        //new usersdao().updateUser(db,2,"dogukan","ankara0666","xiaomi redmixx");//güncelleme işlemi

        //verileri getirme
        ArrayList<users> addedUsersList = new usersdao().getallUsers(db);
        for (users u : addedUsersList) {

            Log.e(String.valueOf(u.getUser_id()), u.getUser_name() + "---" + u.getUser_password() + "---" + String.valueOf(u.getUsed_escooter().getEscooter_model())+ "---" + u.getUsed_escooter().getEscooter_number());
        }

        //veri sayisi fonksiyonu
        int dataCountResult = new usersdao().userRecordControl(db);
        Log.e("Veri Sayısı: ", String.valueOf(dataCountResult));

        //user arama fonk çağırımı
        ArrayList<users> incomingUsers = new usersdao().searchUser(db, "af");
        for (users user : incomingUsers) {

            Log.e(String.valueOf(user.getUser_id()), user.getUser_name() + "---" + user.getUser_password() + "---" + String.valueOf(user.getUsed_escooter().getEscooter_model()));
        }

        if (isLogged == true) {

            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            finish();
        }

        LoginButton = findViewById(R.id.loginButton);

        LoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                EditText usernameEditText = (EditText) findViewById(R.id.editTextUsername);
                String sUsername = usernameEditText.getText().toString();


                EditText passwordEditText = (EditText) findViewById(R.id.editTextPassword);
                String Pass = passwordEditText.getText().toString();

                db = new DatabaseHelper(LoginActivity.this);
                ArrayList<users> addedUsersList = new usersdao().getallUsers(db);
                for (users u : addedUsersList) {
                    if (String.valueOf(u.getUser_name()).equals(usernameEditText.getText().toString()) && String.valueOf(u.getUser_password()).equals(passwordEditText.getText().toString())) {
                        editor.putString("username", sUsername);
                        editor.putString("password", Pass);
                        editor.putBoolean("isLogged", true);
                        editor.commit();


                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                        finish();
                        return;
                    }

                }

                // .matches regular expressionlar için  == ise reference kontrolü için .equals ise objenin içindeki değerleri kontrol eder.
                if (sUsername.equals("") || Pass.equals("")) {
                    Toast.makeText(LoginActivity.this, "Please enter your Username and Password", Toast.LENGTH_SHORT).show();
                    // readExternal();
                    readInternal();
                    return;
                } else if (sUsername.equals("admin") && Pass.equals("123")) {
                    editor.putString("username", sUsername);
                    editor.putString("password", Pass);
                    editor.commit();

                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    finish();
                    // writeExternal();
                    writeInternal();

                } else {
                    Toast.makeText(getApplicationContext(), "Please enter your correct Username and Password", Toast.LENGTH_SHORT).show();

                }


            }
        });

        checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    // show password
                    edtPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    //startService(new Intent(LoginActivity.this,MyServis.class));//start ve stop service fonksiyonları bir switch butonu ile tetiklenebilir burada kullanılması normalde gereksiz
                } else {
                    // hide password
                    edtPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    //stopService(new Intent(LoginActivity.this,MyServis.class));//start ve stop service fonksiyonları bir switch butonu ile tetiklenebilir burada kullanılması normalde gereksiz
                }


            }
        });


        needhelp = findViewById(R.id.helpTextView);
        needhelp.setMovementMethod(LinkMovementMethod.getInstance());

    /*    needhelp.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return false;
            }

        });*/

    }

    public void copyDatabase() {

        DatabaseCopyHelper helper = new DatabaseCopyHelper(this);
        try {
            helper.createDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        }
        helper.openDataBase();
    }


    //harici yaz fonk başlangıç
    public void writeExternal() {
        //usernameEditText1 = (EditText) findViewById(R.id.editTextUsername);
        passwordEditText1 = (EditText) findViewById(R.id.editTextPassword);
        try {
            File filePath = Environment.getExternalStorageDirectory();
            File file = new File(filePath, "myrecordfile.txt");

            if (!file.exists()) {

                file.createNewFile();

            }
            FileWriter fwriter = new FileWriter(file);

            BufferedWriter bwriter = new BufferedWriter(fwriter);
            bwriter.write(usernameEditText1.getText().toString());
            bwriter.flush();
            bwriter.close();
            fwriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Log.e("controlwriteExternal","external yazma çalıştım ben");
    }

    //harici oku fonk başlangıç
    public void readExternal() {
        //usernameEditText1 = (EditText) findViewById(R.id.editTextUsername);
        try {
            File filePath = Environment.getExternalStorageDirectory();
            File file = new File(filePath, "myrecordfile.txt");

            FileReader filereader = new FileReader(file);

            BufferedReader breader = new BufferedReader(filereader);

            StringBuilder sb = new StringBuilder();

            String lineReaded = "";

            while ((lineReaded = breader.readLine()) != null) {

                sb.append(lineReaded + "\n");
            }
            breader.close();

            usernameEditText1.setText(sb.toString());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    //harici sil fonk başlangıç
    // not: kullanılmıyor fakat istenildiği takdirde ilgili yerde kullanılabilir
    public void deleteExternal() {
        File filePath = Environment.getExternalStorageDirectory();
        File file = new File(filePath, "myfile.txt");

        file.delete();
    }


    //dahili yaz fonk başlangıç
    public void writeInternal() {
        //usernameEditText1 = (EditText) findViewById(R.id.editTextUsername);
        try {

            FileOutputStream fos = openFileOutput("myfile.txt", MODE_PRIVATE);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fos);

            outputStreamWriter.write(usernameEditText1.getText().toString());
            outputStreamWriter.close();
            //usernameEditText1.setText("");
            //fos.close();

        } catch (Exception e) {

            e.printStackTrace();
        }


    }

    //dahili oku fonk başlangıç
    public void readInternal() {
        // usernameEditText1 = (EditText) findViewById(R.id.editTextUsername);
        try {
            FileInputStream fis = openFileInput("myfile.txt");

            InputStreamReader inputStreamReader = new InputStreamReader(fis);

            BufferedReader breader = new BufferedReader(inputStreamReader);

            StringBuilder sb = new StringBuilder();

            String lineReaded = "";

            while ((lineReaded = breader.readLine()) != null) {

                sb.append(lineReaded + "\n");
            }
            breader.close();

            usernameEditText1.setText(sb.toString());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //dahili sil fonk başlangıç
    // not: kullanılmıyor fakat istenildiği takdirde ilgili yerde kullanılabilir
    public void deleteInternal() {

        File filePath = getFilesDir();

        File file = new File(filePath, "myfile.txt");

        usernameEditText1.setText(String.valueOf(file.delete()));

    }

}