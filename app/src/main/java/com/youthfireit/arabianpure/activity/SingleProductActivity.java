package com.youthfireit.arabianpure.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;
import com.squareup.picasso.Picasso;
import com.youthfireit.arabianpure.R;
import com.youthfireit.arabianpure.model.Products;
import com.youthfireit.arabianpure.network.APIinstance;
import com.youthfireit.arabianpure.network.ArabianPureApi;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SingleProductActivity extends AppCompatActivity {

    private ImageView imageView;
    private TextView textView, productStock, addToWishlist, addedToWishlist, productDescription, productSku, productQuantity;
    private AppCompatImageButton productQuantityAdd, productQuantityRemove;
    private MaterialButton addToCart;
    private int PQuantity = 1;
    Toolbar toolbar;
    private Products productsList;
    private String slug;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_product);

        mAuth = FirebaseAuth.getInstance();

        String image = getIntent().getStringExtra("image");
        slug = getIntent().getStringExtra("slug");
        /*String title = getIntent().getStringExtra("title");
        String description = getIntent().getStringExtra("description");
        String quantity = getIntent().getStringExtra("quantity");
        String sku = getIntent().getStringExtra("sku");*/
        toolbar = findViewById(R.id.single_product_toolbar);
        //imageView = findViewById(R.id.single_product_image_view);
        textView = findViewById(R.id.single_product_title);
        productStock = findViewById(R.id.single_product_stock);
        addToWishlist = findViewById(R.id.single_product_wishlist);
        addedToWishlist = findViewById(R.id.single_product_wishlisted);
        productDescription = findViewById(R.id.single_product_description);
        productSku = findViewById(R.id.single_product_sku);
        productQuantity = findViewById(R.id.single_product_quantity);
        productQuantityAdd = findViewById(R.id.quantity_add);
        productQuantityRemove = findViewById(R.id.quantity_sub);




        Picasso.get().load(image).into(imageView);
       /* textView.setText(title);
        productDescription.setText(description);
        String st = "Available: "+quantity;
        productStock.setText(st);
        productSku.setText(sku);
        toolbar.setTitle(title);*/
        //toolbar.setTitle(slug);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                goToHome();
            }
        });
        loadProductData();

        //toolbar.setTitle(slug);
        //textView.setText(productsList.get(0).getTitle());
        addedToWishlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                addToWishlist.setVisibility(View.VISIBLE);
                addedToWishlist.setVisibility(View.GONE);
            }
        });
        addToWishlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                addToWishlist.setVisibility(View.GONE);
                addedToWishlist.setVisibility(View.VISIBLE);
            }
        });
        productQuantityAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ++PQuantity;
                productQuantity.setText(String.valueOf(PQuantity));
            }
        });
        productQuantityRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (PQuantity>1) {
                    --PQuantity;
                    productQuantity.setText(String.valueOf(PQuantity));
                }
            }
        });
    }




   private void loadProductData()
    {
        ArabianPureApi arabianPureApi = APIinstance.retroInstace().create(ArabianPureApi.class);
        Call<Products> call = arabianPureApi.getSingleProduct(slug);
        call.enqueue(new Callback<Products>() {
            @Override
            public void onResponse(Call<Products> call, Response<Products> response)
            {
                if (!response.isSuccessful())
                {
                    Toast.makeText(SingleProductActivity.this, response.message(), Toast.LENGTH_SHORT).show();
                }else
                {

                    productsList = response.body();
                    //textView.setText(productsList.get(productsList.size()-1).getTitle());
                    //getSupportActionBar().setTitle(productsList.size());
                    //toolbar.setTitle(productsList.size());


                    textView.setText(productsList.getTitle());
                    productDescription.setText(productsList.getDescription());
                    String st = "Available: "+productsList.getQuantity();
                    productStock.setText(st);
                    productSku.setText(productsList.getSku());
                }
            }

            @Override
            public void onFailure(Call<Products> call, Throwable t) {

            }
        });
    }

    private void goToHome()
    {
        Intent intent = new Intent(SingleProductActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}