package com.youthfireit.dailydeals.activity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.youthfireit.dailydeals.R;
import com.youthfireit.dailydeals.adapter.CategoryAdapter;
import com.youthfireit.dailydeals.adapter.ProductsAdapter;
import com.youthfireit.dailydeals.fragments.AccountFragment;
import com.youthfireit.dailydeals.fragments.CartFragment;
import com.youthfireit.dailydeals.fragments.CategoryFragment;
import com.youthfireit.dailydeals.fragments.HomeFragment;
import com.youthfireit.dailydeals.model.Category;
import com.youthfireit.dailydeals.sqlite.UserSQLiteHelper;

import java.util.List;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener, HomeFragment.HomeFragmentListener, ProductsAdapter.productClickListener, CategoryAdapter.categoryClickListener {

    Toolbar toolbar;
    private BottomNavigationView bottomNavigationView;
    private final static int REQUEST_PERMISSION_READ_EXTERNAL = 2;
    private List<Category> categoryList;
    private CoordinatorLayout mainDrawer;
    private ProgressBar progressBar;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        UserSQLiteHelper userSQLiteHelper = new UserSQLiteHelper(this);
        SQLiteDatabase sqLiteDatabase = userSQLiteHelper.getWritableDatabase();

        /*int readExternalStoragePermission = ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_NETWORK_STATE);
        if (readExternalStoragePermission != PackageManager.PERMISSION_GRANTED) {
            String[] requirePermission = {Manifest.permission.ACCESS_NETWORK_STATE};
            ActivityCompat.requestPermissions(MainActivity.this, requirePermission, REQUEST_PERMISSION_READ_EXTERNAL);
        }*/



        bottomNavigationView = findViewById(R.id.bottom_nav);
        //toolbar = findViewById(R.id.main_toolbar);
        mainDrawer = findViewById(R.id.main_drawer);




        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        setSupportActionBar(toolbar);
        String from = getIntent().getStringExtra("from");
        if (from.equals("splash"))
        {
            loadFragment(new HomeFragment(this, this));
        }else if (from.equals("singleproduct"))
        {
            loadFragment(new HomeFragment(this, this));
        }else if (from.equals("register"))
        {
            bottomNavigationView.setSelectedItemId(R.id.nav_account);
            loadFragment( new AccountFragment());
        }else if (from.equals("categoryproducts"))
        {
            bottomNavigationView.setSelectedItemId(R.id.nav_category);
            loadFragment(new CategoryFragment());
        }else if (from.equals("login"))
        {
            bottomNavigationView.setSelectedItemId(R.id.nav_account);
            loadFragment( new AccountFragment());
        }
        else loadFragment(new HomeFragment(this, this));
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
                fragment = new CategoryFragment();
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
    public void onShowAllClick( boolean click)
    {

        if (click)
        {
            bottomNavigationView.setSelectedItemId(R.id.nav_category);
            loadFragment(new CategoryFragment());
        }
    }


    @Override
    public void onProductClickListener(String id, String slug, String image, String title)
    {
        Intent intent = new Intent(MainActivity.this,SingleProductActivity.class);
        intent.putExtra("id",id);
        intent.putExtra("slug",slug);
        intent.putExtra("image",image);
        intent.putExtra("title",title);

        startActivity(intent);

    }

    @Override
    public void onCategoryClickListener(String id, String title)
    {
        Intent intent = new Intent(MainActivity.this, CategoryProductsActivity.class);
        intent.putExtra("id",id);
        intent.putExtra("title",title);
        startActivity(intent);
    }
}