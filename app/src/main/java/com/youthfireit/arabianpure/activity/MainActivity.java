package com.youthfireit.arabianpure.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.youthfireit.arabianpure.R;
import com.youthfireit.arabianpure.adapter.ProductsAdapter;
import com.youthfireit.arabianpure.fragments.AccountFragment;
import com.youthfireit.arabianpure.fragments.CartFragment;
import com.youthfireit.arabianpure.fragments.CategoryFragment;
import com.youthfireit.arabianpure.fragments.HomeFragment;
import com.youthfireit.arabianpure.model.Category;

import java.util.List;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener, HomeFragment.HomeFragmentListener, ProductsAdapter.productClickListener {

    Toolbar toolbar;
    private BottomNavigationView bottomNavigationView;
    private final static int REQUEST_PERMISSION_READ_EXTERNAL = 2;
    private List<Category> categoryList;
    private CoordinatorLayout mainDrawer;
    private ProgressBar progressBar;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        int readExternalStoragePermission = ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_NETWORK_STATE);
        if (readExternalStoragePermission != PackageManager.PERMISSION_GRANTED) {
            String[] requirePermission = {Manifest.permission.ACCESS_NETWORK_STATE};
            ActivityCompat.requestPermissions(MainActivity.this, requirePermission, REQUEST_PERMISSION_READ_EXTERNAL);
        }



        bottomNavigationView = findViewById(R.id.bottom_nav);
        toolbar = findViewById(R.id.main_toolbar);
        mainDrawer = findViewById(R.id.main_drawer);




        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        setSupportActionBar(toolbar);
        loadFragment(new HomeFragment(this,this));
    }



    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item)
    {
        final int NAV_HOME = R.id.nav_home;
        final int NAV_CART = R.id.nav_cart;
        final int NAV_ACCOUNT = R.id.nav_account;
        final int NAV_CATEGORY = R.id.nav_category;

        Fragment fragment = null;

        switch (item.getItemId())
        {
            case NAV_HOME:
                fragment = new HomeFragment(this,this);
                break;
            case NAV_CART:
                //toolbar.setTitle("Cart");
                fragment = new CartFragment();
                break;
            case NAV_ACCOUNT:
                //toolbar.setTitle("Account");
                fragment = new AccountFragment();
                break;
            case NAV_CATEGORY:
                //toolbar.setTitle("Category");
                fragment = new CategoryFragment(categoryList);
                break;

        }

        return loadFragment(fragment);
    }



    private Boolean loadFragment(Fragment fragment)
    {
        if (fragment!=null)
        {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.main_container, fragment)
                    .commit();
            //toolbar.setTitle("Arabian Pure");
            return true;
        }
        return false;
    }


    @Override
    public void onShowAllClick(List<Category> categories, boolean click)
    {
        categoryList = categories;
        if (click)
        {
            bottomNavigationView.setSelectedItemId(R.id.nav_category);
            loadFragment(new CategoryFragment(categoryList));
        }
    }


    @Override
    public void onProductClickListener(String image, String slug, String title, String description, String quantity, String sku)
    {
        Intent intent = new Intent(MainActivity.this,SingleProductActivity.class);
        intent.putExtra("image",image);
        intent.putExtra("slug",slug);
        intent.putExtra("title",title);
        intent.putExtra("description",description);
        intent.putExtra("quantity",quantity);
        intent.putExtra("sku",sku);

        startActivity(intent);

    }
}