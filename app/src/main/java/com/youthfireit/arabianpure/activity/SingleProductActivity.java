package com.youthfireit.arabianpure.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;
import com.smarteist.autoimageslider.SliderViewAdapter;
import com.youthfireit.arabianpure.R;
import com.youthfireit.arabianpure.adapter.ImageAdapter;
import com.youthfireit.arabianpure.model.PImage;
import com.youthfireit.arabianpure.model.Products;
import com.youthfireit.arabianpure.network.APIinstance;
import com.youthfireit.arabianpure.network.ArabianPureApi;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SingleProductActivity extends AppCompatActivity {

    private ImageView imageView;
    private TextView productTitle, productStock,
            addToWishlist, addedToWishlist, productDescription,
            productSku, productQuantity, productPrice, productOfferPrice;
    private AppCompatImageButton productQuantityAdd, productQuantityRemove;
    private MaterialButton addToCart;
    private int PQuantity = 1;
    Toolbar toolbar;
    private Products productsList;
    private String slug;


    private SliderView singleImageSlider;
    private SliderViewAdapter sliderViewAdapter;
    private List<PImage> pImageList;

    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_product);



        id = Integer.parseInt(getIntent().getStringExtra("id"));
        slug = getIntent().getStringExtra("slug");

        toolbar = findViewById(R.id.single_product_toolbar);
        //imageView = findViewById(R.id.single_product_image_view);
        singleImageSlider = findViewById(R.id.single_product_image_slider);
        productTitle = findViewById(R.id.single_product_title);
        productStock = findViewById(R.id.single_product_stock);
        addToWishlist = findViewById(R.id.single_product_wishlist);
        addedToWishlist = findViewById(R.id.single_product_wishlisted);
        productDescription = findViewById(R.id.single_product_description);
        productSku = findViewById(R.id.single_product_sku);
        productQuantity = findViewById(R.id.single_product_quantity);
        productQuantityAdd = findViewById(R.id.quantity_add);
        productQuantityRemove = findViewById(R.id.quantity_sub);
        productPrice = findViewById(R.id.single_product_price);
        productOfferPrice = findViewById(R.id.single_product_offer_price);




        //Picasso.get().load(image).into(imageView);
       /* textView.setText(title);
        productDescription.setText(description);
        String st = "Available: "+quantity;
        productStock.setText(st);
        productSku.setText(sku);
        toolbar.setTitle(title);*/
        //toolbar.setTitle(slug);


        singleImageSlider.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        singleImageSlider.setIndicatorAnimation(IndicatorAnimationType.WORM);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                goToHome();
            }
        });
        loadProductData();
        loadProductImage();

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

    private void loadProductImage()
    {
        ArabianPureApi arabianPureApi = APIinstance.retroInstace().create(ArabianPureApi.class);
        Call<List<PImage>> call = arabianPureApi.getProductImages(id);
        call.enqueue(new Callback<List<PImage>>() {
            @Override
            public void onResponse(Call<List<PImage>> call, Response<List<PImage>> response)
            {
                if (response.isSuccessful())
                {
                    pImageList = response.body();
                    if (pImageList!=null) {
                        sliderViewAdapter = new ImageAdapter(pImageList);
                        singleImageSlider.setSliderAdapter(sliderViewAdapter);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<PImage>> call, Throwable t) {

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

                    //int id = Integer.parseInt(response.body().getId());

                    String price = productsList.getPrice();
                    String offer_price = productsList.getOffer_price();
                    String sku = "SKU: "+productsList.getSku();
                    String st = "Available: "+productsList.getQuantity();
                    String title = productsList.getTitle();
                    String description = productsList.getDescription();

                    if (offer_price!=null)
                    {
                        price = "৳"+price;
                        offer_price = "৳"+offer_price;
                        productOfferPrice.setVisibility(View.VISIBLE);
                        productPrice.setText(price);
                        productOfferPrice.setText(offer_price);
                        productPrice.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
                    }else
                    {
                        String p;
                        if (price==null)
                            p="0";
                        else p=price;
                        price = "৳"+p;
                        productPrice.setText(price);
                    }

                    //productPrice.setText(productsList.getPrice());
                    productTitle.setText(title);
                    productDescription.setText(description);
                    productStock.setText(st);
                    productSku.setText(sku);
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