package com.youthfireit.dailydeals.fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;


import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;
import com.smarteist.autoimageslider.SliderViewAdapter;
import com.youthfireit.dailydeals.network.APIinstance;
import com.youthfireit.dailydeals.R;
import com.youthfireit.dailydeals.adapter.BannerAdapter;
import com.youthfireit.dailydeals.adapter.CategoryAdapter;
import com.youthfireit.dailydeals.adapter.ProductsAdapter;
import com.youthfireit.dailydeals.network.ArabianPureApi;
import com.youthfireit.dailydeals.model.Category;
import com.youthfireit.dailydeals.model.HomepageBanner;
import com.youthfireit.dailydeals.model.HomepageSlider;
import com.youthfireit.dailydeals.model.Products;
import com.youthfireit.dailydeals.adapter.SliderAdapter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment implements View.OnClickListener
{




    private RecyclerView productsRecyclerView, categoryRecyclerView, breakfastRecyclerView, healthyFoodRecyclerView, localBDFoodRecyclerView;
    private RecyclerView.Adapter productsAdapter, categoryAdapter, breakfastAdapter, healthyFoodAdapter, localBDFoodAdapter;
    private RecyclerView.LayoutManager productsManager, categoryManager, breakfastManager, healthyFoodManager, localBDFoodManager;
    private TextView errorView,categoryShowAll;
    private List<Products> products, breakfastProducts, healthyFoodProducts, localBDFoodProducts;
    private List<Category> categories;
    Context context;
    private SliderView sliderView, bannerView;
    private SliderViewAdapter sliderAdapter, bannerAdapter;
    private List<HomepageSlider> homepageSliderList;
    private List<HomepageBanner> homepageBannerList;
    HomeFragmentListener homeFragmentListener;
    private static ProgressDialog progressDialog;
    private View rootView;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {

        rootView = inflater.inflate(R.layout.home_fragment,container,false);



        initializeViews();

        new loadSlider().execute(null, null, null);
        new loadBanner().execute(null, null, null);
        new loadCategory().execute(null, null, null);
        new loadBreakfast().execute(null, null, null);
        new loadHealthyFood().execute(null, null, null);
        new loadLocalBDFood().execute(null, null, null);
        new loadAllProduct().execute(null, null, null);

       /* new CountDownTimer(9000,1000)
        {
            @Override
            public void onTick(long millisUntilFinished)
            {
                rootView.findViewById(R.id.progress_bar).setVisibility(View.VISIBLE);
            }

            @Override
            public void onFinish()
            {
                rootView.findViewById(R.id.progress_bar).setVisibility(View.GONE);
            }

        }.start();*/

        /*initializeViews();




            loadSliderData();
            loadBannerData();
            loadAllProductData();
            loadCategoryData();
            loadBreakfastData();
            loadHealthyFoodData();
            loadLocalBDFoodData();*/
        //progressDialog.dismiss();
        categoryShowAll.setOnClickListener(this);

        return rootView;
    }

    private void initializeViews()
    {
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Loading");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setProgress(0);
        productsRecyclerView = rootView.findViewById(R.id.product_recyclerview);
        categoryRecyclerView = rootView.findViewById(R.id.category_recyclerview);
        healthyFoodRecyclerView = rootView.findViewById(R.id.healthy_foods_recyclerview);
        localBDFoodRecyclerView = rootView.findViewById(R.id.local_bd_foods_recyclerview);
        errorView = rootView.findViewById(R.id.internet_error);
        categoryShowAll = rootView.findViewById(R.id.category_show_all);
        breakfastRecyclerView = rootView.findViewById(R.id.breakfast_recyclerview);
        //((AppCompatActivity) requireActivity()).getSupportActionBar().setTitle("Arabian pure");
        sliderView = rootView.findViewById(R.id.auto_slider);
        bannerView = rootView.findViewById(R.id.banner_view);

        sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM);
        sliderView.setAutoCycle(true);
        sliderView.startAutoCycle();


        bannerView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        bannerView.setIndicatorAnimation(IndicatorAnimationType.DROP);
        bannerView.setAutoCycle(true);
        bannerView.startAutoCycle();

        productsRecyclerView.setHasFixedSize(true);
        productsRecyclerView.setNestedScrollingEnabled(false);

        categoryRecyclerView.setHasFixedSize(true);
        categoryRecyclerView.setNestedScrollingEnabled(false);

        breakfastRecyclerView.setHasFixedSize(true);
        breakfastRecyclerView.setNestedScrollingEnabled(false);

        healthyFoodRecyclerView.setHasFixedSize(true);
        healthyFoodRecyclerView.setNestedScrollingEnabled(false);

        localBDFoodRecyclerView.setHasFixedSize(true);
        localBDFoodRecyclerView.setNestedScrollingEnabled(false);

        productsManager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        categoryManager = new GridLayoutManager(getContext(),5);
        breakfastManager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        healthyFoodManager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        localBDFoodManager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);


        productsRecyclerView.setLayoutManager(productsManager);
        categoryRecyclerView.setLayoutManager(categoryManager);
        breakfastRecyclerView.setLayoutManager(breakfastManager);
        healthyFoodRecyclerView.setLayoutManager(healthyFoodManager);
        localBDFoodRecyclerView.setLayoutManager(localBDFoodManager);
    }


    private void loadBannerData()
    {
        ArabianPureApi arabianPureApi = APIinstance.retroInstace().create(ArabianPureApi.class);
        Call<List<HomepageBanner>> call = arabianPureApi.getBanner();
        call.enqueue(new Callback<List<HomepageBanner>>() {
            @Override
            public void onResponse( Call<List<HomepageBanner>> call,  Response<List<HomepageBanner>> response)
            {
                homepageBannerList = response.body();
                if (homepageBannerList!=null)
                {
                    bannerAdapter = new BannerAdapter(homepageBannerList);
                    bannerView.setSliderAdapter(bannerAdapter);
                }
            }

            @Override
            public void onFailure( Call<List<HomepageBanner>> call,  Throwable t) {

            }
        });
    }

    private void loadSliderData()
    {
        ArabianPureApi arabianPureApi = APIinstance.retroInstace().create(ArabianPureApi.class);
        Call<List<HomepageSlider>> call = arabianPureApi.getSlider();


        call.enqueue(new Callback<List<HomepageSlider>>() {
            @Override
            public void onResponse( Call<List<HomepageSlider>> call,  Response<List<HomepageSlider>> response)
            {
                homepageSliderList = response.body();
                if (homepageSliderList !=null) {
                    sliderAdapter = new SliderAdapter(homepageSliderList);
                    sliderView.setSliderAdapter(sliderAdapter);
                }
            }

            @Override
            public void onFailure( Call<List<HomepageSlider>> call,  Throwable t)
            {

            }
        });
    }
    private void loadCategoryData()
    {
        ArabianPureApi arabianPureApi = APIinstance.retroInstace().create(ArabianPureApi.class);
        Call<List<Category>> categoryCall = arabianPureApi.getCategory();
        categoryCall.enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse( Call<List<Category>> call,  Response<List<Category>> response)
            {
                if (!response.isSuccessful())
                {
                    Toast.makeText(getContext(), response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                categories = response.body();
                homeFragmentListener.onShowAllClick(false);
                categoryAdapter = new CategoryAdapter(categories,10, (CategoryAdapter.categoryClickListener) getContext());
                categoryRecyclerView.setAdapter(categoryAdapter);
            }

            @Override
            public void onFailure( Call<List<Category>> call,  Throwable t) {

            }
        });
    }

    private void loadLocalBDFoodData()
    {
        ArabianPureApi arabianPureApi = APIinstance.retroInstace().create(ArabianPureApi.class);
        Call<List<Products>> call = arabianPureApi.getSpecificCategoryProducts("42");
        call.enqueue(new Callback<List<Products>>() {
            @Override
            public void onResponse(Call<List<Products>> call, Response<List<Products>> response) {
                if (response.isSuccessful())
                {
                    localBDFoodProducts = response.body();
                    if (localBDFoodProducts!=null)
                    {
                        localBDFoodAdapter = new ProductsAdapter(localBDFoodProducts, getContext(), (ProductsAdapter.productClickListener) getContext());
                        localBDFoodRecyclerView.setAdapter(localBDFoodAdapter);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Products>> call, Throwable t) {

            }
        });
    }

    private void loadHealthyFoodData()
    {
        ArabianPureApi arabianPureApi = APIinstance.retroInstace().create(ArabianPureApi.class);
        Call<List<Products>> call = arabianPureApi.getSpecificCategoryProducts("36");
        call.enqueue(new Callback<List<Products>>() {
            @Override
            public void onResponse(Call<List<Products>> call, Response<List<Products>> response)
            {
                if (response.isSuccessful())
                {
                    healthyFoodProducts = response.body();
                    if (healthyFoodProducts!=null)
                    {
                        healthyFoodAdapter = new ProductsAdapter(healthyFoodProducts, getContext(), (ProductsAdapter.productClickListener) getContext());
                        healthyFoodRecyclerView.setAdapter(healthyFoodAdapter);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Products>> call, Throwable t) {

            }
        });
    }
    private void loadAllProductData()
    {

        ArabianPureApi arabianPureApi = APIinstance.retroInstace().create(ArabianPureApi.class);
        Call<List<Products>> call = arabianPureApi.getProducts();
        call.enqueue(new Callback<List<Products>>() {
            @Override
            public void onResponse( Call<List<Products>> call,  Response<List<Products>> response)
            {
                if (!response.isSuccessful())
                {
                    Toast.makeText(getContext(), response.code(), Toast.LENGTH_LONG).show();
                    return;
                }
                products = response.body();
                if (products!=null)
                {
                    productsAdapter = new ProductsAdapter(products, getContext(), (ProductsAdapter.productClickListener) getContext()
                    );
                    productsRecyclerView.setAdapter(productsAdapter);
                }

            }

            @Override
            public void onFailure( Call<List<Products>> call,  Throwable t)
            {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });



    }
    private void loadBreakfastData()
    {
        ArabianPureApi arabianPureApi = APIinstance.retroInstace().create(ArabianPureApi.class);
        Call<List<Products>> call = arabianPureApi.getSpecificCategoryProducts("41");
        call.enqueue(new Callback<List<Products>>() {
            @Override
            public void onResponse( Call<List<Products>> call,  Response<List<Products>> response)
            {
                if (response.isSuccessful())
                {
                    breakfastProducts = response.body();
                    if (breakfastProducts!=null)
                    {
                        breakfastAdapter = new ProductsAdapter(breakfastProducts, getContext(), (ProductsAdapter.productClickListener) getContext());
                        breakfastRecyclerView.setAdapter(breakfastAdapter);
                    }
                }
            }

            @Override
            public void onFailure( Call<List<Products>> call,  Throwable t)
            {

            }
        });
    }


    @Override
    public void onClick(View v)
    {
        if (v.getId()==R.id.category_show_all)
        {
            homeFragmentListener.onShowAllClick(true);
        }
    }




    public HomeFragment(Context context, HomeFragmentListener homeFragmentListener)
    {
        this.context = context;
        this.homeFragmentListener = homeFragmentListener;
    }

    public interface HomeFragmentListener
    {
        public void onShowAllClick( boolean click);
    }



    private class loadSlider extends AsyncTask<Void, Void, Void>
    {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            new loadBanner().execute(null,null,null);
        }
        @Override
        protected Void doInBackground(Void... voids)
        {



            loadSliderData();
//            loadBannerData();
//            loadAllProductData();
//            loadCategoryData();
//            loadBreakfastData();
//            loadHealthyFoodData();
//            loadLocalBDFoodData();
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }

            return null;
        }
    }
    private class loadBanner extends AsyncTask<Void, Void, Void>
    {

        @Override
        protected Void doInBackground(Void... voids)
        {
            loadBannerData();
            return null;
        }
    }
    private class loadLocalBDFood extends AsyncTask<Void, Void, Void>
    {


        @Override
        protected Void doInBackground(Void... voids)
        {
            loadLocalBDFoodData();
            return null;
        }
    }
    private class loadCategory extends AsyncTask<Void, Void, Void>
    {

        @Override
        protected Void doInBackground(Void... voids)
        {
            loadCategoryData();
            return null;
        }
    }
    private class loadBreakfast extends AsyncTask<Void, Void, Void>
    {

        @Override
        protected Void doInBackground(Void... voids)
        {
            loadBreakfastData();
            return null;
        }
    }
    private class loadHealthyFood extends AsyncTask<Void, Void, Void>
    {

        @Override
        protected Void doInBackground(Void... voids)
        {
            loadHealthyFoodData();
            return null;
        }
    }
    private class loadAllProduct extends AsyncTask<Void, Void, Void>
    {

        @Override
        protected Void doInBackground(Void... voids)
        {
            loadAllProductData();
            return null;
        }
    }
}
