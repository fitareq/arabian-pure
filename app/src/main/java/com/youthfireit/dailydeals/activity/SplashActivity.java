package com.youthfireit.dailydeals.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.youthfireit.dailydeals.R;
import com.youthfireit.dailydeals.local_room.HomeRepository;
import com.youthfireit.dailydeals.viewmodels.HomeViewModel;

import java.util.concurrent.ExecutorService;


public class SplashActivity extends AppCompatActivity {


private ImageView splash2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        ImageView splash1 = findViewById(R.id.splash1);
        //HomeViewModel viewModel = new ViewModelProvider(this).get(HomeViewModel.class);

        //splash2 = findViewById(R.id.splash2);
        Thread fetch = new Thread()
        {
            public void run()
            {
                Application application = getApplication();
                HomeRepository repository = new HomeRepository(application);
                repository.fetchProductsFromRetrofit();
                repository.fetchBannerFromRetrofit();
                repository.fetchCategoriesFromRetrofit();
                repository.fetchSliderFromRetrofit();
            }
        };

        Thread splash = new Thread()
        {
            public void run()
            {
                try {
                   // viewModel.insertProductsList();
                   // viewModel.insertBannerList();
                   // viewModel.insertCategoriesList();
                   // viewModel.insertSliderList();
                    sleep(3*1000);
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


        fetch.setPriority(Thread.MAX_PRIORITY);
        splash.setPriority(Thread.MIN_PRIORITY);
        fetch.start();
        splash.start();




    }
}