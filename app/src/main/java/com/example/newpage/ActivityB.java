package com.example.newpage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;

public class ActivityB extends AppCompatActivity {


    private TextView textViewOutput;

    private Button ButtonGotoB1;

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b);

        webView = findViewById(R.id.webviewB);

        webView.getSettings().setJavaScriptEnabled(true);

        webView.loadUrl("https://solwider-arac-rotalama.herokuapp.com");


        webView.setWebViewClient(new WebViewClient());

        /*butonun keyboard üzerine kaymasını engelleme denemesiydi fakat android manifestte  android:windowSoftInputMode="adjustResize" ile çözüldü
       getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);*/

        textViewOutput = findViewById(R.id.textViewOutput);

        String incomingMessageData = getIntent().getStringExtra("UpcomingMessageData");

        textViewOutput.setText(incomingMessageData);



        ButtonGotoB1 = findViewById(R.id.buttonGotoB1);

        ButtonGotoB1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                startActivity(new Intent(ActivityB.this,ActivityB1.class));
            }
        });


        //buttonGotoB basarken kilitleri aç moruq çünkü bu verileri alacak


        /*String incomingMessage = getIntent().getStringExtra("message");
        int incomingAge = getIntent().getIntExtra("yas", 0);
        double incomingBoy = getIntent().getDoubleExtra("boy", 0);

        Kisiler incomingKisi = (Kisiler) getIntent().getSerializableExtra("nesne");


        Log.e("incomingKisi", String.valueOf(incomingKisi.getTc()));
        Log.e("incomingKisiIsim", incomingKisi.getIsim());
        Log.e("incomingKisiBoy", String.valueOf(incomingKisi.getBoy()));


        Log.e("incomingAge", String.valueOf(incomingAge));
        Log.e("incomingBoy", String.valueOf(incomingBoy));
        Log.e("incomingMessage", incomingMessage);*/
    }
}