package com.youthfireit.dailydeals.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.youthfireit.dailydeals.R;
import com.youthfireit.dailydeals.adapter.ProductsAdapter;
import com.youthfireit.dailydeals.model.Products;
import com.youthfireit.dailydeals.network.APIinstance;
import com.youthfireit.dailydeals.network.ArabianPureApi;

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
    private LinearLayout productContainer, noProductMsg;

    private String category_id,category_title;
    private ImageView backBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_products);

        category_id = getIntent().getStringExtra("id");
        category_title = getIntent().getStringExtra("title");

        noProductMsg = findViewById(R.id.no_product_msg);
        productContainer = findViewById(R.id.category_products_holder);
        categoryTitle = findViewById(R.id.category_wise_title);
        backBtn = findViewById(R.id.back_btn);
        categoryWiseProductsRecyclerView = findViewById(R.id.category_products);
        categoryWiseProductsRecyclerView.setHasFixedSize(true);
        productsManager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        categoryWiseProductsRecyclerView.setLayoutManager(productsManager);
        ArabianPureApi arabianPureApi = APIinstance.retroInstace().create(ArabianPureApi.class);
        Call<List<Products>> call = arabianPureApi.getSpecificCategoryProducts(category_id);
        /*call.enqueue(new Callback<List<Products>>() {
            @Override
            public void onResponse(Call<List<Products>> call, Response<List<Products>> response)
            {
                if (response.isSuccessful())
                {
                    productsList = response.body();
                    if (productsList!=null)
                    {
                        if (productsList.size()!=0) {
                            productContainer.setVisibility(View.VISIBLE);
                            noProductMsg.setVisibility(View.GONE);
                            productsAdapter = new ProductsAdapter(productsList, CategoryProductsActivity.this, CategoryProductsActivity.this);
                            categoryWiseProductsRecyclerView.setAdapter(productsAdapter);
                        }else {
                            productContainer.setVisibility(View.GONE);
                            noProductMsg.setVisibility(View.VISIBLE);
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Products>> call, Throwable t) {

            }
        });
*/
        categoryTitle.setText(category_title);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }

    @Override
    public void onProductClickListener(String id, String slug, String image, String title)
    {
        Intent intent = new Intent(CategoryProductsActivity.this,SingleProductActivity.class);
        intent.putExtra("id",id);
        intent.putExtra("slug",slug);
        intent.putExtra("image",image);
        intent.putExtra("title",title);

        startActivity(intent);
    }

    private void goToHomePage()
    {
        Intent intent = new Intent(CategoryProductsActivity.this, MainActivity.class);
        intent.putExtra("from","categoryproducts");
        startActivity(intent);
        finish();
    }
}