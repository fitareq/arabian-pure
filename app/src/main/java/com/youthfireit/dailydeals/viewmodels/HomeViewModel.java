package com.youthfireit.dailydeals.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.youthfireit.dailydeals.local_room.HomeRepository;
import com.youthfireit.dailydeals.local_room.room_model.CategoriesModel;
import com.youthfireit.dailydeals.local_room.room_model.HomepageBannerModel;
import com.youthfireit.dailydeals.local_room.room_model.HomepageSliderModel;
import com.youthfireit.dailydeals.local_room.room_model.ProductsModel;

import java.util.List;

public class HomeViewModel extends AndroidViewModel
{
    private final HomeRepository repository;
    private final LiveData<List<ProductsModel>> allProducts;
    private final List<CategoriesModel> allCategories;
    private final List<HomepageBannerModel> allBanner;
    private final List<HomepageSliderModel> allSlider;
    private final List<ProductsModel> weekDeals;
    private final List<ProductsModel> topRated;
    public HomeViewModel(@NonNull Application application)
    {

        super(application);
        repository = new HomeRepository(application);
        allProducts = repository.getAllProducts();
        allCategories = repository.getAllCategories();
        allSlider = repository.getAllSlider();
        allBanner = repository.getAllBanners();
        weekDeals = repository.getAllWeekDealsProducts();
        topRated = repository.getAllTopRatedProducts();
    }

    public LiveData<List<ProductsModel>> getAllProducts(){return allProducts;}
    public List<CategoriesModel> getAllCategories(){return allCategories;}
    public List<HomepageBannerModel> getAllBanner(){return allBanner;}
    public List<HomepageSliderModel> getAllSlider(){return allSlider;}
    public List<ProductsModel> getAllTopRatedProducts(){return topRated;}
    public List<ProductsModel> getAllWeekDealsProducts(){return weekDeals;}
    public void insertProductsList(){repository.fetchProductsFromRetrofit();}
    public void insertCategoriesList(){repository.fetchCategoriesFromRetrofit();}
    public void insertSliderList(){repository.fetchSliderFromRetrofit();}
    public void insertBannerList(){repository.fetchBannerFromRetrofit();}

}
