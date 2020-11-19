package com.youthfireit.arabianpure.fragments;

import android.content.Context;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.models.SlideModel;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;
import com.smarteist.autoimageslider.SliderViewAdapter;
import com.youthfireit.arabianpure.network.APIinstance;
import com.youthfireit.arabianpure.network.CheckInternetConnection;
import com.youthfireit.arabianpure.R;
import com.youthfireit.arabianpure.adapter.BannerAdapter;
import com.youthfireit.arabianpure.adapter.CategoryAdapter;
import com.youthfireit.arabianpure.adapter.ProductsAdapter;
import com.youthfireit.arabianpure.network.ArabianPureApi;
import com.youthfireit.arabianpure.model.Category;
import com.youthfireit.arabianpure.model.HomepageBanner;
import com.youthfireit.arabianpure.model.HomepageSlider;
import com.youthfireit.arabianpure.model.Products;
import com.youthfireit.arabianpure.adapter.SliderAdapter;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeFragment extends Fragment implements View.OnClickListener
{




    private RecyclerView productsRecyclerView, categoryRecyclerView;
    private RecyclerView.Adapter adapter, categoryAdapter;
    private RecyclerView.LayoutManager manager, categoryManager;
    private TextView errorView,categoryShowAll;
    private ImageSlider imageSlider;



    private List<Products> products;
    private List<Category> categories;



   Context context;

    private SliderView sliderView, bannerView;
    private SliderViewAdapter sliderAdapter, bannerAdapter;
    private List<HomepageSlider> homepageSliderList;
    private List<HomepageBanner> homepageBannerList;





    HomeFragmentListener homeFragmentListener;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {

        View rootView = inflater.inflate(R.layout.home_fragment,container,false);
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
        productsRecyclerView = rootView.findViewById(R.id.product_recyclerview);
        categoryRecyclerView = rootView.findViewById(R.id.category_recyclerview);
        errorView = rootView.findViewById(R.id.internet_error);
        categoryShowAll = rootView.findViewById(R.id.category_show_all);



        ((AppCompatActivity) requireActivity()).getSupportActionBar().setTitle("Arabian pure");

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
        categoryRecyclerView.setHasFixedSize(true);
        productsRecyclerView.setNestedScrollingEnabled(false);
        categoryRecyclerView.setNestedScrollingEnabled(false);
        manager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        categoryManager = new GridLayoutManager(getContext(),5);
        productsRecyclerView.setLayoutManager(manager);
        categoryRecyclerView.setLayoutManager(categoryManager);


        if (CheckInternetConnection.isConnectionAvailable(getContext()))
        {
            if (errorView.getVisibility()==View.VISIBLE)
            {
                errorView.setVisibility(View.GONE);
            }
            loadSliderData();
            loadBannerData();
            loadData();
        } else
        {
            errorView.setVisibility(View.VISIBLE);

        }
        categoryShowAll.setOnClickListener(this);

        return rootView;
    }











    private void loadBannerData()
    {
        ArabianPureApi arabianPureApi = APIinstance.retroInstace().create(ArabianPureApi.class);
        Call<List<HomepageBanner>> call = arabianPureApi.getBanner();
        call.enqueue(new Callback<List<HomepageBanner>>() {
            @Override
            public void onResponse(@NotNull Call<List<HomepageBanner>> call, @NotNull Response<List<HomepageBanner>> response)
            {
                homepageBannerList = response.body();
                if (homepageBannerList!=null)
                {
                    bannerAdapter = new BannerAdapter(homepageBannerList);
                    bannerView.setSliderAdapter(bannerAdapter);
                }
            }

            @Override
            public void onFailure(@NotNull Call<List<HomepageBanner>> call, @NotNull Throwable t) {

            }
        });
    }



    private void loadSliderData()
    {
        ArabianPureApi arabianPureApi = APIinstance.retroInstace().create(ArabianPureApi.class);
        Call<List<HomepageSlider>> call = arabianPureApi.getSlider();


        call.enqueue(new Callback<List<HomepageSlider>>() {
            @Override
            public void onResponse(@NotNull Call<List<HomepageSlider>> call, @NotNull Response<List<HomepageSlider>> response)
            {
                homepageSliderList = response.body();
                if (homepageSliderList !=null) {
                    sliderAdapter = new SliderAdapter(homepageSliderList);
                    sliderView.setSliderAdapter(sliderAdapter);
                }
            }

            @Override
            public void onFailure(@NotNull Call<List<HomepageSlider>> call, @NotNull Throwable t)
            {

            }
        });
    }


    private void loadData()
    {

        ArabianPureApi arabianPureApi = APIinstance.retroInstace().create(ArabianPureApi.class);
        Call<List<Products>> call = arabianPureApi.getProducts();
        Call<List<Category>> categoryCall = arabianPureApi.getCategory();
        call.enqueue(new Callback<List<Products>>() {
            @Override
            public void onResponse(@NotNull Call<List<Products>> call, @NotNull Response<List<Products>> response)
            {
                if (!response.isSuccessful())
                {
                    Toast.makeText(getContext(), response.code(), Toast.LENGTH_LONG).show();
                    return;
                }
                products = response.body();
                if (products!=null)
                {
                    adapter = new ProductsAdapter(products, getContext(), (ProductsAdapter.productClickListener) getContext()
                    );
                    productsRecyclerView.setAdapter(adapter);
                }

            }

            @Override
            public void onFailure(@NotNull Call<List<Products>> call, @NotNull Throwable t)
            {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        categoryCall.enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(@NotNull Call<List<Category>> call, @NotNull Response<List<Category>> response)
            {
                if (!response.isSuccessful())
                {
                    Toast.makeText(getContext(), response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                categories = response.body();
                homeFragmentListener.onShowAllClick(categories,false);
                categoryAdapter = new CategoryAdapter(categories,10);
                categoryRecyclerView.setAdapter(categoryAdapter);
            }

            @Override
            public void onFailure(@NotNull Call<List<Category>> call, @NotNull Throwable t) {

            }
        });

    }


    @Override
    public void onClick(View v)
    {
        if (v.getId()==R.id.category_show_all)
        {
            homeFragmentListener.onShowAllClick(categories,true);
        }
    }




    public HomeFragment(Context context, HomeFragmentListener homeFragmentListener)
    {
        this.context = context;
        this.homeFragmentListener = homeFragmentListener;
    }

    public interface HomeFragmentListener
    {
        public void onShowAllClick(List<Category> categoryList, boolean click);
    }
}
