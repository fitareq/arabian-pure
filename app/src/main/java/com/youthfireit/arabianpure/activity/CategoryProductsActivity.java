package com.youthfireit.arabianpure.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.youthfireit.arabianpure.R;
import com.youthfireit.arabianpure.adapter.ProductsAdapter;
import com.youthfireit.arabianpure.model.Products;
import com.youthfireit.arabianpure.network.APIinstance;
import com.youthfireit.arabianpure.network.ArabianPureApi;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryProductsActivity extends AppCompatActivity implements ProductsAdapter.productClickListener {

    private TextView categoryTitle;
    private RecyclerView categoryWiseProductsRecyclerView;
    private RecyclerView.Adapter productsAdapter;
    private RecyclerView.LayoutManager productsManager;
    private List<Products> productsList;

    private String category_id,category_title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_products);

        category_id = getIntent().getStringExtra("id");
        category_title = getIntent().getStringExtra("title");

        categoryTitle = findViewById(R.id.category_wise_title);
        categoryWiseProductsRecyclerView = findViewById(R.id.category_products);
        categoryWiseProductsRecyclerView.setHasFixedSize(true);
        productsManager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        categoryWiseProductsRecyclerView.setLayoutManager(productsManager);
        ArabianPureApi arabianPureApi = APIinstance.retroInstace().create(ArabianPureApi.class);
        Call<List<Products>> call = arabianPureApi.getSpecificCategoryProducts(category_id);
        call.enqueue(new Callback<List<Products>>() {
            @Override
            public void onResponse(Call<List<Products>> call, Response<List<Products>> response)
            {
                if (response.isSuccessful())
                {
                    productsList = response.body();
                    if (productsList!=null)
                    {
                        productsAdapter = new ProductsAdapter(productsList, CategoryProductsActivity.this,  CategoryProductsActivity.this);
                        categoryWiseProductsRecyclerView.setAdapter(productsAdapter);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Products>> call, Throwable t) {

            }
        });

        categoryTitle.setText(category_title);

    }

    @Override
    public void onProductClickListener(String id, String slug)
    {
        Intent intent = new Intent(CategoryProductsActivity.this,SingleProductActivity.class);
        intent.putExtra("id",id);
        intent.putExtra("slug",slug);

        startActivity(intent);
    }
}