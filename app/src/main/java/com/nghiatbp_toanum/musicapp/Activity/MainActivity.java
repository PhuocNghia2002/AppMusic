package com.nghiatbp_toanum.musicapp.Activity;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.nghiatbp_toanum.musicapp.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CountDownTimer countDownTimer = new CountDownTimer(3000, 1000) {
            Intent intent;
            @Override
            public void onTick(long l) {
                intent = new Intent(MainActivity.this, HomePageActivity.class);
            }

            @Override
            public void onFinish() {
                startActivity(intent);
            }
        };
        countDownTimer.start();
    }
}