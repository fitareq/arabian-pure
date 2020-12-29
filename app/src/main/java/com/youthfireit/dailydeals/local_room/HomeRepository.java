package com.youthfireit.dailydeals.local_room;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.youthfireit.dailydeals.local_room.room_model.BannerDao;
import com.youthfireit.dailydeals.local_room.room_model.CategoriesDao;
import com.youthfireit.dailydeals.local_room.room_model.CategoriesModel;
import com.youthfireit.dailydeals.local_room.room_model.HomepageBannerModel;
import com.youthfireit.dailydeals.local_room.room_model.HomepageSliderModel;
import com.youthfireit.dailydeals.local_room.room_model.ProductsDao;
import com.youthfireit.dailydeals.local_room.room_model.ProductsModel;
import com.youthfireit.dailydeals.local_room.room_model.SliderDao;
import com.youthfireit.dailydeals.local_room.room_model.UserModel;
import com.youthfireit.dailydeals.network.APIinstance;
import com.youthfireit.dailydeals.network.ArabianPureApi;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HomeRepository
{

    //private UserDao Userdao;
   // private CartDao Cartdao;
    private final ProductsDao productsDao;
    private final CategoriesDao categoriesDao;
    private final SliderDao sliderDao;
    private final BannerDao bannerDao;

    private final LiveData<List<ProductsModel>> allProducts;
    private UserModel currentUser;
    private final List<CategoriesModel> allCategories;
    private final List<HomepageSliderModel> allSlider;
    private final List<HomepageBannerModel> allBanner;
    private final List<ProductsModel> weekDeals;
    private final List<ProductsModel> topRated;

    private final ArabianPureApi api;

    public HomeRepository(Application application)
    {
        localDatabase db = localDatabase.getDatabase(application);
        api = APIinstance.retroInstace().create(ArabianPureApi.class);
       // Userdao = db.userdao();
        //Cartdao = db.cartdao();
        productsDao = db.productsDao();
        categoriesDao = db.categoriesDao();
        sliderDao = db.sliderDao();
        bannerDao = db.bannerDao();
        allProducts = productsDao.getAllProducts();
        weekDeals = productsDao.getAllWeekDealsProducts();
        topRated = productsDao.getAllTopRatedProducts();
        //allCarts = Cartdao.getCarts();
        //currentUser = Userdao.getCurrentUser();
        allCategories = categoriesDao.getAllCategory();
        allBanner = bannerDao.getAllBanner();
        allSlider = sliderDao.getAllSlider();
    }


    public LiveData<List<ProductsModel>> getAllProducts(){return allProducts;}
    public List<CategoriesModel> getAllCategories(){return allCategories;}
    public List<HomepageBannerModel> getAllBanners(){return allBanner;}
    public List<HomepageSliderModel> getAllSlider(){return allSlider;}
    public List<ProductsModel> getAllTopRatedProducts(){return topRated;}
    public List<ProductsModel> getAllWeekDealsProducts(){return weekDeals;}
   // public LiveData<List<CartModel>> getAllCarts(){return allCarts;}
    //public LiveData<UserModel> getCurrentUser(){return currentUser;}

    void insertProductList(List<ProductsModel> products)
    {
        localDatabase.databaseWriteExecutors.execute(() -> productsDao.insertProductList(products));
    }

    void insertCategoriesList(List<CategoriesModel> categories)
    {
        localDatabase.databaseWriteExecutors.execute(() -> categoriesDao.insertCategoryList(categories));
    }

    void insertBannerList(List<HomepageBannerModel> banners)
    {
        localDatabase.databaseWriteExecutors.execute(() -> bannerDao.insertBannerList(banners));
    }
    void insertSliderList(List<HomepageSliderModel> sliders)
    {
        localDatabase.databaseWriteExecutors.execute(() -> sliderDao.insertSliderList(sliders));
    }
    /*void insertCart(CartModel CartModel)
    {
        localDatabase.databaseWriteExecutors.execute(new Runnable() {
            @Override
            public void run() {
                Cartdao.addProductToCart(CartModel);
            }
        });
    }
    void setCurrentUser(UserModel userModel)
    {
        localDatabase.databaseWriteExecutors.execute(new Runnable() {
            @Override
            public void run() {
                Userdao.logInUser(userModel);
            }
        });
    }*/



    public void fetchProductsFromRetrofit()
    {
        Call<List<ProductsModel>> call = api.getAllProducts();
        call.enqueue(new Callback<List<ProductsModel>>() {
            @Override
            public void onResponse(Call<List<ProductsModel>> call, Response<List<ProductsModel>> response) {
                if (response.isSuccessful())
                    insertProductList(response.body());
            }

            @Override
            public void onFailure(Call<List<ProductsModel>> call, Throwable t)
            {

            }
        });
    }
    public void fetchCategoriesFromRetrofit()
    {
        Call<List<CategoriesModel>> call = api.getAllCategory();
        call.enqueue(new Callback<List<CategoriesModel>>() {
            @Override
            public void onResponse(Call<List<CategoriesModel>> call, Response<List<CategoriesModel>> response)
            {
                if (response.isSuccessful())
                    insertCategoriesList(response.body());
            }



            @Override
            public void onFailure(Call<List<CategoriesModel>> call, Throwable t) {

            }
        });
    }

    public void fetchSliderFromRetrofit()
    {
        Call<List<HomepageSliderModel>> call = api.getAllSlider();
        call.enqueue(new Callback<List<HomepageSliderModel>>() {
            @Override
            public void onResponse(Call<List<HomepageSliderModel>> call, Response<List<HomepageSliderModel>> response) {
                if (response.isSuccessful())
                {
                    insertSliderList(response.body());
                }
            }



            @Override
            public void onFailure(Call<List<HomepageSliderModel>> call, Throwable t) {

            }
        });
    }

    public void fetchBannerFromRetrofit()
    {
        Call<List<HomepageBannerModel>> call = api.getAllBanner();
        call.enqueue(new Callback<List<HomepageBannerModel>>() {
            @Override
            public void onResponse(Call<List<HomepageBannerModel>> call, Response<List<HomepageBannerModel>> response) {
                if (response.isSuccessful())
                {
                    insertBannerList(response.body());
                }
            }



            @Override
            public void onFailure(Call<List<HomepageBannerModel>> call, Throwable t) {

            }
        });
    }
}
