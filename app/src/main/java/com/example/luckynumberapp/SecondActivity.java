package com.example.luckynumberapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class SecondActivity extends AppCompatActivity {
    TextView title, luckyNumber;
    Button share;
    String username;
    int randomNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_second);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //Receiving the value from main activity
        Intent i = getIntent();  // Use getIntent() to get the passed Intent
        username = i.getStringExtra("name");



        title = findViewById(R.id.title);
        luckyNumber = findViewById(R.id.textView3);


        //Generating Random Numbers
        randomNumber = generateRandomNumber();
        luckyNumber.setText(String.valueOf(randomNumber));



        share = findViewById(R.id.share_button);
        share.setOnClickListener(new View.OnClickListener() {
       @Override
       public void onClick(View v) {
           //Receiving the value from main activity

           shareData(username, randomNumber);

            }

        });
    }



    public int generateRandomNumber(){
        Random rand = new Random();
        int upperLimit = 1000;
        int randomnum = rand.nextInt(upperLimit);
        return randomnum;
    }

    public void shareData(String username, int randomNum){
        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("text/plain");


        i.putExtra(Intent.EXTRA_SUBJECT, username + "got lucky today!");
        i.putExtra(Intent.EXTRA_TEXT, "His luck number is " + randomNum);


        startActivity(Intent.createChooser(i, "Choose an app"));


    }
}