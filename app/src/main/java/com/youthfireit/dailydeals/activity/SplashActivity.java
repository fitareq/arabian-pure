package com.youthfireit.dailydeals.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.youthfireit.dailydeals.R;

public class SplashActivity extends AppCompatActivity {

    private ImageView splash1, splash2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);




        splash1 = findViewById(R.id.splash1);
        //splash2 = findViewById(R.id.splash2);

        Thread splash = new Thread()
        {
            public void run()
            {
                try {
                    sleep(2*1000);
                    Intent intent = new Intent(SplashActivity.this,MainActivity.class);
                    intent.putExtra("from","splash");
                    startActivity(intent);
                    finish();
                    //splash1.setVisibility(View.GONE);
                    //splash2.setVisibility(View.VISIBLE);
                    //sleep(500);
                    //splash2.setVisibility(View.GONE);
                    //splash1.setVisibility(View.VISIBLE);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };


        /*for (int i=0; i<3; i++)
        {
            if (i==3)
            {
                Intent intent = new Intent(SplashActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }*/
            splash.start();




    }
}